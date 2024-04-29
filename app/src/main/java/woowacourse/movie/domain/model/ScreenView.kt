package woowacourse.movie.domain.model

sealed interface ScreenView {
    data class Screen(
        val id: Int,
        val movie: Movie,
        val startDate: String,
        val endDate: String,
        val selectableDates: List<ScreenDate>,
    ) : ScreenView

    data class Ads(val poster: Int) : ScreenView
}
