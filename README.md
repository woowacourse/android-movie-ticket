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


## 1차 피드백 반영
- [x] MyAdapter -> MovieListAdapter 네이밍 변경
- [x] Serializable -> Parcelable 변경 및 의존성 추가
- [x] 키 값 상수화
- [x] deprecated 메서드 버전별 로직 추가
- [x] 사용자에게 보이는 문자열 string.xml에 선언
- [x] DateFormatter, PriceFormatter 분리
- [x] also 스코프 대신 apply 활용
- [x] Activity에서 화면을 전환하도록 변경
- [x] ViewHolder 패턴 적용

## 변경 사항
- [x] adapter 패키지 domain 패키지에서 분리 
- [x] MainActivity를 MovieListActivity로 네이밍 변경
- [x] Movie 데이터 생성 분리
- [x] 화면별로 패키지 분리
- [x] 예매 화면, 예매 완료 화면 가로 모드 추가
- [x] 파일명 변경 및 xml 레이아웃 id 변경
- [x] onCreate 된 후 데이터를 복원하도록 onRestoreInstanceState 추가
- [x] 영화 상영 목록 화면 UI 테스트 추가
- [x] 영화 예매 화면 UI 테스트 추가 (스피너 제외)
- [x] 티켓 예매 완료 화면 UI 테스트 추가

## 2차 피드백 반영
- [x] Intent 생성 팩토리 메서드 추가 (+ 키 값)
- [x] getParcelableExtra 버전별 대응 확장함수로 변경
- [x] 리스트 아이템의 set을 ViewHolder에서 하도록 변경
- [x] 스피너 기본값 selectedDatePosition, selectedTimePosition 변수 활용
- [x] Movie에서 MovieFactory로 네이밍 변경
- [x] intent 값을 찾을 수 없다는 에러 메시지 추가
- [x] createTicket() 함수에 LocalTime null 검증 로직 분리
- [x] onClick 확장성을 고려하여 interface로 구현
- [ ] DetailBookingActivity 다이어트 (익명 객체를 외부로 분리, 파일 분리)
- [x] Movie의 image 타입 Int를 리소스 ID로 표현하기
- [x] (1000년 뒤) 스피너 테스트 코드 추가
- [x] 화면 회전 테스트 코드 추가
- [ ] createTicket()이 activity에 있는 것이 적절한지 고민하기
- [x] TextView의 freezesText 속성을 활용하여 회전 시 데이터 저장하도록 변경
- [x] Parcelable 버전별 처리 네이밍 변경

## 3차 피드백 반영
- [x] 빈 함수 반환 값 Unit으로 변경
- [ ] onRestoreInstanceState() 제거
- [ ] spinner 필드에서 by lazy로 변경
- [ ] 다이얼로그에서 context 제거하도록 개선


## 📚️ 개요

View와 Model의 의존성을 최소화한다.
Activity에 존재하는 View와 Controller의 역할을 분리하는 것부터 시작해보자.

## 🛠️ 구현할 기능