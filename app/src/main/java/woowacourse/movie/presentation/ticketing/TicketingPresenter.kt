package woowacourse.movie.presentation.ticketing

import android.view.View
import android.widget.AdapterView
import woowacourse.movie.data.MovieRepository
import woowacourse.movie.model.Count
import woowacourse.movie.model.Movie
import woowacourse.movie.model.ScreeningTimeSchedule
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    private val movieRepository: MovieRepository = MovieRepository(),
) : TicketingContract.Presenter, AdapterView.OnItemSelectedListener {
    private val count = Count()
    private lateinit var selectedMovie: Movie
    private var selectedTimePosition = 0

    override fun loadMovieData(id: Int) {
        movieRepository.findMovieById(id)
            .onSuccess { movie ->
                selectedMovie = movie
                ticketingContractView.displayMovieDetail(movie)
                ticketingContractView.setUpDateSpinners(
                    movie.screeningDates.getDatesBetweenStartAndEnd(),
                    this,
                )
                ticketingContractView.displayTicketCount(count.value)
            }
            .onFailure {
                ticketingContractView.showErrorMessage(it.message)
            }
    }

    override fun updateCount(savedCount: Int) {
        count.update(savedCount)
        ticketingContractView.displayTicketCount(count.value)
    }

    override fun updateSelectedTimePosition(savedTimePosition: Int) {
        selectedTimePosition = savedTimePosition
    }

    override fun decreaseCount() {
        count.decrease()
        ticketingContractView.displayTicketCount(count.value)
    }

    override fun increaseCount() {
        count.increase()
        ticketingContractView.displayTicketCount(count.value)
    }

    override fun navigate() {
        ticketingContractView.navigate(selectedMovie.id, count.value)
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
        ticketingContractView.setUpTimeSpinners(timeSlots, selectedTimePosition)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
