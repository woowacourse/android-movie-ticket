package woowacourse.movie.activity.moviedetail

import android.os.Bundle
import android.view.MenuItem
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieModel
import woowacourse.movie.util.getSerializableExtraCompat

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie: MovieModel? = intent.getSerializableExtraCompat(MOVIE_KEY)
        if (movie == null) {
            Toast.makeText(this, DATA_LOADING_ERROR_MESSAGE, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        MovieDetailView(findViewById(R.id.layout_detail_info)).set(movie)
        ReservationInfoView(findViewById(R.id.layout_reservation_info)).set(
            savedInstanceState,
            movie,
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        private const val DATA_LOADING_ERROR_MESSAGE = "데이터가 로딩되지 않았습니다. 다시 시도해주세요."
        const val MOVIE_KEY = "MOVIE"
        const val COUNT_KEY = "COUNT"
        const val SPINNER_DATE_KEY = "SPINNER_DATE"
        const val SPINNER_TIME_KEY = "SPINNER_TIME"
    }
}
