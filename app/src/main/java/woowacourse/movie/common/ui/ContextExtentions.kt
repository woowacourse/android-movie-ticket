package woowacourse.movie.common.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply(block))
}