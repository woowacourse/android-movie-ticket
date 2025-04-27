package woowacourse.movie

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.Movie
import woowacourse.movie.movie.adapter.MovieAdapter
import java.time.LocalDate

class MainActivityTest {
    private lateinit var scenario: ActivityScenario<MovieActivity>
    private val movieList = mockMovieList().map { it.toUiModel() }

    @Before
    fun setUp() {
        Intents.init()

        val intent =
            Intent(ApplicationProvider.getApplicationContext(), MovieActivity::class.java).apply {
                putExtra("movieData", ArrayList(movieList))
            }

        scenario = ActivityScenario.launch(intent)
    }

    @After
    fun tearDown() {
        Intents.release()
        scenario.close()
    }

    @Test
    fun `리사이클러뷰가_화면에_보인다`() {
        onView(withId(R.id.recyclerView_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `리사이클러뷰_영화포스터가_존재하는지_확인한다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withId(R.id.recyclerView_layout))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(
                            allOf(
                                withId(R.id.img_poster),
                                isDisplayed(),
                            ),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화이름이_존재하는지_확인한다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withId(R.id.recyclerView_layout))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(
                            allOf(
                                withId(R.id.tv_movie_title),
                                withText("해리 포터와 마법사의 돌"),
                            ),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영일이_존재하는지_확인한다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withId(R.id.recyclerView_layout))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(
                            allOf(
                                withId(R.id.tv_movie_screening_date),
                                withText("2025.4.1 ~ 2025.4.25"),
                            ),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `리스트뷰_영화상영시간이_존재하는지_확인한다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(withId(R.id.recyclerView_layout))
            .check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(
                            allOf(
                                withId(R.id.tv_movie_running_time),
                                withText("152분"),
                            ),
                        ),
                    ),
                ),
            )
    }

    @Test
    fun `리사이클러뷰_영화예매_클릭후_데이터를_확인한다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(
            allOf(
                withId(R.id.btn_reserve),
                isDescendantOfA(nthChildOf(withId(R.id.recyclerView_layout), 0)),
            ),
        ).perform(click())

        intended(
            allOf(
                hasComponent(BookingDetailActivity::class.java.name),
                hasExtraWithKey("movieData"),
            ),
        )
    }

    @Test
    fun `메인에서_예매버튼_클릭시_BookingActivity로_이동하고_정보가_표시된다`() {
        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        onView(
            allOf(
                withId(R.id.btn_reserve),
                isDescendantOfA(nthChildOf(withId(R.id.recyclerView_layout), 0)),
            ),
        ).perform(click())

        intended(
            allOf(
                hasComponent(BookingDetailActivity::class.java.name),
                hasExtraWithKey("movieData"),
            ),
        )

        onView(withId(R.id.tv_booking_title))
            .check(
                matches(
                    allOf(
                        withText("해리 포터와 마법사의 돌"),
                        isDisplayed(),
                    ),
                ),
            )

        onView(withId(R.id.tv_booking_running_time))
            .check(matches(allOf(withText("152분"), isDisplayed())))

        onView(withId(R.id.tv_booking_screening_date))
            .check(matches(allOf(withText("2025.4.1 ~ 2025.4.25"), isDisplayed())))

        onView(withId(R.id.img_booking_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun `리사이클러뷰에서_3번째마다_광고가_출력된다`() {
        val adPosition = 3

        onView(withId(R.id.recyclerView_layout))
            .perform(RecyclerViewActions.scrollToPosition<MovieAdapter.AdViewHolder>(adPosition))

        onView(
            allOf(
                withId(R.id.img_banner),
                isDescendantOfA(withId(R.id.recyclerView_layout)),
            ),
        ).check(matches(isDisplayed()))
    }

    private fun mockMovieList(): List<Movie> {
        return listOf(
            Movie(
                title = "해리 포터와 마법사의 돌",
                imageSource = "harry_potter.png",
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
            ),
            Movie(
                title = "해리 포터와 비밀의 방",
                imageSource = "harry_potter2.png",
                screeningStartDate = LocalDate.of(2025, 4, 1),
                screeningEndDate = LocalDate.of(2025, 4, 28),
                runningTime = 162,
            ),
            Movie(
                title = "해리 포터와 아즈카반의 죄수",
                imageSource = "harry_potter3.png",
                screeningStartDate = LocalDate.of(2025, 5, 1),
                screeningEndDate = LocalDate.of(2025, 5, 31),
                runningTime = 141,
            ),
            Movie(
                title = "해리 포터와 불의 잔",
                imageSource = "harry_potter4.png",
                screeningStartDate = LocalDate.of(2025, 6, 1),
                screeningEndDate = LocalDate.of(2025, 6, 30),
                runningTime = 157,
            ),
            Movie(
                title = "스타 이즈 본",
                imageSource = "star_is_born.jpg",
                screeningStartDate = LocalDate.of(2025, 4, 19),
                screeningEndDate = LocalDate.of(2025, 5, 25),
                runningTime = 135,
            ),
        )
    }

    private fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                val viewHolder = (view as RecyclerView).findViewHolderForAdapterPosition(position)
                return itemMatcher.matches(viewHolder?.itemView)
            }
        }
    }

    fun nthChildOf(
        parentMatcher: Matcher<View>,
        childPosition: Int,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Nth child of parent")
            }

            override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup &&
                    parentMatcher.matches(parent) &&
                    parent.getChildAt(childPosition) == view
            }
        }
    }
}
