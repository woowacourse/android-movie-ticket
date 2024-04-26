package woowacourse.movie.ui

import android.view.View

fun interface ClickListener {
    fun onClick(
        view: View,
        id: Long,
    )
}
