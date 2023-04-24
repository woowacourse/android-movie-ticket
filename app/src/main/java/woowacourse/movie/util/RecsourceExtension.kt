package woowacourse.movie.util

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

fun View.getString(@StringRes res: Int) = context.getString(res)

fun View.getColor(@ColorRes res: Int) = context.getColor(res)
