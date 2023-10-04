# KMU Art Education Library
> 국민대 미술교육원 도서 대여 프로젝트

## 🖥️ 프로젝트 활용 기술 
<p>
	
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)<img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<br>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"><img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<br>![Ubuntu](https://img.shields.io/badge/Ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white)![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)<img src="https://img.shields.io/badge/aws-232F3E.svg?&style=for-the-badge&logo=amazonaws&logoColor=white">

</p>

## ✍🏻 ERD 

![KMU ERD](https://github.com/ramloper/kookmin/assets/114231216/cde14e22-eed2-49c1-adcd-78acd165026d)

## 🌟 아키텍쳐 
![아키텍쳐](https://github.com/ramloper/kookmin/assets/114231216/5bf8ba62-5c99-4ae1-a4fb-b9515d156d50)



## 💡 주요 기능
### 1. 로그인, 회원가입
- SSR 방식으로 프로젝트를 진행, Spring-Security를 이용해 로그인 정보를 session에 저장하여 인증 및 인가를 구현
### 2. 회원 정보 수정
- DTO에 정보를 담아 학번, 비밀번호, 이름 수정 기능 
### 3. 도서 검색 / 도서 대여
- Spring Page와 BootStrap을 사용해 페이지 view 구현
- BootStrap DatePicker를 사용해 도서 대여시 Calender 활용하여 대여, 반납일 지정
- 현재 도서 검색 시 모든 글자가 일치한 경우에만 데이터를 가져올 수 있는데, 검색어가 포함되어 있는 경우에도 가져올 수 있도록 수정 예정
### 4. 도서 대여 승인
- 관리자가 도서 대여 승인이 NCloudApi를 연동하여 SMS 발송

## 사용 기술
|      사용 기술       | 기술 설명                                        |
|:----------------:|----------------------------------------------|
|       JPA        | 자바에서 객체와 관계형 데이터베이스를 매핑하기 위해 사용              |
| Spring Security  | 인증, 인가를 관리하기 위해 사용                           |
|      Docker      | 애플리케이션을 컨테이너화하여 같은 환경에서 배포 및 실행하기 위해 사용      |
|     Jenkins      | CI/CD (추후 확장성을 생각해서 github actions보다 좋다고 판단) |
|      Lombok      | 반복적인 코드 작성(getter, constructor등)을 줄이기 위해 사용  |
|      MySQL       | 가장 익숙한 RDBMS로, 데이터 저장 및 검색을 위해 사용            |
|     BootStrap    | View페이지 구현시 디자인 템플릿 활용             |
|     Thymeleaf    | 컨트롤러가 전달하는 데이터를 이용하여 동적으로 화면을 구성하기 위해 사             |

<br/>
