package woowacourse.movie.presentation.screening

import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.presentation.screening.adapter.AdViewHolder

class ScreeningMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ScreeningMovieActivity::class.java)

    @Before
    fun setUp() {
        MovieRepositoryFactory.setMovieRepository(repository = FakeMovieRepository())
    }

    @After
    fun tearDown() {
        MovieRepositoryFactory.clear()
    }

    @Test
    @DisplayName("ScreeningMovieActivity 가 화면에 보여지는지 테스트")
    fun test_isActivityInView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("상영중인 영화가 화면에 보여지는지 테스트")
    fun recyclerViewTest() {
        // given
        val screeningMovieUiModel = screenMovieUiModel()
        val title = screeningMovieUiModel.title
        val screenDate = screeningMovieUiModel.screenDate
        val runningTime = screeningMovieUiModel.runningTime
        // when : title 과 일치하는 아이템이 화면에 보여질 때까지 스크롤
        val viewInteraction =
            onView(withId(R.id.rv_screening_movie))
                .perform(
                    RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                        hasDescendant(
                            withText(
                                title,
                            ),
                        ),
                    ).atPosition(0),
                )

        // then : 해당 View 가 screenDate 와 runningTime 을 가지고 있는지 확인
        viewInteraction.check(matches(hasDescendant(withText(screenDate))))
        viewInteraction.check(
            matches(hasDescendant(withText(runningTime))),
        )
    }

    @Test
    @DisplayName("4 번째 아이템에 광고 이미지가 보여지는지 테스트")
    fun recyclerViewTest2() {
        val viewInteraction =
            onView(withId(R.id.rv_screening_movie))
                .perform(
                    RecyclerViewActions.scrollToHolder(
                        instanceOf(AdViewHolder::class.java),
                    ).atPosition(0),
                )

        // then : 0번째 아이템에 대한 정보가 화면에 보여짐
        viewInteraction.check(matches(hasDescendant(withDrawable(R.drawable.img_woowacourse))))
    }

    private fun withDrawable(resourceId: Int): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has drawable resource $resourceId")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                val context = item?.context ?: return false
                val drawable = context.getDrawable(resourceId) ?: return false
                val expectedBitmap = drawable.toBitmap()
                val viewHolder =
                    item.findViewHolderForAdapterPosition(0) as? AdViewHolder ?: return false
                val imageView = viewHolder.itemView.findViewById<ImageView>(R.id.iv_ad)
                val actualBitmap = imageView.drawable.toBitmap()
                return expectedBitmap.sameAs(actualBitmap)
            }
        }
    }

    private // Helper method to perform the assertion
    fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("Has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}
