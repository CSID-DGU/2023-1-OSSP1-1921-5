import pandas as pd
import numpy as np
import warnings
warnings.simplefilter("ignore")

#'미적분학및연습1', '미적분학및연습2', '확률및통계학', '공학선형대수학', '공학수학1', '이산수학', '수치해석및실습', '산업수학'
bsm_수학 = ['PRI4001', 'PRI4012', 'PRI4023', 'PRI4024','PRI4025', 'PRI4027', 'PRI4036', 'PRI4051']

# '일반물리학및실험1', '일반화학및실험1', '일반생물학및실험1', '일반물리학및실험2', '일반화학및실험2', '일반생물학및실험2', '물리학개론', '화학개론', '생물학개론', '지구환경과학'
bsm_과학 = ['PRI4002', 'PRI4003', 'PRI4004', 'PRI4013', 'PRI4014', 'PRI4015', 'PRI4029', 'PRI4030', 'PRI4028', 'PRI4033']

# '기술창조와특허', '공학경제', '공학법제', '기술과사회', '지속가능한발전과인간', '공학윤리'
기본소양 = ['EGC7026','PRI4041', 'PRI4043' 'PRI4040', 'PRI4048' 'EGC4039']

years=['2018','2019','2020','2021','2022'] #2018년~2022년
sems=["1", "2", "ss", "ws"] #학기
resultFile=open("resultSQL.txt", "w") #결과 sql문들이 모두 작성된 파일

for year in years:
    for sem in sems:
        filename = "lectureBase/"+year+"_"+sem+".xlsx"

        df = pd.read_excel(filename)

        TermNumber= year+"_"+sem
        ClassNumber= df['학수강좌번호']
        LectureNick= df['교과목명']
        Curriculum= df['교과과정']
        ProfessorName= df['교원명']
        ClassCredit= df['학점']
        DesignCredit= df['공학설계'].fillna('NULL')

        EnglishClass= df['원어강의'].apply(lambda x: 1 if str(x) != 'nan' else 0)

        ClassArea = df['교과영역구분'].copy()
        ClassArea[df['학수강좌번호'].str.contains('|'.join(bsm_수학))] = 'bsm_수학'
        ClassArea[df['학수강좌번호'].str.contains('|'.join(bsm_과학))] = 'bsm_과학'
        ClassArea[df['학수강좌번호'].str.contains('|'.join(기본소양))] = '기본소양'

        for i in range(len(ClassNumber)):
            sql = 'INSERT INTO lecture VALUES("%s","%s","%s","%s","%s","%s","%s","%s","%s");\n'%(TermNumber, ClassNumber[i], LectureNick[i], Curriculum[i], ClassArea[i], ProfessorName[i], ClassCredit[i], DesignCredit[i], EnglishClass[i])
            resultFile.write(sql)

resultFile.close()
