# android-movie-ticket

## ✅ STEP 1

### Ticket
- 1 이상의 `count` 값을 저장한다.
  - [x] `addCount` 함수를 호출시, count 의 값은 1 증가한다.
  - [x] `subCount` 함수를 호출시, count 의 값은 1 감소한다.
    - [x] count 값이 1 이하일 때는 감소하지 않는다.

### Movie
- 영화는 아이디(Int), 제목(String), 상영기간(List\<LocalDate>), 러닝타임(Int), 설명(String), 이미지주소(Int) 정보를 갖는다.
- 상영기간을 문자열로 반환할 수 있다.
  - [x] 상영기간의 사이즈가 1인 경우, 해당 일자만 문자열로 반환할 수 있다.
  - [x] 상영기간의 사이즈가 2인 경우, 해당 기간을 문자열로 반환할 수 있다.

## ✅ STEP 3

### Row (enum class)
- A ~ E 사이의 값을 행 값을 가지고 있다.

### Col
- `int` type의 열 위치 값을 가지고 있다.

### SeatGrade (enum class)
- `S`,`A`,`B` 의 좌석 등급을 가지고 있다.

### Seat
- 좌석의 행 값을 가지고 있다.
- 좌석의 열 값을 가지고 있다. 
- 금액 정보를 가지고 있다.
  - 좌석 위치에 맞는 금액을 계산할 수 있다. 

### Seats
- List\<Seat> 형태의 일급컬렉션

### Screen(상영정보)
- 상영정보는 아이디(Int), 영화정보(Movie), 상영일시(LocalDateTime) 정보를 갖는다.

### ❗️Ticket (Property Added)
- 🔴 Movie 값을 생성자 파라미터로 받는다.
- 🔴 Seats 정보를 가지고 있다.