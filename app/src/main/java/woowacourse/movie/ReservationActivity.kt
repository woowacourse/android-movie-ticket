package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import domain.Movie
import java.io.Serializable

class ReservationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val posterImageView: ImageView = findViewById(R.id.movie_image_view1)
        val nameTextView: TextView = findViewById(R.id.movie_name_text_view1)
        val screeningDateTextView: TextView = findViewById(R.id.movie_screening_date_text_view1)
        val runningTimeTextView: TextView = findViewById(R.id.movie_running_time_text_view1)
        val descriptionTextView: TextView = findViewById(R.id.movie_description_text_view1)

        intent.customGetSerializable<Movie>("movie")?.let {
            it.posterImage?.let { id -> posterImageView.setImageResource(id) }
            nameTextView.text = it.name
            screeningDateTextView.text = SCREENING_TIME.format(it.screeningDate.year, it.screeningDate.monthValue, it.screeningDate.dayOfMonth)
            runningTimeTextView.text = RUNNING_TIME.format(it.runningTime)
            descriptionTextView.text = it.description
        }
    }

    @Suppress("DEPRECATION")
    inline fun <reified T : Serializable> Intent.customGetSerializable(key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSerializableExtra(key, T::class.java)
        } else {
            getSerializableExtra(key) as? T
        }
    }

    companion object {
        private const val SCREENING_TIME = "상영일: %d.%d.%d"
        private const val RUNNING_TIME = "러닝타임: %d분"
    }
}
