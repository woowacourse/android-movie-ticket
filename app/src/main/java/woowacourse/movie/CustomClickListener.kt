package woowacourse.movie

import android.view.View

object CustomClickListener {
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

