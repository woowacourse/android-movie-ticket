package woowacourse.movie.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.ui.main.itemModel.MovieItemModel
import java.time.LocalDate

@Parcelize
data class MovieState(
    @DrawableRes
    val imgId: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String
) : Parcelable {
    fun convertToItemModel(onClick: (position: Int) -> Unit): MovieItemModel {
        return MovieItemModel(this, onClick)
    }
}
