package woowacourse.movie.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoneyState(
    val price: Int
) : Parcelable
