package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.domain.screening.ScreeningSchedule

data class ScreeningScheduleModel(val dailySchedules: List<DailyScheduleModel>) {
    fun getScheduleDates(): List<String> = dailySchedules.map { it.date }
}

fun ScreeningSchedule.toUiModel(): ScreeningScheduleModel {
    return ScreeningScheduleModel(
        dailySchedules.map { it.toUiModel() }
    )
}
