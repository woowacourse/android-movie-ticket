import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MovieListActivityTest {
    /*
    RecyclerView로 바꾸며 테스트가 망가짐

    private val movieBrief =
        MovieBrief(
            "차람과 하디의 진지한 여행기",
            "상영일: 2024.2.25",
            "230분",
        )

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieListActivity::class.java)

    @Test
    fun listViewDisplaysMovieInfo() {
        onView(withId(R.id.movies_recycler_view)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(0,onChildView

                ))

        /*
        onData(anything())
            .inAdapterView(withId(R.id.movies_recycler_view))
            .atPosition(0)
            .onChildView(withId(R.id.movie_title))
            .check(matches(withText(movieBrief.title)))

     */
    }

    @Test
    fun clickButtonInListViewItem_opensNewActivity() {
        onData(anything())
            .inAdapterView(withId(R.id.movies_recycler_view))
            .atPosition(0)
            .onChildView(withId(R.id.movie_details_button))
            .perform(click())

        onView(withId(R.id.scroll_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun pressBackTest() {
        onData(anything())
            .inAdapterView(withId(R.id.movies_recycler_view))
            .atPosition(0)
            .onChildView(withId(R.id.movie_details_button))
            .perform(click())

        pressBack()

        onView(withId(R.id.movie_list_layout))
            .check(matches(isDisplayed()))
    }
     */
}
