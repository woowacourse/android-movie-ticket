package woowacourse.movie.view.mapper

import domain.DateRange
import domain.Movie
import woowacourse.movie.view.model.MovieViewModel

object MovieMapper : DomainViewMapper<Movie, MovieViewModel> {
    override fun toDomain(movieDto: MovieViewModel): domain.Movie {
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
