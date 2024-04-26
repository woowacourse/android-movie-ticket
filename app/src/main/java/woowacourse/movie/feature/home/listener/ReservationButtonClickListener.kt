package woowacourse.movie.feature.home.listener

import android.view.View

fun interface ReservationButtonClickListener {
    fun onClick(
        view: View,
        moviePosition: Int,
    )
}
