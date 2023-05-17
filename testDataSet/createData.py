import pandas as pd
import numpy as np
import random

cse = pd.read_excel("cse_1.xlsx") # 1학기 
Ind = pd.read_excel("individual.xlsx") # 개별연구 
comcul = pd.read_excel("culture_com.xlsx") # 8개 
gencul = pd.read_excel("culture_gen.xlsx") # 7개 + 기술창조, 공학윤리 
msc = pd.read_excel("msc_1.xlsx") # 8개 + 공학경제 

def input_rule(course):
    # course : 졸업요건 이름 ex) 전공, 개별연구, msc ...
    constraints = 0 
    constraints2 = 0 
    
    user_input = input(course + " : ") # rule 입력 받음. x, a, c, n
    if user_input.lower()=="x":
        pass
    else:
        if user_input.lower()=="a":
            input1 = input("제외하고 싶은 강의를 입력하세요 : ")
            input1_1 = input("포함하고 싶은 강의를 입력하세요 : ")
            constraints2 = input1_1.split(',')
        elif user_input.lower()=="c":
            input1 = input("포함하고 싶은 강의를 입력하세요 : ")
        elif user_input.lower()=="n":
            input1 = input("제외하고 싶은 강의를 입력하세요 : ")
        constraints = input1.split(',')
    
    return user_input, constraints, constraints2

# 해당 졸업요건의 제약사항이 없을 때 실행 -> 졸업 불가능 data 생성
def not_constraints(df, num_rows):
    df = df.sample(n=num_rows, ignore_index=True)
    return df

# 해당 졸업요건에서 포함시키지 않을 강의가 있을 때 실행 -> 졸업 불가능 data 생성
def not_contain(df, keywords, num_rows):
    # df['교과목명'].str.contains('|'.join(keywords)) : keywords list에 포함된 강의를 하나라도 포함하고 있는 행을 찾음
    # 위의 코드에 ~ 붙임 : df에서 그 행들을 제외한 dataframe을 not_included라고 함.
    not_included = df.loc[~df['교과목명'].str.contains('|'.join(keywords))] 
    result = not_included.sample(n=num_rows, ignore_index=True) # cnt 개수만큼 not_included에서 행 임의 추출. ignore_index = True -> 새로운 index 생성
    return result


# 해당 졸업요건에서 포함시킬 강의가 있을 때 실행 -> 졸업 가능 data 생성
def contain(df, keywords, num_rows):
    rows = [] # 추출한 행들을 저장할 빈 리스트 rows
    
    for kw in keywords: # keywords에 있는 각각의 keword kw에 대해 반복문 실행
        df_kw = df.loc[df['교과목명'].str.contains(kw)] # df의 교과목명 열에서 kw를 포함하는 행들을 추출하여 df_kw에 저장
        if not df_kw.empty: # df_kw가 비어있지 않으면 -> kw를 포함하는 행이 존재하면
            row = df_kw.sample() # df_kw에서 랜덤으로 1개의 행을 추출하여 row에 저장
            rows.append(row) # rows에 row 추가
    
    rowss = pd.concat(rows).values.tolist() # 3차원 list인 rows에 저장된 dataframe들을 concat하여 2차원 list로 변경.

    if len(rows) < num_rows: # 추출된 행의 개수가 원하는 추출 행의 개수보다 작으면
        cnt = num_rows - len(rows) # 더 추출해야 할 행의 개수 a 계산.

        Xdup = df.loc[~df['교과목명'].str.contains('|'.join(keywords))] # keywords 포함 강의 -> 이미 rows에 추출한 강의들 -> 중복 방지 위해 df에서 제외.
        add_rows = Xdup.sample(n=cnt, ignore_index=True) # not_included에서 더 추출해야 하는 행 개수만큼 랜덤으로 추출하여 add_rows에 저장
        
        # list인 rows를 column이 '년도' ~ '대학대학원'인 dataframe으로 만들어줌. add_rows는 dataframe
        rowdf = pd.DataFrame(rowss, columns=['년도', '학기', '이수구분', '이수구분영역', '학수강좌번호', None, '교과목명', '담당교원', '학점', 
                                         '등급', '삭제구분', '재수강구분', '공학인증', '공학요소', '공학세부요소', '원어강의', '인정구분', '성적인정대학명', '교과목영문명', '대학대학원'])
        contain_result = pd.concat([rowdf, add_rows], axis=0) # rowdf와 add_rows를 합침.
    else:
        contain_result = rowss
        
    return contain_result


# 해당 졸업요건에서 포함, 제외시킬 강의가 있을 때 실행
def all_constraints(df, keywords1, keywords2, num_rows):
    # keywords1 -> 제외할 강의들
    # keywords2 -> 포함할 강의들
    # not_contain 함수와 contain 함수 합친 거임.
    not_included = df.loc[~df['교과목명'].str.contains('|'.join(keywords1))] # 강의들 제외
    
    rows = [] 
    
    for kw in keywords2: 
        df_kw = not_included.loc[not_included['교과목명'].str.contains(kw)] 
        if not df_kw.empty: 
            row = df_kw.sample() 
            rows.append(row) 
    
    rows = pd.concat(rows).values.tolist() 
    
    if len(rows) < num_rows: 
        cnt = num_rows - len(rows)
        Xdup = not_included.loc[~not_included['교과목명'].str.contains('|'.join(keywords2))] 
        add_rows = Xdup.sample(n=cnt, ignore_index=True) 
        
        rowdf = pd.DataFrame(rows, columns=['년도', '학기', '이수구분', '이수구분영역', '학수강좌번호', None, '교과목명', '담당교원', '학점', 
                                         '등급', '삭제구분', '재수강구분', '공학인증', '공학요소', '공학세부요소', '원어강의', '인정구분', '성적인정대학명', '교과목영문명', '대학대학원'])
        all_result = pd.concat([rowdf, add_rows], axis=0)
        
    return all_result


def conn(user, df, constraints, constraints_, cnt):
    if user.lower() == "x": # 제약사항 없음
        result = not_constraints(df, cnt)
    elif user.lower() == "a": # 제외, 포함 둘 다 진행
        result = all_constraints(df, constraints, constraints_, cnt)
    elif user.lower() == "c": # 포함
        result = contain(df, constraints, cnt)
    elif user.lower() == "n": # 제외
        result = not_contain(df, constraints, cnt)
        
    return result


datacnt = input("만들 data 개수 입력 : ")
semester = input("이수학기 입력 : ")
# 한 학기에 최대 10개의 강의 듣는다고 가정

total_lec = 0
while total_lec != int(semester) * 10:
    Rcm = random.randint(0, 18) # 전공 36
    RcI = random.randint(0, 3) # 개별연구
    Rcc = random.randint(0, 10) # 공통교양
    Rgc = random.randint(0, 15) # 일반교양
    Rms = random.randint(0, 10) # msc
    
    total_lec = Rcm + RcI + Rcc + Rgc + Rms

print("-----------------------")
print("총 이수 강의 수 : ", total_lec)
print("전공 이수 :", Rcm)
print("개별연구 이수 :", RcI)
print("공통교양 이수 :", Rcc)
print("일반교양 이수 :", Rgc)
print("msc 이수 :", Rms)
print("---------------------------------------------------------------------")

print("제약사항이 없으면 X, 포함 제약사항만 있으면 C, 제외 제약사항만 있으면 N")
print("포함, 제외 제약사항 모두 있으면 A를 입력하세요.")
print("(입력형식 ex) 자료구조와실습,인공지능,머신러닝,...)")
print("---------------------------------------------------------------------")

user1, constraints1, constraints1_1 = input_rule("전공")
user3, constraints3, constraints3_1 = input_rule("공통교양")
user4, constraints4, constraints4_1 = input_rule("일반교양")
user5, constraints5, constraints5_1 = input_rule("msc")


df = pd.DataFrame({
    '공통교양': [],
    '학문기초': [],
    '기본소양': [],
    '전공': [],
    '영어': [],
    'fail_major': []
})

for i in range(0, int(datacnt)): # 제약사항 한 번 받아서, 입력받은 data 개수만큼 반복.
    result1 = conn(user1, cse, constraints1, constraints1_1, Rcm)
    result2 = not_constraints(Ind, RcI)
    result3 = conn(user3, comcul, constraints3, constraints3_1, Rcc)
    result4 = conn(user4, gencul, constraints4, constraints4_1, Rgc)
    result5 = conn(user5, msc, constraints5, constraints5_1, Rms)

    df_list = [result1, result2, result3, result4, result5]
    dataset = pd.concat(df_list, ignore_index=True) # 랜덤 추출한 거 합치기
    dataset.index = dataset.index+1 # index 1부터 시작
    
    # 성적 랜덤 지정
    grade = ['A+', 'A0', 'B+', 'B0', 'C+', 'C0', 'D+', 'D0', 'F']
    choice = np.random.choice(grade, total_lec, p=[0.1, 0.2, 0.2, 0.15, 0.15, 0.05, 0.05, 0.05, 0.05]) # grade의 각 값이 나올 확률 지정
    dataset['등급'] = choice
    

    # 같은 강의 2개 이상이면 그냥 제거~!!!!!!!!
    dataset = dataset.drop_duplicates(['교과목명'], keep='first', inplace=False, ignore_index=False)
    dataset.to_excel('./data/data' + str(i) + '이수' + semester +'학기.xlsx') # 만들어진 dataset xlsx로 내보내기

    
    
    # 공통교양, 학문기초, 기본소양, 전공 학점 수 확인
    count_com = 0 # 들은 공통교양 학점 
    count_base = 0 # 들은 학문기초 학점
    count_found = 0 # 들은 기본소양 학점
    count_major = 0 # 들은 전공 학점
    count_en = 0 # 들은 영어 강의 개수
    
    comlist = ['커리어 디자인', '자아와명상1', '자아와명상2', '불교와인간', '소셜앙트레프레너십과리더십', '글로벌앙트레프레너십과리더십',
              '테크노앙트레프레너십과리더십', '기술보고서작성및발표', 'EAS1', 'EAS2'] # 23년 세미나 추가, 리더십 중복 추출 해결해야 됨.
    baselist = ['미적분학및연습1', '미적분학및연습2', '확률및통계학', '공학선형대수학', '공학수학1', '이산수학',
                '일반물리학및실험1', '일반물리학및실험2', '일반화학및실험1', '일반화학및실험2', '일반생물학및실험1', '일반생물학및실험2', 
                '물리학개론', '화학개론', '생물학개론', '지구환경과학']
    foundlist = ['기술창조와특허', '공학경제', '공학윤리', '공학법제']
    
    re_major = ['어드벤처디자인', '컴퓨터공학종합설계1', '컴퓨터공학종합설계2', '자료구조와실습',
               '컴퓨터구성', '시스템소프트웨어와실습', '공개SW프로젝트', '계산적사고법', '이산구조']  # 23년 이산구조 삭제
    major_fail = []
    
    # 공교, 학문기초, 기본소양, 전공 몇 학점 들었는지, 영어 강의 개수 확인. 성적이 F가 아니어야 함.
    for i, row in dataset.iterrows(): 
        # 공통교양
        if row['교과목명'] in comlist and row['학점'] != 'F':
            count_com += row['학점']
        # 학문기초
        if row['교과목명'] in baselist and row['학점'] != 'F':
            count_base += row['학점']
        # 기본소양
        if row['교과목명'] in foundlist and row['학점'] != 'F':
            count_found += row['학점']
        # 전공
        if row['이수구분'] == '전공' and row['학점'] != 'F':
            count_major += row['학점']
        # 영어 강의 개수 확인
        if row['교과목명'] == 'EAS1' or row['교과목명'] == 'EAS2' and row['학점'] != 'F':
            count_en += 1
        if row['이수구분'] == '전공' and row['원어강의'] == '영어' and row['학점'] != 'F':
            count_en += 1
            
        # 전공 필수 중 F 맞은 과목 있는지 확인.
        if row['교과목명'] in re_major and row['학점'] == 'F':
            major_fail.append(row['교과목명'])
    
    df = df.append({
    '공통교양': count_com,
    '학문기초': count_base,
    '기본소양': count_found,
    '전공': count_major,
    '영어': count_en
    }, ignore_index=True)
    df['fail_major'] = pd.Series(major_fail)
    
df.to_excel('./data/data확인.xlsx') # 들은 학점들이랑 f 받은 전공 정보 정리. 