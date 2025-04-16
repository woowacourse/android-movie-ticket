package woowacourse.movie

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.ReservationCompleteActivity.Companion.MOVIE_SCREENING_DATE_KEY

class ReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.booking)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val memberPlusButton = findViewById<Button>(R.id.plus_button)
        val memberMinusButton = findViewById<Button>(R.id.minus_button)
        val memberCount = findViewById<TextView>(R.id.count)
        val bookedRunningDayText = findViewById<TextView>(R.id.booked_movie_running_day_text)
        val titleTextView = findViewById<TextView>(R.id.booked_movie_title_text)
        val runningTimeTextView = findViewById<TextView>(R.id.booked_movie_running_time_text)

        memberPlusButton.setOnClickListener {
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.plus(1)
                ?.toString()
                ?: "1"
        }

        memberMinusButton.setOnClickListener {
            memberCount.text = memberCount.text.toString()
                .toIntOrNull()
                ?.minus(1)
                ?.toString()
                ?: "1"
        }

        val screenStartDate: String = intent.getStringExtra(MOVIE_SCREENING_START_DATE_KEY) ?: "2025.04.01"
        val screenEndDate: String = intent.getStringExtra(MOVIE_SCREENING_END_DATE_KEY) ?: "2025.04.01"
        val title:String  = intent.getStringExtra(MOVIE_TITLE_KEY) ?: ""
        val runningTime:String = intent.getStringExtra(MOVIE_RUNNING_TIME_KEY)?:""

        bookedRunningDayText.text = bookedRunningDayText.context.getString(
            R.string.movie_screening_date,
            screenStartDate,
            screenEndDate
        )
        titleTextView.text = title
        runningTimeTextView.text = runningTimeTextView.context.getString(
            R.string.movie_running_time,
            runningTime.toInt()
        )


    }
    companion object {
        const val MOVIE_TITLE_KEY = "title"
        const val MOVIE_SCREENING_START_DATE_KEY = "screeningStartDate"
        const val MOVIE_SCREENING_END_DATE_KEY = "screeningEndDate"
        const val MOVIE_RUNNING_TIME_KEY = "runningTime"
    }
}