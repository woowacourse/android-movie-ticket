package woowacourse.movie.view.activities.screeninglist

import woowacourse.movie.repository.ScreeningRepository

class ScreeningListPresenter(private val view: ScreeningListContract.View): ScreeningListContract.Presenter {

    override fun loadScreenings() {
        val screenings = ScreeningRepository.findAll()
        view.setScreeningList(screenings)
    }
}
