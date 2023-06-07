# Z3 Solver

<h1> 1단계 : </h1>
  1. 학생정보파일에서 F학점 제외한 과목들 받아옴특특수경우  : 개별연구는 학수강좌번호가 모두 달라서 교과목에서 '개별연구'일시 앞의 세글자만 받아옴(DES/ DAI)
  ![image](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/92643238/be7f52e7-6d28-4986-b02f-93fb6c81cb55)

 <h1> 2단계 : 필수과목확인</h1>
 1. grad_dict : 필수과목 
select_dict : 선택 필수 과목(ㅇ택ㅇ)
졸업요건이 필수이면 이수구분(전필, 공교 등등)에 따라 grad_dict의 이수구분 리스트에 과목 추가
졸업요건이 선택필수이면 이수구분에 따라 select_dict의 이수구분 리스트에 과목 추가(몇개를 택해야하는지 정보 추가(5택3과 같은))
![image](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/92643238/d7b1cff6-fea0-4f3b-a9ca-fd44f7b27328)

2.grad_dict_keys는 grad_dict의 key들. (전공필수, 공통교양 등이 들어있다)
select_dict_keys는 select_dict의 key들. (세미나, 기본소양, 과학실험 등이 들어있다)
check_vars는 key들을 Bool변수로 선언한 리스트
grad_dict_keys를 돌면서 각 이수구분 리스트에 학수강좌번호를 추가한다.
select_dict_keys를 돌면서 각 이수구분 리스트에 학수강좌번호를 추가한다.
![image](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/92643238/5c181a6a-dc00-46e9-a8c2-8dc90bd354b1)

3. 학수번호 변경사항 적용 단계 - 필수과목에서..
변경사항 엑셀을 참고하여 기존학수강좌번호가 있다면 제약조건으로 기존학수번호, 새학수강좌번호를 Or로 묶어준다. (중복 수강 제한 및 학수번호 변경시 반영하기 위하여)
변경사항이 없는 과목들은 Bool로 넣어준다.
모든 제약조건들을 필수과목 이수구분 리스트를 참고하여 And로 묶어준다.
![image](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/92643238/d7e86067-d307-4f60-bf80-a8f43160c4a8)

4. 학수번호 변경사항 적용 단계 - 선택과목에서..
변경사항 엑셀을 참고하여 기존학수강좌번호가 있다면 제약조건으로 기존학수번호, 새학수강좌번호를 Or로 묶어준다. (중복 수강 제한 및 학수번호 변경시 반영하기 위하여)
변경사항이 없는 과목들은 Bool로 넣어준다.
모든 제약조건들을 필수과목 이수구분 리스트를 참고하여 몇개를 선택해야 하는지를 넣어  AtLeast로 묶어준다.
![image](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/92643238/f98e7cfe-3081-43b5-9afa-c42a323983a7)
