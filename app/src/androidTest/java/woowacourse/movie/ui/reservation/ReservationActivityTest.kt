 package woowacourse.movie.ui.reservation

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
 import woowacourse.movie.domain.model.Movie
 import woowacourse.movie.domain.model.Screen
 import woowacourse.movie.domain.repository.DummyReservation

 @RunWith(AndroidJUnit4::class)
 class ReservationActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<ReservationActivity> =
        ActivityScenarioRule<ReservationActivity>(
            Intent(ApplicationProvider.getApplicationContext(), ReservationActivity::class.java).apply {
                putExtra("reservationId", testFixtureReservationId())
            },
        )

    @Test
    fun 예약한_영화의_제목을_표시한다() {
        onView(withId(R.id.tv_reservation_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun 예약한_영화의_러닝타임을_표시한다() {
        onView(withId(R.id.tv_reservation_date)).check(matches(withText("2024-03-01")))
    }

    @Test
    fun 상영_영화_예매_수를_표시한다() {
        onView(withId(R.id.tv_reservation_count)).check(matches(withText("일반 1명")))
    }

    @Test
    fun 상영_영화_예매_가격을_표시한다() {
        onView(withId(R.id.tv_reservation_amount)).check(matches(withText("13,000원 (현장 결제)")))
    }

    private fun testFixtureReservationId() =
        DummyReservation.save(
            Screen(
                id = 1,
                movie = Movie(
                    id = 1,
                    title = "해리 포터와 마법사의 돌",
                    runningTime = 151,
                    description = "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. " +
                            "해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다.",
                ),
                date = "2024-03-01",
                price = 13_000,
            ),
            1,
        ).getOrThrow()
 }
