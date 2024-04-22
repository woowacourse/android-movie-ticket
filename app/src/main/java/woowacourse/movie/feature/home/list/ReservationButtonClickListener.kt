package woowacourse.movie.feature.home.list

import android.view.View

fun interface ReservationButtonClickListener {
    fun onClick(view: View, movieContentId: Long)
}
