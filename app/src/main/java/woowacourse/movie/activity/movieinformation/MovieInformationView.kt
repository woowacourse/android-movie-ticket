package woowacourse.movie.activity.movieinformation

import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import java.time.format.DateTimeFormatter

class MovieInformationView(
    private val binding: ActivityReservationBinding
) {

    fun set(movieModel: MovieModel) {
        setPosterImageView(movieModel)
        setNameTextView(movieModel)
        setScreeningPeriodTextView(movieModel)
        setRunningTimeTextView(movieModel)
        setDescriptionTextView(movieModel)
    }

    private fun setPosterImageView(movieModel: MovieModel) {
        movieModel.posterImage?.let { id -> binding.moviePosterImageView.setImageResource(id) }
    }

    private fun setNameTextView(movieModel: MovieModel) {
        binding.movieNameTextView.text = movieModel.name.value
    }

    private fun setScreeningPeriodTextView(movieModel: MovieModel) {
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        binding.movieScreeningPeriodTextView.text =
            binding.root.context.getString(R.string.screening_period_form).format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeTextView(movieModel: MovieModel) {
        binding.movieRunningTimeTextView.text =
            binding.root.context
                .getString(R.string.running_time_form)
                .format(movieModel.runningTime)
    }

    private fun setDescriptionTextView(movieModel: MovieModel) {
        binding.movieDescriptionTextView.text = movieModel.description
    }
}
