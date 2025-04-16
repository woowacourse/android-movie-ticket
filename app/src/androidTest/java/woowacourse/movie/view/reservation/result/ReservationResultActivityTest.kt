package woowacourse.movie.view.reservation.result

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R

private val fakeContext = ApplicationProvider.getApplicationContext<Context>()

class ReservationResultActivityTest {
    @Before
    fun setUp() {
        val intent =
            Intent(fakeContext, ReservationResultActivity::class.java).apply {
                putExtra("title", "해리 포터와 마법사의 돌")
                putExtra("date", "2025.4.1")
            }

        ActivityScenario.launch<ReservationResultActivity>(intent)
    }

    @Test
    fun `예매_취소_가능_시간을_보여준다`() {
        onView(withId(R.id.tv_cancel_description))
            .check(matches(withText("영화 상영 시작 시간 15분 전까지\n취소가 가능합니다.")))
    }

    @Test
    fun `예매한_영화의_제목을_보여준다`() {
        onView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `예매한_영화의_상영일을_보여준다`() {
        onView(withId(R.id.tv_movie_date))
            .check(matches(withText("2025.4.1")))
    }
}
