package woowacourse.movie.domain.repository

import woowacourse.movie.domain.model.reservation.ScreeningMovieInfo

interface ScreeningMovieInfoRepository {
    fun getScreeningMovieInfo(): ScreeningMovieInfo?

    fun saveMovieInfo(screeningMovieInfo: ScreeningMovieInfo)
}
