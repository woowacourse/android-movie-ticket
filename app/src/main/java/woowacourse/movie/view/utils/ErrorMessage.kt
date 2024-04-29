package woowacourse.movie.view.utils

enum class ErrorMessage(val value: kotlin.String) {
    ERROR_INVALID_SCREENING_ID("존재하지 않는 상영 정보입니다."),
    ERROR_NON_POSITIVE_NUMBER("구매 티켓은 1개 이상이어야 합니다."),
    ERROR_OVER_COUNT("예약 가능 인원 수를 초과하였습니다."),
}
