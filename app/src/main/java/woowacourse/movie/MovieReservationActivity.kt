package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.ui.DateUi

class MovieReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        val id = intent.getLongExtra(MovieContentKey.ID, -1)
        if (id == -1L) {
            Log.e(TAG, "Invalid MovieContentKey")
        }

        val posterImage = findViewById<ImageView>(R.id.poster_image)
        val titleText = findViewById<TextView>(R.id.title_text)
        val screeningDateText = findViewById<TextView>(R.id.screening_date_text)
        val runningTimeText = findViewById<TextView>(R.id.running_time_text)
        val synopsisText = findViewById<TextView>(R.id.synopsis_text)

        MovieContentsImpl.find(id).run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text = DateUi.format(screeningDate, this@MovieReservationActivity)
            runningTimeText.text = resources.getString(R.string.running_time).format(runningTime)
            synopsisText.text = synopsis
        }
    }

    companion object {
        private val TAG = MovieReservationActivity::class.simpleName
    }
}
