package woowacourse.movie.model

class Movies(val list: List<Movie>) {
    companion object {
        fun of(list: List<Movie>): Movies {
            return Movies(list)
        }
    }
}
