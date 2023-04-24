package woowacourse.movie.activity.movieinformation

import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import java.time.format.DateTimeFormatter

class MovieInformationView(
    private val binding: ActivityReservationBinding,
    private val movieModel: MovieModel
) {

    fun set() {
        setPosterImageView()
        setNameTextView()
        setScreeningPeriodTextView()
        setRunningTimeTextView()
        setDescriptionTextView()
    }

    private fun setPosterImageView() {
        movieModel.posterImage?.let { id -> binding.moviePosterImageView.setImageResource(id) }
    }

    private fun setNameTextView() {
        binding.movieNameTextView.text = movieModel.name.value
    }

    private fun setScreeningPeriodTextView() {
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        binding.movieScreeningPeriodTextView.text =
            binding.root.context.getString(R.string.screening_period_form).format(
                movieModel.screeningPeriod.startDate.format(dateFormat),
                movieModel.screeningPeriod.endDate.format(dateFormat)
            )
    }

    private fun setRunningTimeTextView() {
        binding.movieRunningTimeTextView.text =
            binding.root.context
                .getString(R.string.running_time_form)
                .format(movieModel.runningTime)
    }

    private fun setDescriptionTextView() {
        binding.movieDescriptionTextView.text = movieModel.description
    }
}
