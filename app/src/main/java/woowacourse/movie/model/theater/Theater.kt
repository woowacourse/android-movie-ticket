package woowacourse.movie.model.theater

import woowacourse.movie.model.movieInfo.MovieInfo
import java.io.Serializable

class Theater(val movie: MovieInfo, val charge: Int = INITIAL_PRICE) : Serializable {
    companion object {
        const val INITIAL_PRICE = 13000
    }
}
