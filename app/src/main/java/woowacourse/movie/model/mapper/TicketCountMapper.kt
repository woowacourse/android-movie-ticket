package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.TicketCount
import woowacourse.movie.model.TicketCountUI

fun TicketCount.toTicketCountUI(): TicketCountUI = TicketCountUI(count)

fun TicketCountUI.toTicketCount(): TicketCount = TicketCount(count)
