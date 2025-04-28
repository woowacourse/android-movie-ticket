package woowacourse.movie.presenter.reservation

import woowacourse.movie.contract.reservation.ScreeningContract
import woowacourse.movie.data.reservation.AdvertisementData
import woowacourse.movie.data.reservation.LocalAdvertisementData
import woowacourse.movie.data.reservation.LocalScreeningData
import woowacourse.movie.data.reservation.ScreeningData
import woowacourse.movie.domain.reservation.DefaultScreeningContentsPolicy
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.domain.reservation.ScreeningContentsPolicy

class ScreeningPresenter(
    private val view: ScreeningContract.View,
    private val screeningData: ScreeningData = LocalScreeningData(),
    private val advertisementData: AdvertisementData = LocalAdvertisementData(),
) : ScreeningContract.Presenter {
    override fun presentScreenings() {
        val screeningContentsPolicy: ScreeningContentsPolicy =
            DefaultScreeningContentsPolicy(screeningData.value, advertisementData.value)
        view.setScreeningContents(screeningContentsPolicy.screeningContents())
    }

    override fun selectScreening(screening: Screening) {
        view.navigateToReservationScreen(screening)
    }
}
