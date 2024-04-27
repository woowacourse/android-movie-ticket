package woowacourse.movie.presentation.reservation.booking.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R

@Parcelize
data class ScreeningMovieUiModel(
    val title: String = "",
    @DrawableRes val imageRes: Int = R.drawable.img_movie_poster,
    val screenDate: String = "",
    val description: String = "",
    val runningTime: String = "",
) : Parcelable
