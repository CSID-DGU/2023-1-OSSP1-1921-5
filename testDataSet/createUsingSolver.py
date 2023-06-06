import pandas as pd
import numpy as np
import random
import warnings
warnings.filterwarnings('ignore')

def input_rule(course):
    # course : 졸업요건 이름 ex) 전공, 개별연구, msc ...
    constraints = 0 
    constraints2 = 0 
    
    user_input = input(course + " : ") # rule 입력 받음. x, a, c, n
    if user_input.lower()=="x":
        pass
    else:
        while True:
            if user_input.lower()=="a":
                input1 = input("포함하고 싶은 강의의 학수번호를 입력하세요 : ")
                input1_1 = input("제외하고 싶은 강의의 학수번호를 입력하세요 : ")
                constraints2 = input1_1.split(',')
                break
            elif user_input.lower()=="c":
                input1 = input("포함하고 싶은 강의의 학수번호를 입력하세요 : ")
                break
            elif user_input.lower()=="n":
                input1 = input("제외하고 싶은 강의의 학수번호를 입력하세요 : ")
                break
            else:
                print("입력한 값이 유효하지 않습니다. 다시 입력해주세요.")
                break
        constraints = input1.split(',')
    
    return user_input, constraints, constraints2


def conn(user, df, constraints, constraints_, cnt, ind):
    if user.lower() == "x": # 제약사항 없음
        result = not_constraints(df, cnt, ind)
    elif user.lower() == "a": # 제외, 포함 둘 다 진행
        result = all_constraints(df, constraints, constraints_, cnt, ind)
    elif user.lower() == "c": # 포함
        result = contain(df, constraints, cnt, ind)
    elif user.lower() == "n": # 제외
        result = not_contain(df, constraints, cnt, ind)
        
    return result

# 해당 졸업요건의 제약사항이 없을 때 실행 -> 졸업 불가능 data 생성
def not_constraints(df, num_rows, ind):
    if ind:
        filtered_df = df
    else:
        condition = df['학수강좌번호'].str.contains('DES') == False
        filtered_df = df[condition]
    df = filtered_df.sample(n=num_rows, ignore_index=True)
    return df

# 해당 졸업요건에서 포함시키지 않을 강의가 있을 때 실행 -> 졸업 불가능 data 생성
def not_contain(df, keywords, num_rows, ind):
    # df['교과목명'].str.contains('|'.join(keywords)) : keywords list에 포함된 강의를 하나라도 포함하고 있는 행을 찾음
    # 위의 코드에 ~ 붙임 : df에서 그 행들을 제외한 dataframe을 not_included라고 함.
    not_included = df.loc[~df['학수강좌번호'].str.contains('|'.join(keywords))] 
    if ind:
        filtered_df = not_included
    else:
        condition = not_included['학수강좌번호'].str.contains('DES') == False
        filtered_df = not_included[condition]
    result = filtered_df.sample(n=num_rows, ignore_index=True) # cnt 개수만큼 not_included에서 행 임의 추출. ignore_index = True -> 새로운 index 생성
    return result

# 해당 졸업요건에서 포함시킬 강의가 있을 때 실행 -> 졸업 가능 data 생성
def contain(df, keywords, num_rows, ind):
    rows = pd.DataFrame() # 추출한 행들을 저장할 빈 리스트 rows
    
    for kw in keywords: # keywords에 있는 각각의 keword kw에 대해 반복문 실행
        df_kw = df.loc[df['학수강좌번호'].str.contains(kw)] # df의 교과목명 열에서 kw를 포함하는 행들을 추출하여 df_kw에 저장
        if not df_kw.empty: # df_kw가 비어있지 않으면 -> kw를 포함하는 행이 존재하면
            row = df_kw.sample() # df_kw에서 랜덤으로 1개의 행을 추출하여 row에 저장
            rows = pd.concat([rows, row]) # rows에 row 추가

    if len(rows) < num_rows: # 추출된 행의 개수가 원하는 추출 행의 개수보다 작으면
        cnt = num_rows - len(rows) # 더 추출해야 할 행의 개수 a 계산.

        Xdup = df.loc[~df['학수강좌번호'].str.contains('|'.join(keywords))] # keywords 포함 강의 -> 이미 rows에 추출한 강의들 -> 중복 방지 위해 df에서 제외.
        
        if ind:
            filtered_df = Xdup
        else:
            condition = Xdup['학수강좌번호'].str.contains('DES') == False
            filtered_df = Xdup[condition]
        
        add_rows = filtered_df.sample(n=cnt, ignore_index=True) # not_included에서 더 추출해야 하는 행 개수만큼 랜덤으로 추출하여 add_rows에 저장
        
        # list인 rows를 column이 '년도' ~ '대학대학원'인 dataframe으로 만들어줌. add_rows는 dataframe
        rowdf = pd.DataFrame(rows, columns=['년도', '학기', '이수구분', '이수구분영역', '학수강좌번호', '분반', '교과목명', '담당교원', '학점', 
                                         '등급', '삭제구분', '재수강구분', '공학인증', '공학요소', '공학세부요소', '원어강의종류', '인정구분', '성적인정대학명', '교과목영문명', '대학대학원'])
        contain_result = pd.concat([rowdf, add_rows], axis=0) # rowdf와 add_rows를 합침.
    else:
        contain_result = rows
        
    return contain_result


# 해당 졸업요건에서 포함, 제외시킬 강의가 있을 때 실행
def all_constraints(df, keywords1, keywords2, num_rows, ind):
    # keywords1 -> 포함할 강의들
    # keywords2 -> 제외할 강의들
    # not_contain 함수와 contain 함수 합친 거임.
    not_included = df.loc[~df['학수강좌번호'].str.contains('|'.join(keywords2))] # 강의들 제외
    all_result = contain(not_included, keywords1, num_rows, ind)
        
    return all_result

pf = ['RGC1001', 'RGC1074', 'RGC0017', 'RGC0018', 'RGC1050', 'RGC1051', 'RGC1051', 'RGC1052', 'RGC1030']

select_sem = []
inputlist = []
conslist1 = []
conslist2 = []
cnt_leclist = []
result_list = []
ind = False

sem171 = pd.read_excel("./base/2017_1.xlsx") 
sem172 = pd.read_excel("./base/2017_2.xlsx") 
sem181 = pd.read_excel("./base/2018_1.xlsx") 
sem182 = pd.read_excel("./base/2018_2.xlsx") 
sem191 = pd.read_excel("./base/2019_1.xlsx") 
sem192 = pd.read_excel("./base/2019_2.xlsx")  
sem201 = pd.read_excel("./base/2020_1.xlsx") 
sem202 = pd.read_excel("./base/2020_2.xlsx") 
sem211 = pd.read_excel("./base/2021_1.xlsx") 
sem212 = pd.read_excel("./base/2021_2.xlsx") 
sem221 = pd.read_excel("./base/2022_1.xlsx") 
sem222 = pd.read_excel("./base/2022_2.xlsx") 
sem231 = pd.read_excel("./base/2023_1.xlsx") 

sem_list = [sem171, sem172, sem181, sem182, sem191, sem192, 
            sem201, sem202, sem211, sem212, sem221, sem222, sem231]

datacnt = int(input("만들 data 개수 입력 : "))
while True:
    start_year = int(input("입학년도(2017 ~ 2023) 입력 : "))
    if start_year in [2017, 2018, 2019, 2020, 2021, 2022, 2023]:
        break
    else:
        print("입력한 입학년도가 유효하지 않습니다. 다시 입력해주세요.")
        break

while True:
    num_semester = int(input("이수학기(1 ~ 10) 입력 : "))
    if num_semester in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
        break
    else:
        print("입력한 이수학기가 유효하지 않습니다. 다시 입력해주세요.")
        break

if num_semester > 4:
    ind = True
        

# 21년도에 입학을 하고 5학기 이수 -> sem211부터 5개
sIndex = (start_year - 2017) * 2
eIndex = sIndex + num_semester
select_sem = sem_list[sIndex:eIndex]

print("---------------------------------------------------------------------")
print("제약사항이 없으면 X, 포함 제약사항만 있으면 C, 제외 제약사항만 있으면 N")
print("포함, 제외 제약사항 모두 있으면 A를 입력하세요.")
print("(입력형식 : ,가 구분자, 띄어쓰기x. ex) CSE2028,CSE4066,CSE4067,...)")
print("---------------------------------------------------------------------")

for i in range(1, num_semester + 1): 
    cnt_lec = int(input(f"입학 {start_year}년, {i}학기 이수 강의 수 : "))
    cnt_leclist.append(cnt_lec)
    userinput, constraints, constraints_1 = input_rule(f"{i}학기 제약사항 (X, C, N, A)")
    
    inputlist.append(userinput)
    conslist1.append(constraints)
    conslist2.append(constraints_1)
    print("---------------------------------------------------------------------")  

for i in range(0, datacnt): # 제약사항 한 번 받아서, 입력받은 data 개수만큼 반복.
    result_list = []
    for j in range(0, num_semester):
        result = conn(inputlist[j], select_sem[j], conslist1[j], conslist2[j], cnt_leclist[j], ind)
        result_list.append(result)
        
    dataset = pd.concat(result_list, ignore_index=True) # 랜덤 추출한 거 합치기
    
    
    # 성적 랜덤 지정
    total_lec = sum(cnt_leclist)
    grade = ['A+', 'A0', 'B+', 'B0', 'C+', 'C0', 'D+', 'D0', 'F']
    choice = np.random.choice(grade, total_lec, p=[0.1, 0.2, 0.2, 0.15, 0.15, 0.05, 0.05, 0.05, 0.05]).tolist() # grade의 각 값이 나올 확률 지정
    dataset['등급'] = choice
    
    #dataset['분반'] = "'" + dataset['분반'].astype(str)

    for index, row in dataset.iterrows(): 
    # 등급 P/F 처리
        if (row['학수강좌번호'] in pf) or ('DES' in row['학수강좌번호'] and row['년도'] != "2023") or ('DAI' in row['학수강좌번호']):
            dataset.at[index, '등급'] = random.choice(['P', 'F'])

    # 포함 강의는 F 안 받게
        for sublist in conslist1:
            if (sublist != 0) and (row['학수강좌번호'] in sublist) and (dataset.at[index, '등급'] == 'F'):
                if (row['학수강좌번호'] in pf) or ('DES' in row['학수강좌번호'] and row['년도'] != "2023") or ('DAI' in row['학수강좌번호']):
                    new_grade = 'P'
                else:
                    new_grade = np.random.choice(grade[:-1])
                dataset.at[index, '등급'] = new_grade
                break
    
    # 개별연구 4개 이상 들었으면 3개만 남겨~!!!!!!
    # 포함 강의에 입력된 거 있으면 그거부터 남기도록
    Ind_target = []
    for sublist in conslist1:
        if sublist != 0:
            sublist_targets = [item for item in sublist if 'DES' in item or 'DAI' in item]
            Ind_target.extend(sublist_targets)

    Ind = dataset['학수강좌번호'].str.contains('DES|DAI')
    IndP = dataset['학수강좌번호'].isin(Ind_target)
    dataset = pd.concat([dataset[~Ind], dataset[IndP]])

    # 중복된 행 중 마지막 행을 제외한 모든 중복 행 제거
    dataset.drop_duplicates(subset='학수강좌번호', keep='last', inplace=True)
    dataset.iloc[-1, dataset.columns.get_loc('재수강구분')] = 'NEW재수강'

    dataset.to_excel('./data/data' + str(i) + '입학' + str(start_year) + '이수' + str(num_semester) +'학기.xlsx', float_format={'분반': '00', '학점': '0.0'}) # 만들어진 dataset xlsx로 내보내기

    # 이 부분이 이제 solver
    #df_add = check(dataset, comlist, baselist, foundlist, req_com, req_base, req_found, req_major)
    #df = pd.concat([df, df_add])
    
    
#df = df.reset_index(drop=True)
#df.to_excel('./data/data확인.xlsx') # 들은 학점들이랑 f 받은 전공 정보 정리. 
print("data 생성 완료")