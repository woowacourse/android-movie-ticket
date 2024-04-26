# android-movie-ticket

## 기능 요구 사항
사용자는 영화 예매 시스템을 이용해 쉽고 빠르게 보고 싶은 영화를 예매할 수 있다.

- [x] 하나의 영화가 목록에서 보인다.
- [x] 영화의 상영일은 단 하루다.
- [x] 영화를 선택하면 상세 정보가 나타나며, 예약할 인원을 선택할 수 있다.
- [x] 영화 티켓 한 장은 13,000원이다.


## Model
- MovieInfo
  - 제목
    - [x] 빈 문자열 X
  - 러닝타임
    - [x] 영화 시간 1분 이상
  - 시놉시스
    - [x] 빈 문자열 X
- Theater
  - MovieInfo
  - 가격
- Schedule
  - Theater
  - Day

## step3 기능 요구 사항

- [x] 영화를 선택한 후 날짜와 시간을 정할 수 있다.
- [ ] 화면이 회전 되어도 입력한 정보는 유지 되어야 한다.
- [x] 사용자는 인원/날짜 선택 후에 좌석을 고를 수 있다.
- [x] 좌석을 선택하면 배경색이 바뀐다.
- [ ] 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다.
- [x] 선택된 좌석을 재 선택 하면 선택이 해제 된다.
- [x] 최종 예매를 확인하는 다이얼로그가 표시되고 배경을 터치해도 사라지지 않아야 한다.

### 좌석 정책
- [x] 좌석은 총 5행 4열로 구성되어 있고 각 행은 알파벳, 열은 숫자로 표현한다.
- [x] 1, 2행은 B등급, 보라색 글자로 표시한다. (10,000원)
- [x] 3, 4행은 S등급, 초록색 글자로 표시한다. (15,000원)
- [x] 5행은 A등급, 파란색 글자로 표시한다. (12,000원)

### 날짜 및 시간 정책
- [ ] 각 영화의 상영일은 각자의 범위를 갖는다(예: 2024.4.1 ~ 2024.4.28).
- [ ] 영화 상영 시간 범위는 오전 9시부터 자정까지다.
- [ ] 주말에는 오전 9시부터 두 시간 간격으로 상영한다.
- [ ] 평일에는 오전 10시부터 두 시간 간격으로 상영한다.
- [ ] 날짜와 시간은 기본값으로 초기화되어있다.
