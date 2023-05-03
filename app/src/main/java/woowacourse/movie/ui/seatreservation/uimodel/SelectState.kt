package woowacourse.movie.ui.seatreservation.uimodel

sealed class SelectState {
    object ABLE : SelectState()
    object DISABLE : SelectState()
    object RETRY : SelectState()
    object MAX : SelectState()
}
