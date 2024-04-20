package woowacourse.movie.presentation.base

import woowacourse.movie.presentation.model.MessageType

interface BaseView {
    fun showToastMessage(messageType: MessageType)

    fun showSnackBar(messageType: MessageType)

    fun showToastMessage(e: Throwable)

    fun showSnackBar(e: Throwable)
}
