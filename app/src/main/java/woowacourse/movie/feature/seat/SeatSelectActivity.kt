package woowacourse.movie.feature.seat

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.model.Ticket
import java.lang.IllegalArgumentException

class SeatSelectActivity : AppCompatActivity(), SeatSelectContract.View {
    private val seatTable by lazy { findViewById<TableLayout>(R.id.seat_table) }
    private val titleText by lazy { findViewById<TextView>(R.id.title_text) }
    private val reservationAmountText by lazy { findViewById<TextView>(R.id.reservation_amount_text) }
    private val confirmButton by lazy { findViewById<Button>(R.id.confirm_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_seat_select)
        val seatViews =
            seatTable
                .children
                .filterIsInstance<TableRow>()
                .map { it.children.filterIsInstance<TextView>().toList() }
                .toList()

        val movieId = movieId()
        val ticket = ticket()
        if (isError(movieId, ticket)) {
            handleError(IllegalArgumentException(resources.getString(R.string.invalid_key)))
            return
        }
    }

    private fun movieId() = intent.getLongExtra(MOVIE_ID_KEY, MOVIE_ID_DEFAULT_VALUE)

    private fun ticket(): Ticket? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(TICKET_KEY, Ticket::class.java)
        } else {
            intent.getParcelableExtra(TICKET_KEY) as? Ticket
        }
    }

    private fun isError(
        movieId: Long,
        ticket: Ticket?
    ): Boolean {
        return movieId == MOVIE_ID_DEFAULT_VALUE || ticket == null
    }

    override fun handleError(throwable: Throwable) {
        Log.d(TAG, throwable.stackTrace.toString())
        Toast.makeText(this, throwable.localizedMessage, Toast.LENGTH_LONG).show()
        finish()
    }

    companion object {
        private val TAG = SeatSelectActivity::class.simpleName
        private const val MOVIE_ID_KEY = "movie_id"
        private const val MOVIE_ID_DEFAULT_VALUE = -1L
        private const val TICKET_KEY = "ticket_id"

        fun startActivity(
            context: Context,
            movieId: Long,
            ticket: Ticket,
        ) {
            Intent(context, SeatSelectActivity::class.java).run {
                putExtra(MOVIE_ID_KEY, movieId)
                putExtra(TICKET_KEY, ticket)
                context.startActivity(this)
            }
        }
    }
}
