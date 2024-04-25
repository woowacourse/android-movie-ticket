# android-movie-ticket

# 🎞️ 도메인 기능 명세

### 영화 (Movie)
- [x] 아이디
- [x] 제목
- [x] 포스터
- [x] 상영 기간
- [x] 상영 시간
  - [x] 평일은 오전 10시부터 자정까지 두 시간 간격으로 상영한다
  - [x] 주말은 오전 9시부터 자정까지 두 시간 간격으로 상영한다
- [x] 줄거리

### 좌석 (Seat)
- [x] 좌석 번호
  - [x] 행(알파벳)과 열(숫자)을 가진다
- [x] 좌석 등급 (Enum)
  - [x] 등급과 가격을 가진다

### 선택된 좌석 (Seats)
- [x] 선택된 좌석들을 관리한다

### 티켓 (Ticket)
- [ ] 영화 제목
- [ ] 상영 일시
- [ ] 좌석 번호 
- [ ] 좌석별 가격
- [x] 좌석별 가격에 따른 총 금액 계산

<br>

# 📱안드로이드 기능 명세

## 상영표 화면 (ScreeningTableActivity)
### 🎨 View
- [x] 액션바
  - [x] 제목
- [x] 영화 리스트뷰
  - [x] 제목
  - [x] 상영 기간
  - [x] 러닝타임
  - [x] 포스터
  - [x] 지금 예매 버튼

### ⚙️ 기능
**상영표**
- [x] 리스트 뷰는 영화 종류를 보여준다
- [x] 상영표에서 지금 예매 버튼을 누르면 예매 화면으로 이동한다
  - [x] 이동 시 선택된 아이템에 해당하는 영화 정보를 전달한다
---
## 예매 화면 (ReservationActivity)
### 🎨 View
**영화 정보**
- [x] 액션바
  - [x] 제목
  - [x] 뒤로 가기 버튼
- [x] 포스터
- [x] 제목
- [x] 상영 기간
- [x] 러닝타임

**예매 정보 선택**
- [x] 액션바
  - [x] 제목
  - [x] 뒤로 가기 버튼
- [x] 상영일 선택 Spinner
- [x] 상영 시간 선택 Spinner
- [x] 줄거리
- [x] 예매 장수
- [x] 예매 장수 +, - 버튼
- [x] 좌석 선택 버튼

### ⚙️ 기능
**액션바**
- [x] 뒤로 가기 버튼을 누르면 상영표 화면으로 이동한다

**영화 정보**
- [x] 영화 정보를 영화 예매 홈 화면으로 부터 전달 받아 보여준다
  - [x] 포스터
  - [x] 제목
  - [ ] 상영 일시
  - [x] 상영 시간
  - [x] 줄거리

**상영 일시 및 인원 선택**
- [x] 상영일을 선택할 수 있다
- [x] 상영 시간을 선택할 수 있다
- [x] 마이너스 버튼을 1씩 티켓 수가 감소한다
  - [x] 최소 1장 이상 구매 가능 (범위를 벗어나면 알려준다)
- [x] 플러스 버튼을 누르면 100보다 클 수 없음
  - [x] 최대 20장 까지만 구매 가능 (범위를 벗어나면 알려준다)

**좌석 선택 버튼**
- [x] 영화 예매에서 좌석 선택 버튼을 누르면 영화 좌석 선택 화면으로 이동한다
  - [x] 이동 시 티켓 및 영화 정보 전달
---
## 좌석 선택 화면 (SeatSelectActivity)
### 🎨 View
- [ ] 액션바
  - [x] 제목
  - [ ] 뒤로 가기 버튼
- [x] 스크린 
- [x] 좌석 선택 (TableLayout)
- [x] 영화 제목
- [X] 가격
- [X] 확인 버튼
- [ ] 예매 확인 (Dialog)
  - [ ] 취소 버튼
  - [ ] 예매 완료 버튼

### ⚙️ 기능
**액션바**
- [ ] 뒤로 가기 버튼을 누르면 예매 화면으로 이동한다

**좌석 선택**
- [x] 좌석 배치도를 보여준다
- [x] 좌석을 선택하면 배경색이 바뀐다
  - [x] 선택된 좌석을 재선택하면 선택이 해제된다
- [x] 선택한 좌석 수를 반영한 최종 가격이 표시된다
- [x] 1, 2행은 B등급, 보라색 글자로 표시한다
- [x] 3, 4행은 S등급, 초록색 글자로 표시한다
- [x] 5행은 A등급, 파란색 글자로 표시한다 (12,000원)

**확인 버튼**
- [x] 확인 버튼의 기본 상태는 비활성화이다
  - [ ] 비활성화 상태에서 버튼을 누르면 좌석을 에러 토스트를 띄워준다
- [x] 선택한 인원 수와 선택한 좌석의 수가 동일할 때만 확인 버튼을 활성화한다
  - [ ] 확인 버튼을 누르면 예매 확인 다이얼로그를 띄운다

**예매 확인 다이얼로그**
- [ ] 예매 확인 다이얼로그는 취소, 예매 완료 버튼을 가지고 있다
  - [ ] 취소 버튼을 누르면 좌석 선택 화면에서 다이얼로그만 사라진다
  - [ ] 예매 완료 버튼을 누르면 티켓 화면으로 이동한다
    - [ ] 이동 시 티켓을 전달한다

---

## 티켓 화면 (TicketActivity)
### 🎨 View
- [x] 액션
  - [x] 제목
  - [x] 뒤로 가기 버튼
- [x] 예매 취소 안내 문구
- [x] 제목
- [x] 상영일
- [x] 상영 시간
- [x] 예매 인원 수
- [x] 예매 좌석
- [x] 가격

### ⚙️ 기능
**액션바**
- [x] 뒤로 가기 버튼을 누르면 상영표 화면으로 이동한다

**티켓 정보**
- [x] 예매 취소 안내 문구를 보여준다
- [ ] 티켓 정보를 보여준다
  - [x] 제목
  - [ ] 상영 일시
  - [x] 관람 인원
  - [ ] 좌석 번호
  - [x] 결제 금액
