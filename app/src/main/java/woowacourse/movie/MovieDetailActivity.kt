package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import woowacourse.movie.databinding.ActivityMovieDetailBinding
import woowacourse.movie.domain.MovieSchedule

class MovieDetailActivity : BackButtonActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private var restoreInstanceFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreInstanceFlag = true
        val movieData = intent.getParcelableCompat<Movie>("movieData")
        processEmptyMovieData(movieData)

        setViewData(movieData)

        val movieSchedule = MovieSchedule(movieData!!.startDate, movieData.endDate)
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
            binding.tvTicketCount.text = savedInstanceState.getString("ticketCount")
        }
    }

    private fun processEmptyMovieData(movieData: Movie?) {
        if (movieData == null) {
            Toast.makeText(this, "시스템 오류가 발생 했습니다. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
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
                            savedInstanceState.getString("time")
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

        var currentCount = binding.tvTicketCount.text.toString().toInt()

        binding.btTicketCountMinus.setOnClickListener {
            if (binding.tvTicketCount.text == "1") {
                Toast.makeText(this, "1장 이상의 표를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            currentCount--
            binding.tvTicketCount.text = currentCount.toString()
        }

        binding.btTicketCountPlus.setOnClickListener {
            currentCount++
            binding.tvTicketCount.text = currentCount.toString()
        }

        binding.btBookComplete.setOnClickListener {
            val intent = Intent(this, BookCompleteActivity::class.java).apply {
                putExtra(
                    "movieBookingInfo",
                    MovieBookingInfo(
                        movieData, binding.spMovieDate.selectedItem.toString(),
                        binding.spMovieTime.selectedItem.toString(),
                        currentCount
                    )
                )
            }
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("time", binding.spMovieTime.selectedItemPosition.toString())
        outState.putInt("date", binding.spMovieDate.selectedItemPosition)
        outState.putString("ticketCount", binding.tvTicketCount.text.toString())
    }
}
