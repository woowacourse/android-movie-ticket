package woowacourse.movie.domain

class ReservationScheduler(
    private val dateScheduler: DateScheduler,
    private val timeScheduler: TimeScheduler,
) : DateScheduler by dateScheduler, TimeScheduler by timeScheduler
