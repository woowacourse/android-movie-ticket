package woowacourse.movie.view.activities.screeningdetail

import woowacourse.movie.repository.ScreeningRepository

class ScreeningDetailPresenter(private val view: ScreeningDetailContract.View) :
    ScreeningDetailContract.Presenter {

    override fun loadScreeningData(screeningId: Long) {
        val screening = ScreeningRepository.findById(screeningId)
            ?: throw IllegalArgumentException("아이디가 ${screeningId}인 상영이 존재하지 않아서 프레젠터를 생성할 수 없습니다.")
        view.setScreening(screening)
    }

}
