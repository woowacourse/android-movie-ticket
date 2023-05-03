package woowacourse.movie.view.activities.screeninglist

import woowacourse.movie.domain.Screening
import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(view: ScreeningListContract.View): ScreeningListContract.Presenter {

    private val screenings: List<Screening> = ScreeningRepository.findAll()

    init {
        view.setScreeningList(screenings)
    }
}
