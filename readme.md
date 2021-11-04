- 스터디용 간단한 프로젝트, 샘플 리포지토리입니다.
- 구현하기 어려운 내용이 있다면, 샘플 리포지토리를 참고해 보도록 합시다.
- 단, 이 프로젝트는 일회성 구현이 아니라, 기능 확장이 있을 예정입니다.

# Project #1. 간단한 게시판 만들기

- Spring CRUD를 모두 체험해보기 위해, 간단한 게시판을 만들어 보자

1. 회원가입 페이지가 제공되어야 한다.
    - 회원가입은 ID/비밀번호만 입력받기로 한다.
    - 주의 : 회원 탈퇴, ID/비밀번호 변경은 제공되지 않는다.
2. 글 등록 페이지가 제공되어야 한다.
    - 글 등록시에는, 작성자를 입력할 수 있어야 한다.
    - 작성자는 현재 서버에 회원 가입되어 있는 모든 사용자 중 한 명을 고를 수 있도록 제공한다.

3. 글 조회 페이지가 제공되어야 한다.
    - 글 조회시, 현재 서버에 저장되어 있는 모든 글을 보여줘야 한다.

4. 글 수정 페이지가 제공되어야 한다.
    - 글 수정시, 현재 서버에 저장되어 있는 글이 수정되어야 한다.

5. 글 삭제 페이지가 제공되어야 한다.
    - 글 삭제시, 현재 서버에 저장되어 있는 글이 삭제되어야 한다.

6. 예제를 단순화하기 위해, 데이터베이스는 사용하지 않는다.
    - HashMap 사용해서 메모리에 저장할 것!

### 2주차 추가 요구사항 :
7. 기존 HashMap에 저장하던 데이터를 데이터베이스에 저장할 수 있을 것

### 3주차 추가 요구사항 :
8. CI를 추가하여 빌드시 TEST가 실행되도록 할 것

*** 

- 폴더 구조를 보면, Src 폴더 아래 main과 test 폴더가 있습니다.
  - Main 폴더는 실제 구현 코드들이 들어가 있습니다.
  - Test 폴더는 우리가 작성한 코드들이 잘 실행되는지, 테스트하는 코드가 들어가 있습니다.


- Main 폴더 아래를 내려가다 보면, java 폴더와 resources 폴더가 있습니다.
  - Java 폴더는 Spring 실행과 관련된 Java 코드들이 들어갑니다.
  - Resource 폴더는 Spring 서버가 보여줄 html 파일 등이 들어갑니다.


- Resource 폴더 아래에는 static 폴더와 templates 폴더가 있습니다.
  - Static 폴더 안에서, index.html 파일을 찾을 수 있습니다.
    - Static 폴더 안에 있는 index.html 이란 파일은, Welcome page를 구성하는 특별한 파일입니다.
    - 즉, 사이트에 접근할때 별다른 입력을 하지 않았다면, /static/index.html 파일을 보여줍니다.
  - templates 폴더는, 이후 Spring Java 코드에 사용할 여러 html 코드들이 들어갑니다.
    - 정확히는 Thymeleaf 라는 Template Engine을 사용하였는데, HTML 안에 변수 등을 동적으로 넣을 수 있습니다.
    - 동적이라는 말은, A라는 유저가 접속했을 때, B라는 유저가 접속했을 때, 다른 데이터를 보여줄 수 있단 뜻입니다.

- Java/forum 아래에 controller, domain, repository, service, ForumApplication.java 가 있습니다.
  - ForumApplication은 Spring의 시작 파일입니다.
    - Spring 실행 시, ForumApplication 내부의 아래 코드가 실행됩니다.
    - ```public static void main(String[] args) {
      SpringApplication.run(ForumApplication.class, args);
      }
      ```
    - Controller 폴더 내 코드들은, 들어온 HTTP Request를 적합한 서비스를 호출하여 처리하고, 적합한 View (HTML, 눈에 보이는 것) 과 연결해주는 역할을 합니다.
    - Service 폴더 내 코드들은, 비즈니스 로직을 구현합니다.
      - 비즈니스 로직이란, 회원가입, 글 생성, 글 삭제와 같이 실 업무에 필요한 데이터를 처리하는 과정입니다.
    - Repository 폴더 내 코드들은, 데이터를 데이터 저장소에 넣고 빼는 역할을 합니다.
      - 이 때, Interface를 사용하여 이후 수정이 유연하도록 처리하였습니다.
      - Interface를 사용하는게 왜 수정에 유연해 지는가? 는 다음에 확장/기능변경을 진행하며 체험해 볼 예정입니다.
    - Domain 폴더 내 코드들은, 비즈니스에 필요한 각 개체를 나타냅니다.
      - 예를 들어 지금과 같은 경우, 유저(User), 작성글(Post) 로 구분되었습니다.
  

- 샘플 코드에는 테스트가 작성되어 있지만, 요구사항에 테스트 작성은 없으므로 테스트 작성은 선택 사항입니다.
  - 단, 이후 이 프로젝트를 기반으로 확장을 할 것임을 염두하시면 좋을 것 같습니다.
