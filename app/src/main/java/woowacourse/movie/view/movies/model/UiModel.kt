package woowacourse.movie.view.movies.model

sealed interface UiModel {
    data class MovieUiModel(
        val id: Long,
        val title: String,
        val imgName: String,
        val releaseStartDate: String,
        val releaseEndDate: String,
        val runningTime: Int,
    ) : UiModel

    data class AdvertiseUiModel(
        val imgResource: String,
    ) : UiModel
}
