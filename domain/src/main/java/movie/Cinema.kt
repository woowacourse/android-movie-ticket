package movie

class Cinema(
    private val movies: List<MovieSchedule>,
) {
    val size = movies.size

    operator fun get(index: Int): MovieSchedule = movies[index]
}
