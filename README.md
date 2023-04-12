# android-movie-ticket

## 액티비티
- MovieListActivity
- TicketingActivity
- MovieTicketActivity

## 어댑터
- MoviesAdapter

## 데이터
- Movie 
  - 이미지, 제목, 상영일(PlayingTimes), 러닝타임, 소개
- TicketingInfo
  - 영화 이름, 상영일, 몇명, 가격, 무슨 결제
- Price
  - 음수 체크
  - 티켓 한 장의 가격은 13000원
  - 영화 가격에 할인을 적용한다.
    - Discount 구현 클래스를 리스트로 받는다.
- PlayingTimes
  - 날짜-상영시간 map 타입으로 가지고 있다.
- Discount (인터페이스)
  - calculate 메소드
