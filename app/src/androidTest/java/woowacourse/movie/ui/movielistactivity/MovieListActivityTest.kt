package woowacourse.movie.ui.movielistactivity

import android.app.Activity
import android.content.Context
import android.widget.Button
import androidx.core.view.children
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.ui.model.MovieUIModel.Companion.movieToMovieUiModel
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    private fun setDummyData(activity: Activity, movieSize: Int) {
        val movieRecyclerView = activity.findViewById<RecyclerView>(R.id.lv_movie)
        val adapter =
            movieRecyclerView.adapter as ListAdapter<MovieUIModel, RecyclerView.ViewHolder>

        val tempMovies = List(movieSize) {
            Movie(
                title = "해리 포터와 마법사의 돌$it",
                screeningDay = woowacourse.movie.domain.datetime.ScreeningPeriod(
                    LocalDate.parse("2023-04-01"),
                    LocalDate.parse("2023-04-28")
                ),
                runningTime = 152,
                description = ApplicationProvider.getApplicationContext<Context>()
                    .getString(R.string.dummy_data)
            )
        }

        val movieUIModels =
            tempMovies.map { movie: Movie -> movie.movieToMovieUiModel() }

        adapter.submitList(movieUIModels)
    }

    @Test
    fun `3편의_영화목록_후_1개의_광고가_등장한다`() {
        activityRule.scenario.onActivity { activity ->
            setDummyData(activity, 1000)
            val recyclerView = activity.findViewById<RecyclerView>(R.id.lv_movie)
            (0..999).filter { (it + 1) % 4 == 0 }.forEach { index ->
                assertEquals(
                    MovieListRecyclerAdapter.MovieListItemType.numberToMovieListItemType(
                        recyclerView.adapter?.getItemViewType(index) ?: throw IllegalStateException(
                            WRONG_VIEW_TYPE_ERROR
                        )
                    ),
                    MovieListRecyclerAdapter.MovieListItemType.ADVERTISE_ITEM
                )
            }
        }
    }

    @Test
    fun `지금_예매_버튼을_누르면_다음_화면으로_이동한다`() {
        activityRule.scenario.onActivity { activity ->
            setDummyData(activity, 1000)
            val recyclerView = activity.findViewById<RecyclerView>(R.id.lv_movie)
            val firstItemButton =
                recyclerView.children.first().findViewById<Button>(R.id.btn_booking)
            firstItemButton.performClick()
        }

        Intents.intended(IntentMatchers.hasComponent(MovieBookingActivity::class.java.name))
    }

    companion object {
        private const val WRONG_VIEW_TYPE_ERROR = "뷰타입이 정의되지않는 값입니다."
    }
}
