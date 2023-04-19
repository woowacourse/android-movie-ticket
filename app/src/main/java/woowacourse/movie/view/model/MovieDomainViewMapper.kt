package woowacourse.movie.view.model

import domain.DateRange
import domain.Movie

class MovieDomainViewMapper : DomainViewMapper<domain.Movie, MovieViewModel> {
    override fun toDomain(movieDto: MovieViewModel): domain.Movie {
        return domain.Movie(
            imagePath = movieDto.picture.toString(),
            title = movieDto.title,
            date = domain.DateRange(movieDto.startDate, movieDto.endDate),
            runningTime = movieDto.runningTime,
            description = movieDto.description
        )
    }

    override fun toView(movie: domain.Movie): MovieViewModel {
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
