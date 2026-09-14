# MyBatis 학생·강좌 수강 관계 매핑

<!-- workspace-readme-learning:start -->
## 파일과 연결한 학습 안내

Student와 Course를 중개 수강 관계로 연결하고 EnrollMapper에서 등록·목록·중첩 결과를 처리하는 프로젝트입니다. 현재 EnrollService에는 Transactional 애노테이션이 없으므로 선언적 트랜잭션이 구현된 예제로 단정하지 않습니다.

### 주요 파일과 역할

| 파일 | 역할과 읽을 내용 |
| --- | --- |
| [pom.xml](<pom.xml>) | Maven 의존성·플러그인·패키징 설정 |
| [src/main/java/org/example/mybatis3/controller/MainController.java](<src/main/java/org/example/mybatis3/controller/MainController.java>) | 요청 매핑·입력 바인딩과 응답 처리 — `index`, `addStudent`, `addCourse` |
| [src/main/java/org/example/mybatis3/Mybatis3Application.java](<src/main/java/org/example/mybatis3/Mybatis3Application.java>) | Spring Boot 애플리케이션 진입점 — `main` |
| [src/main/java/org/example/mybatis3/mapper/EnrollMapper.java](<src/main/java/org/example/mybatis3/mapper/EnrollMapper.java>) | SQL 호출과 Java 파라미터·결과 매핑 |
| [src/main/java/org/example/mybatis3/service/EnrollService.java](<src/main/java/org/example/mybatis3/service/EnrollService.java>) | 업무 처리와 외부 의존성 호출 — `createStudent`, `createCourse`, `findAllStudents` |
| [src/main/resources/mappers/EnrollMapper.xml](<src/main/resources/mappers/EnrollMapper.xml>) | MyBatis SQL·파라미터·결과 매핑 정의 |
| [src/main/resources/sql/schema.sql](<src/main/resources/sql/schema.sql>) | 테이블·키·제약 조건 정의와 데이터 준비 SQL |
| [HELP.md](<HELP.md>) | 설계·학습·운영 내용을 설명하는 문서 |
| [src/main/java/org/example/mybatis3/dto/CourseWithStudentsDTO.java](<src/main/java/org/example/mybatis3/dto/CourseWithStudentsDTO.java>) | 입력·응답 데이터의 구조 |
| [src/main/java/org/example/mybatis3/dto/StudentWithCoursesDTO.java](<src/main/java/org/example/mybatis3/dto/StudentWithCoursesDTO.java>) | 입력·응답 데이터의 구조 |
| [src/main/java/org/example/mybatis3/entity/Course.java](<src/main/java/org/example/mybatis3/entity/Course.java>) | Java 타입과 동작 정의 |
| [src/main/java/org/example/mybatis3/entity/Student.java](<src/main/java/org/example/mybatis3/entity/Student.java>) | Java 타입과 동작 정의 |
| [src/main/java/org/example/mybatis3/ServletInitializer.java](<src/main/java/org/example/mybatis3/ServletInitializer.java>) | Java 타입과 동작 정의 |
| [src/main/webapp/WEB-INF/views/index.jsp](<src/main/webapp/WEB-INF/views/index.jsp>) | MyBatis 화면 — 서버 모델을 표시하는 JSP |
| [src/test/java/org/example/mybatis3/Mybatis3ApplicationTests.java](<src/test/java/org/example/mybatis3/Mybatis3ApplicationTests.java>) | 테스트 코드 |

### 실행과 설정 확인

- [pom.xml](<pom.xml>)의 의존성과 패키징을 기준으로 구성합니다. 선언된 Java 설정은 17입니다.
- 저장소 루트에서 `.\mvnw.cmd spring-boot:run`으로 Spring Boot를 실행합니다.
- 환경 설정: [src/main/resources/application-dev.properties](<src/main/resources/application-dev.properties>), [src/main/resources/application.properties](<src/main/resources/application.properties>).
- 코드·설정에서 참조하는 환경 변수 이름: `DB_HOST`, `DB_NAME`, `DB_PASSWORD`, `DB_PORT`, `DB_USERNAME`. 기본값과 필수 여부는 각 참조 위치에서 확인합니다.

### 관련 PDF와 보충 설명

- [7/22 강의](<../260629_ex/새 폴더/7-22/README.md>): 동적 SQL·association·collection과 중첩 조회 비용을 연결합니다.
- [7/20 강의](<../260629_ex/새 폴더/7-20/README.md>): JdbcTemplate·RowMapper·Repository와 서비스 트랜잭션을 연결합니다.

이 링크는 구현을 이해하기 위한 관련 기초 자료입니다. 해당 강의가 이 저장소의 모든 기능이나 이후 버전의 API를 설명한다는 뜻은 아닙니다.

### 읽는 순서와 복습

- 조건 조립 → JOIN·중첩 select → 객체·컬렉션 매핑을 추적합니다. 조건 없음과 빈 IN 목록, 부모 행별 추가 쿼리 수를 확인합니다.
- Controller의 입력 → Service의 업무 단위 → Repository의 SQL·매핑을 읽습니다. 함께 성공해야 하는 변경을 묶는 경계와 연결 반납 시점을 설명합니다.

이 코드에서는 Student·Course와 EnrollMapper의 수강 관계를 중심으로 다대다 매핑 및 서비스 호출을 확인합니다. 트랜잭션의 실제 적용 여부는 서비스의 애노테이션과 호출 경로를 기준으로 읽습니다.

테스트 소스가 포함되어 있습니다. 이 문서 수정 작업에서는 애플리케이션·DB·외부 API 테스트를 실행하지 않았으므로 실행 결과를 보장하는 기록은 아닙니다.

<!-- workspace-readme-learning:end -->

Student와 Course를 중개 수강 관계로 연결하고 EnrollMapper에서 등록·목록·중첩 결과를 처리하는 프로젝트입니다. 현재 EnrollService에는 Transactional 애노테이션이 없으므로 선언적 트랜잭션이 구현된 예제로 단정하지 않습니다.

<!-- pdf-til-supplement:start -->
## TIL 부연 설명 — PDF와 연결하기

기존 실습 내용을 이해하기 위한 PDF 기반 부연 설명이다. 아래 예시는 개념을 설명하기 위한 것이며, 이 프로젝트에서 실행해 관찰한 결과와는 구분한다. 페이지 번호는 표지를 포함한 PDF 순서다.

함께 읽을 파일: [src/main/java/org/example/mybatis3/controller/MainController.java](<src/main/java/org/example/mybatis3/controller/MainController.java>) · [src/main/java/org/example/mybatis3/Mybatis3Application.java](<src/main/java/org/example/mybatis3/Mybatis3Application.java>) · [src/main/java/org/example/mybatis3/mapper/EnrollMapper.java](<src/main/java/org/example/mybatis3/mapper/EnrollMapper.java>)

### 동적 SQL은 최종 SQL까지 확인하기

동적 SQL은 입력에 따라 조건 조각을 조립한다. where는 조건이 있을 때 WHERE를 붙이고 앞쪽 연결자를 정리하며 set은 부분 수정의 쉼표 처리를 돕는다. 태그가 문법을 조립해 주어도 “조건이 하나도 없을 때 전체 조회·수정해도 되는가”라는 업무 판단까지 대신하지는 않는다.

**예시로 이해하기:** 검색어 없음, 검색어만 있음, 여러 조건 조합을 나누어 최종 SQL과 바인딩 값을 예상한다. IN 절의 컬렉션이 비었을 때 전체 조회로 바뀌지 않도록 의도를 정한다. 1:N 결과를 collection에 매핑할 때는 부모 식별자를 지정해 중복 행을 같은 부모로 묶는다.

근거: 322-2 MyBatis 동적 SQL과 연관관계 매핑 — [8쪽](<../260629_ex/새 폴더/7-21/322-2_MyBatis_동적_SQL과_연관관계_매핑.pdf#page=8>) · [10쪽](<../260629_ex/새 폴더/7-21/322-2_MyBatis_동적_SQL과_연관관계_매핑.pdf#page=10>) · [12쪽](<../260629_ex/새 폴더/7-21/322-2_MyBatis_동적_SQL과_연관관계_매핑.pdf#page=12>) · [16쪽](<../260629_ex/새 폴더/7-21/322-2_MyBatis_동적_SQL과_연관관계_매핑.pdf#page=16>)

### JdbcTemplate과 업무 트랜잭션의 경계

JdbcTemplate은 반복되는 JDBC 자원 관리와 예외 변환을 줄이고 RowMapper는 한 결과 행을 객체로 바꾼다. SQL과 매핑 규칙은 개발자가 작성한다. 여러 저장소 호출이 함께 성공해야 하는 업무에서는 서비스가 트랜잭션의 시작과 끝을 표현하는 위치가 된다.

**예시로 이해하기:** 이체 서비스가 출금·입금을 각각 호출할 때 둘 사이에 실패하면 함께 되돌아가야 한다. Repository 메서드 각각의 성공만 확인하면 업무 전체의 일관성을 놓칠 수 있다. 외부 HTTP 호출이나 파일 저장은 DB 트랜잭션으로 자동 복구되지 않는다.

근거: 321-2 Spring JDBC와 영속성 — [21쪽](<../260629_ex/새 폴더/7-20/321-2_Spring_JDBC와_영속성.pdf#page=21>) · [22쪽](<../260629_ex/새 폴더/7-20/321-2_Spring_JDBC와_영속성.pdf#page=22>) · [29쪽](<../260629_ex/새 폴더/7-20/321-2_Spring_JDBC와_영속성.pdf#page=29>) · [35쪽](<../260629_ex/새 폴더/7-20/321-2_Spring_JDBC와_영속성.pdf#page=35>) · [37쪽](<../260629_ex/새 폴더/7-20/321-2_Spring_JDBC와_영속성.pdf#page=37>)

<!-- pdf-til-supplement:end -->
