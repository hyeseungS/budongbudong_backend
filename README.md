# 여섯번째 관통 프로젝트 


# 팀원 소개

| 팀원   |
| ------ |
| 이가영 |
| 신혜승 |

# 1. 기능 구현 목록 
| 난이도 | 구현 기능                           | 완성 여부 |
| :----: | :---------------------------------- | :-------: |
|  기본  | 메인페이지           |    ⭕     |
|  기본  | 회원관리 페이지  |    ⭕     |
|  기본  | 로그인/로그아웃 페이지             |    ⭕     |
|  기본  | 실거래가 검색, 결과 페이지   |    ⭕      |
|  추가 | 비밀번호 찾기/사이트맵/메뉴구성 화면   |    X       |
|  추가  | 관심지역 동네 업종 정보 관심지역 대기오염 정보   |    X      |
|  심화  | 웹 사이트 소개/ 공지사항 관리 화면     |   X     |



- 로그인, 회원가입 이외의 API는 토큰 삽입 필요 (Bearer [토큰] 형식)
![image](/uploads/90a8ee430e2a9f73d1413857bf22af30/image.png)

# 2. 기능 구현 시연
## 2.1. 메인페이지  
![image](https://github.com/gayoung1115/Algorithm/assets/63089026/2251a558-755c-47c1-91d2-63a3dd2645eb)

## 2.2. 회원관리 페이지
### 회원정보등록
- JWT 기반 회원가입

![image](/uploads/ef0e6a094fb4837e5f997e397050d782/image.png)
![image](/uploads/16663ff1039e832229375772c00a33b2/image.png)
![image](/uploads/93fb352eabef2ef37136795401c8d0a8/image.png)

### 회원정보검색
- header에 Bearer [토큰] 넣어 회원 정보 검색

![image](/uploads/54166428ed2e9335c4f05ac587061032/image.png)

### 회원정보수정
- name을 ssafy -> hellossafy로 변경

![image](/uploads/d9eb5f1134ee289aaec87f320eda2f94/image.png)
![image](/uploads/e6de731d75a4e58cdfbedb1f051bc8a2/image.png)
![image](/uploads/c4e26a1578a31d4f913c9e5b5272c00a/image.png)

### 회원정보삭제
![image](/uploads/99088f6231672a6afee4d17800e852f1/image.png)
![image](/uploads/83c39b2b6b2991083a78e91194ab8334/image.png)


## 2.3. 로그인/로그아웃
### 로그인/로그아웃
- JWT 기반 로그인

![image](/uploads/e208176ea93e71b3880001a7e79b8e16/image.png)
![image](/uploads/891fd91bac61d02be7d667dcf44ac58b/image.png)
- Redis 토큰 삭제를 통한 로그아웃

![image](/uploads/ade75802dce586a280009e640503f2a1/image.png)


## 2.4.  실거래가 검색 결과 페이지
- 시/도, 구/군, 동, 거래 년도, 거래 월로 검색

![image](/uploads/5abd4ad553413691e714732e91910a88/image.png)
![image](/uploads/28037c929fcfd27e63115d3a756c03c3/image.png)

