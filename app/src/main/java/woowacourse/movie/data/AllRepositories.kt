package woowacourse.movie.data

class AllRepositories {
    fun restoreRepositories(): List<MovieAndAd> {
        val movieRepositories = MovieRepositories().movies
        val adRepositories = AdRepositories().ads
        val allRepositories = mutableListOf<MovieAndAd>()
        var index = 0

        movieRepositories.forEachIndexed { movieIndex, movie ->
            allRepositories.add(movie)
            if ((movieIndex + 1) % 3 == 0) {
                allRepositories.add(adRepositories[index % adRepositories.size])
                index++
            }
        }

        return allRepositories.toList()
    }
}
