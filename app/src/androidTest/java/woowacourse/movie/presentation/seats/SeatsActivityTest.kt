package woowacourse.movie.presentation.seats

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.MovieTicket
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class SeatsActivityTest {
    private lateinit var activityScenario: ActivityScenario<SeatsActivity>

    @Before
    fun setUp() {
        val ticket =
            MovieTicket(
                "Test",
                LocalDateTime.of(2025, 4, 28, 12, 0),
                1,
            )

        val intent =
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatsActivity::class.java,
            ).apply {
                putExtra("Ticket", ticket)
            }

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 영화_제목이_출력된다() {
        onView(withId(R.id.textview_title))
            .check(matches(withText("Test")))
    }

    @Test
    fun 총_금액이_출력된다() {
        onView(withId(R.id.textview_amount))
            .check(matches(withText("0원")))
    }

    @Test
    fun 좌석을_클릭하면_변경된_금액이_출력된다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.textview_amount))
            .check(matches(withText("10,000원")))
    }

    @Test
    fun 선택한_좌석이_인원수보다_적으면_확인_버튼이_비활성화된다() {
        onView(withId(R.id.textview_confirm))
            .check(matches(isNotClickable()))
    }

    @Test
    fun 선택한_좌석이_인원수와_일치하면_확인_버튼이_활성화된다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.textview_confirm))
            .check(matches(isClickable()))
    }

    @Test
    fun 확인_버튼을_클릭하면_다이얼로그가_나타난다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.textview_confirm))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그가_출력될때_뒤로가기_버튼을_눌러도_다이얼로그가_사라지지_않는다() {
        onView(withText("A1"))
            .perform(click())

        onView(withId(R.id.textview_confirm))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))

        pressBack()

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }
}
