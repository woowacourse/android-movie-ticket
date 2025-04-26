package woowacourse.movie.ui.view.booking

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.MovieTicket
import woowacourse.movie.presenter.BookingSummaryPresenter
import woowacourse.movie.ui.mapper.PosterMapper
import woowacourse.movie.ui.view.BaseActivity
import woowacourse.movie.ui.view.utils.intentSerializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BookingSummaryActivity :
    BaseActivity(),
    BookingSummaryContract.View {
    private val presenter = BookingSummaryPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScreen(R.layout.activity_bookingsummary)
        presenter.loadBookingSummary()
    }

    override fun showBookingSummary() {
        val movieTicket =
            intent.intentSerializable(getString(R.string.ticket_info_key), MovieTicket::class.java)
        val title = findViewById<TextView>(R.id.title)
        val screeningDateTime = findViewById<TextView>(R.id.screeningDateTime)
        val headCount = findViewById<TextView>(R.id.headCount)
        val amount = findViewById<TextView>(R.id.amount)

        title.text = PosterMapper.mapMovieIdToMovieTitle(movieTicket.movieId)
        screeningDateTime.text = formatDateTime(movieTicket.screeningDateTime)
        headCount.text =
            formatHeadCount(
                getString(R.string.headCount_message),
                movieTicket.headCount,
                movieTicket.selectedSeats,
            )
        amount.text =
            formatAmount(getString(R.string.amount_message), movieTicket.amount)
    }

    companion object {
        private fun formatDateTime(dateTime: LocalDateTime): String {
            val dateTimeFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")

            return dateTime.format(dateTimeFormatter)
        }

        private fun formatHeadCount(
            message: String,
            headCount: Int,
            selectedSeats: List<String>,
        ): String = String.format(message, headCount, selectedSeats.joinToString())

        fun formatAmount(
            message: String,
            value: Int,
        ): String = String.format(message, value.formatWithComma())

        private fun Int.formatWithComma(): String = "%,d".format(this)
    }
}
