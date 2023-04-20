package woowacourse.movie.activity.movieinformation

import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationBinding
import woowacourse.movie.uimodel.MovieModel
import java.time.format.DateTimeFormatter

class MovieInformationView(
    private val binding: ActivityReservationBinding,
    private val movieModel: MovieModel
) {

    // step1, 2를 하던 중에는 Activity를 어떻게 나누어야할지 감이 오지 않았습니다.
    // 그리고 잭슨님께서 주신 피드백을 계속해서 고민하고 있던 와중에 이에 대해 칭찬을 받은 크루가 있어 참고하였습니다!! ♡◝(・▿・)◜♡

    // 하지만 여전히 바인딩 전체를 받는 것이 좋을지(불필요 정보 포함),
    // 여기서 findViewById를 하는 것이 좋을지(전체 탐색, 성능 낭비)는 고민입니다... (˘•_•˘)
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
