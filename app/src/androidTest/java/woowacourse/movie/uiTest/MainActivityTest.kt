package woowacourse.movie.uiTest

import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.activity.MainActivity

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    private lateinit var item: DataInteraction

    @Before
    fun setUp() {
        item =
            onData(anything())
                .inAdapterView(withId(R.id.list_view))
                .atPosition(0)
    }

    @Test
    fun `첫번째_아이템의_영화_제목을_보여준다`() {
        item.onChildView(withId(R.id.tv_movie_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `첫번째_아이템의_영화_상영일을_보여준다`() {
        item.onChildView(withId(R.id.tv_movie_date))
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
    }

    @Test
    fun `첫번째_아이템의_영화_러닝타임을_보여준다`() {
        item.onChildView(withId(R.id.tv_movie_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun `첫번째_아이템의_영화_예매버튼을_보여준다`() {
        item.onChildView(withId(R.id.btn_reserve))
            .check(matches(withText("지금 예매")))
    }
}
