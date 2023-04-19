package woowacourse.movie.view.model

import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Movie

class MovieDomainViewMapper : DomainViewMapper<Movie, MovieViewModel> {
    override fun toDomain(movieDto: MovieViewModel): Movie {
        return Movie(
            imagePath = movieDto.picture.toString(),
            title = movieDto.title,
            date = DateRange(movieDto.startDate, movieDto.endDate),
            runningTime = movieDto.runningTime,
            description = movieDto.description
        )
    }

    override fun toView(movie: Movie): MovieViewModel {
        return MovieViewModel(
            picture = movie.imagePath.toInt(),
            title = movie.title,
            startDate = movie.date.startDate,
            endDate = movie.date.endDate,
            runningTime = movie.runningTime,
            description = movie.description
        )
    }
}
