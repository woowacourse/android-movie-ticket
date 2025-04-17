package woowacourse.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.init
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.release
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import woowacourse.movie.BookingCompleteActivity.Companion.MOVIE_DATE_KEY
import woowacourse.movie.BookingCompleteActivity.Companion.MOVIE_TIME_KEY
import woowacourse.movie.BookingCompleteActivity.Companion.MOVIE_TITLE_KEY
import woowacourse.movie.BookingCompleteActivity.Companion.TICKET_COUNT_KEY
import woowacourse.movie.BookingDetailActivity.Companion.newIntent

@Suppress("ktlint:standard:function-naming")
class BookingDetailActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingDetailActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                title = "해리 포터와 마법사의 돌",
                startDate = "2025-04-01",
                endDate = "2025-04-25",
                runningTime = 152,
                poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 선택한_날이_평일이면_기본_시간은_9시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 선택한_날이_주말이면_기본_시간은_10시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_date))
            .perform(click())

        onData(anything())
            .atPosition(4)
            .perform(click())

        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("10:00")))
    }

    @Test
    fun 플러스_버튼을_누르면_티켓_장수가_1_증가한다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 마이너스_버튼을_누르면_티켓_장수가_1_감소한다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())
            .perform(click())

        onView(withId(R.id.btn_booking_detail_count_down))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 티켓_장수가_0일때_마이너스_버튼을_눌러도_변동되지_않는다() {
        onView(withId(R.id.btn_booking_detail_count_down))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("0")))
    }

    @Test
    fun 선택_완료_버튼을_누르면_예매_확인_다이얼로그가_노출된다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        onView(withId(R.id.btn_booking_detail_select_complete))
            .perform(click())

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그_외_영역을_터치해도_닫히지_않는다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        onView(withId(R.id.btn_booking_detail_select_complete))
            .perform(click())

        pressBack()

        onView(withText("정말 예매하시겠습니까?"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 예매_완료_클릭시_예매_정보가_정상적으로_전달된다() {
        init()

        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        onView(withId(R.id.btn_booking_detail_select_complete))
            .perform(click())

        onView(withText("예매 완료"))
            .perform(click())

        intended(
            allOf(
                hasComponent(BookingCompleteActivity::class.java.name),
                hasExtra(MOVIE_TITLE_KEY, "해리 포터와 마법사의 돌"),
                hasExtra(MOVIE_DATE_KEY, "2025-04-01"),
                hasExtra(MOVIE_TIME_KEY, "09:00"),
                hasExtra(TICKET_COUNT_KEY, 1),
            ),
        )

        release()
    }
}
