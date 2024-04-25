package woowacourse.movie.presentation.ui.screen.fake

import woowacourse.movie.domain.model.ScreenViewType
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler
import woowacourse.movie.presentation.ui.screen.ScreenContract

class FakeScreenView : ScreenContract.View, ScreenActionHandler {
    var screens: List<ScreenViewType>? = null
    var detailScreenId: Int? = null
    var toastMessage: MessageType? = null
    var snackBarMessage: MessageType? = null
    var throwable: Throwable? = null

    override fun showScreens(screens: List<ScreenViewType>) {
        this.screens = screens
    }

    override fun navigateToDetail(id: Int) {
        detailScreenId = id
    }

    override fun showToastMessage(messageType: MessageType) {
        toastMessage = messageType
    }

    override fun showToastMessage(e: Throwable) {
        throwable = e
    }

    override fun showSnackBar(messageType: MessageType) {
        snackBarMessage = messageType
    }

    override fun showSnackBar(e: Throwable) {
        throwable = e
    }

    override fun onScreenClick(id: Int) {
    }
}
