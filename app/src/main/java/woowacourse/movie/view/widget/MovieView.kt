package woowacourse.movie.view.widget

import android.widget.ImageView
import android.widget.TextView

data class MovieView(
    val poster: ImageView? = null,
    val title: TextView? = null,
    val date: TextView? = null,
    val runningTime: TextView? = null,
    val description: TextView? = null
)
