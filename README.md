# android-movie-ticket

## 기능 요구 사항
사용자는 영화 예매 시스템을 이용해 쉽고 빠르게 보고 싶은 영화를 예매할 수 있다.
 
 
- [x] 좌석
  - [x] 행 별로 등급이 다르다
    - [x] 1~2행은 B등급이다
    - [x] 3~4행은 S등급이다
    - [x] 1~2행은 A등급이다
  - [x] 등급별로 가격이 다르다
    - [x] B등급 (10,000원)
    - [x] S등급 (15,000원)
    - [x] A등급 (12,000원)
- [x] 영화관
  - [x] 5행 4열의 좌석으로 구성되어 있다
 
- [x] 날짜 및 시간 정책
  - [x] 각 영화의 상영일은 각자의 범위를 갖는다(예: 2024.4.1 ~ 2024.4.28).
  - [x] 영화 상영 시간 범위는 오전 9시부터 자정까지다.
  - [x] 일정 시간 간격으로 영화를 상영한다.
    - [x] 주말에는 오전 9시부터 두 시간 간격으로 상영한다.
    - [x] 평일에는 오전 10시부터 두 시간 간격으로 상영한다.

- [ ] 화면이 회전되어도 입력한 정보는 유지되어야 한다.
 
- [x] 영화 목록 화면
  - [x] 상영중인 영화의 리스트를 표기할 수 있어야 한다
  - [x] 지금 예매 버튼을 누르면 상영 상세 화면으로 넘어가야 한다

- [x] 영화 예매 화면
  - [x] 영화의 상세 정보를 표시할 수 있어야 한다
  - [x] 티켓 개수 plus 버튼을 눌렀을 때 티켓 개수가 늘어나야 한다
  - [x] 티켓 개수 minus 버튼을 눌렀을 때 티켓 개수가 줄어들어야 한다
  - [x] 영화의 상영일 목록을 표기할 수 있어야 한다
  - [x] 상영일을 고를 수 있어야 한다
  - [x] 상영일을 고른 후 상영일에 맞는 상영 시간 목록을 표기할 수 있어야 한다
  - [x] 상영 시간을 고를 수 있어야 한다
  - [x] 예매 버튼을 누르면 좌석 선택 화면으로 넘어가야 한다
     
- [ ] 좌석 선택 화면
  - [x] 좌석을 표기할 수 있어야 한다
    - [ ] 각 행은 알파벳, 열은 숫자로 표현한다
    - [ ] 예매 가능 좌석과 불가능 좌석은 구분되어 표기되어야 한다
    - [ ] 예매 가능 좌석은 등급별로 표기가 달라야 한다
      - [ ] B등급은 보라색 글자로 표시한다
      - [ ] S등급은 초록색 글자로 표시한다
      - [ ] A등급은 파란색 글자로 표시한다
  - [ ] 영화 예매 화면에서 정한 티켓 개수만큼 좌석을 선택할 수 있어야 한다
    - [ ] 이미 예매된 좌석은 선택할 수 없다
    - [x] 좌석을 선택하면 어떤 좌석을 선택했는지 확인할 수 있다
    - [x] 선택된 좌석을 재선택하면 선택이 해제된다
    - [x] 좌석을 모두 선택하면 확인 버튼이 활성화 된다
    - [x] 좌석을 선택하면 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다
  - [x] 확인 버튼을 누르면 예매 확정 확인 다이얼로그를 표기한다
  - [x] 예매 확정 확인 다이얼로그
    - [x] 확인 버튼을 누르면 결제 확인 화면으로 넘어간다
    - [ ] 취소 버튼을 누르면 좌석을 선택한 상태로 돌아간다
    - [ ] 다이얼로그가 표시되고 배경을 터치해도 사라지지 않아야 한다

- [x] 결제 확인 화면
  - [x] 예매 정보를 표기해야 한다

