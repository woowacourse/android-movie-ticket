package woowacourse.movie.model.movieInfo

import java.io.Serializable
import java.time.LocalDate

class MovieInfo(val title: Title, val releaseDate: MovieDate, val runningTime: RunningTime, val synopsis: Synopsis): Serializable

