package woowacourse.movie.ui.utils

import android.content.Context

fun String.getImageFromId(context: Context): Int = context.resources.getIdentifier(this, "drawable", context.packageName)
