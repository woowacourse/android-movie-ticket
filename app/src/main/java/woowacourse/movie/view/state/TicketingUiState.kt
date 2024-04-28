package woowacourse.movie.view.state

import woowacourse.movie.model.screening.AvailableTimes
import woowacourse.movie.model.screening.Screening

data class TicketingUiState(
    val screening: Screening,
    val availableTimes: AvailableTimes,
)
