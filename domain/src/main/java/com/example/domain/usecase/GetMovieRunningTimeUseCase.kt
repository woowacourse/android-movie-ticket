package com.example.domain.usecase

import com.example.domain.dateTime.RunningTime
import java.time.LocalDate
import java.time.LocalTime

class GetMovieRunningTimeUseCase(private val runningTime: RunningTime = RunningTime()) {
    operator fun invoke(
        date: LocalDate,
        onResult: (List<LocalTime>) -> Unit = {}
    ) {
        val times = runningTime.getRunningTimes(date)
        onResult(times)
    }
}
