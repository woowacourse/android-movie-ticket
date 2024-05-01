package woowacourse.movie.selectseat.uimodel

enum class SeatState {
    SELECTED,
    NONE, ;

    fun reserveState(): SeatState =
        when (this) {
            SELECTED -> NONE
            NONE -> SELECTED
        }
}
