package movie

class Cinema(
    private val movies: List<MovieInfo>,
) {
    val size = movies.size

    operator fun get(index: Int): MovieInfo = movies[index]
}
