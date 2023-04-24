package woowacourse.movie.activity

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
    return AtPosition(position, itemMatcher)
}

class AtPosition(private val position: Int, private val matcher: Matcher<View?>) :
    BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

    override fun matchesSafely(view: RecyclerView): Boolean {
        val viewHolder = view.findViewHolderForAdapterPosition(position) ?: return false
        return matcher.matches(viewHolder.itemView)
    }

    override fun describeTo(description: Description) {
        description.appendText("item position : $position")
    }
}
