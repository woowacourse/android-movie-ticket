package woowacourse.movie.ui.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import woowacourse.movie.R

class ConfirmView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        isClickable = false
    }

    override fun setClickable(clickable: Boolean) {
        super.setClickable(clickable)
        if (clickable) {
            setBackgroundColor(ContextCompat.getColor(this.context, R.color.confirm_enabled_true))
        } else {
            setBackgroundColor(ContextCompat.getColor(this.context, R.color.confirm_enabled_false))
        }
    }
}
