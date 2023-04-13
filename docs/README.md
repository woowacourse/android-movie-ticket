# 영화 예매 기능 목록

## 클래스 구조

### Movie

- picture
- title
- date
- runningTime
- description

### Reservation

- date
- peopleCount
- movie
- price

## 기능 목록

- [x] 영화 목록 표시
- [x] 영화 예매 창 표시
    - [x] 인원 선택
    - [x] 영화 시간 선택 창 표시
      - [x] 평일이면 9시부터 24시까지 2시간 간격으로 상영 가능
      - [x] 주말이면 10시부터 24시까지 2시간 간격으로 상영 가능
- [x] 예약 가격 계산
    - [x] 무비데이(10일,20일,30일) 이라면 10% 할인
    - [x] 11시 이전, 21시 이후 라면 2000원 할인
- [x] 예약 정보 창 표시
