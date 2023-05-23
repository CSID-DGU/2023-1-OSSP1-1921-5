#!/usr/bin/env python
# coding: utf-8

# In[1]:


from z3 import *
import pandas as pd


# ## 2017~2020 학년도 졸업요건 (_20) 
# dict 자료형 (key,value) = (학수번호, 과목이름)

# In[23]:


# 계사 공소 어벤디 창공 자구 컴구 이산 종설1 종설2 시소프 개연1 개연2(~2022까지 들은 과목)
major_subjects_2020 = {'CSE2025': '계산적사고법', 'CSE4074': '공개SW프로젝트', 
                    'CSE2016': '창의적공학설계', 'CSE2017': '자료구조', 'CSE2018': '컴퓨터구조',
                    'CSE2026': '이산수학', 'CSE4066': '종합설계1', 'CSE4067': '종합설계2',
                    'CSE2013': '시스템프로그래밍'}
major_subjects_21 = {'CSE2025': '계산적사고법', 'CSE4074': '공개SW프로젝트', 
                    'CSE2028': '어드벤처디자인', 'CSE2017': '자료구조', 'CSE2018': '컴퓨터구조',
                    'CSE2026': '이산수학', 'CSE4066': '종합설계1', 'CSE4067': '종합설계2',
                    'CSE2013': '시스템프로그래밍'}
major_subjects_research_20 = {'DES':'개별연구'}
# 창공 -> 어벤디 OR로 해보기

# basicEAS EAS1 EAS2 자명1 자명2 불인 기보작 커디 나삶나비
# 나삶나비 -> 커디
common_subjects_2020 = {'RGC1030': 'basicEAS', 'RGC1033':'EAS1', 'RGC1034':'EAS2', 'RGC0017':'자아와명상1',
                      'RGC0018':'자아와명상2', 'RGC0003':'불교와인간', 'RGC0005':'기술보고서작성및발표',
                      'RGC1074':'커리어디자인', 'RGC1001':'나의삶,나의비전'}
common_subjects_leadership_20 = {'RGC1050':'소셜앙트레프레너십과리더십', 'RGC1051':'글로벌앙트레프레너십과리더십',
                                 'RGC1052':'테크노앙트레프레너십과리더십'}

#공경 공윤 기창특 기사 공법 지속가능한발전과인간
gs_subjects_2020 = {'PRI4041':'공학경제', 'EGC4039':'공학윤리', 'EGC7026':'기술창조및특허',
                  'PRI4040':'기술과사회', 'PRI4043':'공학법제', 'PRI4048':'지속가능한발전과인간'}
gs_subjects_21 = {'PRI4041':'공학경제', 'EGC4039':'공학윤리', 'EGC7026':'기술창조및특허'}

# 미적1 확통 공선대
bsm_subjects_2020= {'PRI4001':'미적분학및연습1', 'PRI4023':'확률및통계학', 'PRI4024':'공학선형대수학'}
# 물실1 화실1 생실1 물실2 화실2 생실2
bsm_subjects_experiment_20 = {'PRI4002':'일반물리학및실험1', 'PRI4003':'일반화학및실험1', 'PRI4004':'일반생물학및실험1',
                           'PRI4013':'일반물리학및실험2', 'PRI4014':'일반화학및실험2', 'PRI4015':'일반생물학및실험2'}
# 미적2 공수1 이수 산수 수치해석및실습
bsm_subjects_math_20 = {'PRI4012':'미적분학및연습2', 'PRI4025':'공학수학1', 'PRI4027':'이산수학', 'PRI4036':'수치해석및실습'}
bsm_subjects_math_21 = {'PRI4012':'미적분학및연습2', 'PRI4025':'공학수학1', 'PRI4027':'이산수학', 'PRI4036':'수치해석및실습','PRI4051':'산업수학'}
                                 
#물리학개론 화학개론 생물학개론 지구환경과학
bsm_subjects_science = {'PRI4029':'물리학개론', 'PRI4030':'화학개론','PRI4028':'생물학개론', 'PRI4033':'지구환경과학'}


# 각 졸업요건 _20, _21을 비교하고, value값은 같은데 키값이 다르면 키 값 추가하는 그런 함수 필요 (학수번호 변경 대비)

# In[24]:


# 변경된 학수번호
# 학수번호, 과목 명 모두 바뀜
{'CSE2016': '창의적공학설계'->'CSE2028': '어드벤처디자인' }
{'RGC1001':'나의삶,나의비전' -> 'RGC1074':'커리어디자인'}
#학수번호만 바뀜
{'RGC1033':'EAS1' -> 'RGC1080':'EAS1'}
{'RGC1034':'EAS2' -> 'RGC1081':'EAS2'}


# In[25]:


def notTaking(subjects, completed_courses):
    s = Solver()
    bool_vars = {}
    for i, subject in enumerate(subjects):
        bool_vars[i] = Bool(subject)
        if not subject in completed_courses:
            s.add(bool_vars[i] == False)
#         else:
#             s.add(bool_vars[i] == True)
    return s
    #교양 필수 들은 리스트/안들은 리스트 나눌 수 있음 근데 반환할 때 한 리스트로 반환하는 게 좋아보임 


# In[26]:


def user_subject(dataset):
    user_subject_list = []
    for k, row in dataset.iterrows():
        if row['년도'] <= 2022:
            if row['학수강좌번호'].startswith('DES'):
                user_subject_list.append('DES')
            else:
                user_subject_list.append(row['학수강좌번호'])
        if row['년도'] == 2023:
            if row['학수강좌번호'].startswith('DAI'):
                user_subject_list.append('DAI')
            else:
                user_subject_list.append(row['학수강좌번호'])
                # 특수 경우 : 개별연구 
    return user_subject_list


# In[27]:


def print_check_subject(subjects_name, user_subject_list):
    if is_all_true(notTaking(subjects_name, user_subject_list)):
        print("충족됩니다.")
    else:
        print("이수하지 않은 과목이 있습니다.")   
        print(notTaking(subjects_name, user_subject_list))    


# In[28]:


def check_subject(dataset, year):
    
    user_subject_list = user_subject(dataset)
    if year<2020:
        year=2020
    
    print_check_subject(globals()[f'major_subjects_{year}'], user_subject_list)
    print_check_subject(globals()[f'common_subjects_{year}'], user_subject_list)
    print_check_subject(globals()[f'bsm_subjects_{year}'], user_subject_list)
    print_check_subject(globals()[f'gs_subjects_{year}'], user_subject_list)


# In[29]:


def make_conditions(dataset):
    basic_list = user_subject(dataset)
    s = Solver()
    bool_vars = {}
    for subject in basic_list:
        bool_vars[subject] = Bool(subject)
        s.add(bool_vars[subject] == True)
    return s


# In[9]:


s = make_conditions(pd.read_excel("./samples/data0입학2018이수8학기.xlsx"))
s


# In[10]:


def pop_condition(s, conditions):
    # solver에 조건들을 추가
    s.push()
    s.add(conditions)
    # 조건을 체크하고, 조건을 만족하지 않으면 마지막에 추가된 조건을 pop
    if s.check() != sat:
        li.add(conditions) 
        # 충족 못한 조건 리스트 안에 쏙
        s.pop()
        print(conditions, "pop!", s)
    else:
        print(conditions, "push~", s)
    return s


# In[11]:


def is_all_true(s):
    if s.check() == unsat:
        print("All variables cannot be satisfied.")
        return False
    model = s.model()
    for d in model.decls():
        if not model[d]:
            return False
    return True


# In[32]:


z3로 하고싶은거 : 학수벼ㅓㄴ호가 변경되었을 때, 반영되게
졸업요건이 변경되었을 때, 반영되게
이러한 졸업요건들이 계속 추가되고, 그거를 충족하는지 확인하게.
조건들이 계속 추가되고, 그런거를 판별할 수이다
전공학점이 몇 이상,, 

class, 


# In[12]:


# 조건 리스트
conditions = []
# 조건들 
# 전공 조건 리스트
CheckMajor = []
bool_vars = {}
# CSE2016, CSE2017, CSE2018 중 적어도 하나 이상을 이수해야 한다.
# CSE2016_or_CSE2017_or_CSE2018 = z3.Or(CSE2016, CSE2017, CSE2018)
# conditions.append(CSE2016_or_CSE2017_or_CSE2018)
conditions.append(z3.Bool('CSE2016'))
bool_vars['CSE2016'] = z3.Bool('CSE2016')
bool_vars['CSE2017'] = z3.Bool('CSE2017')
bool_vars['CSE2018'] = z3.Bool('CSE2018')
conditions.append(Or(bool_vars['CSE2016'], bool_vars['CSE2017'], bool_vars['CSE2018']))



# In[31]:


df_add = check_subject(pd.read_excel("./samples/data0입학2018이수8학기.xlsx"), 2018)


# In[22]:


pop_condition(s, conditions)


# In[15]:


s.model()


# In[16]:


conditions = []
bool_vars = {}

# 각 과목에 해당하는 불 변수 생성
for subject_code in major_subjects_2020.keys():
    bool_vars[subject_code] = z3.Bool(subject_code)


# CSE2016, CSE2017, CSE2018 중 적어도 하나 이상을 이수해야 한다.
CSE2016_or_CSE2017_or_CSE2018 = z3.Or(bool_vars['CSE2000'], bool_vars['CSE2011'], bool_vars['CSE2010'])
conditions.append(CSE2016_or_CSE2017_or_CSE2018)

# 이수해야 하는 전공 과목 조건 리스트
CheckMajor = conditions


# In[18]:


CheckMajor


# In[18]:


# import z3

# # major_subjects_2020 딕셔너리에서 key값(과목 코드)만 추출하여 리스트로 저장
# major_subjects_1 = {'CSE2016': '창의적공학설계'}
# subject_codes = list(major_subjects_1.keys())


# # bool 변수를 담을 딕셔너리 생성
# bool_vars = {}
# for code in subject_codes:
#     bool_vars[code] = z3.Bool(code)
    

basic_list = subject_codes
c = Solver()
bool_vars = {}
for subject in basic_list:
    bool_vars[subject] = Bool(subject)
    c.add(Or(bool_vars[subject]))
        


# In[66]:


s.reset()


# In[17]:


c


# In[19]:


# 모든 조건이 만족되는지 체크하는 Solver 객체 생성
s = z3.Solver()
s = make_conditions(pd.read_excel("./samples/data0입학2018이수8학기.xlsx"))
print(s)


# In[74]:


solve(s, c)
s


# In[55]:


s.reset()


# In[85]:


bool_vars['CSE2016'] = z3.Bool('CSE2016')
bool_vars['CSE2025'] = z3.Bool('CSE2016')
s = ['CSE2016' == True]
# 필요한 조건들
conditions =[Or('CSE2025')]

# 모든 조건이 True인지 평가
result = solve(s , conditions)
print(result)  # True or False


# In[20]:


# Define major subjects and their codes
major_subjects_1 = {'CSE2016': '창의적공학설계', 'CSE2017': '객체지향프로그래밍', 'CSE2018': '데이터구조',
                   'CSE2022': '시스템프로그래밍', 'CSE2025': '컴퓨터구조', 'CSE2026': '운영체제',
                   'CSE2028': '알고리즘', 'CSE4036': '소프트웨어공학', 'CSE4043': '컴퓨터네트워크', 
                   'CSE4066': '인공지능', 'CSE4067': '데이터마이닝'}
major_subjects_2 = {'EGC2110': '공학수학1', 'EGC3050': '시스템프로그래밍실습', 'EGC3052': '데이터베이스', 
                    'EGC3063': '객체지향프로그래밍실습', 'EGC7026': '캡스톤디자인1', 'EGC7098': '창업기초'}
major_subjects_3 = {'DEV1068': '스토리텔링과디자인씽킹', 'PRI4001': '일반물리학1', 'PRI4002': '일반물리학실험1',
                    'PRI4023': '전자기학', 'PRI4024': '전자기학실험', 'PRI4033': '통신이론', 'PRI4036': '신호및시스템',
                    'PRI4040': '전자회로1', 'PRI4048': '전자회로실험1'}

# Create a list of all major subjects
major_subjects = {**major_subjects_1, **major_subjects_2, **major_subjects_3}
subject_codes = list(major_subjects.keys())

# Create boolean variables for each major subject
bool_vars = {}
for code in subject_codes:
    bool_vars[code] = z3.Bool(code)

# Define graduation requirements as conditions
conditions = [z3.Or(bool_vars[code]) for code in subject_codes]


# In[88]:


p, q = Bools('p q')
demorgan = And(p, q) == Not(Or(Not(p), Not(q)))
print (demorgan)

def prove(f):
    s = Solver()
    s.add(Not(f))
    if s.check() == unsat:
        print ("proved")
    else:
        print ("failed to prove")

print ("Proving demorgan...")
prove(demorgan)


# In[91]:


Tie, Shirt = Bools('Tie Shirt')
solve(And(Tie, Shirt))


# In[92]:


n = 10
x = [Bool('x' + str(k)) for k in range(n)]
phi = And([x[k] for k in range(n)])
print(phi)

solve(phi)


# In[93]:


# input example
S = ["001010011101", "1001010101011", "101101010101010",
     "10111011001010101010", "10110101011010101010", "10110101101010101010"]
m = len(S)
n = 74 # tight?

# how to declare a list of n variables x0, ..., x(n-1)
x = [ Bool('x' + str(k)) for k in range(n) ]

# Solution for S: 001010011101101011010101010100101010101110110010101010101101010110101010100

# this procedure returns an assignment (as a dictionary) if any exists
def model(phi):
    
    # create a SAT instance
    s = Solver()
    s.add(phi)

    # return a satisfying assignment
    return s.model() if s.check() == sat else []

phi = And([x[k] for k in range(n)])
solution = model(phi)
print([(var, solution[var]) for var in x])


# In[ ]:




