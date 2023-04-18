package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalTime

@Parcelize
data class ViewingTime(val value: LocalTime) : Parcelable
