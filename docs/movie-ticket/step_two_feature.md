# 기능 요구 사항
사용자는 영화 예매 시스템에서 등급별 좌석을 선택할 수 있다.

# UI

## 예매 시스템 - 날짜 및 시간 정책
- [x] : 날짜는 "yyyy.MM.dd" format
- [x] : 시간은 "HH:mm" format
- [x] : 날짜를 선택 하면, timePicker 새로운 times 로 업데이트 된다.
## 영화 좌석
- [x] : 행은 알파벳, 열은 숫자로 표현한다.
- [x] : B등급, 보라색 글자
- [x] : S등급, 초록색 글자
- [x] : A등급, 파란색 글자
- [x] : 선택된 좌석은 노란색 배경으로 표시한다.
- [x] : BAN 좌석은 검은 배경
- [x] : RESERVED 좌석은 x 표시
- [x] : EMPTY 좌석은 빈 좌석
- [x] : 선택된 좌석을 재선택하면 선택이 해제된다.
- [x] : 좌석을 선택하면 배경색이 바뀌고, 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다.

# domain
## 날짜 및 시간 정책
- [x]: 영화 상영 시간 범위(09:00 ~ 24:00)
- [x]: 주말에는 오전 9시부터 두 시간 간격으로 상영
- [x]: 평일에는 오전 10시부터 두 시간 간격으로 상영

## 영화 좌석
- [x] : 좌석은 열과 행으로 구성된다.
- [x] : 좌석은 기본적으로 5행 5열로 구성되어 있다.
- [x] : 열행 밖으로 앉을 수 없다.
- [x] : 좌석은 등급이 존재한다.(B, S, A)
  - 1, 2행은 B등급
  - 3, 4행은 S등급
  - 5행은 A등급
- [x] : 좌석 등급 마다 Price 가 다르다.
  - [x] : A: 12,000원, B: 10,000원, S: 15,000원
- [x] : 좌석 상태: RESERVED, BAN, EMPTY, NONE
- [x] : Selected 된 좌석을 다시 선택 하면 EMPTY 상태가 된다.
- [x] : Empty 된 좌석을 선택 하면 Selected 상태가 된다.
- [x] : Ban 된 좌석을 선택하면 실패한다.
- [x] : reserved 된 좌석을 선택하면 실패한다.
- [x] : 좌석을 선택할 인원이 정해져 있다.
- [x] : 좌석을 선택할 인원이 다 선택되면 선택이 완료된다.
- [x] : 선택이 완료 됐을 때, 빈 좌석을 선택 하면 실패한다.
# 리팩토링
- [x] : FakeRepository 를 DefaultRepository 로 이름을 바꾼다.
- [x] : Ui Test 를 위한 FakeRepository 를 만든다.

# 질문
## 1) Spinner(Dropdown) 의 크기 조정
Date 와 Time 의 Item의 수가 많아질 경우, Spinner 의 크기가 너무 커져서 화면을 가리는 문제가 발생했습니다.

![spinner](https://github.com/woowacourse/android-movie-ticket/assets/87055456/73aedb58-6fdc-4cef-a049-40e6a3aef9e3)

Spinner 의 MaxHeight 를 설정하거나 ItemMaxCount 를 제한하려 했으나 Spinner 의 경우  
windowManager 에서 관리되는 View 이기 때문에 Spinner 의 기본 내장 함수로 제어하는 방법이 없었습니다.
따라서, Spinner 를 CustomView 로 만들어서 FixedHeight 를 설정하는 방법을 사용했습니다..  
MaxHeight 나 itemCount 를 설정하는 방법이 있을까요..?