package woowacourse.movie.feature.reservation.ui

import android.widget.TextView

class SeatBoardView(private val spaceViews: List<TextView>) {
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

    fun update(selectedSeatList: List<String>) {
        selectedSeatList.forEach { selectedSeatText ->
            spaceViews.forEach { singleSeat ->
                if (selectedSeatText == singleSeat.text) {
                    singleSeat.isSelected = true
                }
            }
        }
    }
}
