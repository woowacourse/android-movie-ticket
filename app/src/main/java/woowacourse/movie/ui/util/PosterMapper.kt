package woowacourse.movie.ui.util

import androidx.annotation.DrawableRes
import woowacourse.movie.R

object PosterMapper {
    private val nameToResId = mapOf(
        "Match" to R.drawable.match,
        "Mickey 17" to R.drawable.mickey
    )

    private val resIdToName = nameToResId.entries.associate { (key, value) -> value to key }

    @DrawableRes
    fun mapNamesToRes(name: String): Int = nameToResId[name] ?: R.drawable.ic_launcher_foreground

    fun mapResToString(resId: Int): String = resIdToName[resId] ?: ""
}