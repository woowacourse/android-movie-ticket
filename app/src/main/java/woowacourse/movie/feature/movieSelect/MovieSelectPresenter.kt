package woowacourse.movie.feature.movieSelect

import woowacourse.movie.extension.ResourceMapper
import woowacourse.movie.feature.movieSelect.adapter.AdvertisementData
import woowacourse.movie.feature.movieSelect.adapter.MovieSelectViewData
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.model.movieSelect.Advertisement
import woowacourse.movie.model.movieSelect.MovieItemCategory
import woowacourse.movie.model.movieSelect.MovieListItem
import woowacourse.movie.model.movieSelect.screening.Screening

class MovieSelectPresenter(
    private val view: MovieSelectContract.View,
) : MovieSelectContract.Presenter {
    override fun loadMovieList() {
        // 3. presenter는 model에서 데이터 획득
        val movieItems = MovieListItem().createItems()

        // 4. presenter는 view에 사용할 데이터로 가공
        val movieSelectViewData = movieSelectItemToViewData(movieItems)

        // 5. presenter는 view에 가공한 데이터를 전달
        view.updateMovieList(movieSelectViewData)
    }

    private fun movieSelectItemToViewData(movieItem: List<MovieItemCategory>): List<MovieSelectViewData> =
        movieItem.map { movieSelectItem ->
            when (movieSelectItem) {
                is MovieItemCategory.Movie ->
                    MovieSelectViewData.Movie(
                        screeningToScreeningData(
                            movieSelectItem.screening,
                        ),
                    )

                is MovieItemCategory.Ad -> MovieSelectViewData.Ad(adToAdData(movieSelectItem.advertisement))
            }
        }

    private fun adToAdData(ad: Advertisement): AdvertisementData =
        AdvertisementData(
            ResourceMapper.adIdToBannerImageResource(ad.adId),
        )

    private fun screeningToScreeningData(screening: Screening): ScreeningData =
        ScreeningData(
            title = screening.title,
            startDate = screening.period.start,
            endDate = screening.period.endInclusive,
            movieId = screening.movieId,
            runningTime = screening.runningTime,
            poster = ResourceMapper.movieIdToPosterImageResource(screening.movieId),
        )

    override fun navigateToReservationView(screeningData: ScreeningData) {
        view.navigateToReservationView(screeningData)
    }
}
