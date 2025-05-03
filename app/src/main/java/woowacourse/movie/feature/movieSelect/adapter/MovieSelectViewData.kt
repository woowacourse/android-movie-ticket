package woowacourse.movie.feature.movieSelect.adapter

sealed class MovieSelectViewData {
    data class Movie(
        val screeningData: ScreeningData,
    ) : MovieSelectViewData()

    data class Ad(
        val adData: AdvertisementData,
    ) : MovieSelectViewData()
}
