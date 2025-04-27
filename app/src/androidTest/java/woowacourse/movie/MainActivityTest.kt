package woowacourse.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.RecyclerViewMatcher.Companion.withRecyclerView
import woowacourse.movie.activity.main.MainActivity
import woowacourse.movie.activity.reservation.ReservationActivity
import woowacourse.movie.adapter.MovieListAdapter
import woowacourse.movie.dto.MovieListData
import woowacourse.movie.global.ServiceLocator

@RunWith(AndroidJUnit4::class)
@Suppress("FunctionName")
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 영화_정보가_표시된다() {
        onView(withId(R.id.main))
            .check(matches(isDisplayed()))

        onView(withRecyclerView(R.id.movies).atPositionOnView(0, R.id.movie_title))
            .check(matches(withText("해리포터와 마법사의 돌0")))
        onView(withRecyclerView(R.id.movies).atPositionOnView(0, R.id.movie_date))
            .check(matches(withText("상영일: 2025.04.03 ~ 2025.04.05")))
        onView(withRecyclerView(R.id.movies).atPositionOnView(0, R.id.movie_running))
            .check(matches(withText("러닝타임: 125분")))
        onView(withRecyclerView(R.id.movies).atPositionOnView(0, R.id.btn_book))
            .check(matches(withText("지금 예매")))
    }

    @Test
    fun 지금_예매_버튼을_누르면_예매_화면으로_이동한다() {
        onView(withRecyclerView(R.id.movies).atPositionOnView(0, R.id.btn_book))
            .perform(click())
        onView(withId(R.id.booking))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 영화목록에_영화가_세_번_노출될_때마다_광고가_한_번_노출된다() {
        (0..10).forEach {
            onView(withId(R.id.movies))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(it))
            if ((it + 1) % 4 == 0) {
                onView(withRecyclerView(R.id.movies).atPositionOnView(it, R.id.movie_item_ad))
                    .check(matches(isDisplayed()))
            } else {
                onView(withRecyclerView(R.id.movies).atPositionOnView(it, R.id.movie_item_root))
                    .check(matches(isDisplayed()))
            }
        }
    }

    @Test
    fun 영화_목록의_요소는_10_000개까지_추가될_수_있다() {
        activityRule.scenario.onActivity {
            val movies = mutableListOf<MovieListData.MovieDto>()
            (0..100).forEach {
                movies.addAll(ServiceLocator.movies.map { MovieListData.MovieDto.fromMovie(it) })
            }

            val ads = ServiceLocator.ads
            val flatten = MovieListData.flatten(movies, ads)

            val recyclerView = it.findViewById<RecyclerView>(R.id.movies)
            recyclerView.adapter =
                MovieListAdapter { movieDto ->
                    val intent = ReservationActivity.newIntent(it, movieDto)
                    it.startActivity(intent)
                }.apply {
                    submitList(
                        flatten,
                    )
                }

            assertThat(recyclerView.adapter?.getItemCount()).isEqualTo(10_000)
        }
    }
}
