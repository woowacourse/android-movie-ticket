package woowacourse.movie.view.utils

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import woowacourse.movie.R

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key)
    }
}

fun Context.buildAlertDialog(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes yes: Int = R.string.alert_default_yes,
    @StringRes no: Int = R.string.alert_default_no,
    cancelable: Boolean = false,
    onPositive: () -> Unit,
): AlertDialog.Builder {
    return AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(yes) { _, _ -> onPositive() }
        .setNegativeButton(no) { dialog, _ -> dialog.dismiss() }
        .setCancelable(cancelable)
}
