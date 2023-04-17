package woowacourse.movie.ui

import woowacourse.movie.R
import woowacourse.movie.data.MovieDatabase
import woowacourse.movie.data.MovieEntity

object MovieService {
    private val thumbnailMap: Map<Long, Int> = mapOf(
        1L to R.drawable.harry_potter_thumbnail,
        2L to R.drawable.iron_man_thumbnail,
        3L to R.drawable.suzume_thumbnail,
    )

    private val posterMap: Map<Long, Int> = mapOf(
        1L to R.drawable.harry_potter_poster,
        2L to R.drawable.iron_man_poster,
        3L to R.drawable.suzume_poster,
    )

    private fun getThumbnail(id: Long): Int {
        return thumbnailMap[id] ?: throw IllegalArgumentException("없는 아이디 입니다")
    }

    private fun getPoster(id: Long): Int {
        return posterMap[id] ?: throw IllegalArgumentException("없는 아이디 입니다")
    }

    private fun MovieEntity.toUiModel(): MovieUiModel {
        return MovieUiModel(
            id = this.id,
            title = this.title,
            startDate = this.startDate,
            endDate = this.endDate,
            runningTime = this.runningTime,
            description = this.description,
            thumbnail = getThumbnail(this.id),
            poster = getPoster(this.id),
        )
    }

    fun getMovies(): List<MovieUiModel> {
        return MovieDatabase.movies.map { it.toUiModel() }
    }

    fun getMovie(id: Long): MovieUiModel {
        return MovieDatabase.selectMovie(id).toUiModel()
    }
}
