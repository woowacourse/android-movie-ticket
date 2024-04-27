package woowacourse.movie.feature.reservation.ui

import android.widget.TextView

class SeatView(private val spaceViews: List<TextView>) {
    fun initText(seatModels: List<SeatModel>) {
        spaceViews.forEachIndexed { index, textView ->
            textView.text = seatModels[index].row + seatModels[index].column.toString()
        }
    }

    fun setupClickListener(onPlace: (Pair<Int, Int>, TextView) -> Unit) {
        spaceViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                val position = convertIndexToPosition(index)
                onPlace(position, textView)
            }
        }
    }

    private fun convertIndexToPosition(index: Int): Pair<Int, Int> {
        val x = index % 4 + 1
        val y = index / 4 + 1
        return Pair(x, y)
    }
}
