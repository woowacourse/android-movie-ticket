package woowacourse.movie.movieReservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import entity.Screening
import woowacourse.movie.R
import woowacourse.movie.movieTicket.MovieTicketActivity

class ReservationActivity : AppCompatActivity() {
    private val screening by lazy { intent.getSerializableExtra(KEY_MOVIE_Screening) as? Screening ?: throw IllegalArgumentException() }

    private val activityView by lazy { window.decorView.rootView }

    private val contents by lazy { ReservationContents(activityView) }
    private val navigate by lazy { ReservationNavigation(activityView, screening, ::onReservationButtonClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initToolbar()
        initMovieView()
        initNavigate()

        loadInstanceState(savedInstanceState)
    }

    private fun loadInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            navigate.load(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        navigate.save(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.reservation_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieView() {
        contents.update(screening)
    }

    private fun initNavigate() {
        navigate
    }

    private fun onReservationButtonClicked() {
        val movieTicket = screening.reserve(navigate.ticketCount, navigate.selectedDateTime)
        startActivity(
            Intent(this, MovieTicketActivity::class.java).apply {
                putExtra(MovieTicketActivity.KEY_MOVIE_TICKET, movieTicket)
            },
            null,
        )
    }

    companion object {
        const val KEY_MOVIE_Screening = "movieScreening"
    }
}
