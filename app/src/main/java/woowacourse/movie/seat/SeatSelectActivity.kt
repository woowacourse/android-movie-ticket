package woowacourse.movie.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.Rank
import woowacourse.movie.model.Ticket

class SeatSelectActivity : AppCompatActivity(), SeatSelectContract.View {
    private val seats: GridLayout by lazy { findViewById(R.id.grid_layout_seat_select) }
    private val movieTitleTextView: TextView by lazy { findViewById(R.id.text_view_seat_select_movie_title) }

    private lateinit var presenter: SeatSelectPresenter

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat_select)

        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val ticket =
            intent.getSerializableExtra(TICKET, Ticket::class.java)
                ?: throw IllegalArgumentException("빈 티켓이 넘어 왔습니다.")

        presenter = SeatSelectPresenter(this, movieId, ticket)
        presenter.loadMovieTitle()

        seats.children
            .filterIsInstance<TextView>()
            .forEachIndexed { index, textView ->
                val rank =
                    when (index / 4) {
                        0, 1 -> {
                            Rank.A
                        }
                        2, 3 -> {
                            Rank.S
                        }
                        else -> {
                            Rank.B
                        }
                    }

                textView.setOnClickListener {
                    if (it.isSelected) {
                        presenter.selectSeat { color ->
                            it.setBackgroundColor(getColor(color))
                        }
                    } else {
                        presenter.unselectSeat { color ->
                            it.setBackgroundColor(getColor(color))
                        }
                    }
                    it.isSelected = !it.isSelected
                }
            }
    }

    override fun showMovieTitle(movieTitle: String) {
        movieTitleTextView.text = movieTitle
    }

    override fun showTotalPrice() {
        TODO("Not yet implemented")
    }

    override fun showReservationCheck() {
        TODO("Not yet implemented")
    }

    override fun changeSeatColor(
        isSelected: Boolean,
        onColor: (Int) -> Unit,
    ) {
        if (isSelected) {
            onColor(R.color.white)
        } else {
            onColor(R.color.yellow)
        }
    }

    override fun moveToReservationFinished() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val MOVIE_ID = "movieId"
        private const val TICKET = "ticket"
        private const val DEFAULT_MOVIE_ID = 0

        fun getIntent(
            context: Context,
            movieId: String,
            ticket: Ticket,
        ): Intent {
            return Intent(context, SeatSelectActivity::class.java)
                .putExtra(MOVIE_ID, movieId)
                .putExtra(TICKET, ticket)
        }
    }
}
