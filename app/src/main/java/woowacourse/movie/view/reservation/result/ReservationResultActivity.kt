package woowacourse.movie.view.reservation.result

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_result)

        setWindowInsets()
        displayReservationResult()
    }

    private fun displayReservationResult() {
        val title = intent.getStringExtra(getString(R.string.bundle_key_movie_title))
        val date = intent.getStringExtra(getString(R.string.bundle_key_movie_date))

        val tvCancelDescription = findViewById<TextView>(R.id.tv_cancel_description)
        tvCancelDescription.text =
            getString(R.string.reservation_result_cancel_time_description, CANCELLATION_TIME)

        val tvMovieTitle = findViewById<TextView>(R.id.tv_movie_title)
        tvMovieTitle.text = title

        val tvMovieDate = findViewById<TextView>(R.id.tv_movie_date)
        tvMovieDate.text = date
    }

    private fun setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val CANCELLATION_TIME = 15
    }
}
