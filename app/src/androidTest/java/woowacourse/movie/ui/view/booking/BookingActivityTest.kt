package woowacourse.movie.ui.view.booking

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.Poster

class BookingActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>
    private val movieUiModel = mockMovieUiModel()

    @Before
    fun setUp() {
        Intents.init()

        val intent = BookingActivity.newIntent(ApplicationProvider.getApplicationContext(), movieUiModel)
//            Intent(ApplicationProvider.getApplicationContext(), BookingActivity::class.java).apply {
//                putExtra(Keys.Extra.SELECTED_MOVIE_ITEM, movieUiModel)
//            }

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
                    withText("2025.4.1 ~ 2035.4.25"),
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

    @Test
    fun `minus_버튼을_눌렀을때_인원수가_0_이하면_변하지_않는다`() {
        // given
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("0")))

        // when
        onView(withId(R.id.btn_minus))
            .perform(click())

        // then
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("0")))
    }

    @Test
    fun `plus_버튼을_누르면_인원수가_증가한다`() {
        // given && when
        onView(withId(R.id.btn_plus)).perform(click())

        // then
        onView(withId(R.id.tv_people_count)).check(
            matches(
                allOf(
                    withText("1"),
                    isDisplayed(),
                ),
            ),
        )
    }

    @Test
    fun `minus_버튼을_눌렀을때_인원수가_1_이상이면_줄어든다`() {
        // given
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("1")))

        // when
        onView(withId(R.id.btn_minus))
            .perform(click())

        // then
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("0")))
    }

    @Test
    fun `확인_버튼을_누르면_다이알로그가_뜬다`() {
        // given
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("1")))

        // when
        onView(withId(R.id.btn_reserve_confirm)).perform(click())

        // then
        onView(withText("예매 확인"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
        onView(withText("예매 완료"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `다이알로그에서_취소를_누르면_화면이_닫힌다`() {
        // given
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_reserve_confirm)).perform(click())

        // when
        onView(withText("취소"))
            .inRoot(isDialog())
            .perform(click())

        // then
        onView(withId(R.id.img_booking_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `특정_날짜를_선택했을_때_주말인_경우_시간이_정상적으로_표시되고_선택된다`() {
        // given
        onView(withId(R.id.spinner_screening_date)).perform(click())
        // 2035-04-21(주말)
        val targetDate = "2035-04-21"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(targetDate)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinner_screening_date))
            .check(matches(withSpinnerText(containsString(targetDate))))

        // when
        onView(withId(R.id.spinner_screening_time)).perform(click())

        // then
        // 주말 로직에 따라 12:00이 포함 되어 있어야 한다.
        onData(`is`("12:00"))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinner_screening_time))
            .check(matches(withSpinnerText(containsString("12:00"))))
    }

    @Test
    fun `특정_날짜를_선택했을_때_평일인_경우_시간이_정상적으로_표시되고_선택된다`() {
        // given
        onView(withId(R.id.spinner_screening_date)).perform(click())
        // 2035-04-18(평일)
        val targetDate = "2035-04-18"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(targetDate)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinner_screening_date))
            .check(matches(withSpinnerText(containsString(targetDate))))

        // when
        onView(withId(R.id.spinner_screening_time)).perform(click())

        // then
        // 평일 로직에 따라 9:00이 떠야함
        onData(`is`("09:00"))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinner_screening_time))
            .check(matches(withSpinnerText(containsString("09:00"))))
    }

    @Test
    fun `예매_완료_버튼을_누르면_다음_화면으로_데이터를_가지고_넘어간다`() {
        // given
        onView(withId(R.id.spinner_screening_date)).perform(click())
        // 2035-04-18(평일)
        val targetDate = "2035-04-18"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(targetDate)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.spinner_screening_time)).perform(click())
        val targetTime = "11:00"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(targetTime)))
            .inRoot(isPlatformPopup())
            .perform(click())
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withText("선택 완료")).perform(click())

        // when
        onView(withText("예매 완료")).perform(click())

        // then
        intended(
            allOf(
                hasComponent(BookingCompleteActivity::class.java.name),
            ),
        )

        onView(withId(R.id.tv_complete_title))
            .check(matches(allOf(withText("해리 포터와 마법사의 돌"), isDisplayed())))
        onView(withId(R.id.tv_complete_screening_date))
            .check(matches(allOf(withText("2035.4.18"), isDisplayed())))
        onView(withId(R.id.tv_complete_screening_time))
            .check(matches(allOf(withText("11:00"), isDisplayed())))
        onView(withId(R.id.tv_head_count))
            .check(matches(allOf(withText("일반 1명"), isDisplayed())))
        onView(withId(R.id.tv_booking_amount))
            .check(matches(allOf(withText("13,000원 (현장 결제)"), isDisplayed())))
    }

    @Test
    fun `화면_회전_시에도_데이터는_유지된다`() {
        // given
        onView(withId(R.id.spinner_screening_date)).perform(click())
        // 2035-04-18(평일)
        val targetDate = "2035-04-18"
        onData(`is`(targetDate)).perform(click())
        onView(withId(R.id.spinner_screening_time)).perform(click())
        val targetTime = "11:00"
        onData(`is`(targetTime)).perform(click())
        onView(withId(R.id.btn_plus)).perform(click())

        // when
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))
        onView(withId(R.id.spinner_screening_date)).check(matches(withSpinnerText(targetDate)))
        onView(withId(R.id.spinner_screening_time)).check(matches(withSpinnerText(targetTime)))
    }

    private fun mockMovieUiModel(): MovieUiModel {
        return MovieUiModel(
            id = 1L,
            poster = Poster.Resource(R.drawable.harry_potter),
            title = "해리 포터와 마법사의 돌",
            runningTime = "152",
            screeningStartDate = "2025.4.1",
            screeningEndDate = "2035.4.25",
        )
    }
}
