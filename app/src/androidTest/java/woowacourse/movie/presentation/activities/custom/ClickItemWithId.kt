package woowacourse.movie.presentation.activities.custom

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher

object ClickViewAction {
    fun clickViewWithId(id: Int): ViewAction = object : ViewAction {
        override fun getDescription(): String = "id를 기반으로 아이템을 클릭한다."

        override fun getConstraints(): Matcher<View>? = null

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(id)
            v.performClick()
        }
    }
}
