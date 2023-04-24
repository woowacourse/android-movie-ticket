package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.activity.SelectSeatActivity
import woowacourse.movie.view.model.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
class SelectSeatActivityTest {
    private val movieUiModel = MovieUiModel(0, "해리포터", LocalDate.MAX, LocalDate.MIN, 0, "")
    private val date = LocalDate.of(2023, 4, 22)
    private val time = LocalTime.of(9, 0)
    private val dateTime: LocalDateTime = LocalDateTime.of(date, time)
    private val ticketDateUiModel = TicketDateUiModel(dateTime)

    private val intent =
        Intent(ApplicationProvider.getApplicationContext(), SelectSeatActivity::class.java).apply {
            putExtra("movie", movieUiModel)
            putExtra("peopleCount", 3)
            putExtra("ticket", ticketDateUiModel)
        }

    @get:Rule
    val activityRule = ActivityScenarioRule<SelectSeatActivity>(intent)

    @Test
    fun check_seats_created() {
        // given
        val seatFirst = onView(withText("A1"))
        val seatLast = onView(withText("E4"))
        // then
        seatFirst.check(matches(isDisplayed()))
        seatLast.check(matches(isDisplayed()))
    }

    @Test
    fun 선택되지_않았던_좌석을_클릭했을때_선택된_상태로_바뀐다(){
        // given
        val seat = onView(withText("A1"))
        // when
        seat.perform(click())
        // then
        seat.check(matches(isSelected()))
    }

    @Test
    fun 선택했던_좌석을_클릭했을때_선택하지_않은_상태로_바뀐다(){
        // given
        val seat = onView(withText("A1"))
        // when
        seat.perform(click())
        seat.perform(click())
        // then
        seat.check(matches(isNotSelected()))
    }

    @Test
    fun 인원수와_같은_수만큼_좌석을_클릭하면_더이상_좌석이_선택된_상태로_변경되지_않는다(){
        // given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val seat3 = onView(withText("A3"))
        val seat4 = onView(withText("B4"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        seat3.perform(click())
        seat4.perform(click())
        // then
        seat4.check(matches(isNotSelected()))
    }

    @Test
    fun 조조할인을_받고_B등급_좌석을_클릭하면_가격텍스트가_8000원_으로_바뀐다(){
        // given
        val seat = onView(withText("A1"))
        val price = onView(withId(R.id.select_seat_price_text_view))
        // when
        seat.perform(click())
        // then
        price.check(matches(withText("8,000원")))
    }

    @Test
    fun 조조할인을_받고_A등급_좌석을_클릭하면_가격텍스트가_10000원_으로_바뀐다(){
        // given
        val seat = onView(withText("E1"))
        val price = onView(withId(R.id.select_seat_price_text_view))
        // when
        seat.perform(click())
        // then
        price.check(matches(withText("10,000원")))
    }

    @Test
    fun 조조할인을_받고_S등급_좌석을_클릭하면_가격텍스트가_13000원_으로_바뀐다(){
        // given
        val seat = onView(withText("C1"))
        val price = onView(withId(R.id.select_seat_price_text_view))
        // when
        seat.perform(click())
        // then
        price.check(matches(withText("13,000원")))
    }

    @Test
    fun 조조할인을_받고_S등급_좌석과_A등급_좌석을_클릭하면_가격텍스트가_23000원_으로_바뀐다(){
        // given
        val seatS = onView(withText("C1"))
        val seatA = onView(withText("E1"))
        val price = onView(withId(R.id.select_seat_price_text_view))
        // when
        seatS.perform(click())
        seatA.perform(click())
        // then
        price.check(matches(withText("23,000원")))
    }

    @Test
    fun 인원수와_같은_수만큼_좌석이_선택된_상태가_아니라면_버튼을_클릭할_수_없다(){
        // given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val button = onView(withText("확인"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        // then
        button.check(matches(isNotClickable()))
    }

    @Test
    fun 인원수와_같은_수만큼_좌석이_선택된_상태이면_버튼을_클릭할_수_있다(){
        // given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val seat3 = onView(withText("A3"))
        val button = onView(withText("확인"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        seat3.perform(click())
        // then
        button.check(matches(isClickable()))
    }

    @Test
    fun 인원수와_같은_수만큼_좌석이_선택된_상태에서_버튼을_클릭하면_다이얼로그가_나온다(){
        // given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val seat3 = onView(withText("A3"))
        val button = onView(withText("확인"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        seat3.perform(click())
        button.perform(click())
        // then
        onView(withText(R.string.select_seat_dialog_title)).check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그가_나온_상태에서_배경을_클릭해도_다이얼로그가_사라지지_않는다(){
        // given
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val seat3 = onView(withText("A3"))
        val button = onView(withText("확인"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        seat3.perform(click())
        button.perform(click())
        val dialog = onView(withText(R.string.select_seat_dialog_title))
        onView(isRoot()).perform(click())
        // then
        (dialog).check(matches(isDisplayed()))
    }

    @Test
    fun 다이얼로그가_나온_상태에서_예매확인을_클릭하면_예매결과창으로_넘어간다(){
        // given
        Intents.init()
        val seat1 = onView(withText("A1"))
        val seat2 = onView(withText("A2"))
        val seat3 = onView(withText("A3"))
        val button = onView(withText("확인"))
        val dialogPositiveButton = onView(withText("예매 완료"))
        // when
        seat1.perform(click())
        seat2.perform(click())
        seat3.perform(click())
        button.perform(click())
        dialogPositiveButton.perform(click())
        // then
        Intents.intended(IntentMatchers.hasComponent(ReservationResultActivity::class.java.name))
        Intents.release()
    }
}
