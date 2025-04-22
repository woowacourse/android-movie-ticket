package woowacourse.movie.ui.view.utils

import android.view.View
import android.widget.ImageView

fun ImageView.setImage(res: Int?) {
    if (res != null) {
        this.setImageResource(res)
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}
