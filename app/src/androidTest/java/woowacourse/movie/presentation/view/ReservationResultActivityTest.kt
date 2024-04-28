package woowacourse.movie.presentation.view

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R

class ReservationResultActivityTest {
    private val testContext = ApplicationProvider.getApplicationContext<Context>()
    private val intent: Intent = reservationResultActivityIntent(testContext)

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationResultActivity>(intent)

    @Test
    fun `영화_상세_화면에서_전달_받은_예매_정보를_화면에_나타낸다`() {
        onView(withId(R.id.title)).check(matches(withText(TITLE)))
        onView(withId(R.id.screeningDate)).check(matches(withText(SCREENING_DATE)))
        onView(
            withId(R.id.reservationInfo),
        ).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.reservation_info_format,
                        RESERVATION_COUNT,
                    ),
                ),
            ),
        )
        onView(
            withId(R.id.totalPrice),
        ).check(
            matches(
                withText(
                    testContext.getString(
                        R.string.reservation_total_price_format,
                        TOTAL_PRICE,
                    ),
                ),
            ),
        )
    }
}
