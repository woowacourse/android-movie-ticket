package woowacourse.movie.utils

import android.content.Context

object ImageConverter {
    fun getDrawableIdByName(context: Context, imageName: String?): Int? {
        imageName ?: return null
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }
}
