package woowacourse.movie.presentation.reservation.booking

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ListPopupWindow
import android.widget.Spinner
import android.widget.SpinnerAdapter
import woowacourse.movie.R

@SuppressLint("AppCompatCustomView")
class MovieReservationPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.spinnerStyle
) : Spinner(context, attrs, defStyleAttr) {

    private var dropdownHeight: Int = minimumHeight
    private var popupWindow: ListPopupWindow? = null

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MovieReservationPicker,
            0, 0
        ).apply {
            try {
                dropdownHeight =
                    getDimensionPixelSize(
                        R.styleable.MovieReservationPicker_maxHeight,
                        minimumHeight
                    )
            } finally {
                recycle()
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        if (MeasureSpec.AT_MOST == heightMode) {
            popupWindow?.height = height.coerceAtMost(dropdownHeight)
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun setAdapter(adapter: SpinnerAdapter?) {
        super.setAdapter(adapter)
        val popup =
            Spinner::class.java.getDeclaredField("mPopup").apply { isAccessible = true }[this]
        if (popup is ListPopupWindow) {
            this.popupWindow = popup
        }
    }
}
