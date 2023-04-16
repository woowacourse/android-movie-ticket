package woowacourse.movie.activity.moviedetail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDTO
import woowacourse.movie.util.getSerializableExtraCompat

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movieDTO: MovieDTO? = intent.getSerializableExtraCompat(MOVIE_KEY)
        if (movieDTO != null) {
            initMovieDetailView(movieDTO)
            initReservationInfoView(savedInstanceState, movieDTO)
        } else {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieDetailView(movieDTO: MovieDTO) {
        MovieDetailViewInitializer(movieDTO).run {
            initImageView(findViewById(R.id.img_movie))
            initTitle(findViewById(R.id.text_title))
            initPlayingDate(findViewById(R.id.text_playing_date))
            initRunningTime(findViewById(R.id.text_running_time))
            initDescription(findViewById(R.id.text_description))
        }
    }

    private fun initReservationInfoView(savedInstanceState: Bundle?, movieDTO: MovieDTO) {
        val savedCount = savedInstanceState?.getInt(COUNT_KEY) ?: DEFAULT_COUNT
        val savedDate = savedInstanceState?.getInt(SPINNER_DATE_KEY) ?: DEFAULT_POSITION
        val savedTime = savedInstanceState?.getInt(SPINNER_TIME_KEY) ?: DEFAULT_POSITION

        ReservationInfoViewInitializer(movieDTO).run {
            initCount(savedCount, findViewById(R.id.text_count))
            initMinusButton(findViewById(R.id.btn_minus), findViewById(R.id.text_count))
            initPlusButton(findViewById(R.id.btn_plus), findViewById(R.id.text_count))
            initReserveButton(
                findViewById(R.id.btn_reserve),
                findViewById(R.id.text_count),
                findViewById(R.id.spinner_date),
                findViewById(R.id.spinner_time)
            )
            initDateSpinner(
                savedDate,
                findViewById(R.id.spinner_date),
                findViewById(R.id.spinner_time)
            )
            initTimeSpinner(
                savedTime,
                findViewById(R.id.spinner_date),
                findViewById(R.id.spinner_time)
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
