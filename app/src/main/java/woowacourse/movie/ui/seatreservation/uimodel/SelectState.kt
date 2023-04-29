package woowacourse.movie.ui.seatreservation.uimodel

sealed class SelectState {
    object ABLE : SelectState()
    object DISABLE : SelectState()
    object REABLE : SelectState()
    object MAX : SelectState()
}
