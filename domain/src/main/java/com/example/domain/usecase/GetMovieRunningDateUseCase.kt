package com.example.domain.usecase

import com.example.domain.dateTime.RunningDate
import com.example.domain.model.Movie
import java.time.LocalDate

class GetMovieRunningDateUseCase(private val runningDate: RunningDate = RunningDate()) {
    operator fun invoke(movie: Movie): List<LocalDate> =
        runningDate.getRunningDates(movie.startDate, movie.endDate)
}
