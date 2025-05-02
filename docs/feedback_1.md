## 3,4 단계 피드백 문서

### 1. 리사이클러뷰의 리스너 인덱스 반환으로 인한 문제 해결 및 영화 데이터 접근 최적화
+ 인덱스를 받아 [영화 + 광고] 리스트에서 불필요한 인덱스 계산 발생
+ 계산 로직은 인덱스 만큼 리스트를 자르고 UiModel에 해당하는 개수를 탐색
```
override fun onSelectMovie(movieIdx: Int) {
    val movieIndex =
        items
            .subList(0, movieIdx)
            .count { it is UiModel.MovieUiModel }
    view.moveToBookingComplete(movieIndex)
}
```
### ⛔️ Warning
+ 리스트 탐색 비용(O(N)) 증가
+ 사용하는 곳에 대한 복잡성이 증가
+ 단, 이를 직접 객체를 반환하도록 수정할 경우 다음 리뷰에 위배된다.
> 지금은 단순히 모든 정보가 똑같을거라고 가정하고 UiModel을 여러곳에서 쓰고있어요.
> 하지만 지금 구조는 영화, 광고를 분리해서 보여주기 위한 아이템 전략이지 여러 화면에서 재사용 하기 위한 구조로 보기는 어렵습니다.
> Booking 화면에서는 다르게 출력해야 한다거나 불필요한 광고 관련 정보도 알고 있는 등 의존성이 만들어집니다.
> 저라면 차라리 Movie 모델을 그대로 전달하고 재사용 되는 UI 로직을 어떻게 처리할지 고민하는 방법이 나아 보입니다.

- 위 리뷰에 극히 공감한다. `UiModel`을 만든 이유는 어디까지나 ViewType을 분기하는 과정에서 `Class Type`에 따라 분기하기 위함이다.
+ 다만, 객체를 직접 반환할 경우 `UiModel`을 반환하기 때문에 위 리뷰 내용에 위배된다.

### 💡Solution
+ id를 반환해 영화 저장소에서 영화를 직접 찾도록 하자

### ⛔️ Warning
+ 하지만 이 또한 id를 가지고 영화를 찾는 리스트 탐색 비용(O(N))이 발생한다.
  + 만약 10,000번째 영화일 경우 리스트를 만번이나 반복한다.

### 💡Solution
+ 저장소에서 영화의 Id를 Key로 갖는 map 자료구조를 사용해 탐색 시간이 O(1)이 들도록 최적화하자

### 2. Presenter의 DIP 위반 수정
```
companion object PresenterFactory {
    fun providePresenter(view: View): Presenter {
        val seats = Seats()
        return SeatPresenter(view, seats)
    }
}
```
### ⛔️ Warning
- 인터페이스가 구현체를 알게 되어 DIP를 위반하는 형태가 되어 제거

### 3. Presenter의 비즈니스 로직 제거
```
when (seats.isSelected(newSeat)) {
    true -> seats.removeSeat(newSeat)
    false -> {
        if (canSelect(limit).not()) return
        seats.addSeat(newSeat)
    }
}
```
- [x] SeatsPresenter에 존재하는 비즈니스 로직 제거
- [x] 이외의 Presenter에 비즈니스 로직이 존재하는지 확인

### 4. Presenter의 UI 로직 제거
```
private fun seatToLabel(): String {
    return seats
        .item
        .joinToString {
            val rowLetter = ('A' + it.x - 1)
            val columnNumber = it.y
            "$rowLetter$columnNumber"
        }
}
```
- [x] SeatsPresenter에 존재하는 UI 로직 제거
- [x] 이외의 Presenter에 UI 로직이 존재하는지 확인

## 5. 테이블 레이아웃 코드 개선
- 좌석의 전체 뷰와 각 row를 관리하도록 수정

## 6. Presenter 코드 개선

### 6-1. Prestener에서 비즈니스 로직을 요청 하지 않는 메서드 제거
### ⛔️ Warning
- View 인터페이스(BookingContract.View)에 onClickIncrease(), onClickDecrease() 등 중간 전달 전용 메서드가 존재
- 해당 메서드들은 별도의 추가 로직 없이 단순히 Presenter 메서드를 호출하는 역할만 수행

### 💡Solution
Button 클릭 이벤트에서 Presenter 메서드를 직접 호출하도록 수정

### 6-2. View와 Presenter의 관심사 분리
- [X] 프레젠터의 존재하는 onXXX 메서드 제거
  - 유저와 상호작용하여 이벤트를 발생 시키는 역할은 View의 책임이므로 제거

### 6-3. 구체적인 이벤트를 명시하는 메서드명 수정
- XXClick 이란 이름을 가진 메서드가 더블클릭이나 롱클릭 드래그로 이벤트가 시작된다면 인터페이스 이름도 변경
- [x] View와 Presenter의 인터페이스는 기능 관점에서 작명
- 의문점 : 만약 "클릭"에 관한 이벤트일 경우 "클릭"이란 단어로 롱클릭, 더블클릭 같은 행위를 추상화할 수 있지않을까 ?
    + 메서드의 네이밍을 추상화하는 것은 큰 이점이 없어보인다.
    + 하지만 UI에서 트리거되는 이벤트가 변경되어 Presenter의 메서드명이 수정된다면 이는 SRP를 위반한다
