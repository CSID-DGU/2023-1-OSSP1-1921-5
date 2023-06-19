# 2023-1-OPPS1-1921-5
2023년 1학기 공개SW프로젝트01 5조입니다.

## TEAM MEMBER ✨
<table>
  <tbody>
    <tr>
      <td align="center"  width="16.66%"><a href="https://github.com/dabeann"><img src="https://avatars.githubusercontent.com/u/127164905?v=4" width="100px;" alt="최다빈"/><br /><sub><b>최다빈</b></sub></a><br />팀장, BackEnd</td>
      <td align="center"  width="16.66%"><a href="https://github.com/pletain"><img src="https://avatars.githubusercontent.com/u/45763117?v=4" width="100px;" alt="김태형"/><br /><sub><b>김태형</b></sub></a><br />BackEnd</td>
      <td align="center"  width="16.66%"><a href="https://github.com/heejjinkim"><img src="https://avatars.githubusercontent.com/u/108065505?v=4" width="100px;" alt="김희진"/><br /><sub><b>김희진</b></sub></a><br />BackEnd</td>
      <td align="center"  width="16.66%"><a href="https://github.com/Sj0-0i"><img src="https://avatars.githubusercontent.com/u/80145172?v=4" width="100px;" alt="신지영"/><br /><sub><b>신지영</b></sub></a><br />FrontEnd, 데이터셋</td>
      <td align="center"  width="16.66%"><a href="https://github.com/eeheueklf"><img src="https://avatars.githubusercontent.com/u/92643238?v=4" width="100px;" alt="하유경"/><br /><sub><b>하유경</b></sub></a><br />FrontEnd, z3솔버</td>
      <td align="center"  width="16.66%"><a href="https://github.com/junnyange"><img src="https://avatars.githubusercontent.com/u/103040750?v=4" width="100px;" alt="허준상"/><br /><sub><b>허준상</b></sub></a><br />FrontEnd,z3솔버</td>
  </tbody>
</table>

# 졸업할 수 있을까?
> 동국대학교 **컴퓨터공학과** 졸업 가능 여부 판정 웹 서비스 (23/03/15 ~ 22/06/17)
[기존 프로젝트](https://github.com/CSID-DGU/2022-2-OSSP1-MooMinn-4/blob/main/README.md)

### ✨ 특장점 및 차별점
- 지속적으로 변화하는 졸업요건을 반영
- 관리자 페이지 기능을 추가하여 여러 기능 제공
- 기존에 정상적으로 작동하지 않던 통계 페이지 구현
- 백엔드 스택 변경으로 가독성, 유지보수성, 확장성, 유연성 향상
- 테스트 데이터 셋으로 정확도 측정
- z3 solver를 이용해 테스트 데이터 셋의 정답 확인
- 개발자의 db, email정보가 노출되지 않도록 환경변수화


### ✨ 기대 효과
- 현재는 학과를 대상으로 하고 있지만 단과대나 종합대 처럼 넓은 범위로 확장 가능
- 테스트 데이터 셋으로 정확도를 측정함으로써 추후 생길 프로젝트의 문제점을 발견해 지속적인 업데이트가 가능
- 학과 졸업 요건이 변경되더라도 지속적인 사용 가능


### 🎓 Entity Relationship Diagram
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/0800c2c9-c30b-48a1-8cc5-63aece31d145)

### 🎓 Overview
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/8113c84a-be39-4518-8a2f-13733898568d)
### 🎓 Admin Page
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/901d394e-061c-4fec-9ef9-fd7a43d0b9db)
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/d634294e-b671-42c3-ab0b-bec5ff1280c6)
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/385b495f-e0ca-4166-8bcb-e4db8e08514a)
### 🎓 Spring 서버와 기존 Node.js 서버 성능 비교
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/1b04e5f4-1700-41ea-bafb-02ccfbc1cb06)
> 동시에 병렬로 600개의 동일한 요청이 왔을 때, 각 서버별 처리 속도를 비교하기 위해 시간을 측정하였다.<br>
> 컴퓨터공학과의 한 학년을 어림잡아 150명이라 했을 때, 4학년 전원은 600명이라고 하였다.<br>
> 총 100번의 시도를 했을 때 평균 처리 시간은 다음과 같다<br><br>
> Spring: 15 sec<br>
> Node.js: 12 sec<br><br>
> 결과 분석:<br>
> 정확도가 더 상승하고 그에 따라 더 많은 로직이 들어갔음에도<br>
> 평균 약 3초의 차이는 기존 Node.js 서버와 성능이 동등하거나 그 이상이라고 평가된다.<br>
> 현실에서 모든 컴퓨터공학과 학생들이 요청을 했을 때라는 worst case에서 이러한 결과는<br>
> 충분히 합리적인 결과값으로 평가 된다.


### 🎓 Test DataSet 생성
> 관리자 페이지 중 dataset 페이지에서, 관리자로부터 생성할 DataSet의 개수, 입학년도, 이수학기 수, 이수학기 마다의 
> 이수 강의 정보를 입력받아 2017~2023년의 강의 목록 엑셀 파일에서 랜덤으로 행을 추출하여 결과적으로 실제로 입력 받는
> 학생 성적 정보 엑셀 파일과 format이 동일한 엑셀 파일을 생성한다.

### 🎓 Z3 Solver
> 년도 별 졸업요건, 신설과목, 학수번호 변경 정보로 솔버 객체 생성 후 학생 성적 파일을 입력으로 받아 각 졸업요건 충족 사항과 이수해야 하는 강의들을 출력한다.

### 🎓 Github - Jenkins - SonarQube
![image](https://github.com/CSID-DGU/2023-1-OSSP1-1921-5/assets/127164905/d4fc83d7-3715-43c6-810b-96a72b560728)
> 1. Github에 프로젝트 진행한 것을 commit, push하면 Github의 web-hook을 통해 내용을 Jenkins에 전송
> 2. Jenkins pipline을 통해 자동으로 빌드 (우리 팀은 수동으로 수정해 활용)
> 3. 빌드된 내용은 Doker내의 SonarScanner에서 설정한 내용을 통해 SonarQube로 이동해 정적 분석 수행

### ⚙️ Setup
```
1. 프로젝트 clone
$ git clone 
3. 프로젝트 폴더로 이동
$ cd 
4. 패키지 설치
$ npm install
5. 앱 실행
$ npm start
6. 파이썬 서버 실행
$ python testDataSet/createDataset.py
```
### 🛠️ Tools
<span><img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=github&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/VisualStudioCode-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/React-61DAFB?style=flat-square&logo=react&logoColor=black"/></span>
<span><img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black"/></span>
<span><img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=flat-square&logo=intellij-idea&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=Docker&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/Jenkins-D24939?style=flat-square&logo=Jenkins&logoColor=white"/></span>
<span><img src="https://img.shields.io/badge/SonarQube-4E9BCD?style=flat-square&logo=sonarqube&logoColor=white"/></span>


<!-- <span><img src="https://img.shields.io/badge/AWS-FF9900?style=flat-square&logo=AmazonAWS&logoColor=232F3E"/></span> -->
