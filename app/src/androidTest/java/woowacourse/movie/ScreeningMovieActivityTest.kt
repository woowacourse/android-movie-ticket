package woowacourse.movie

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.model.ScreeningMovie
import woowacourse.movie.model.ScreeningMovies
import woowacourse.movie.screeningmovie.AdvertiseViewHolder
import woowacourse.movie.screeningmovie.MovieAdapter
import woowacourse.movie.screeningmovie.ScreenMovieUiModel
import woowacourse.movie.screeningmovie.ScreeningMovieActivity
import woowacourse.movie.screeningmovie.toScreenItems

class ScreeningMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ScreeningMovieActivity::class.java)

    @Before
    fun setUp() {
        activityRule.scenario.onActivity { activity ->
            val listView = activity.findViewById<RecyclerView>(R.id.rcv_screening_movie)

            val items =
                ScreeningMovies(
                    listOf(
                        ScreeningMovie.STUB,
                        ScreeningMovie.STUB,
                        ScreeningMovie.STUB,
                    ),
                ).insertAdvertisements(3).toScreenItems() + screenMovieUiModel3

            listView.adapter = MovieAdapter(items)
        }
    }

    @Test
    @DisplayName("Activity가 실행되면 뷰가 보인다.")
    fun view_is_display_when_Activity_is_created() {
        onView(withId(R.id.screening_movie)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("title이 `해리 포터와 마법사의 돌`인 item 중 첫 번째 뷰의 상영 시간은 `러닝타임: 181분` 이다. ")
    fun `itemValues_are_placed_in_textView_when_listView_is_created`() {
        onView(withId(R.id.rcv_screening_movie)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(
                    withText(
                        screenMovieUiModel3.title,
                    ),
                ),
            ).atPosition(0),
        )
        onView(withText(screenMovieUiModel3.runningTime)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("리스트의 길이가 3 이상이면 광고가 나타난다.")
    fun advertisement_display_When_list_size_is_more_3() {
        onView(withId(R.id.rcv_screening_movie)).perform(
            RecyclerViewActions.scrollToHolder(
                instanceOf(AdvertiseViewHolder::class.java),
            ).atPosition(0),
        )
        onView(withDrawable(R.drawable.img_advertisement)).check(matches(isDisplayed()))
    }

    private fun withDrawable(
        @DrawableRes id: Int,
    ) = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("ImageView with drawable same as drawable with id $id")
        }

        override fun matchesSafely(view: View): Boolean {
            val context = view.context
            val expectedBitmap = context.getDrawable(id)?.toBitmap()
            return view is ImageView && view.drawable.toBitmap().sameAs(expectedBitmap)
        }
    }

    companion object {
        private val screenMovieUiModel3 =
            ScreenMovieUiModel(
                2,
                title = "해리 포터와 아즈카반의 죄수",
                R.drawable.img_movie_poster,
                "상영일: 2024.3.2",
                "러닝타임: 181분",
            )
    }
}
