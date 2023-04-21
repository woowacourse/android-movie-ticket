package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.seatselection.SeatSelectionActivity
import woowacourse.movie.view.viewmodel.toUIModel
import java.time.LocalDate
import java.time.LocalDateTime

class SeatSelectionActivityTest {

    private val movie: Movie = Movie(
        "테스트 제목",
        startDate = LocalDate.of(2023, 4, 1),
        endDate = LocalDate.of(2023, 4, 30),
        100,
        "테스트",
        R.drawable.img,
    )

    private val selectedDate: LocalDateTime = LocalDateTime.of(2023, 4, 3, 15, 0)
    private val selectedNumberOfPeople: Int = 2

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            SeatSelectionActivity::class.java
        ).apply {
            putExtra("date", selectedDate)
            putExtra("numberOfPeople", selectedNumberOfPeople)
            putExtra("movie", movie.toUIModel())
        }

    @get:Rule
    internal val activityRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun 사용자가_좌석을_클릭하면_해당_좌석이_선택된다() {
        // given: 앱 실행 시 좌석표가 나오고

        // when : 사용자가 좌석을 클릭하면
        onView(withText("A1")).perform(click())

        // then: 해당 좌석이 선택된다
        onView(withText("A1"))
            .check(matches(isSelected()))
    }

    @Test
    fun 이미_선택된_좌석을_사용자가_클릭하면_해당_좌석이_선택해제된다() {
        // given: 앱 실행 시 좌석표가 나오고

        // when : 사용자가 좌석을 클릭했다가 다시 클릭하면
        onView(withText("A1")).perform(click()).perform(click())

        // then: 해당 좌석이 선택해제 된다
        onView(withText("A1"))
            .check(matches(isNotSelected()))
    }

    @Test
    fun 정해진_인원수대로_선택하면_확인버튼이_활성화된다() {
        // given: 앱 실행 시 좌석표가 나오고

        // when : 사용자가 좌석을 2개 클릭하면
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())

        // then: 확인버튼이 활성화된다
        onView(withId(R.id.seat_selection_check))
            .check(matches(isEnabled()))
    }

    @Test
    fun 정해진_인원수대로_선택하지않으면_확인버튼이_활성화되지않는다() {
        // given: 앱 실행 시 좌석표가 나오고

        // when : 사용자가 좌석을 1개 클릭하면
        onView(withText("A1")).perform(click())

        // then: 확인버튼이 활성화되지 않는다
        onView(withId(R.id.seat_selection_check))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun 자리를_선택하면_금액이_오른다() {
        // given: 앱 실행 시 좌석표가 나오고

        // when : 사용자가 좌석을 클릭하면
        onView(withText("A1")).perform(click())

        // then: 해당 좌석의 금액이 화면에 나타난다
        onView(withId(R.id.seat_selection_price))
            .check(matches(withText("10000원")))
    }
}
