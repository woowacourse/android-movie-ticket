package woowacourse.movie.presentation.view.moviedetail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.domain.MovieSchedule
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityMovieDetailBinding
import woowacourse.movie.model.Movie
import woowacourse.movie.model.MovieBookingInfo
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.view.common.BackButtonActivity
import woowacourse.movie.presentation.view.seatpick.SeatPickerActivity

class MovieDetailActivity : BackButtonActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private var restoreInstanceFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreInstanceFlag = true
        val movieData = intent.getParcelableCompat<Movie>(MOVIE_DATA_INTENT_KEY)
        processEmptyMovieData(movieData)

        setViewData(movieData)

        val movieSchedule =
            MovieSchedule(movieData!!.startDate, movieData.endDate)
        val scheduleDate = movieSchedule.getScheduleDates()

        setViewData(movieData)
        setClickListener(movieData)
        setSpinnerSelectedListener(movieSchedule, scheduleDate, savedInstanceState)
        setSpinnerAdapter(scheduleDate, movieSchedule)
        reloadTicketCountInstance(savedInstanceState)
    }

    private fun setSpinnerAdapter(
        scheduleDate: List<String>,
        movieSchedule: MovieSchedule
    ) {
        binding.spMovieDate.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            scheduleDate
        )
        binding.spMovieTime.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            movieSchedule.getScheduleTimes(scheduleDate[0])
        )
    }

    private fun setViewData(movieData: Movie?) {
        binding.ivMoviePoster.setImageResource(movieData!!.poster)
        binding.tvMovieTitle.text = movieData.title
        binding.tvMovieReleaseDate.text = movieData.releaseDate
        binding.tvMovieRunningTime.text = movieData.runningTime
        binding.tvMovieSynopsis.text = movieData.synopsis
    }

    private fun reloadTicketCountInstance(
        savedInstanceState: Bundle?,
    ) {
        if (savedInstanceState != null) {
            binding.tvTicketCount.text =
                savedInstanceState.getString(USER_TICKET_COUNT_BUNDLE_KEY)
        }
    }

    private fun processEmptyMovieData(movieData: Movie?) {
        if (movieData == null) {
            Toast.makeText(this, getString(R.string.error_intent_message), Toast.LENGTH_SHORT)
                .show()
            this.finish()
        }
    }

    private fun setSpinnerSelectedListener(
        movieSchedule: MovieSchedule,
        scheduleDate: List<String>,
        savedInstanceState: Bundle?
    ) {

        binding.spMovieDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.spMovieTime.adapter = ArrayAdapter(
                    this@MovieDetailActivity,
                    android.R.layout.simple_spinner_item,
                    movieSchedule.getScheduleTimes(scheduleDate[position])
                )
                if (restoreInstanceFlag && savedInstanceState != null) {
                    binding.spMovieTime.setSelection(
                        (
                            savedInstanceState.getString(MOVIE_INFO_TIME_BUNDLE_KEY)
                                ?: movieSchedule.getScheduleTimes(binding.spMovieDate.selectedItem.toString())
                                    .first()
                            ).toInt()
                    )
                    restoreInstanceFlag = false
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setClickListener(movieData: Movie) {

        binding.btTicketCountMinus.setOnClickListener {
            if (binding.tvTicketCount.text == BASE_TICKET_COUNT_CHARACTER) {
                Toast.makeText(
                    this,
                    getString(R.string.error_booking_over_one_ticket),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val newTicketCount = binding.tvTicketCount.text.toString().toInt() - 1
            binding.tvTicketCount.text = newTicketCount.toString()
        }

        binding.btTicketCountPlus.setOnClickListener {
            val newTicketCount = binding.tvTicketCount.text.toString().toInt() + 1
            binding.tvTicketCount.text = newTicketCount.toString()
        }

        binding.btBookComplete.setOnClickListener {
            val intent = Intent(this, SeatPickerActivity::class.java).apply {
                putExtra(
                    SeatPickerActivity.MOVIE_BOOKING_INFO_SCHEDULE_INTENT_KEY,
                    MovieBookingInfo(
                        movieData, binding.spMovieDate.selectedItem.toString(),
                        binding.spMovieTime.selectedItem.toString(),
                        binding.tvTicketCount.text.toString().toInt()
                    )
                )
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(
            MOVIE_INFO_TIME_BUNDLE_KEY,
            binding.spMovieTime.selectedItemPosition.toString()
        )
        outState.putString(
            USER_TICKET_COUNT_BUNDLE_KEY,
            binding.tvTicketCount.text.toString()
        )
    }

    companion object {
        private const val BASE_TICKET_COUNT_CHARACTER = "1"
        const val MOVIE_DATA_INTENT_KEY = "MOVIE_DATA_INTENT_KEY"
        const val MOVIE_INFO_TIME_BUNDLE_KEY = "MOVIE_INFO_TIME_BUNDLE_KEY"
        const val USER_TICKET_COUNT_BUNDLE_KEY = "USER_TICKET_COUNT_BUNDLE_KEY"
    }
}
