package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@JvmInline
@Parcelize
value class ViewingTime(val value: LocalTime) : Parcelable
