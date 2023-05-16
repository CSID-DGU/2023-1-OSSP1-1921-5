import pandas as pd
import numpy as np
import random

cse1 = pd.read_excel("cse_1.xlsx") # 1학기
cse2 = pd.read_excel("cse_2.xlsx") # 2학기
Ind = pd.read_excel("individual.xlsx") # 개별연구
comcul = pd.read_excel("culture_com.xlsx") # 8개
gencul = pd.read_excel("culture_gen.xlsx") # 7개 + 기술창조, 공학윤리
msc1 = pd.read_excel("msc_1.xlsx") # 8개 + 공학경제
msc2 = pd.read_excel("msc_2.xlsx") 

for i in range(0, 11):
    Rc1 = random.randint(0, 18) # 1학기 전공
    Rc2 = random.randint(0, 18) # 2학기 전공
    RcI = random.randint(0, 3) # 개별연구
    Rcc = random.randint(0, 10) # 공통교양
    Rgc = random.randint(0, 15) # 일반교양
    Rm1 = random.randint(0, 5) # 1학기 msc
    Rm2 = random.randint(0, 5) # 2학기 msc

    total = Rc1 + Rc2 + RcI + Rcc + Rgc + Rm1 + Rm2 # 등급 넣으려고 개수
    
    cse_1 = cse1.sample(n=Rc1, ignore_index=True) # 행 랜덤 추출
    cse_2 = cse2.sample(n=Rc2, ignore_index=True)
    Ind_R = Ind.sample(n=RcI, ignore_index=True)
    com_cul = comcul.sample(n=Rcc, ignore_index=True)
    gen_cul = gencul.sample(n=Rgc, ignore_index=True)
    msc_1 = msc1.sample(n=Rm1, ignore_index=True)
    msc_2 = msc2.sample(n=Rm2, ignore_index=True)
    
    df_list = [cse_1, cse_2, Ind_R, com_cul, gen_cul, msc_1, msc_2]
    dataset = pd.concat(df_list, ignore_index=True) # 랜덤 추출한 거 합치기
    dataset.index = dataset.index+1 # index 1부터 시작
    
    grade = ['A+', 'A0', 'B+', 'B0', 'C+', 'C0', 'D+', 'D0', 'F']
    choice = [random.choice(grade) for j in range(total)] # 등급 랜덤추출
    dataset['등급'] = choice
    
    # 같은 강의 2번 듣기 방지, 중복제거
    dataset.drop_duplicates(['교과목명'], keep='first', inplace=False, ignore_index=False)
    
    # dataset 이름 설정
    m=i
    
    dataset.to_excel('./data/data' + str(m) +'.xlsx')
    
print("dataset 생성 완료")