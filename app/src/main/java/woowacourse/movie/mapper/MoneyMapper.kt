package woowacourse.movie.mapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class MoneyMapper(val value: Int) : Parcelable
