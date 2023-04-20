# android-movie-ticket

# 기능 목록
### Step 1
- [x] 레이아웃을 만든다.
  - 메인, 상세 정보, 예매 완료 페이지
  - 리스트 아이템
- [x] 메인 페이지에 영화 리스트가 보인다.
- [x] 영화를 선택하면 (리스트 아이템, 버튼) 상세 정보 페이지로 이동한다.
- [x] 상세 정보 페이지에서 예약할 인원을 선택한다.
- [x] 상세 정보 페이지에서 예매 완료를 선택하면 예매 완료 페이지로 이동한다.
- [x] 예매 완료 페이지에서 인원과 가격을 보여준다.

### Step 2
- [x] 상영 날짜 `범위`를 갖는다.
- [x] 영화를 볼 날짜를 선택한다. (`스피너` 이용)
- [x] 영화를 볼 시간을 선택한다. (`스피너` 이용)
  - [x] 평일 시간의 범위는 오전 9시부터 자정까지 2시간 간격이다.
  - [x] 주말 시간의 범위는 오전 10시부터 자정까지 2시간 간격이다.
- [x] 할인 조건에 따라 다른 할인 정책이 적용된다.
  - [x] 무비데이: 10, 20, 30일 -> 10% 할인
  - [x] 조조/야간: 11시 이전, 20시 이후 -> 2000원 할인
  - 무비데이 할인이 선적용, 중복 할인 가능
- [x] 화면이 가로로 전환되어도 데이터가 유지된다.
- [x] 액션바 back 버튼을 누르면 뒤로가기가 된다.

### Step 3
- [ ] 좌석 지정 레이아웃을 만든다. (TableLayout 이용)
- [ ] 좌석을 인원 수만큼 선택할 수 있다.
- [ ] 좌석을 인원 수만큼 선택하지 않으면 `확인` 버튼이 disabled 상태이다.
- [x] 좌석의 행에 따라서 좌석의 금액을 구한다.
- [x] 선택한 좌석들의 총 가격을 구한다.
- [ ] 좌석을 선택할 때마다 배경 색이 바뀐다.
- [ ] 좌석을 선택할 때마다 하단 금액이 업데이트 된다.
- [ ] 선택한 좌석을 다시 선택하면 선택이 해제된다.
- [ ] `확인` 버튼을 클릭하면 dialog를 띄운다.