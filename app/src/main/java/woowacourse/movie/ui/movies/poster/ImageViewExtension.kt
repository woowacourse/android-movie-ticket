package woowacourse.movie.ui.movies.poster

import android.view.View
import android.widget.ImageView

fun ImageView.setImage(res: Int?) {
    if (res != null) {
        this.setImageResource(res)
    } else {
        this.visibility = View.INVISIBLE
    }
}
