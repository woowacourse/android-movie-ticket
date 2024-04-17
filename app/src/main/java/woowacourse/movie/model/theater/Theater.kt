package woowacourse.movie.model.theater

import woowacourse.movie.model.movieInfo.MovieInfo
import java.io.Serializable

class Theater(private val movie: MovieInfo, private val charge: Int = 13000): Serializable
