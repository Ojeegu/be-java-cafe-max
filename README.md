# be-java-cafe
### 마스터즈 2023 스프링 카페 프로젝트

💻 2023.03.27 ~ing

<br>

## Step 1. 회원가입 및 회원 목록 조회 기능 (Done)

----

### 기능요구사항
- [x] 사용자는 회원가입 폼으로부터 입력받은 데이터로 회원가입을 할 수 있어야 한다.
  - [x] 가입하기 페이지에서 회원 가입 폼을 표시한다.
  - [x] 개인정보를 입력하고 확인을 누르면 회원 목록 조회 페이지로 이동한다.
- [x] 사용자는 회원 목록 페이지에서 현재 가입된 전체 회원목록을 조회할 수 있어야 한다.
  - [x] 가입한 회원들의 목록을 출력한다.
- [x] 사용자는 회원 프로필 페이지에서 개별 회원 프로필 정보를 확인할 수 있어야 한다.

<br>

### 프로그래밍 요구사항
- [x] Lombok은 사용하지 않는다.
- [x] 별도의 데이터베이스는 사용하지 않는다.
- [x] 웹 템플릿은 Mustache를 추천한다. 원할 경우 Thymeleaf를 사용해도 무방하다.


<br>

### 기능별 구현요구사항
#### 1. 회원가입 기능
- [x] 사용자 관리 기능 구현을 담당할 UserController를 추가하고 애노테이션(@Controller)을 매핑한다.
- [x] 회원가입하기 요청(POST 요청)을 처리할 메소드를 추가(@PostMapping)하고 URL을 매핑한다.
- [x] User 클래스를 생성해 사용자가 전달한 값을 저장한다.
  - [x] 회원가입할 때 전달한 값을 저장할 수 있는 필드를 생성한 후 setter와 getter 메서드를 생성한다.
- [x] 사용자 목록을 관리하기 위해 JCF의 클래스를 활용한다.
- [x] 사용자 추가를 완료한 후 사용자 목록 페이지("redirect:/users")로 이동한다.

#### 2. 회원 목록 기능
- [x] Controller 클래스는 회원가입하기 과정에서 추가한 UserController를 그대로 사용한다.
- [x] 회원목록 요청(GET 요청)을 처리할 메서드를 추가하고 URL 매핑(@GetMapping)한다.
- [x] Model을 메서드의 인자로 받은 후 Model에 사용자 목록을 users라는 이름으로 전달한다.
- [x] 사용자 목록을 user/list.html로 전달하기 위해 메서드 반환 값을 "user/list"로 한다.
- [x] user/list.html에서 사용자 목록을 출력한다.

<br>

#### 3. 회원 프로필 정보 기능
- [x] user/list.html 파일에 닉네임을 클릭하면 프로필 페이지로 이동한다.
- [x] Controller 클래스는 앞 단계에서 사용한 UserController를 그대로 사용한다.
- [x] 회원프로필 요청(Get 요청)을 처리할 메서드를 추가하고 URL 매핑(@GetMapping)한다.
- [x] URL을 통해 전달한 사용자 아이디 값은 @PathVarialbe 애노테이션을 활용해 전달받는다.
- [x] List에 저장되어 있는 사용자 중 사용자 아이디와 일치하는 User 데이터를 Model에 저장한다.
- [x] user/profile.html에서는 Controller에서 전달한 User 데이터를 활용해 사용자 정보를 출력한다.

<br>

#### 4. HTML 중복 제거
- [x] 각 html의 header, navigation bar, footer 부분에서 중복 코드를 제거한다.
  - [x] index.html 중복 코드 제거
  - [x] /user/form.html 중복 코드 제거

<br>

## Step 2. 글쓰기 기능 

---

### 기능요구사항
- [x] 모든 사용자는 글 쓰기 기능으로 게시글을 작성할 수 있어야 한다.
- [x] 모든 사용자는 글 목록 조회 기능으로 게시글 목록을 볼 수 있어야 한다.
- [x] 모든 사용자는 게시글 상세 내용을 볼 수 있어야 한다.
- [x] (선택) 입력된 게시글을 수정할 수 있어야 한다.

<br>

### 프로그래밍 요구사항
- [ ] WebMVCConfigurer 적용
  - [ ] Adapter가 deprecated 된 이유와 해결책에 대해 생각 및 해결

<br>

### 기능별 구현요구사항
#### 1. 글 쓰기 기능
- [x] 게시글 기능 구현을 담당할 ArticleController를 추가하고 애노테이션(@Contoller)을 매핑한다.
- [x] 게시글 작성 요청(POST 요청)을 처리할 메서드를 추가하고 매핑(@PostMapping)한다.
- [x] Article 클래스를 생성해 사용자가 전달한 값을 저장한다.
- [x] 게시글 목록을 관리하는 컬렉션 클래스를 생성한 후 Article 인스턴스를 저장한다.
- [x] 게시글 추가 완료 후 메인페이지("redirect:/")로 이동한다.

<br>

#### 2. 글 목록 조회 기능
- [x] 메인 페이지(요청 URL : "/")를 담당하는 Controller의 메서드에서 게시글 목록을 조회한다.
- [x] 조회한 게시글 목록을 Model에 저장한 후 View에 전달한다.
  - [x] 게시글 목록은 게시글 작성시 생성한 컬렉션을 그대로 전달한다.
- [x] View에서 Model을 통해 전달한 게시글 목록을 출력한다.
  - [x] 게시글 목록 구현 과정은 사용자 목록을 구현하는 html 코드를 참고한다.

<br>

#### 3. 게시글 상세보기 기능
- [x] 게시글 목록의 제목을 클릭했을 때 게시글 상세 페이지에 접속할 수 있도록 한다.
  - [x] 게시글 상세 페이지 접근 URL은 "/post/{index}"와 같이 구현한다.
  - [x] 게시글 객체에 Id 인스턴스 변수를 추가하고 새로운 게시글마다 증가하도록 구현한다.
- [x] Controller에 상세 페이지 접근 메서드를 추가하고 URL은 /article/{index}로 매핑(@GetMapping)한다. 
- [x] 해당하는 데이터를 조회한 후 Model에 저장해 /post/show에 전달한다.
- [x] /post/show에서는 Controller에서 전달한 데이터를 활용해 html을 생성한다.
- [x] 게시글 수정 후 상세보기 페이지("redirect:/post/{index}")로 이동한다.

<br>

#### 4. 회원정보 수정 기능
- [x] 회원목록에서 회원가입한 사용자의 정보를 수정할 수 있다.
  - [x] 비밀번호, 이름, 이메일만 수정할 수 있으며, 사용자 아이디는 수정할 수 없다.
  - [x] 비밀번호가 일치하는 경우에만 수정 가능하다.
- [x] 개인정보수정 페이지는 "/user/{id}/form"와 같이 구현한다.
  - [x] URL 매핑 시 @PathVarialbe 애노테이션을 활용해 인자 값을 얻는다.
  - [x] {id}에 해당하는 User를 DB에서 조회한다(UserRepository의 findOne()).
- [x] UserController의 사용자가 수정한 정보를 User 클래스에 저장한다.
- [x] DB에서 조회한 User 데이터를 새로 입력받은 데이터로 업데이트한다.

<br>

Step 3.


<br>

## Step 4. 로그인
---

### 기능요구사항
- [x] 로그인, 로그아웃 기능 정상적으로 동작
- [x] 로그인 상태 : 상단메뉴 로그아웃, 개인정보수정 표시
- [x] 로그아웃 상태 : 상단메뉴 로그인, 회원가입 표시

<br>

### 프로그래밍 요구사항
- [x] Spring MVC에서 메서드 인자로 HttpSession을 이용해서 로그인 구현
- [x] 로그인 성공하는 경우 HttpSession에 로그인 정보 추가
  ```java
    session.setAttribute(“sessionedUser", user);

- [x] Spring Security 같은 별도 라이브러리 사용 금지
- [x] API가 아닌 템플릿 기반으로 구현

<br>

### 추가 구현요구사항
#### 개인정보 수정 기능 추가
- [x] 로그인한 사용자는 자신의 정보를 수정할 수 있어야 한다
  - [x] HttpSession에 저장된 User 데이터를 가져온다
  - [x] 로그인한 사용자와 수정하는 계정의 id가 같은 경우에만 수정하도록 한다
  - [x] 다른 사용자의 정보를 수정하려는 경우 에러 페이지를 출력한다
- [x] 이름, 이메일만 수정할 수 있으며 사용자 아이디는 수정할 수 없다
- [x] 비밀번호가 일치하는 경우에만 수정 가능하다


<br>

## Step 4. 로그인
---

### 기능요구사항
- [x] 로그인 하지 않은 사용자는 게시글의 목록만 볼 수 있다.
  - [x] 로그인 한 사용자만 게시글의 세부내용을 볼 수 있다.
- [x] 로그인 한 사용자만 게시글을 작성할 수 있다.
  - [x] 로그인 하지 않은 사용자가 글쓰기 페이지에 접근할 경우 로그인 페이지로 이동한다.
- [ ] 로그인 한 사용자는 자신의 글을 수정 및 삭제할 수 있다.
  - [ ] 수정/삭제 기능은 로그인 사용자와 글쓴이의 사용자 아이디가 같은 경우에만 가능하다.
  - [ ] 상황에 따라 "다른 사람의 글을 수정할 수 없다."와 같은 에러 메세지를 출력하는 페이지로 이동하도록 구현한다.

<br>

### 프로그래밍 요구사항
- [ ] 글쓴이 값은 User의 name 값을 가지는 것으로 구현한다.
- [x] 게시글 수정하기는 `@PutMapping`을 사용해 매핑한다.
  - [x] html에서 form submit을 할 때 `<input type="hidden" name="_method" value="PUT" />`과 같이 PUT method를 값으로 전송한다.
- [ ] 게시글 삭제하기는 `@DeleteMapping`을 사용해 매핑한다.
  - [ ] html에서 form submit을 할 때 `<input type="hidden" name="_method" value="DELETE" />`와 같이 PUT method를 값으로 전송한다.

<br>
