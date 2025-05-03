package woowacourse.movie.feature.movieSelect.adapter

sealed class MovieSelectItems {
    data class MovieItem(
        val screeningData: ScreeningData,
    ) : MovieSelectItems()

    data class AdItem(
        val adData: AdvertisementData,
    ) : MovieSelectItems()
}
