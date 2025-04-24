package woowacourse.movie.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.model.Poster

@Parcelize
sealed class PosterUiModel : Parcelable {
    data class Resource(
        val resId: Int,
    ) : PosterUiModel()

    data class Url(
        val url: String,
    ) : PosterUiModel()
}

fun Poster.toUiModel(): PosterUiModel =
    when (this) {
        is Poster.Resource -> PosterUiModel.Resource(this.resId)
        is Poster.Url -> PosterUiModel.Url(this.url)
    }

fun PosterUiModel.toModel(): Poster =
    when (this) {
        is PosterUiModel.Resource -> Poster.Resource(this.resId)
        is PosterUiModel.Url -> Poster.Url(this.url)
    }
