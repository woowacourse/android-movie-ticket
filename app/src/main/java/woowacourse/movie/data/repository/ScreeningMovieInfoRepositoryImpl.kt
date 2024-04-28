package woowacourse.movie.data.repository

import woowacourse.movie.domain.model.reservation.ScreeningMovieInfo
import woowacourse.movie.domain.repository.ScreeningMovieInfoRepository

object ScreeningMovieInfoRepositoryImpl : ScreeningMovieInfoRepository {
    private lateinit var screeningMovieInfo: ScreeningMovieInfo

    override fun getScreeningMovieInfo(): ScreeningMovieInfo? {
        return screeningMovieInfo
    }

    override fun saveMovieInfo(screeningMovieInfo: ScreeningMovieInfo) {
        this.screeningMovieInfo = screeningMovieInfo
    }
}
