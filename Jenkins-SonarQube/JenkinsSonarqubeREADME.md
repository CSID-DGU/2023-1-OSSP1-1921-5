
<h1>Github-Jenkins-Sonarqube Code Static Analysis System Using Docker</h1>

1. 이론
   
   각 프레임워크에 대해 간단히 소개와 설명을 하면, 깃허브는 프로젝트 관리 및 협업 프레임워크이며, 젠킨스는 협업을 하는 과정에서 빌드과정을 해주는 프레임워크이며, 소나큐브는 코드의 정적 분석을    통해 여러 버그나 보안 이슈를 확인해주는 프레임워크이다.

2. 필요성
   
   이번 공개SW프로젝트를 진행할때 프론트, 백엔드, solver등과 같이 여러 분야를 나누어 진행하기 때문에 빌드를 자동화하고 그에 따른 오류와 보안 이슈를 분석하여 해결하는 작업이 필요하다고 판단하
   여  깃허브 - 젠킨스 - 소나큐브 연동을 하였다.
   이 시스템을 통해 소나큐브에서 분석한 여러 취약점에 대해 보안적으로 개선해나가고, 팀프로젝트의 코드 개발과정과 통합과정에서 일어나는 여러 번거로움을 해결 할 수 있을 것으로 기대된다.

3. 작동 과정
   ![unnamed](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/2a695c14-83d8-450d-b5ca-0b5407c3c46d)
   a. 먼저 깃허브에 프로젝트 진행한 것을 커밋하고 푸쉬하게 되면, 깃허브의 web-hook을 통해 내용들을 jenkins에 전송한다.
   b. jenkins에 내용이 전달되면 jenkins pipeline을 통해 자동으로 빌드가 된다. (로컬 환경이라는 한계로 자동화 대신 수동으로 후에 수정함)
   c. 빌드 된 내용은 Docker내의 SonarScanner에서 설정한 내용을 통해 SonarQube로 이동하여 정적 분석이 이루어진다.
   d. 분석된 내용을 다시 Jenkins를 통해 Github에게 comment된다 (수동 빌드 과정에서 삭제함 - 어떤 것을 수정했는지 보기 어렵기 때문)

5. 구현
   Jenkins와 SonarQube는 모두 Docker 이미지를 통해 컨테이너화를 하였습니다.
   Docker를 사용한 이유는 각 프레임워크를 독립적으로 사용해 안정성이 있으며, 여러 프레임워크를 연동하고 통합하는 데 있어 가상환경이 통합에 유리하기 때문에 사용하였다.
   
   ![unnamed (1)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/d48530fb-3c9c-4c4c-87ac-29ab97a973b9)
   1)Jenkins 설정
       Jenkins에서는 먼저 Github에 있는 내용을 가져오는 작업부터 진행한다.
 		   ![unnamed (2)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/115e969d-d5c3-497d-8d9a-d249214b0c9f)

       Git - Jenkins 관련 연동 플러그인들을 설치 후에 Github Repositories의 토큰을 GitHub Jenkins Server에 설정하고 경로를 지정해준다.
      ![unnamed (3)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/9e3188a8-72b8-4d6d-99f7-51a37b39191d)

      ![unnamed (4)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/0c7ca5cd-d686-4305-88a6-1d262a1443cb)


       또한 Jenkins는 JDK 기반으로 작동하기 때문에 JDK의 환경변수 설정을 Jenkins와 연동하는 과정을 거쳤다. 
       이러한 과정을 거치고 젠킨스에서 빌드 과정을 진행하면 아래와 같이 변경사항과 빌드된 내역이 나오게 된다.
      ![unnamed (5)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/929bce6f-4711-4c63-81bd-13d0d774bf0b)

       위와 같이 버전관리와 빌드를 체계적으로 할 수 있다.

   2)SonarQube와 SonarScanner
       SonarQube에서 코드를 받아와 정적 분석을 하기 위해서는 빌드된 파일이 필요하며 이는 Jenkins에서 SonarScanner 플러그인을 통해 사용된다. SonarScanner는 Jenkins의 빌드 파일을 스캔하고         정의된속성에 맞게 스캔하여 SonarQube로 넘겨주는 역할을 한다.
      ![unnamed (6)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/eb44a5d7-46be-4527-a184-a7bc932b0a49)

       그 다음으로는 SonarQube Server를 지정해주어야 한다.
       SonarQube Token을 발급받은 뒤 스캔한 내용을 어디로 보낼지 설정해준다. SonarQube Local url을 입력해주고 토큰을 설정해준다.
      ![unnamed (7)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/4addeb04-e00f-4df7-84b5-a725df1d7a97)

       이와 자동화 파이프라인을 구축하고 컨테이너에서 환경변수와 여러 설정을 하게 되면 Jenkins와 SonarQube가 연결되어 자동화가 가능해진다.
      ![unnamed (8)](https://github.com/CSID-DGU/2023-1-OPPS1-1921-5/assets/103040750/c17c5377-e321-45b1-a81b-82f28b1347c9)


       빌드를 하게되면 바로 SonarQube로 빌드파일이 넘어가 정적분석을 하게된다.

다른 여러 파이프라인이나 설정, 타이머 같은 파일들은 모두 Jenkins-SonarQube 파일 안에 존재합니다.
