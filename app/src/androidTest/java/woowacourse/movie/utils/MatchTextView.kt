package woowacourse.movie.utils

import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

fun TableLayout.findTextViewByTextInTableLayout(text: String): TextView? {
    for (i in 0 until this.childCount) {
        val child = this.getChildAt(i)
        if (child is TableRow) {
            for (j in 0 until child.childCount) {
                val grandChild = child.getChildAt(j)
                if (grandChild is TextView && grandChild.text.toString() == text) {
                    return grandChild
                }
            }
        }
    }
    return null
}
