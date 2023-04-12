# android-movie-ticket

## domain

### Movie
- [x] 제목, 상영일, 러닝타임, 이미지를 알아야 한다.
- [x] MovieDetail을 소유해야 한다.
- [x] 한 명 이상의 인원이 예매를 할 수 있다.

### MovieDetail
- [x] 영화의 줄거리를 알아야 한다.

### Reservation
- [x] 영화, 인원 수, 예매 금액을 알아야 한다.
- [x] 영화 티켓 한 장의 가격을 알아야 한다.
- [x] 최소, 최대 예매 인원 수를 알아야 한다.

### Poster
- [x] 리소스 아이디를 알아야 한다.

## View

### MovieListActivity
- [x] 모든 영화의 제목, 상영일, 러닝타임, 이미지를 보여준다.
- [x] 영화마다 예매할 수 있는 버튼이 존재한다.

### ReservationActivity
- [ ] 영화의 이미지, 제목, 상영일, 러닝타임, 상세정보를 보여준다.
- [ ] 예매 인원을 조절할 수 있는 버튼이 존재한다.
- [ ] 클릭하면 예매 정보를 보여주는 화면을 띄우는 예매 완료 버튼이 존재한다.

### ReservationCompletedActivity
- [ ] 영화 제목, 상영일, 예매 인원, 예매 금액을 보여준다.

## Repository

### MovieRepository
- [x] 영화 데이터들을 조회할 수 있다.
