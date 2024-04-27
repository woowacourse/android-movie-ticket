package woowacourse.movie.feature.reservation.ui

import android.widget.TextView

class SeatView(private val spaceViews: List<TextView>) {
    fun initText(seatModels: List<SeatModel>) {
        spaceViews.forEachIndexed { index, textView ->
            textView.text = seatModels[index].joinRowColText()
        }
    }

    fun setupClickListener(onPlace: (index: Int, TextView) -> Unit) {
        spaceViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                onPlace(index, textView)
            }
        }
    }
}
