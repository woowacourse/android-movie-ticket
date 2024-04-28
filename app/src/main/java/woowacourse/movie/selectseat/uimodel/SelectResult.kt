package woowacourse.movie.selectseat.uimodel

sealed interface SelectResult {
    data class Exceed(val message: String) : SelectResult

    data class LessSelect(val message: String) : SelectResult

    data object Success : SelectResult
}
