package woowacourse.movie.model

import com.example.domain.model.Movie
import woowacourse.movie.mapper.toPlayingTimes
import woowacourse.movie.mapper.toPlayingTimesModel

fun MovieModel.toMovie() = Movie(image, title, playingTimes.toPlayingTimes(), runningTime, description)

fun Movie.toMovieModel() = MovieModel(image, title, playingTimes.toPlayingTimesModel(), runningTime, description)
