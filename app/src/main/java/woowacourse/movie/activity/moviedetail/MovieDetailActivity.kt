package woowacourse.movie.activity.moviedetail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDTO
import woowacourse.movie.util.getKeyFromIndex
import woowacourse.movie.util.getOrEmptyList
import woowacourse.movie.util.getSerializableExtraCompat

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieDTO: MovieDTO? = intent.getSerializableExtraCompat(MOVIE_KEY)
        if (movieDTO == null) {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        initMovieDetailView(movieDTO)
        initReservationInfoView(savedInstanceState, movieDTO)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieDetailView(movieDTO: MovieDTO) {
        with(MovieDetailView(findViewById(R.id.layout_detail_info))) {
            setImageView(movieDTO.image)
            setTitle(movieDTO.title)
            setPlayingDate(movieDTO.startDate, movieDTO.endDate)
            setRunningTime(movieDTO.runningTime)
            setDescription(movieDTO.description)
        }
    }

    private fun initReservationInfoView(savedInstanceState: Bundle?, movieDTO: MovieDTO) {
        val savedCount = savedInstanceState?.getInt(COUNT_KEY) ?: DEFAULT_COUNT
        val savedDate = savedInstanceState?.getInt(SPINNER_DATE_KEY) ?: DEFAULT_POSITION
        val savedTime = savedInstanceState?.getInt(SPINNER_TIME_KEY) ?: DEFAULT_POSITION

        with(ReservationInfoView(findViewById(R.id.layout_reservation_info))) {
            setCount(savedCount)
            setMinusButton()
            setPlusButton()
            setReserveButton(movieDTO.title)
            setDateSpinner(
                savedDate,
                movieDTO.playingDateTimes
            )
            setTimeSpinner(
                savedTime,
                movieDTO.playingDateTimes.getOrEmptyList(movieDTO.playingDateTimes.getKeyFromIndex(savedDate))
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val countText = findViewById<TextView>(R.id.text_count)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)
        outState.putInt(COUNT_KEY, countText.text.toString().toInt())
        outState.putInt(SPINNER_DATE_KEY, spinnerDate.selectedItemPosition)
        outState.putInt(SPINNER_TIME_KEY, spinnerTime.selectedItemPosition)
    }

    companion object {
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
        const val MOVIE_KEY = "MOVIE"
        const val COUNT_KEY = "COUNT"
        const val SPINNER_DATE_KEY = "SPINNER_DATE"
        const val SPINNER_TIME_KEY = "SPINNER_TIME"
    }
}
