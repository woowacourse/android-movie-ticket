package woowacourse.movie.presentation.ui.screen.fake

import woowacourse.movie.domain.model.ScreenView
import woowacourse.movie.presentation.model.MessageType
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler
import woowacourse.movie.presentation.ui.screen.ScreenContract

class FakeScreenView : ScreenContract.View, ScreenActionHandler {
    private var screens: List<ScreenView>? = null
    var detailScreenId: Int? = null
    private var toastMessage: MessageType? = null
    private var snackBarMessage: MessageType? = null
    private var throwable: Throwable? = null

    override fun showScreens(screens: List<ScreenView>) {
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
        detailScreenId = id
    }
}
