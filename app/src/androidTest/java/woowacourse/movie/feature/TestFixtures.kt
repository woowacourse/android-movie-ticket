package woowacourse.movie.feature

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.DataInteraction
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import woowacourse.movie.model.data.MovieRepositoryImpl

const val FIRST_MOVIE_ID = 0L
val firstMovie = MovieRepositoryImpl.findAll().first()

fun view(id: Int): ViewInteraction {
    return onView(withId(id))
}

fun ViewInteraction.equalText(text: String) {
    check(matches(withText(text)))
}

fun ViewInteraction.scroll(): ViewInteraction {
    return perform(scrollTo())
}

fun ViewInteraction.click(): ViewInteraction {
    return perform(ViewActions.click())
}

fun ViewInteraction.child(position: Int): ViewInteraction {
    return perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
}

fun ViewInteraction.equalTextOnRecyclerViewItem(text: String): ViewInteraction {
    return check(
        matches(
            hasDescendant(allOf(withText(text), isDisplayed())),
        ),
    )
}

fun ViewInteraction.checkViewHolderType(
    position: Int,
    viewHolderClass: Class<out RecyclerView.ViewHolder>,
): ViewInteraction {
    val viewHolderTypeMatcher =
        object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {}

            override fun matchesSafely(view: View): Boolean {
                if (view !is RecyclerView) return false
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                return viewHolder != null && viewHolderClass.isInstance(viewHolder)
            }
        }
    return check(matches(viewHolderTypeMatcher))
}

fun spinnerItemByText(text: String): DataInteraction {
    return onData(allOf(`is`(CoreMatchers.instanceOf(String::class.java)), `is`(text)))
        .atPosition(0)
}

fun DataInteraction.click(): ViewInteraction {
    return perform(ViewActions.click())
}

fun ViewInteraction.equalTextSpinnerItem(text: String): ViewInteraction {
    return check(matches(withSpinnerText(containsString(text))))
}
