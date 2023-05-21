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
    
    rows = pd.DataFrame()
    
    for kw in keywords1: 
        df_kw = not_included.loc[not_included['학수강좌번호'].str.contains(kw)] 
        if not df_kw.empty: 
            row = df_kw.sample() 
            rows = pd.concat([rows, row])
    
    if len(rows) < num_rows: 
        cnt = num_rows - len(rows)
        Xdup = not_included.loc[~not_included['학수강좌번호'].str.contains('|'.join(keywords1))] 
        if ind:
            filtered_df = Xdup
        else:
            condition = Xdup['학수강좌번호'].str.contains('DES') == False
            filtered_df = Xdup[condition]
        
        add_rows = filtered_df.sample(n=cnt, ignore_index=True)
        
        rowdf = pd.DataFrame(rows, columns=['년도', '학기', '이수구분', '이수구분영역', '학수강좌번호', '분반', '교과목명', '담당교원', '학점', 
                                         '등급', '삭제구분', '재수강구분', '공학인증', '공학요소', '공학세부요소', '원어강의종류', '인정구분', '성적인정대학명', '교과목영문명', '대학대학원'])
        all_result = pd.concat([rowdf, add_rows], axis=0)
        
    return all_result

def check(dataset, comlist, baselist, foundlist, req_com, req_base, req_found, req_major):
    count = 0 # 총 이수 학점
    count_com = 0 # 들은 공통교양 학점  
    require_com = 0 # 들은 필수 공통교양 학점 
    require_base = 0 # 들은 필수 학문기초 학점
    require_found = 0 # 들은 필수 기본소양 학점
    require_major = 0 # 들은 필수 전공 학점
    count_en = 0 # 들은 영어 강의 개수
    major_fail = []
    com = []
    base = [] 
    found = []
    major = []
    
    
    # 공교, 학문기초, 기본소양, 전공 몇 학점 들었는지, 영어 강의 개수 확인. 성적이 F가 아니어야 함.
    for k, row in dataset.iterrows(): 
        # 총 학점
        if row['등급'] != 'F':
            count += row['학점']
        # 공통교양
        if row['이수구분'] == '공교' and row['등급'] != 'F':
            count_com += row['학점']

        # 필수 공통교양
        if row['학수강좌번호'] in comlist and row['등급'] != 'F':
            if row['학수강좌번호'] in ['RGC1050', 'RGC1051', 'RGC1052']:
                com.append('leadership')
            else:
                com.append(row['교과목명'])
            require_com += row['학점']
        # 학문기초
        if row['학수강좌번호'] in baselist and row['등급'] != 'F':
            if row['학수강좌번호'] in ['PRI4002', 'PRI4003', 'PRI4004', 'PRI4013', 'PRI4014', 'PRI4015']:
                base.append('Experiment')
            else:
                base.append(row['교과목명'])
            require_base += row['학점']
        # 기본소양
        if row['학수강좌번호'] in foundlist and row['등급'] != 'F':
            require_found += row['학점']
            found.append(row['교과목명'])
        # 전공
        if (row['이수구분'] == '전공' or row['이수구분'] == '전필') and row['등급'] != 'F':
            require_major += row['학점']
            major.append(row['교과목명'])
        # 영어 강의 개수 확인
        if (row['교과목명'] == 'EAS1' or row['교과목명'] == 'EAS2') and row['등급'] != 'F':
            count_en += 1
        if (row['이수구분'] == '전공' or row['이수구분'] == '전필') and row['원어강의종류'] == '영어' and row['등급'] != 'F':
            count_en += 1
            
        # 전공 필수 중 F 맞은 과목 있는지 확인.
        if (row['이수구분'] == '전필' or str(row['학수강좌번호']).startswith('DES')) and row['등급'] == 'F':
            major_fail.append(row['교과목명'])
        
        not_take_com = [item for item in req_com if item not in com]
        not_take_base = [item for item in req_base if item not in base]
        not_take_found = [item for item in req_found if item not in found]
        not_take_major = [item for item in req_major if item not in major]
        
    # 필수로 들으라고 선택된 건 F 받으면 안되지 않뉘?

    df = pd.DataFrame({
        '총 이수학점': [count],
        '공통교양': [count_com],
        '필수 공통교양': [require_com],
        '필수 학문기초': [require_base],
        '필수 기본소양': [require_found],
        '필수 전공': [require_major],
        '영어': [count_en],
        'fail_major' : [major_fail],
        '미이수 필수 공교' : [not_take_com],
        '미이수 필수 학문기초' : [not_take_base],
        '미이수 필수 기본소양' : [not_take_found],
        '미이수 필수 전공' : [not_take_major]
    })
    return df

pf = ['RGC1001', 'RGC1074', 'RGC0017', 'RGC0018', 'RGC1050', 'RGC1051', 'RGC1051', 'RGC1052', 'RGC1030']
# 공통교양
comlist = ['RGC1001', 'RGC1074', 'RGC0017', 'RGC0018', 'RGC0003', 'RGC1050', 'RGC1051',
            'RGC1052', 'RGC0005', 'RGC1030', 'RGC1080', 'RGC1081', 'RGC1033', 'RGC1034'] # 23년 세미나 추가
# 필수 공통교양
req_com = ['나의삶,나의비전', '커리어 디자인', '자아와명상1', '자아와명상2', '불교와인간', '소셜앙트레프레너십과리더십', 
            '기술보고서작성및발표', 'EAS1', 'EAS2'] # S4면 BasicEas
# 세미나
seminar = ['RGC1010', 'RGC1011', 'RGC1012', 'RGC1013', 'RGC1014']
# 학문기초
baselist = ['PRI4001', 'PRI4012', 'PRI4023', 'PRI4024', 'PRI4024', 'PRI4027', 'PRI4036',
            'PRI4002', 'PRI4013', 'PRI4003', 'PRI4014', 'PRI4004', 'PRI4015', 
            'PRI4029', 'PRI4030', 'PRI4028', 'PRI4033', 'PRI4051'] # 21년부터 산업수학 PRI4051 추가
# 필수 학문기초 
req_base = ['미적분학및연습1', '확률및통계학', '공학선형대수학'] # 23학번 이산수학 추가
# 기본소양
foundlist = ['EGC7026', 'PRI4041', 'EGC4039', 'PRI4043', 'PRI4040', 'PRI4048']
req_found = ['기술창조와특허', '공학경제', '공학윤리', '공학법제', '기술과사회', '지속가능한발전과인간']
# 필수 전공
req_major = ['계산적사고법', '이산구조', '창의적공학설계', '어드벤처디자인', '자료구조와실습', 
             '컴퓨터구성', '시스템소프트웨어와실습', '공개SW프로젝트', '컴퓨터공학종합설계1', '컴퓨터공학종합설계2']

select_sem = []
inputlist = []
conslist1 = []
conslist2 = []
cnt_leclist = []
result_list = []
ind = False

df = pd.DataFrame({
    '총 이수학점': [],
    '공통교양': [],
    '필수 공통교양': [],
    '필수 학문기초': [],
    '필수 기본소양': [],
    '필수 전공': [],
    '영어': [],
    'fail_major': [],
    '미이수 필수 공교' : [],
    '미이수 필수 학문기초' : [],
    '미이수 필수 기본소양' : [],
    '미이수 필수 전공' : []
}) 


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
    
    for index, row in dataset.iterrows(): 
    # 등급 P/F 처리
        if (row['학수강좌번호'] in pf) or ('DES' in row['학수강좌번호'] and row['년도'] != "2023") or ('DAI' in row['학수강좌번호']):
            dataset.at[index, '등급'] = random.choice(['P', 'F'])

    # 포함 제약사항으로 입력 받은 강의는 F 안 받게
        for sublist in conslist1:
            if (sublist != 0) and (row['학수강좌번호'] in sublist) and (dataset.at[index, '등급'] == 'F'):
                if (row['학수강좌번호'] in pf) or ('DES' in row['학수강좌번호'] and row['년도'] != "2023") or ('DAI' in row['학수강좌번호']):
                    new_grade = 'P'
                else:
                    new_grade = np.random.choice(grade[:-1])
                dataset.at[index, '등급'] = new_grade
                break
    
    # 개별연구 포함 제약사항으로 입력 받은 것만 남기기.
    Ind_target = []
    for sublist in conslist1:
        if sublist != 0:
            sublist_targets = [item for item in sublist if 'DES' in item or 'DAI' in item]
            Ind_target.extend(sublist_targets)

    Ind = dataset['학수강좌번호'].str.contains('DES|DAI')
    IndP = dataset['학수강좌번호'].isin(Ind_target)
    dataset = pd.concat([dataset[~Ind], dataset[IndP]])

    # 학문기초 : 개론 실험 같이 들었으면 삭제
    delete_base = []
    if ('PRI4004' in dataset['학수강좌번호'].values or 'PRI4015' in dataset['학수강좌번호'].values) and 'PRI4028' in dataset['학수강좌번호'].values:
        delete_base.append('PRI4028')
    if ('PRI4002' in dataset['학수강좌번호'].values or 'PRI4013' in dataset['학수강좌번호'].values) and 'PRI4029' in dataset['학수강좌번호'].values:
        delete_base.append('PRI4029')
    if ('PRI4003' in dataset['학수강좌번호'].values or 'PRI4014' in dataset['학수강좌번호'].values) and 'PRI4030' in dataset['학수강좌번호'].values:
        delete_base.append('PRI4030')
    dataset = dataset[~dataset['학수강좌번호'].isin(delete_base)]

    # 중복된 행 중 마지막 행을 제외한 모든 중복 행 제거
    dataset.drop_duplicates(subset='학수강좌번호', keep='last', inplace=True)
    dataset.iloc[-1, dataset.columns.get_loc('재수강구분')] = 'NEW재수강'
    
    # EAS와 창공, 나삶나비, 객체지향언어와실습
    if ('RGC1033' in dataset['학수강좌번호'].values) and ('RGC1080' in dataset['학수강좌번호'].values): # EAS1
        dataset = dataset[dataset['학수강좌번호'] != 'RGC1033']
        dataset.loc[dataset['학수강좌번호'] == 'RGC1080', '재수강구분'] = 'NEW재수강'
    if ('RGC1034' in dataset['학수강좌번호'].values) and ('RGC1081' in dataset['학수강좌번호'].values): # EAS2
        dataset = dataset[dataset['학수강좌번호'] != 'RGC1034']
        dataset.loc[dataset['학수강좌번호'] == 'RGC1081', '재수강구분'] = 'NEW재수강'
    if ('CSE2016' in dataset['학수강좌번호'].values) and ('CSE2028' in dataset['학수강좌번호'].values): # 창공
        dataset = dataset[dataset['학수강좌번호'] != 'CSE2016']
        dataset.loc[dataset['학수강좌번호'] == 'CSE2028', '재수강구분'] = 'NEW재수강'
    if ('RGC1001' in dataset['학수강좌번호'].values) and ('RGC1074' in dataset['학수강좌번호'].values): # 나삶나비
        dataset = dataset[dataset['학수강좌번호'] != 'RGC1001']
        dataset.loc[dataset['학수강좌번호'] == 'RGC1074', '재수강구분'] = 'NEW재수강'
    if ('CSE2019' in dataset['학수강좌번호'].values) and ('CSE2027' in dataset['학수강좌번호'].values): # 객체
        dataset = dataset[dataset['학수강좌번호'] != 'CSE2019']
        dataset.loc[dataset['학수강좌번호'] == 'CSE2027', '재수강구분'] = 'NEW재수강'

    dataset.to_excel('./data/data' + str(i) + '입학' + str(start_year) + '이수' + str(num_semester) +'학기.xlsx') # 만들어진 dataset xlsx로 내보내기

    df_add = check(dataset, comlist, baselist, foundlist, req_com, req_base, req_found, req_major)
    df = pd.concat([df, df_add])
    
    
df = df.reset_index(drop=True)
df.to_excel('./data/data확인.xlsx') # 들은 학점들이랑 f 받은 전공 정보 정리. 
print("data 생성 완료")