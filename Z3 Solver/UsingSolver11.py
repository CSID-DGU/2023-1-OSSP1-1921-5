import warnings
warnings.filterwarnings('ignore')

#!/usr/bin/env python
# coding: utf-8

from z3 import *
import pandas as pd
 
def search_credit(dataset, grad, edit, solver, year): 
    total = 0 #전체 학점
    major = 0 #전공 학점
    cs = 0 #공통 교양 학점
    leadership = 0 #리더십 학점
    basic = 0 #기본 소양 학점
    bsm = 0 #bsm 학점 (학문)
    
    newly = pd.read_excel("./신설과목.xlsx")
    for k, row in newly.iterrows():
        if row['신설년도'] > year:
            new_row = {
                '이수구분': row['이수구분'],
                '학수강좌번호': row['학수강좌번호'],
                '학점': row['학점'],
                '교과목명': row['교과목명'],
                '비고': row['비고']
            }
            grad = grad.append(new_row, ignore_index=True)
    
    for k, row in dataset.iterrows():
        if row['등급'] != 'F': #F면 수강 이력이 아님!
            total = total + row['학점']
            
            for k3, row3 in edit.iterrows():
                if row3['새학수강좌번호'] == row['학수강좌번호']:
                    solver.add(row3['기존학수강좌번호'] == True)
                    
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
    
    majorZ3, csZ3, leadershipZ3, basicZ3, bsmZ3, totalZ3 = Ints('majorZ3 csZ3 leadershipZ3 basicZ3 bsmZ3 totalZ3')

    학점조건, major학점, cs학점, leadership학점, basic학점, bsm학점, total학점 = Bools('학점조건 major학점 cs학점 leadership학점 basic학점 bsm학점 total학점')
    grad_row = grad.loc[0]
    total_major = grad_row[6]
    total_common = grad_row[7]
    total_bsm = grad_row[8]
    total_credit = grad_row[9]
    
    solver.add(majorZ3 == major)
    solver.add(csZ3 == cs)
    solver.add(leadershipZ3 == leadership)
    solver.add(basicZ3 == basic)
    solver.add(bsmZ3 == bsm)
    solver.add(totalZ3 == total)
    
    solver.add(major학점 == (majorZ3 >= total_major))
    solver.add(cs학점 == (csZ3 >= total_common))
    solver.add(leadership학점 == (leadershipZ3 >= 2))
    solver.add(basic학점 == (basicZ3 >= 9))
    solver.add(bsm학점 == (bsmZ3 >= total_bsm))
    solver.add(total학점 == (totalZ3 >= total_credit))
    solver.add(학점조건 == And(major학점, cs학점, leadership학점, basic학점, bsm학점, total학점))
    
    
    solver.check()
    return solver


def listing_lecture(grad, year):
    row = grad.loc[0]
    total_major = row[6]
    total_common = row[7]
    total_gsmsc = row[8]
    total = row[9]

    grad_dict = {}
    select_dict = {}
    
    newly = pd.read_excel("./신설과목.xlsx")
    for k, row in newly.iterrows():
        if row['신설년도'] > year:
            new_row = {
                '이수구분': row['이수구분'],
                '학수강좌번호': row['학수강좌번호'],
                '학점': row['학점'],
                '교과목명': row['교과목명'],
                '비고': row['비고']
            }
            grad = grad.append(new_row, ignore_index=True)

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
    
    global total_constraints #모든 학수번호 bool 변수 리스트
    total_constraints = []
    
    modify = pd.read_excel("./변경사항.xlsx")
    for key in grad_dict_keys:
        constraints = []
        for course in grad_dict[key]:
            change = False
            for k, row in modify.iterrows():
                if row['기존학수강좌번호'] == course:
                    constraints.append(Or(Bool(course), Bool(row['새학수강좌번호'])))
                    total_constraints.append(Or(Bool(course), Bool(row['새학수강좌번호'])))
                    change = True
                    break

            if not change:
                constraints.append(Bool(course))
                total_constraints.append(Bool(course))

        constraint = check_vars[grad_dict_keys.index(key)] == And(*constraints)
        solver.add(constraint)
        
            
    for key in select_dict_keys:
        constraints = []
        for course in select_dict[key]:
            change = False  
            for k, row in modify.iterrows():
                if row['기존학수강좌번호'] == course:
                    constraints.append(Or(Bool(course), Bool(row['새학수강좌번호'])))
                    total_constraints.append(Or(Bool(course), Bool(row['새학수강좌번호'])))
                    change = True
                    break

            if not change:
                constraints.append(Bool(course))
                total_constraints.append(Bool(course))
                
        constraint = check_vars[len(grad_dict_keys) + select_dict_keys.index(key)] == AtLeast(*constraints, int(key[-1])) 
        solver.add(constraint)
        
    solver.check()
    return solver

def user_subject(dataset, solver):
    s = Solver()
    s.reset()
    s.add(solver.assertions())
    s.check()
    for k, row in dataset.iterrows():
        if row['등급'] != 'F':
            if row['교과목명'][0:4] == '개별연구' or row['교과목명'][0:4] == 'CS개별':
                a = Bool(row['학수강좌번호'][0:3])
            else:
                a = Bool(row['학수강좌번호'])
            s.add(a == True)
    return s

def fail_course(s): #이수하지 못한 과목 출력
    model = s.model()
    for var in total_constraints:
        if model.evaluate(var, model_completion=True) == False:
            print(var)


s = user_subject(pd.read_excel("./data020188.xlsx"),listing_lecture(pd.read_excel("./GraduationRequirements2020.xlsx"),2020))
s = search_credit(pd.read_excel("./data020189.xlsx"), pd.read_excel("./GraduationRequirements2020.xlsx"), pd.read_excel("./변경사항.xlsx"),s, 2020)


m = s.model()
print(m)

grad_credit_keys = Ints('major cs leadership basic bsm total')
check_credits = [Bool(str(key) + "학점") for key in grad_credit_keys]

for key in grad_dict_keys:
    print("'", key, "' is", s.model()[check_vars[grad_dict_keys.index(key)]])
for key in select_dict_keys:
    print("'", key, "' is", s.model()[check_vars[len(grad_dict_keys)+select_dict_keys.index(key)]])
for key in grad_credit_keys:
    print("'", key, "학점' is", s.model()[check_credits[grad_credit_keys.index(key)]])

results = []
for key in grad_dict_keys:
    result = {
        '졸업요건': key,
        '만족여부': s.model()[check_vars[grad_dict_keys.index(key)]]
    }
    results.append(result)
    
for key in select_dict_keys:
    result = {
        '졸업요건': key,
        '만족여부': s.model()[check_vars[len(grad_dict_keys) + select_dict_keys.index(key)]]
    }
    results.append(result)
    
for key in grad_credit_keys:
    result = {
        '졸업요건': str(key) + '학점',
        '만족여부': s.model()[check_credits[grad_credit_keys.index(key)]]
    }
    results.append(result)

# Create a DataFrame from the results
df = pd.DataFrame(results)

# Save the DataFrame to an Excel file
df.to_excel('results.xlsx', index=False)

print()
print("수강하지 않은 과목")
fail_course(s)
