package woowacourse.movie.mapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@JvmInline
@Parcelize
value class CountMapper(val value: Int) : Parcelable
