package woowacourse.movie.view

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Ticket
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ReservationResultActivityTest {
    private val movie =
        Movie(
            img = R.drawable.harry_image1,
            title = "해리 포터와 마법사의 돌",
            description = "해리 포터 1편입니다.",
            screenDate = listOf(LocalDate.of(2024, 3, 1)),
            runningTime = 152,
        )
    private val ticket = Ticket()

    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            ReservationResultActivity::class.java,
        ).putExtra("count", ticket.count().toString())
            .putExtra("title", movie.title)
            .putExtra("screenDate", movie.screenDateToString())
            .putExtra("price", ticket.price())

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun 액티비티가_시작하면_제목이_보인다() {
        onView(withId(R.id.result_title_textview))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 액티비티가_시작하면_상영일이_보인다() {
        onView(withId(R.id.result_screen_date_textview))
            .check(matches(withText("2024.3.1")))
    }

    @Test
    fun 액티비티가_시작하면_count가_보인다() {
        onView(withId(R.id.result_count_textview))
            .check(matches(withText("1")))
    }

    @Test
    fun 액티비티가_시작하면_지불금액이_보인다() {
        onView(withId(R.id.result_price_textview))
            .check(matches(withText("13,000")))
    }
}
