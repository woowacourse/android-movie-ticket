package woowacourse.movie.presentation.utils

import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

inline fun <reified T : ImageView> withDrawable(resourceId: Int): Matcher<View> {
    return object : BoundedMatcher<View, T>(T::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("has drawable resource $resourceId")
        }

        override fun matchesSafely(imageView: T?): Boolean {
            val context = imageView?.context ?: return false
            val drawable = ContextCompat.getDrawable(context, resourceId) ?: return false
            val expectedBitmap = drawable.toBitmap()
            val actualBitmap = imageView.drawable.toBitmap()
            return expectedBitmap.sameAs(actualBitmap)
        }
    }
}
