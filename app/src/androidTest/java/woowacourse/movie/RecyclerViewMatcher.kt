package woowacourse.movie

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(private val recyclerViewId: Int) {
    fun atPositionOnView(
        position: Int,
        targetViewId: Int,
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                this.resources?.let {
                    idDescription =
                        runCatching {
                            it.getResourceName(recyclerViewId)
                        }.onFailure { e ->
                            String.format("%s (resource name not found)", recyclerViewId)
                        }.getOrThrow()
                }
                description.appendText("with id: $idDescription")
            }

            override fun matchesSafely(view: View): Boolean {
                this.resources = view.resources
                val recyclerView = view.rootView.findViewById<RecyclerView>(recyclerViewId)
                val childView = recyclerView?.findViewHolderForAdapterPosition(position)?.itemView

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView?.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }

    companion object {
        fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(recyclerViewId)
        }
    }
}
