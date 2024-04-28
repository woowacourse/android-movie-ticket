# android-movie-ticket

## View 기능 명세
### 영화 목록
- [x] 툴바
  - [x] 제목 
- [x] 영화 리스트뷰
  - [x] 제목
  - [x] 상영일
  - [x] 러닝타임
  - [x] 포스터  
  - [x] 지금 예매 버튼

### 영화 상세
- [x] 툴바
  - [x] 제목
  - [x] 뒤로 가기 버튼
- [x] 포스터
- [x] 제목
- [x] 상영일
  - [x] ex) 2024.3.1 ~ 2024.3.31
- [x] 러닝타임
- [x] 줄거리
- [x] 예매 장수
- [x] 예매 장수 +, - 버튼
- [x] 상영일 목록
  - [x] ex) 2024.03.01
- [x] 러닝타임 목록
  - [x] ex) 09:00

### 영화 예매 - 완료
- [x] 툴바
  - [x] 제목
  - [x] 뒤로 가기 버튼
- [x] 제목
- [x] 상영일
- [x] 가격
- [x] 예매 인원 수
- [x] 예매 취소 안내 문구

### 좌석 선택
- [x] SCREEN
- [x] 좌석표 
- [x] 영화 제목
- [x] 총 가격
- [x] 확인 버튼

## 도메인 기능 명세

### 티켓
- [x] 티켓 수 증가
- [x] 티켓 수 감소
- [x] 총 티켓 가격 계산
- [x] 상영 일 업데이트
- [x] 상영 시간 업데이트
- [x] 상영 일들 얻기
- [x] 상영 시간들 얻기

### 영화 목록 화면
- [x] 리스트 뷰는 영화 종류를 보여줌
- [x] 영화 목록에서 지금 예매 버튼을 누르면 영화 예매 화면으로 이동
  - [x] 이동 시 선택된 아이템에 해당하는 영화 정보 전달

### 영화 상세 화면
- [x] 영화 예매에서 좌석 선택 버튼을 누르면 좌석 선택 화면으로 이동
  - [x] 티켓 전달 
    - [x] 티켓 매수
    - [x] 상영 날짜 전달
    - [x] 상영 시간 전달
  - [x] 영화 정보 전달
- [x] 영화 정보 받아서 보여줌
- [x] 마이너스 버튼을 누르면 1보다 작을 수 없음
  - [x] 범위를 벗어나면 알려준다 
- [x] 플러스 버튼을 누르면 100보다 클 수 없음
  - [x] 범위를 벗어나면 알려준다
- [x] 뒤로 가기
- [x] 주말, 평일별 러닝타임
- [x] 인원, 날짜 선택후에 좌석을 고를 수 있음

### 좌석 선택 화면
- [x] 예약 하려는 영화 타이틀 보여주기
- [x] 선택된 좌석 총 가격 계산
- [x] 좌석을 티켓 수 만큼 선택후에 확인 버튼 활성화
- [x] 선택된 좌석 총 가격 계산해서 보여주기
- [x] 좌석 선택시 색상 변경 구현
- [x] 확인 선택 시 다이얼로그 띄우기
- [ ] 뒤로가기 구현
- [x] 다이얼로그에서 예매 완로 클릭시 예매 완료 화면으로 이동
  - [x] 영화 제목
  - [x] 상영 날짜
  - [x] 상영 시간
  - [x] 인원 수
  - [x] 좌석 위치
  - [x] 총 가격
 
### 영화 예매 완료 화면
- [x] 티켓 총 매수에 따른 금액 계산
- [ ] 영화 예매 정보 받아서 보여줌
- [x] 뒤로 가기
