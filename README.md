# android-movie-ticket

## 📚️ 개요

사용자는 영화 예매 시스템을 이용해 쉽고 빠르게 보고 싶은 영화를 예매할 수 있다.

## 🛠️ 구현할 기능

- [x] item.xml | 영화 목록틀 추가
- [x] 하나의 영화가 목록에서 보인다.
- [x] 목록은 영화 제목을 가진다.
- [x] 목록은 상영일을 가진다.
- [x] 목록은 러닝타임을 가진다.
- [x] 목록은 예매 버튼을 가진다.
- [x] 지금 예매 버튼을 누르면 예매 완료 화면으로 넘어간다.
- [x] 지금 예매 버튼을 누르면 기본 정보를 담은 화면이 표시된다.
- [x] 예매완료 뷰에서는 취소 가능 시간을 알려준다.
- [x] 예매완료 뷰에서는 영화제목과 상영일을 알려준다.

## 📚️ 2단계 개요
사용자는 영화 예매 시스템에서 일정과 인원을 선택할 수 있다.

## 🛠️ 2단계 구현할 기능
- [x] activity_reservation | 영화 예매 화면 추가
- [x] 영화를 선택 후 나타나며, 예약할 인원을 선택할 수 있다.
- [x] 영화를 선택하면 포스터와 영화 제목과, 상영일, 러닝타임을 가진다.
- [x] 버튼을 사용하여 인원을 증감할 수 있다.
- [x] 영화를 선택 후 날짜와 시간을 spinner로 선택할 수 있다.
- [x] 영화 스케줄은 현재 상영 가능한 날짜 리스트를 반환한다
- [x] 영화 스케줄은 평일 상영 가능한 시간 리스트를 반환한다
- [x] 영화 스케줄은 주말 상영 가능한 시간 리스트를 반환한다
- [x] 영화 티켓 한 장은 13,000원이다.
- [x] 화면이 회전되어도 입력한 정보는 유지되어야 한다.
- [x] 예매 완료를 확인하는 다이얼로그가 표시되고 배경을 터치해도 사라지지 않아야 한다.
- [x] 다이얼로그에서 예매 완료를 선택하면 예매 내역 화면으로 이동한다.
- [x] 각 영화의 상영일은 각자의 범위를 갖는다(예: 2025.4.1 ~ 2025.4.25).
- [x] 영화 상영 시간 범위는 오전 9시부터 자정까지다.
- [x] 주말에는 오전 9시부터 두 시간 간격으로 상영한다.
- [x] 평일에는 오전 10시부터 두 시간 간격으로 상영한다.
- [x] 날짜와 시간은 기본값으로 초기화되어있다.
- [x] 15분 전 setText에서 집어넣는 걸로 변경

## 피드백 반영

- [x] MyAdapter | 네이밍 수정
- [x] MyAdapter | ViewHolder패턴 적용
- [x] MyAdapter | 람다 함수를 적용하여 Intent를 넘기도록 수정
- [x] 각 xml에 scrollView 넣기
- [x] 각 xml layout id 변경
- [x] CompleteActivity 네이밍 변경
- [x] ReservationActivity | 잘못된 값을 받았을 때 Dialog를 띄우고 전 Activity로 돌아가도록 수정
- [x] CompleteActivity | 잘못된 값을 받았을 때 Dialog를 띄우고 전 Activity로 돌아가도록 수정
- [x] ReservationActivity | onRestoreInstanceState를 추가
- [x] ReservationDialog | Dialog Factory로 개선
- [x] ReservationActivity | return 부분 수정
- [x] ReservationActivity | createTicket 메서드 삭제
- [x] Uitest 추가

## 2차 피드백 반영

- [x] 패키지 구조 분리
- [x] MovieAdapter | onButtonListener 네이밍 onClickReservation으로 수정
- [x] MovieViewHolder | bind 메서드 추가
- [x] MainActicity | 더미 데이터 위치 domain으로 이동
- [x] 각 xml파일 scrollView를 전체 Layout으로 수정
- [x] ReservationCompleteActivity | getSerializableExtra() 버전에 따라 메서드를 다르게 사용하도록 수정
- [x] DialogFactory | DialogFactory의 구조를 변경하여 다이얼로그 중복 생성을 방지

## 3단계 개요

- [ ] 필요없는 companion 메서드 제거
- [x] 패키지 구조 분리
- [ ] dialog 인스턴스 전역으로 따로 생성
- [x] MainActivity | MVP구조로 분리
- [x] ReservationActivity | MVP구조로 분리
- [x] ReservationCompleteActivity | MVP구조로 분리

## 4단계 개요

- [ ] 영화 목록에 영화가 세 번 노출될 때마다 광고가 한 번 노출된다.
- [ ] 영화 목록의 요소는 10,000개까지 추가될 수 있다.
- [ ] 사용자는 인원/날짜 선택 후에 좌석을 고를 수 있다.
- [ ] 좌석을 선택하면 배경색이 바뀌고, 하단에 선택한 좌석 수를 반영한 최종 가격이 표시된다.
- [ ] 선택된 좌석을 재선택하면 선택이 해제된다.