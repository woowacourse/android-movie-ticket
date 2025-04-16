package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reservation_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_reservation_result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        val title = intent.getStringExtra(MainActivity.EXTRA_TITLE)
        val date = intent.getStringExtra(MainActivity.EXTRA_DATE)

        val titleView = findViewById<TextView>(R.id.tv_reservation_result_movie_title)
        titleView.text = title

        val screeningDateView = findViewById<TextView>(R.id.tv_reservation_result_screening_date)
        screeningDateView.text = date
    }
}
