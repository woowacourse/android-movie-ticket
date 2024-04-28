package woowacourse.movie.ui.home

import woowacourse.movie.domain.movie.ScreenView
import woowacourse.movie.repository.DummyScreenList

class HomePresenter : HomeContract.Presenter {
    override fun loadList(): List<ScreenView> {
        return DummyScreenList.list
    }
}
