#!/usr/bin/env python
# coding: utf-8

# In[3]:


from z3 import *
import pandas as pd


# # 졸업요건 설정!

# In[157]:


s.reset()
def add_condition(dataset, year):
    major_subjects[year-2020] = {}
    for k, row in dataset.iterrows():
        subject_class = row['이수구분']
        if subject_class == '전공필수':
            major_subjects[year-2020].append({row['학수강좌번호']: row['학점']}
        elif subject_class == '공통교양':
            a = row['학수강좌번호']
        elif subject_class == '리더십':
            a = row['학수강좌번호'] 
        elif subject_class == '기본소양':
            a = row['학수강좌번호']
        elif subject_class == '학문필수':
            a = row['학수강좌번호']
        elif subject_class == '학문실험':
            a = row['학수강좌번호']
        elif subject_class == '학문기초':
            a = row['학수강좌번호']
        elif subject_class == '학문개론':
            a = row['학수강좌번호']
        else : print("올바르지 않은 이수구분입니다")
#a = Int(a)

    return s
s = add_condition(pd.read_excel("./2020.xlsx"))


# In[4]:


""" 2020년 졸업요건을 딕셔너리로 엑셀파일에 읽어오기"""
major_subjects = [10]
major_subjects[0] = {'CSE2025': 3, 'CSE4074': 3, 'CSE2016': 3, 'CSE2017': 3, 
                       'CSE2018': 3,'CSE2026': 3, 'CSE4066': 3, 'CSE4067': 3,
                       'CSE2013': 3}
바로 ㅇ

common_subjects = []
gs_subjects = []
bsm_subjects = []
bsm_sciences = []
#TODO : 엑셀파일에서 읽어오기
#TNK : major_subjects를 리스트형으로 만들지 고민
#TNK : 리스트를 동적으로 할지? 


# In[5]:


"""Ints형으로 필수과목들을 생성한다."""
major_list = []
common_list = []
gs_list = []
bsm_list = []
sci_list = []

for i in major_subjects[0]:
    major_list.append(Int(i) > 0)
    #TODO : 액셀에서 받아오기. 
#TODO : Ints형 리스트 생성과 반복문 뭔가 과정을 줄이고 싶다.


# In[6]:


"""필수과목 리스트를 솔버 객체에 추가한다."""
solver = Solver()
check_major= Bool('check_major')
solver.add(check_major==And(tuple(major_list)))
#TODO : Ints형 리스트 생성과 반복문 뭔가 과정을 줄이고 싶다.
print(solver.check())


# # 인정과목(예외조건)을 추가하기

# In[ ]:





# # 내가 들은 과목 넣기 !

# In[7]:


def user_subject(dataset, solver):
    s = solver
    for k, row in dataset.iterrows():
        a = row['학수강좌번호']
        b = row['학점']
        a = Int(a)
        s.add(a == b)
    return s


# In[8]:


s = user_subject(pd.read_excel("./samples/data0입학2018이수8학기.xlsx"), solver)


# In[9]:


print(s)


# In[10]:


print(s.check())
m = s.model()


# In[11]:


print(m)


# In[227]:


solver.reset()
s.reset()


# In[215]:


print(solver)


# In[ ]:




