package woowacourse.movie.view.ticket

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.contract.ticket.TicketContract
import woowacourse.movie.domain.reservation.Row
import woowacourse.movie.domain.reservation.Seat
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.presenter.ticket.TicketPresenter
import woowacourse.movie.view.util.ErrorMessage
import java.io.Serializable
import java.time.LocalDateTime

class TicketActivity :
    AppCompatActivity(),
    TicketContract.View {
    private var ticket: Ticket? = null
    private var presenter: TicketContract.Presenter? = null

    private lateinit var cancelDescriptionView: TextView
    private lateinit var priceView: TextView
    private lateinit var countView: TextView
    private lateinit var showtimeView: TextView
    private lateinit var titleView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_ticket)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViews()
        initModel()
        val ticket =
            intent?.getTicketExtra(EXTRA_TICKET) ?: error(
                ErrorMessage(CAUSE_TICKET).notProvided(),
            )
        val seats: Set<Seat> =
            intent.getSeatsExtra() ?: error(
                ErrorMessage(CAUSE_SEATS).notProvided(),
            )
        presenter = TicketPresenter(this, ticket, seats)
        initViews()
    }

    @Suppress("DEPRECATION")
    private fun Intent.getSeatsExtra(): Set<Seat>? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(
                    EXTRA_SEATS,
                    LinkedHashSet::class.java,
                ) as Set<Seat>

            else -> (getSerializableExtra(EXTRA_SEATS) as? Set<Seat>)
        }

    private fun findViews() {
        cancelDescriptionView = findViewById<TextView>(R.id.tv_ticket_cancel_description)
        priceView = findViewById<TextView>(R.id.tv_ticket_price)
        countView = findViewById<TextView>(R.id.tv_ticket_count)
        showtimeView = findViewById<TextView>(R.id.tv_ticket_showtime)
        titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
    }

    private fun initModel() {
        ticket = intent.getTicketExtra(EXTRA_TICKET) ?: error(
            ErrorMessage(CAUSE_TICKET).notProvided(),
        )
    }

    @Suppress("DEPRECATION")
    private fun Intent.getTicketExtra(key: String): Ticket? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(
                    key,
                    Ticket::class.java,
                )

            else -> getSerializableExtra(key) as? Ticket
        }

    private fun initViews() {
        (presenter ?: error(ErrorMessage(CAUSE_TICKET).notProvided())).run {
            presentCancelDescription()
            presentTitle()
            presentShowtime()
            presentCount()
            presentPrice()
        }
    }

    override fun setCancelDescription(minutes: Int) {
        cancelDescriptionView.text =
            getString(
                R.string.ticket_cancel_time_description,
                minutes,
            )
    }

    override fun setMovieTitle(movieTitle: String) {
        titleView.text = movieTitle
    }

    override fun setShowtime(showtime: LocalDateTime) {
        showtimeView.text =
            showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }
    }

    override fun setCount(
        count: Int,
        seats: Set<Seat>,
    ) {
        countView.text =
            getString(R.string.ticket_count, count, seats.joinToString { it.prettyString })
    }

    private val Seat.prettyString: String get() = "${row.prettyString}${column.value}"

    private val Row.prettyString: String get() = ('A' + this.value - 1).toString()

    override fun setPrice(price: Int) {
        priceView.text = getString(R.string.ticket_price, price)
    }

    companion object {
        private const val CAUSE_TICKET = "ticket"
        private const val CAUSE_SEATS = "seats"

        private const val EXTRA_TICKET = "woowacourse.movie.EXTRA_TICKET"
        private const val EXTRA_SEATS = "woowacourse.movie.EXTRA_SEATS"

        fun newIntent(
            context: Context,
            title: String,
            count: Int,
            showtime: LocalDateTime,
            seats: Set<Seat>,
        ): Intent =
            run {
                val ticket = Ticket(title, count, showtime)
                Intent(context, TicketActivity::class.java)
                    .putExtra(EXTRA_TICKET, ticket)
                    .putExtra(EXTRA_SEATS, seats as? Serializable)
            }
    }
}
