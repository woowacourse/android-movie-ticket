package woowacourse.movie

import android.content.Context
import android.widget.Toast

object ErrorHandler {
    private const val ERROR_MESSAGE = "에러가 발생했습니다."

    fun printError(context: Context) {
        val myToast = Toast.makeText(context, ERROR_MESSAGE, Toast.LENGTH_SHORT)
        myToast.show()
    }
}
