package woowacourse.movie.model

import woowacourse.movie.R

class MovieRepository {
    private val movies: MutableList<Movie> = mutableListOf()

    init {
        loadInitialData()
    }

    fun getMovies() = movies.toList()

    fun getMovieAt(position: Int) = movies[position]

    private fun loadInitialData() {
        addMovie(
            Movie(
                R.drawable.poster,
                "해리 포터와 마법사의 돌",
                "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, " +
                    "영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다.",
                "2024.3.1",
                152,
            ),
        )
    }

    private fun addMovie(movie: Movie) {
        movies.add(movie)
    }
}
