package woowacourse.movie.ui.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import woowacourse.movie.R

class SeatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var isChoosed: Boolean = false
        private set

    init {
        setBackgroundColor(ContextCompat.getColor(context, R.color.seat_no_selected))
    }

    fun toggle() {
        if (isChoosed) {
            setBackgroundColor(ContextCompat.getColor(context, R.color.seat_no_selected))
        } else {
            setBackgroundColor(ContextCompat.getColor(context, R.color.seat_selected))
        }
        isChoosed = isChoosed.not()
    }
}
