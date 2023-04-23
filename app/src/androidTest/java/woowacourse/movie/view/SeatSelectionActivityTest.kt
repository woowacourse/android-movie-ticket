package woowacourse.movie.view

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
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.Minute
import woowacourse.movie.domain.Movie
import woowacourse.movie.view.mapper.toUiModel
import woowacourse.movie.view.model.ReservationOptions
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class SeatSelectionActivityTest {

    private val reservationOptions = ReservationOptions(
        "해리 포터와 마법사의 돌",
        LocalDateTime.of(LocalDate.of(2024, 3, 1), LocalTime.of(13, 0)),
        2
    )

    private val movie = Movie(
        "해리 포터와 마법사의 돌",
        LocalDate.of(2024, 3, 1),
        LocalDate.of(2024, 3, 31),
        Minute(152),
        R.drawable.harry_potter1_poster,
        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
    )

    private val intent = SeatSelectionActivity.newIntent(
        ApplicationProvider.getApplicationContext(),
        reservationOptions,
        movie.toUiModel()
    )

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatSelectionActivity>(intent)

    @Test
    fun 영화_제목을_표시한다() {
        onView(withId(R.id.movie_title_textview))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 한_번_클릭하면_좌석이_선택된다() {
        onView(withText("A1")).perform(click()).check(matches(isSelected()))
    }

    @Test
    fun 두_번_클릭하면_좌석_선택이_해제된다() {
        onView(withText("A1")).perform(click())
        onView(withText("A1")).perform(click()).check(matches(isNotSelected()))
    }

    @Test
    fun 인원수에_해당하는_좌석이_모두_선택되지_않았다면_확인_버튼은_비활성화_상태다() {
        onView(withText("A1")).perform(click())
        onView(withId(R.id.confirm_reservation_button))
            .check(matches(isNotEnabled()))
    }

    @Test
    fun 인원수에_해당하는_좌석이_모두_선택되었다면_확인_버튼은_활성화_상태다() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withId(R.id.confirm_reservation_button))
            .check(matches(isEnabled()))
    }

    @Test
    fun 인원수에_해당하는_좌석이_모두_선택되었다면_최종_금액이_표시된다() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withId(R.id.reservation_fee_textview)).check(matches(withText("20,000원")))
    }

    @Test
    fun 좌석_선택을_해제하여_인원수에_해당하는_좌석이_모두_선택되지_않았다면_최종_금액은_0원으로_표시된다() {
        onView(withText("A1")).perform(click())
        onView(withText("A2")).perform(click())
        onView(withText("A1")).perform(click())
        onView(withId(R.id.reservation_fee_textview)).check(matches(withText("0원")))
    }
}
