package woowacourse.movie.activity.moviedetail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.Movie
import woowacourse.movie.util.Keys
import woowacourse.movie.util.getVersionDependentSerializableExtra

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie: Movie? = intent.getVersionDependentSerializableExtra(Keys.MOVIE_KEY)
        if (movie != null) {
            initMovieDetailView(movie)
            initReservationInfoView(savedInstanceState, movie)
        } else {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieDetailView(movie: Movie) {
        MovieDetailViewInitializer(movie).run {
            initImageView(findViewById(R.id.img_movie))
            initTitle(findViewById(R.id.text_title))
            initPlayingDate(findViewById(R.id.text_playing_date))
            initRunningTime(findViewById(R.id.text_running_time))
            initDescription(findViewById(R.id.text_description))
        }
    }

    private fun initReservationInfoView(savedInstanceState: Bundle?, movie: Movie) {
        val savedCount = savedInstanceState?.getInt(Keys.COUNT_KEY) ?: DEFAULT_COUNT
        val savedDate = savedInstanceState?.getInt(Keys.SPINNER_DATE_KEY) ?: DEFAULT_POSITION
        val savedTime = savedInstanceState?.getInt(Keys.SPINNER_TIME_KEY) ?: DEFAULT_POSITION

        ReservationInfoViewInitializer(movie).run {
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
        outState.putInt(Keys.COUNT_KEY, countText.text.toString().toInt())
        outState.putInt(Keys.SPINNER_DATE_KEY, spinnerDate.selectedItemPosition)
        outState.putInt(Keys.SPINNER_TIME_KEY, spinnerTime.selectedItemPosition)
    }

    companion object {
        private const val DEFAULT_COUNT = 1
        private const val DEFAULT_POSITION = 0
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
    }
}
