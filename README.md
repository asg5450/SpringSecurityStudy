# SpringSecurityStudy

Spring Security 공부 과정 - 수업 목차별 비교 목적

## 📌 프로젝트 개요

**Spring Security**를 학습하기 위해 수업 목차별로 내용을 정리하고 형상관리를 통해 전/후 비교하는 목적

---

## 📂 디렉토리 구조

```plaintext
SpringSecurityStudy/
├── spring_security_study/    # 스프링 시큐리티 학습 코드 및 자료
│   ├── src/                  # 소스 코드 디렉토리
│   │   ├── main/             # 메인 소스 코드
│   │   │   ├── java/         # 자바 소스 파일
│   │   │   └── resources/    # 리소스 파일 (설정, 템플릿 등)
│   │   └── test/             # 테스트 코드
│   ├── build.gradle          # Gradle 빌드 파일
└── README.md                 # 프로젝트 설명 파일
```
## 🛠 사용된 기술 스택

### 📌 Backend
- **Spring Boot**: 2.7.9
- **Spring Security**: 인증 및 인가 처리
- **JPA & Hibernate**: ORM 기반 데이터베이스 관리
- **OAuth2**: 소셜 로그인 및 권한 위임
- **Gradle**: 빌드 및 의존성 관리

### 📌 Frontend (예정)
- **Thymeleaf**: 서버 사이드 템플릿 엔진
- **JavaScript**: 클라이언트 사이드 동작

### 📌 Database
- **H2 Database**

---

## 📖 학습 내용

이 프로젝트에서는 다음과 같은 **Spring Security의 주요 개념과 기능**을 다룹니다:

### 🔹 인증(Authentication)
- 사용자의 신원을 확인하는 과정
- ID & 비밀번호 기반 인증

### 🔹 인가(Authorization)
- 인증된 사용자의 권한 부여
- 역할(Role) 기반 접근 제어
- RBAC(Role-Based Access Control) 적용

### 🔹 보안 구성(Security Configuration)
- `SecurityFilterChain`을 활용한 보안 설정
- HTTP 요청별 접근 권한 설정

### 🔹 사용자 및 권한 관리(User and Authority Management)
- 사용자 정보 관리
- 권한(Role) 기반 정책 적용

### 🔹 보안 필터(Security Filters)
- Spring Security 기본 필터 체계
- 커스텀 필터 적용

### 🔹 OAuth2
- OAuth2 프로토콜을 활용한 인증 방식

---

## 📚 참고 자료

- [Spring Security 공식 문서](https://spring.io/projects/spring-security)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)

---
