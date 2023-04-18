# android-movie-ticket

## domain

### Movie
- [x] 제목, 상영기간, 러닝타임, 이미지를 알아야 한다.
- [x] MovieDetail을 소유해야 한다.

### MovieDetail
- [x] 영화의 줄거리를 알아야 한다.

### Reservation
- [x] 영화, 예약된 좌석, 선택한 날짜와 시간, 예매 금액을 알아야 한다.
- [x] 최소, 최대 예매 인원 수를 알아야 한다.

### ReservationAgency
- [ ] 영화, 인원 수, 선택된 날짜와 시간을 알아야 한다.
- [ ] 예약 가능한지 판단한다.
- [ ] 예약한다. (Reservation을 생성한다.)
- [ ] 할인 정책이 적용된 총 금액을 계산한다.

### DiscountPolicy
- [x] 할인 조건을 가져야 한다.
- [x] 할인 조건을 만족한다면 특정 금액의 할인 가격을 반환할 수 있다.

### MovieDayDiscountPolicy
- [x] 할인 조건을 만족하면 10% 할인한다.
- [x] 10, 20, 30일에 할인 조건을 만족한다는 할인 조건을 가지고 있다.

### ScreeningTimeDiscountPolicy
- [x] 할인 조건을 만족하면 2000원 할인한다.
- [x] 조조, 야간 할인 조건을 가지고 있다.

### DiscountCondition
- [x] 영화 예매에 대해 할인할 수 있는지 판단할 수 있다.

### DayDiscountCondition
- [x] 상영 날짜로 할인할 수 있는지 판단한다.

### ScreeningTimeDiscountCondition
- [x] 상영 시간으로 할인할 수 있는지 판단한다.

## View

### MovieListActivity
- [x] 모든 영화의 제목, 상영 기간, 러닝타임, 이미지를 보여준다.
- [x] 영화마다 예매할 수 있는 버튼이 존재한다.

### ReservationActivity
- [x] 영화의 이미지, 제목, 상영 기간, 상영 시간, 러닝타임, 상세정보를 보여준다.
- [x] 상영 기간과 상영 시간은 스피너로 선택할 수 있다.
- [x] 예매 인원을 조절할 수 있는 버튼이 존재한다.
- [x] 클릭하면 예매 정보를 보여주는 화면을 띄우는 예매 완료 버튼이 존재한다.

### ReservationCompletedActivity
- [x] 영화 제목, 상영일, 상영 시간, 예매 인원, 예매 금액을 보여준다.

## Repository

### MovieRepository
- [x] 영화 데이터들을 조회할 수 있다.
