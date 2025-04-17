package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.model.Movie
import java.time.LocalDate

class BookingActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>

    @get:Rule
    val activityRule = ActivityScenarioRule(BookingActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()

        val movie = mockMovie()

        val intent =
            Intent(ApplicationProvider.getApplicationContext(), BookingActivity::class.java).apply {
                putExtra("movieData", movie)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun `영화_포스터가_화면에_보인다`() {
        onView(withId(R.id.img_booking_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화제목이_화면에_보인다`() {
        onView(withId(R.id.tv_booking_title)).check(
            matches(
                allOf(
                    withText("해리 포터와 마법사의 돌"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `영화_상영일이_화면에_보인다`() {
        onView(withId(R.id.tv_booking_screening_date)).check(
            matches(
                allOf(
                    withText("2024.4.1 ~ 2024.4.25"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `영화_상영시간이_화면에_보인다`() {
        onView(withId(R.id.tv_booking_running_time)).check(
            matches(
                allOf(
                    withText("152분"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `영화_예매_인원이_화면에_보인다`() {
        onView(withId(R.id.tv_people_count)).check(
            matches(
                allOf(
                    withText("0"),
                    isDisplayed(),
                ),
            ),
        )
    }

//    @Test
//    fun `minus_버튼을_누르면_인원수가_줄어든다`() {
//        onView(withId(R.id.btn_minus))
//            .perform(click())
//            .check(
//            matches(
//                allOf(
//                    withText(""),
//                    isDisplayed(),
//                ),
//            ),
//        )
//    }
//
//    @Test
//    fun `plus_버튼을_누르면_인원수가_증가한다`() {
//        TODO("Not yet implemented")
//    }
//
//    @Test
//    fun `인원수가_0_이면_-버튼을_눌러도_다이알로그가_뜨지_않는다`() {
//        TODO("Not yet implemented")
//    }

    private fun mockMovie(): Movie {
        return Movie(
            imageSource = R.drawable.harry_potter,
            title = "해리 포터와 마법사의 돌",
            runningTime = 152,
            screeningStartDate = LocalDate.of(2024, 4, 1),
            screeningEndDate = LocalDate.of(2024, 4, 25),
        )
    }
}
