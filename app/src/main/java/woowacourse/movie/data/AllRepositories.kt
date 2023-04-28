package woowacourse.movie.data

import woowacourse.movie.model.MovieAndAd

class AllRepositories {
    fun restoreRepositories(): List<MovieAndAd> {
        val movieRepository = MovieRepository().movies
        val adRepository = AdRepository().ads
        val allRepositories = mutableListOf<MovieAndAd>()
        var index = 0

        movieRepository.forEachIndexed { movieIndex, movie ->
            allRepositories.add(movie)
            if ((movieIndex + PLUS_INDEX) % RULE_AD == ZERO) {
                allRepositories.add(adRepository[index % adRepository.size])
                index++
            }
        }
        return allRepositories.toList()
    }

    companion object {
        private const val PLUS_INDEX = 1
        private const val RULE_AD = 3
        private const val ZERO = 0
    }
}
