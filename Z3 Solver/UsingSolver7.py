#!/usr/bin/env python
# coding: utf-8

# In[103]:


from z3 import *
import pandas as pd


# In[104]:


def search_credit(dataset, grad):
    total = 0 #전체 학점
    
    major = 0 #전공 학점
    cs = 0 #공통 교양 학점
    leadership = 0 #리더십 학점
    basic = 0 #기본 소양 학점
    bsm = 0 #bsm 학점 (학문)
    
    for k, row in dataset.iterrows():
        if row['등급'] != 'F': #F면 수강 이력이 아님!
            subject_class = row['이수구분']
            if subject_class == '전필' or subject_class == '전공':
                major += row['학점']
        
        
            elif subject_class == '공교':
                student_course_number = row['학수강좌번호'] #학생이 들은 강좌
                for k2, row2 in grad.iterrows():
                    if row2['이수구분'] == '공통교양': 
                        grad_course_number = row2['학수강좌번호'] #졸업 필수 강좌
                        if student_course_number == grad_course_number:
                            cs += row['학점']
        
            else: #전공과 공교를 제외하고는, 이수구분이 일관적이지 않아 학수번호로 인정과목을 판단
                student_course_number = row['학수강좌번호']
                for k2, row2 in grad.iterrows():
                    grad_course_number = row2['학수강좌번호']
                    if student_course_number == grad_course_number:
                        if row2['이수구분'] == '리더십':
                            leadership += row['학점']
                        elif row2['이수구분'] == '기본소양':
                            basic += row['학점']
                        elif row2['이수구분'] == '학문필수' or  row2['이수구분'] == '학문실험' or  row2['이수구분'] == '학문개론':
                            bsm += row['학점']
                
    print("전공학점 =",major)
    print("공통교양 =",cs)
    print("리더십   =",leadership)
    print("기본소양 =",basic)
    print("BSM      =",bsm)
           
search_credit(pd.read_excel("./grad/data020188.xlsx"), pd.read_excel("./grad/GraduationRequirements2020.xlsx"))


# In[122]:


def search_credit(grad):
    row = grad.loc[0]
    total_major = row[6]
    total_common = row[7]
    total_gsmsc = row[8]
    total = row[9]

    grad_dict = {}
    select_dict = {}
    for k, row in grad.iterrows(): 
        if row['비고']=='필수':
            grad_dict[row['이수구분']] = []
        elif str(row['비고'])[0] == '택':
            select_dict[row['이수구분']+row['비고']]  = []  
    
    global grad_dict_keys
    global select_dict_keys
    grad_dict_keys = list(grad_dict.keys())
    select_dict_keys = list(select_dict.keys())
    
    global check_vars
    check_vars = [Bool(key) for key in grad_dict_keys+select_dict_keys]
    
    for key in grad_dict_keys:
        for k, row in grad.iterrows(): 
            if key == row['이수구분']:
                grad_dict[key].append(row['학수강좌번호'])
    
    for key in select_dict_keys:
        for k, row in grad.iterrows(): 
            if key[:-2] == row['이수구분']:
                select_dict[key].append(row['학수강좌번호'])
                
    solver = Solver()
    solver.reset()
    
    for key in grad_dict_keys:
        constraint = check_vars[grad_dict_keys.index(key)] == And(*[Bool(course) for course in grad_dict[key]])
        solver.add(constraint)
        
    for key in select_dict_keys:
        constraint = check_vars[len(grad_dict_keys)+select_dict_keys.index(key)] == Or(*[Bool(course) for course in select_dict[key]])
        solver.add(constraint)
#         solver.add(check_vars[grad_dict_keys.index(key)] ==And(*[Bool(course) for course in grad_dict[key]]))
    
    print(solver.check())
    return solver

search_credit(pd.read_excel("./grad/GraduationRequirements2020.xlsx"))


# In[123]:


def user_subject(dataset, solver):
    s = Solver()
    s.add(solver.assertions())
    s.check()
    for k, row in dataset.iterrows():
        a = row['학수강좌번호']
        a = Bool(a)
        s.add(a == True)
    return s


# In[131]:


s = user_subject(pd.read_excel("./samples/data0입학2018이수8학기.xlsx"), search_credit(pd.read_excel("./grad/GraduationRequirements2020.xlsx")))


# In[132]:


print(s)


# In[133]:


print(s.check())
print(s.model())


# In[134]:


for key in grad_dict_keys:
    is_major_required = s.model()[check_vars[grad_dict_keys.index(key)]]
    print("'", key, "' is", is_major_required)
for key in select_dict_keys:
    is_major_required = s.model()[check_vars[select_dict_keys.index(key)]]
    print("'", key, "' is", is_major_required)


# In[ ]:




