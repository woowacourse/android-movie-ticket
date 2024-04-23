package woowacourse.movie.presentation.utils

import android.content.Context

fun String?.toDrawableIdByName(context: Context): Int? {
    this ?: return null
    return context.resources.getIdentifier(this, "drawable", context.packageName)
}
