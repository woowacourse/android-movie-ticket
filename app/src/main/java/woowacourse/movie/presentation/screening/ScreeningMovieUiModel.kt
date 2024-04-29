package woowacourse.movie.presentation.screening

import androidx.annotation.DrawableRes
import woowacourse.movie.R

data class ScreeningMovieUiModel(
    val id: Long,
    val title: String,
    @DrawableRes val imageRes: Int = dummyImageRes(id),
    val screenDate: String,
    val runningTime: String,
)

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
