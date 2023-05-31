from z3 import *
import pandas as pd

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
       
#testset과 2020년 졸업요건을 기준으로 학점 TEST
search_credit(pd.read_excel("./data020188.xlsx"), pd.read_excel("./GraduationRequirements2020.xlsx"))
