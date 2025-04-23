package woowacourse.movie.helper

import android.view.View

object CustomClickListenerHelper {
    @JvmStatic
    fun View.setOnSingleClickListener(
        intervalTime: Long = 1000,
        onSingleClick: (View) -> Unit,
    ) {
        this.setOnClickListener { view ->
            if (this.isClickable) {
                onSingleClick(this)
                this.isClickable = false
            }

            this.postDelayed({
                this.isClickable = true
            }, intervalTime)
            return@setOnClickListener
        }
    }
}
