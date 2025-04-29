package woowacourse.movie

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object BackgroundColorMatcher {
    fun withBackgroundColor(expectedColor: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean {
                val background = view.background
                return if (background is ColorDrawable) {
                    ContextCompat.getColor(view.context, expectedColor) == background.color
                } else {
                    false
                }
            }

            override fun describeTo(description: Description) {
                description.appendText("배경색이 일치하는 View (예상 색상: $expectedColor)")
            }
        }
    }
}
