package woowacourse.movie.selectseat.uimodel

import android.os.Parcelable
import androidx.annotation.ColorRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R

@Parcelize
enum class RateColor(
    @ColorRes val color: Int,
) : Parcelable {
    GREEN(R.color.green),
    BLUE(R.color.blue),
    PURPLE(R.color.purple),
}
