package woowacourse.movie.presentation.screening

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import woowacourse.movie.R

@Parcelize
data class ScreeningMovieUiModel(
    val id: Long = 0,
    val title: String = "",
    @DrawableRes val imageRes: Int = dummyImageRes(id),
    val screenDate: String = "",
    val description: String = "",
    val runningTime: String = "",
) : Parcelable

private fun dummyImageRes(id: Long): Int {
    return DummyImageRes[(id % DummyImageRes.size).toInt()]
}

@DrawableRes
private val DummyImageRes: List<Int> =
    listOf(
        R.drawable.img_movie_poster,
        R.drawable.harry_potter_2,
        R.drawable.harry_potter_3,
        R.drawable.harry_potter_4,
    )
