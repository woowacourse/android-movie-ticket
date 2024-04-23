package woowacourse.movie.domain.model

import java.lang.IllegalStateException

data class Movie(
    override val id: Int,
    override val title: String,
    override val runningTime: Int,
    override val description: String,
) : IMovie

interface IMovie {
    val id: Int
    val title: String
    val runningTime: Int
    val description: String
}

data class NullMovie(
    override val id: Int = -1,
    override val title: String = "",
    override val runningTime: Int = -1,
    override val description: String = "",
    val throwable: Throwable = IllegalStateException("예기치 못한 오류"),
) : IMovie
