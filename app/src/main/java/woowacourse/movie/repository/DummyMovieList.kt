package woowacourse.movie.repository

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
import java.time.LocalDate

object DummyMovieList : MovieListRepository {
    override val list: List<Movie>
        get() = _list.toList()

    private val _list: MutableList<Movie> =
        mutableListOf(
            Movie(
                id = 1,
                title = "해리 포터와 마법사의 돌",
                runningTime = 152,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "example description",
                imgResId = R.drawable.harry_potter_1,
            ),
            Movie(
                id = 2,
                title = "해리 포터와 비밀의 방",
                runningTime = 141,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "example description",
                imgResId = R.drawable.harry_potter_2,
            ),
            Movie(
                id = 3,
                title = "해리 포터와 아즈카반의 죄수",
                runningTime = 156,
                screenPeriod = listOf(LocalDate.of(2024, 4, 1)),
                description = "example description",
                imgResId = R.drawable.harry_potter_3,
            ),
        )

    override fun listSize(): Int {
        return _list.size
    }

    override fun findOrNull(id: Long): Movie? {
        val filteredList = list.filter { it.id == id }
        return if (filteredList.size == 1) filteredList[0] else null
    }

    override fun findOrNull(title: String): Movie? {
        val filteredList = list.filter { it.title == title }
        return if (filteredList.size == 1) filteredList[0] else null
    }
}
