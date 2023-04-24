# android-movie-ticket

## 액티비티
- MovieListActivity
- MovieDetailActivity
- SeatSelectActivity
- TicketResultActivity

## 어댑터
- MovieListAdapter

## 모델
- Movie 
  - 이미지, 제목, 상영일(PlayingTimes), 러닝타임, 소개
- PlayingDateTimes
- Ticket
  - 영화 이름, 상영일, 몇명, 가격
- DiscountPolicy (다형성을 위한 추상 클래스)
  - MorningPolicy (조조)
  - MovieDayPolicy (무비데이)
  - NightPolicy (야간)
- Price
- Grade (enum class)
  - price (등급별 가격)
- Seat
  - row, col (범위 0 이상)
- TheaterInfo
  - 등급별 가격, 좌석 정보

## 서비스
- PriceSystem (SelectResult에 따른 가격 변화 계산기)
- PriceCalculator (정책에 따른 가격 계산기)
- SeatSelectSystem
- SelectResult (결과를 나타내는 sealed class)
