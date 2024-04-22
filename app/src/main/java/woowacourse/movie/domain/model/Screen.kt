package woowacourse.movie.domain.model

import java.lang.IllegalStateException

data class Screen(
    override val id: Int,
    override val movie: Movie,
    override val date: String,
    override val price: Int,
) : IScreen

interface IScreen {
    val id: Int
    val movie: IMovie
    val date: String
    val price: Int
}

data class NullScreen(
    override val id: Int = -1,
    override val movie: IMovie = NullMovie(),
    override val date: String = "",
    override val price: Int = -1,
    val throwable: Throwable = IllegalStateException("예기치 못한 오류"),
) : IScreen