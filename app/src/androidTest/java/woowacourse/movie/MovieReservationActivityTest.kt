package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.model.Date
import woowacourse.movie.model.MovieContent
import woowacourse.movie.model.data.MovieContentsImpl
import woowacourse.movie.view.activity.MovieReservationActivity

@RunWith(AndroidJUnit4::class)
class MovieReservationActivityTest {
    private val intent =
        Intent(
            ApplicationProvider.getApplicationContext(),
            MovieReservationActivity::class.java,
        ).run {
            putExtra(MovieContentKey.ID, 0L)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<MovieReservationActivity>(intent)

    @Test
    fun `화면이_띄워지면_영화_제목이_보인다`() {
        onView(withId(R.id.title_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `화면이_띄워지면_상영일이_보인다`() {
        onView(withId(R.id.screening_date_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("상영일: 2024.3.1")))
    }

    @Test
    fun `화면이_띄워지면_러닝타임이_보인다`() {
        onView(withId(R.id.running_time_text))
            .check(matches(isDisplayed()))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun `스크롤_하면_시놉시스가_보인다`() {
        onView(withId(R.id.synopsis_text))
            .check(matches(isDisplayed()))
            .perform(scrollTo())
            .check(
                matches(
                    withText(
                        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                            "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
                    ),
                ),
            )
    }

    @Test
    fun `증가_버튼을_누르면_예매_인원_수가_증가한다`() {
        onView(withId(R.id.plus_button))
            .perform(click())

        onView(withId(R.id.reservation_count_text))
            .check(matches(withText("2")))
    }

    @Test
    fun `예매_인원이_최대인_경우_증가_버튼을_누르면_예매_인원_수가_증가하지_않는다`() {
        // given
        repeat(50) {
            onView(withId(R.id.plus_button))
                .perform(click())
        }

        // when
        onView(withId(R.id.plus_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_text))
            .check(matches(withText("50")))
    }

    @Test
    fun `감소_버튼을_누르면_예매_인원_수가_감소한다`() {
        // given
        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.plus_button))
            .perform(click())

        // when
        onView(withId(R.id.minus_button))
            .perform(click())

        // then
        onView(withId(R.id.reservation_count_text))
            .check(matches(withText("2")))
    }

    @Test
    fun `예매_인원이_최소인_경우_감소_버튼을_누르면_예매_인원_수가_감소하지_않는다`() {
        onView(withId(R.id.minus_button))
            .perform(click())

        onView(withId(R.id.reservation_count_text))
            .check(matches(withText("1")))
    }

    @Test
    fun `예매_완료_버튼을_누르면_영화_예매_완료_페이지로_이동한다`() {
        onView(withId(R.id.reservation_button))
            .perform(click())

        onView(withId(R.id.movie_reservation_complete_root))
            .check(matches(isDisplayed()))
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() {
            MovieContentsImpl.save(
                MovieContent(
                    R.drawable.movie_poster,
                    "해리 포터와 마법사의 돌",
                    Date(2024, 3, 1),
                    152,
                    "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, " +
                        "판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다. ",
                ),
            )
        }
    }
}
