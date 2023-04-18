package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class ViewingDate(val value: LocalDate) : Parcelable
