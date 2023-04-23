package woowacourse.movie

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.activity.SeatPickerActivity
import woowacourse.movie.movie.Movie
import woowacourse.movie.movie.MovieBookingInfo
import java.time.LocalDate

class SeatPickerActivityTest {

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), SeatPickerActivity::class.java)
            .putExtra(
                BundleKeys.MOVIE_BOOKING_INFO_KEY,
                MovieBookingInfo(
                    movieInfo = Movie(
                        R.drawable.hansan,
                        "한산",
                        169,
                        "나라의 운명을 바꿀 압도적 승리의 전투가 시작된다! 1592년 4월, 조선은 임진왜란 발발 후 단 15일 만에 왜군에 한양을 빼앗기며 절체절명의 위기에 놓인다. 조선을 단숨에 점령한 왜군은 명나라로 향하는 야망을 꿈꾸며 대규모 병역을 부산포로 집결시킨다. 한편, 이순신 장군은 연이은 전쟁의 패배와 선조마저 의주로 파천하며 수세에 몰린 상황에서도 조선을 구하기 위해 전술을 고민하며 출전을 준비한다. 하지만 앞선 전투에서 손상을 입은 거북선의 출정이 어려워지고, 거북선의 도면마저 왜군의 첩보에 의해 도난 당하게 되는데… 왜군은 연승에 힘입어 그 우세로 한산도 앞바다로 향하고, 이순신 장군은 조선의 운명을 가를 전투를 위해 필사의 전략을 준비한다. 1592년 여름, 음력 7월 8일 한산도 앞바다, 압도적인 승리가 필요한 조선의 운명을 건 지상 최고의 해전이 펼쳐진다.",
                        LocalDate.of(2023, 4, 1),
                        LocalDate.of(2023, 4, 20),
                    ),
                    date = "2023.4.11",
                    time = "10:00",
                    ticketCount = 2
                )
            )

    @get:Rule
    val activityRule = ActivityScenarioRule<SeatPickerActivity>(intent)

    @Test
    fun 넘겨져온_영화_제목을_출력한다() {
        onView(withId(R.id.tv_seat_picker_movie)).check(matches(withText("한산")))
    }

    @Test
    fun 좌석을_클릭하면_해당_좌석이_색이_바뀐다() {
        // when
        onView(withText("A1")).perform(click())

        // then
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.picked_seat_color)))
    }

    @Test
    fun 좌석을_클릭하면_해당_좌석의_가격으로_계산된_값이_변경된다() {
        // when
        onView(withText("C1")).perform(click())

        // then
        onView(withId(R.id.tv_seat_picker_ticket_price)).check(matches(withText("13000원")))
    }

    @Test
    fun 선택된_좌석을_클릭하면_선택이_해제되며_원래의_색으로_돌아간다() {
        // given
        onView(withText("A1")).perform(click())

        // when
        onView(withText("A1")).perform(click())

        // then
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.unpicked_seat_color)))
    }

    @Test
    fun 선택된_좌석을_클릭하면_가격은_선택된_좌석의_가격만큼_뺀값으로_변경된다() {
        // given
        onView(withText("A1")).perform(click())

        // when
        onView(withText("A1")).perform(click())

        // then
        onView(withId(R.id.tv_seat_picker_ticket_price)).check(matches(withText("0원")))
    }

    @Test
    fun 티켓갯수만큼_좌석을_선택했다면_확인버튼이_활성화된다() {
        // when
        onView(withText("B1")).perform(click())
        onView(withText("B2")).perform(click())

        // then
        onView(withId(R.id.bt_seat_picker_done)).check(matches(isClickable()))
    }

    @Test
    fun 선택된_좌석을_재선택하여_티켓갯수보다_적게_선택되어있다면_확인버튼이_비활성화된다() {
        // given
        onView(withText("B2")).perform(click())
        onView(withText("B3")).perform(click())

        // when
        onView(withText("B2")).perform(click())

        // then
        onView(withId(R.id.bt_seat_picker_done)).check(matches(isNotClickable()))
    }

    @Test
    fun 좌석을_선택하고_확인_버튼을_누를시_AleartDialog가_띄워진다() {
        // given
        onView(withText("B2")).perform(click())
        onView(withText("B3")).perform(click())

        // when
        onView(withId(R.id.bt_seat_picker_done)).perform(click())

        // then
        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun AleartDialog가_띄어져_있을_때_배경을_터치해도_AleartDialog가_사라지지_않는다() {
        // given
        onView(withText("B2")).perform(click())
        onView(withText("B3")).perform(click())
        onView(withId(R.id.bt_seat_picker_done)).perform(click())

        // when
        val x = 50
        val y = 50
        // 0, 0 좌표는 디바이스 기준 왼쪽 상단 꼭지점
        val clickAction = GeneralClickAction(
            Tap.SINGLE,
            { view ->
                val screenPos = IntArray(2)
                view.getLocationOnScreen(screenPos)
                val screenX = (screenPos[0] + x).toFloat()
                val screenY = (screenPos[1] + y).toFloat()
                floatArrayOf(screenX, screenY)
            },
            Press.FINGER,
            InputDevice.SOURCE_MOUSE,
            MotionEvent.BUTTON_PRIMARY
        )
        onView(isRoot()).perform(clickAction)

        // then
        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    private fun hasBackgroundColor(@ColorRes color: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                description?.appendText("has background color resource $color")
            }

            override fun matchesSafely(item: View?): Boolean {
                return item?.background is ColorDrawable &&
                    (item.background as ColorDrawable).color == ContextCompat.getColor(
                    item.context,
                    color,
                )
            }
        }
    }
}
