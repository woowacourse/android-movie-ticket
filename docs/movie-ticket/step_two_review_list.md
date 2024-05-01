# 수정 및 기능 목록


- [x]: BundleCompat, IntentCompat getParcelable common으로 빼기
- [x]: seat 액티비티 export false로 변경
- [x]: MovieRepository movieReservationById 반환값 nullable
- [x]: SeatSelection 화면 회전 대응 로직 수정
- [x]: SeatReservation Landscape 구현
- [x]: MovieReservationPresenter - loadScreenMovie 공통 로직 빼기
- [x]: 영화 목록 - 피드마 대로 수정(비밀의 방, 아즈카반...)

- [x]: 예약완료 화면에서 뒤로가기 시 사용자는 홈으로 돌아가기 구현
- [x]: 홈 화면 재진입 시, 영화 목록을 다시 불러오기
- [x]: ScreeningMoviePresenter - init 로직 loadScreenMovie 로 변경
- [x]: MovieAdapter 의 isAdPosition 상수화 혹은 전략 패턴
- [x]: MovieRepository 무분별한 error 삭제
- [x]: MovieReservationPresenter - CompleteReservation 네이밍 변경
- [x]: SeatSelectionResult sealed class 들 {}로 묶기
- [x]: SeatBoardView 의 clickListener 생성자로 받기
- [x]: ScreeningMoviePresenterTest 함수명 변경 (에러화면을 보여준다)
- [ ]: 값도 함께 검증하기
```kotlin
// Capturing 사용하기
@Test
fun `presenter 가 생성될 때, view 가 영화 목록을 보여준다`() {
    // given
    every { view.showMovies(any()) } just Runs
    // when & then
    verify { view.showMovies(any()) }
}
// 값도 함께 검증하기
 @Test
    fun `presenter 가 생성될 때, 저장소에서 상영중인 영화들을 가져온다`() {
        // given
        every { repository.screenMovies() } returns listOf()
        // when & then
        verify { repository.screenMovies() }
    }
```
- [ ]: Capture 사용하기 (Presenter test에)
- [ ]: DefaultRepository 에서 ID 생성하는 부분 삭제
- 추후 SeatBoardView 커스텀뷰로 개선하는거 공부

## Test
- [x]: TimePicker, DatePicker 화면 대응 Ui Test 추가
- [ ]: SeatSelection Ui Test