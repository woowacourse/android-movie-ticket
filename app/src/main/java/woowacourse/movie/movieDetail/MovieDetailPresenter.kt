package woowacourse.movie.movieDetail

import android.content.Intent
import android.os.Build
import woowacourse.movie.model.theater.Theater
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailPresenter(
    private val view: MovieDetailContract.View,
    intent: Intent
) : MovieDetailContract.Presenter {
    private var ticketNum = 1
    private val theater = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra("Theater", Theater::class.java)
    } else {
        TODO("VERSION.SDK_INT < TIRAMISU")
    }
    val movie = theater?.movie
    override fun load() {
        movie?.let { view.initializeViews(it) }
    }

    override fun onTicketPlusClicked(currentTicketNum: Int) {
        if (currentTicketNum < 10) {
            ticketNum = currentTicketNum + 1
            view.onTicketCountChanged(currentTicketNum)
        } else {
            view.showToast("최대 티켓 수량에 도달했습니다.")
        }
    }

    override fun onTicketMinusClicked(currentTicketNum: Int) {
        if (currentTicketNum > 1) {
            ticketNum = currentTicketNum - 1
            view.onTicketCountChanged(ticketNum)
        } else {
            view.showToast("최소 티켓 수량입니다.")
        }
    }

    override fun getTheater(): Theater? {
        return this.theater
    }

    override fun updateTimeSpinner(date: String) {
        val times = mutableListOf<String>()
        var time = if (isWeekend(date)) 9 else 10
        while (time <= 24) {
            times.add("${time}:00")
            time += 2
        }
        view.updateTimeAdapter(times)
    }

    private fun isWeekend(date: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val parsedDate = LocalDate.parse(date, formatter)
        return parsedDate.dayOfWeek == DayOfWeek.SATURDAY || parsedDate.dayOfWeek == DayOfWeek.SUNDAY
    }

}
