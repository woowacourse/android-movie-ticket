# android-movie-ticket

## STEP 1

### Ticket

- 1 이상의 값을 저장한다.
    - [x] 1 미만의 경우 예외를 던져야한다.
    - [x] add 함수를 호출시, count 의 값은 1 증가한다.
    - [x] sub 함수를 호출시, count 의 값은 1 감소한다.

### Movie

- 영화는 제목(String), 상영일(List\<Date>), 러닝타임(Int), 설명(String), 이미지(String) 정보를 갖는다.

### Movies

- List \<Movie> 형태이다.

### Seat

- 좌석 정보를 가지고 있다.

### Reservation

- Seat 과 Ticket 를 가진다.

### Reservations

- List \<Reservation> 형태이다.

### Audience

- List \<Reservations> 형태이다.
