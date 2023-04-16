package woowacourse.movie.util

import android.app.Activity
import woowacourse.movie.ui.Toaster

fun Activity.keyNoExistError(key: String) {
    Toaster.showToast(this, "$key 에 해당하는 value를 전달받지 않았습니다")
    finish()
}
