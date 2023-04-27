package woowacourse.movie.ui.moviedetail

import android.widget.Button
import android.widget.TextView
import woowacourse.movie.domain.PeopleCount
import woowacourse.movie.mapper.toModel
import woowacourse.movie.model.PeopleCountModel

class PeopleCountControllerView(
    private val minusButton: Button,
    private val plusButton: Button,
    private val peopleCountView: TextView
) {
    private var peopleCount = PeopleCount()

    val peopleCountModel: PeopleCountModel
        get() = peopleCount.toModel()

    fun setMinusButton() {
        minusButton.setOnClickListener {
            peopleCount = peopleCount.minusCount()
            setPeopleCountView()
        }
    }

    fun setPlusButton() {
        plusButton.setOnClickListener {
            peopleCount = peopleCount.plusCount()
            setPeopleCountView()
        }
    }

    fun setPeopleCountNumber(count: Int) {
        peopleCount = PeopleCount(count)
    }

    fun setPeopleCountView() {
        peopleCountView.text = "${peopleCount.count}"
    }
}
