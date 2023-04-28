package woowacourse.movie.ui.main.util

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

fun oneLayerFindViewClickAction(description: String, @IdRes findId: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String = description

        override fun getConstraints(): Matcher<View>? = null

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(findId)
            v.performClick()
        }
    }
}
