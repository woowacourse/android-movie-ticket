package woowacourse.movie.util

import android.app.Activity
import android.util.Log

private const val INTENT_DATA_NULL_ERROR = "intent 데이터 전달중 %s 값이 null 입니다."

fun Activity.intentDataNullProcess(variable: String) {
    Log.e("IntentDataNullError", INTENT_DATA_NULL_ERROR.format(variable))
    finish()
}
