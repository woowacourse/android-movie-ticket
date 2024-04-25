package woowacourse.movie.ui.reservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.repository.DummyMovieList
import woowacourse.movie.repository.DummyScreenList
import woowacourse.movie.repository.ScreenListRepository
import woowacourse.movie.ui.SeatSelectionActivity

class ReservationActivity : AppCompatActivity() {
    private lateinit var reservationViewGroup: ReservationViewGroup
    private lateinit var ticket: Ticket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        bindViewGroup()
        ticket = Ticket(intent.getLongExtra("screenId", 0))
        setUpViews(intent.getLongExtra("screenId", 0), DummyScreenList)
        setButtonClickListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun bindViewGroup() {
        reservationViewGroup = ReservationViewGroup(
            imageView = findViewById(R.id.reservation_imageview),
            titleTextView = findViewById(R.id.reservation_title_textview),
            screenDateTextView = findViewById(R.id.reservation_screen_date_textview),
            runningTimeTextView = findViewById(R.id.reservation_running_time_textview),
            descriptionTextView = findViewById(R.id.reservation_description),
            countTextView = findViewById(R.id.reservation_count_textview),
            addButton = findViewById(R.id.reservation_add_button),
            subButton = findViewById(R.id.reservation_sub_button),
            reservationCompleteButton = findViewById(R.id.reservation_complete_button),
        )
    }

    private fun setUpViews(screenId: Long, screenListRepository: ScreenListRepository) {
        reservationViewGroup.updateWithScreenId(screenId, screenListRepository)
        updateTicketCount()
    }

    private fun updateTicketCount() {
        reservationViewGroup.countTextView.text = ticket.count.toString()
    }

    private fun setButtonClickListeners() {
        reservationViewGroup.addButton.setOnClickListener {
            ticket.addCount()
            updateTicketCount()
        }

        reservationViewGroup.subButton.setOnClickListener {
            ticket.subCount()
            updateTicketCount()
        }

        reservationViewGroup.reservationCompleteButton.setOnClickListener {
            val intent = Intent(this, SeatSelectionActivity::class.java)

            this.startActivity(intent)
        }
    }

}

class ReservationViewGroup(
    val imageView: ImageView,
    val titleTextView: TextView,
    val screenDateTextView: TextView,
    val runningTimeTextView: TextView,
    val descriptionTextView: TextView,
    val countTextView: TextView,
    val addButton: Button,
    val subButton: Button,
    val reservationCompleteButton: Button,
) {
    fun updateWithScreenId(screenId: Long, screenListRepository: ScreenListRepository) {
        val screenData = screenListRepository.findOrNull(screenId)
        if (screenData != null) {
            val movieData = DummyMovieList.findOrNull(id = screenData.movieId)
            if (movieData != null) {
                initImageView(movieData)
                initTitleTextView(movieData)
                initScreenDateTextView(movieData)
                initRunningTimeTextView(movieData)
                initDescriptionTextView(movieData)
            }
        }
    }

    private fun initImageView(movie: Movie) {
        imageView.setImageResource(movie.imgResId)
    }

    private fun initTitleTextView(movie: Movie) {
        titleTextView.text = movie.title
    }

    private fun initScreenDateTextView(movie: Movie) {
        screenDateTextView.text = movie.screenPeriodToString()
    }

    private fun initRunningTimeTextView(movie: Movie) {
        runningTimeTextView.text = movie.runningTime.toString()
    }

    private fun initDescriptionTextView(movie:Movie) {
        descriptionTextView.text = movie.description
    }

}
