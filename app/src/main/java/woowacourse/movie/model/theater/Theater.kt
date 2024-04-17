package woowacourse.movie.model.theater

import woowacourse.movie.model.movieInfo.MovieInfo
import java.io.Serializable

class Theater(val movie: MovieInfo, val charge: Int = 13000): Serializable
