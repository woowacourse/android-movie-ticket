package woowacourse.movie

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @DrawableRes val poster: Int,
    val title: String,
    val date: String, // TODO: LocalDateTime으로 리팩토링
    val runTime: String,
    val isChecked: Boolean = false
) : Parcelable {
    val information: String = title + "\n" + date + "\n" + runTime
}


