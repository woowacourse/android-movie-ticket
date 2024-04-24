package woowacourse.movie.presentation.ticketing

import android.view.View
import android.widget.AdapterView
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Count
import woowacourse.movie.model.ScreeningTimeSchedule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    private val movieId: Int,
    savedCount: Int?,
) : TicketingContract.Presenter, AdapterView.OnItemSelectedListener {
    private val movieRepository = MovieRepository()
    private val count = savedCount?.let { Count(it) } ?: Count()

    override fun assignInitialView() {
        movieRepository.findMovieById(movieId)
            .onSuccess { movie ->
                ticketingContractView.assignInitialView(movie, count.value)
                ticketingContractView.setUpDateSpinners(
                    movie.screeningDates.getDatesBetweenStartAndEnd(),
                    this,
                )
            }
            .onFailure {
                ticketingContractView.showErrorMessage(it.message)
            }
    }

    override fun decreaseCount() {
        count.decrease()
        ticketingContractView.updateCount(count.value)
    }

    override fun increaseCount() {
        count.increase()
        ticketingContractView.updateCount(count.value)
    }

    override fun navigate() {
        ticketingContractView.navigate(movieId, count.value)
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selected = parent?.getItemAtPosition(position).toString()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeSlots =
            ScreeningTimeSchedule.generateAvailableTimeSlots(
                LocalDate.parse(selected, formatter),
            )
        ticketingContractView.setUpTimeSpinners(timeSlots)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
