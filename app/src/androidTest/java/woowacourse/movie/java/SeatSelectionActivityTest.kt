package woowacourse.movie.java

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.SeatSelectionActivity
import woowacourse.movie.SeatSelectionActivity.Companion.DATE_KEY
import woowacourse.movie.SeatSelectionActivity.Companion.MOVIE_KEY
import woowacourse.movie.SeatSelectionActivity.Companion.TICKET_KEY
import woowacourse.movie.SeatSelectionActivity.Companion.TIME_KEY
import woowacourse.movie.TicketActivity
import woowacourse.movie.dto.MovieDateDto
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieTimeDto
import woowacourse.movie.dto.TicketCountDto
import java.time.LocalDate
import java.time.LocalTime

@RunWith(AndroidJUnit4::class)
class SeatSelectionActivityTest {

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @get:Rule
    val activityRule: ActivityScenarioRule<SeatSelectionActivity> =
        ActivityScenarioRule(
            Intent(
                ApplicationProvider.getApplicationContext(),
                SeatSelectionActivity::class.java,
            ).apply {
                val movieDto = MovieDto(
                    title = "해리포터",
                    startDate = LocalDate.of(2024, 3, 1),
                    endDate = LocalDate.of(2024, 4, 1),
                    runningTime = 200,
                    description = "오래전 사악한 마법사 볼드모트에게서 부모를 잃지만 그를 몰락시키고 살아남은 아이 해리 포터는 자신이 마법사라는 사실을 알지 못하고 친척인 더즐리 집안에서 자라게 된다. 친척들로부터 갖은 구박을 받으며 힘든 나날을 보내던 도중, 11세가 되던 해에 해리에게로 마법 학교인 호그와트의 입학 통지서가 오게 된다. 이모부인 버넌 더즐리는 해리가 편지를 받지 못하게 방해하지만 해리는 우여곡절 끝에 자신이 마법사라는 사실을 알게 된다. 그리고 그를 맞이하러 온 호그와트의 숲지기 루비우스 해그리드의 안내로 호그와트에 입학하기 위한 준비를 하고, 마침내 학교로 가게 되는데...",
                    moviePoster = R.drawable.img,
                )
                val movieDateDto = MovieDateDto(LocalDate.now())
                val movieTimeDto = MovieTimeDto(LocalTime.now())
                val ticketCountDto = TicketCountDto(3)
                putExtra(MOVIE_KEY, movieDto)
                putExtra(DATE_KEY, movieDateDto)
                putExtra(TIME_KEY, movieTimeDto)
                putExtra(TICKET_KEY, ticketCountDto)
            },
        )

    @Test
    fun `영화_제목이_전달받은_데이터와_일치하는지_확인`() {
        onView(withId(R.id.movie_title))
            .check(matches(allOf(withText("해리포터"), isDisplayed())))
    }

    @Test
    fun `좌석이_선택되지_않았을_때_가격이_0원인지_확인`() {
        onView(withId(R.id.ticket_price))
            .check(matches(allOf(withText("0원"), isDisplayed())))
    }

    @Test
    fun `좌석을_선택하면_가격이_0원이_아닌지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.ticket_price))
            .check(matches(not(withText("0원"))))
    }

    @Test
    fun `좌석을_선택했다가_재선택하면_가격이_0원인지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).perform(ViewActions.click())
        onView(withId(R.id.ticket_price))
            .check(matches(allOf(withText("0원"), isDisplayed())))
    }

    @Test
    fun `좌석을_정해진_좌석_수_만큼_선택하지_않으면_버튼이_클릭되지_않는지_확인`() {
        onView(withId(R.id.enterBtn))
            .check(matches(isNotClickable()))
    }

    @Test
    fun `좌석을_정해진_좌석_수_만큼_선택하면_버튼이_클릭되는지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.enterBtn))
            .check(matches(isClickable()))
    }

    @Test
    fun `좌석을_선택하면_좌석의_색깔이_변하는지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.select_seat)))
    }

    @Test
    fun `좌석을_선택했다가_재선택하면_기본_색깔로_돌아오는지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("A1")).check(matches(hasBackgroundColor(R.color.white)))
    }

    @Test
    fun `확인_버튼을_누르면_다이얼로그가_나오는지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.enterBtn)).perform(ViewActions.click())
        onView(withText(R.string.seat_dialog_title)).check(matches(isDisplayed()))
    }

    @Test
    fun `다이얼로그에서_예를_누르면_티켓_확인_페이지로_이동하는지_확인`() {
        onView(withText("A1")).perform(ViewActions.click())
        onView(withText("B1")).perform(ViewActions.click())
        onView(withText("C1")).perform(ViewActions.click())
        onView(withId(R.id.enterBtn)).perform(ViewActions.click())
        onView(withText(R.string.seat_dialog_yes)).perform(ViewActions.click())
        intended(hasComponent(TicketActivity::class.java.name))
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
