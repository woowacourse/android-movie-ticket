package mapper

import androidx.annotation.DrawableRes
import data.Movie
import model.MovieModel

fun MovieModel.toMovie() = Movie(
    title = title,
    runningTime = runningTime,
    summary = summary,
)

fun Movie.toMovieModel(@DrawableRes poster: Int) = MovieModel(
    title = title,
    runningTime = runningTime,
    summary = summary,
    poster = poster,
)
