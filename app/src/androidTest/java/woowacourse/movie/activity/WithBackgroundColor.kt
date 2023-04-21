package woowacourse.movie.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun withBackgroundColor(color: Int): Matcher<View?> {
    return WithBackgroundColor(View::class.java, color)
}

class WithBackgroundColor(expectedType: Class<out View>?, private val color: Int) :
    BoundedMatcher<View?, View>(expectedType) {
    var viewColor: Int = 0
    override fun matchesSafely(view: View): Boolean {
        if (view.background is ColorDrawable) {
            viewColor = (view.background as ColorDrawable).color
            return color == viewColor
        }
        return false
    }

    override fun describeTo(description: Description) {
        description.appendText("background color: " + Color.valueOf(viewColor).toString())
    }
}
