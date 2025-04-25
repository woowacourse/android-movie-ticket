package woowacourse.movie.view.ext

import android.content.Context

fun String.toDrawableResourceId(context: Context): Int {
    return context.resources.getIdentifier(this, "drawable", context.packageName)
}
