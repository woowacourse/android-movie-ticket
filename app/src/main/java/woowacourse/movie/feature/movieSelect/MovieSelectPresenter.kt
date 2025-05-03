package woowacourse.movie.feature.movieSelect

import woowacourse.movie.feature.movieSelect.adapter.AdvertisementData
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.model.movie.Advertisement
import woowacourse.movie.model.movie.screening.Screening
import woowacourse.movie.view.model.ResourceMapper

class MovieSelectPresenter(
    private val view: MovieSelectContract.View,
) : MovieSelectContract.Presenter {
    private var screeningDataList: List<ScreeningData> = emptyList()
    private var adsDataList: List<AdvertisementData> = emptyList()

    override fun loadMovieList() {
        // 3. presenter는 model에서 데이터 획득
        val screenings: List<Screening> = Screening.getDefaultScreenings()
        val ads: List<Advertisement> = Advertisement.getDefaultAds()

        // 4. presenter는 view에 사용할 데이터로 가공
        this.screeningDataList = screeningsToScreeningDatas(screenings)
        this.adsDataList = adsToAdDatas(ads)

        // 5. presenter는 view에 가공한 데이터를 전달
        view.updateMovieList(screeningDataList, adsDataList)
    }

    private fun adsToAdDatas(ads: List<Advertisement>): List<AdvertisementData> =
        ads.map {
            AdvertisementData(
                ResourceMapper.adIdToBannerImageResource(it.adId),
            )
        }

    private fun screeningsToScreeningDatas(screenings: List<Screening>): List<ScreeningData> =
        screenings.map {
            ScreeningData(
                title = it.title,
                startDate = it.period.start,
                endDate = it.period.endInclusive,
                movieId = it.movieId,
                runningTime = it.runningTime,
                poster = ResourceMapper.movieIdToPosterImageResource(it.movieId),
            )
        }

    override fun navigateToReservationView(screeningData: ScreeningData) {
        view.navigateToReservationView(screeningData)
    }
}
