package woowacourse.movie.ui

import android.content.Context
import android.widget.Toast

object Toaster {
    private var toast: Toast? = null
    fun showToast(context: Context, message: String) {
        toast?.cancel()
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT).also {
            it.show()
        }
    }

    fun cancel() = toast?.cancel()
}
