package woowacourse.movie.view.seat

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.view.booking.BookingActivity.Companion.KEY_BOOKING
import woowacourse.movie.view.ext.getSerializable

class SeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val booking =
            intent.getSerializable(KEY_BOOKING, Booking::class.java)?.let {
                initView(it.title)
            }
    }

    private fun initView(movieTitle: String) {
        initMovieTitle(movieTitle)
    }

    private fun initMovieTitle(movieTitle: String) {
        findViewById<TextView>(R.id.tv_title).text = movieTitle
    }
}
