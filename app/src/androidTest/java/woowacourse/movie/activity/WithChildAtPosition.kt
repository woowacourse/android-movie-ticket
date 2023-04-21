package woowacourse.movie.activity

import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun withChildAtPosition(position: Int): Matcher<View?> {
    return WithChildAtPosition(View::class.java, position)
}

class WithChildAtPosition(expectedType: Class<out View>?, private val position: Int) :
    BoundedMatcher<View?, View>(expectedType) {
    override fun matchesSafely(view: View): Boolean {
        if (view.parent is ViewGroup) {
            return (view.parent as ViewGroup).children.toList()[position] == view
        }
        return false
    }

    override fun describeTo(description: Description) {
        description.appendText("position: $position")
    }
}
