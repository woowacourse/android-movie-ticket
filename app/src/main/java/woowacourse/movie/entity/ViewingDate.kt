package woowacourse.movie.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@JvmInline
@Parcelize
value class ViewingDate(val value: LocalDate) : Parcelable
