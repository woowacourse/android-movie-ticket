package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Price
import woowacourse.movie.domain.TicketingInfo
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticketing)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val movie: Movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("movie", Movie::class.java)
                ?: throw IllegalArgumentException("오류가 발생했습니다.")
        } else {
            intent.getSerializableExtra("movie") as Movie
        }

        val image = findViewById<ImageView>(R.id.img_movie)
        val title = findViewById<TextView>(R.id.text_title)
        val playingDate = findViewById<TextView>(R.id.text_playing_date)
        val runningTime = findViewById<TextView>(R.id.text_running_time)
        val description = findViewById<TextView>(R.id.text_description)
        val countText = findViewById<TextView>(R.id.text_count)
        val minusButton = findViewById<Button>(R.id.btn_minus)
        val plusButton = findViewById<Button>(R.id.btn_plus)
        val ticketingButton = findViewById<Button>(R.id.btn_ticketing)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_time)

        image.setImageResource(movie.image)
        title.text = movie.title
        val dateFormatter = DateTimeFormatter.ofPattern("YYYY.M.d")
        playingDate.text = getText(R.string.playing_time).toString().format(movie.playingTimes.startDate.format(dateFormatter).toString(), movie.playingTimes.endDate.format(dateFormatter).toString())
        runningTime.text = getText(R.string.running_time).toString().format(movie.runningTime.toString())
        description.text = movie.description

        var count = 1
        minusButton.setOnClickListener {
            if (count > 1) {
                count--
                countText.text = count.toString()
            }
        }
        plusButton.setOnClickListener {
            countText.text = (++count).toString()
        }
        val dates = movie.playingTimes.times.keys.sorted()
        val dateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDate.adapter = dateAdapter
        spinnerDate.setSelection(0)
        var times: List<LocalTime> = listOf()
        val timeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTime.adapter = timeAdapter
        spinnerTime.setSelection(0)

        spinnerDate.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, p3: Long) {
                times = movie.playingTimes.times[dates[index]] ?: listOf()
                spinnerTime.adapter = ArrayAdapter(timeAdapter.context, android.R.layout.simple_spinner_item, times)
                timeAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                println()
            }
        }

        ticketingButton.setOnClickListener {
            val intent = Intent(this, MovieTicketActivity::class.java)
            val ticketingInfo = TicketingInfo.of(movie.title, spinnerDate.selectedItem as LocalDate, spinnerTime.selectedItem as LocalTime, count, Price(), "현장")
            intent.putExtra("ticketingInfo", ticketingInfo)
            startActivity(intent)
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
}
