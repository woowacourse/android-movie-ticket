package woowacourse.movie.movieList.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.ReservationModel
import model.ScreeningModel
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val posterView: ImageView = view.findViewById(R.id.movie_poster)
    private val titleView: TextView = view.findViewById(R.id.movie_title)
    private val releaseDateView: TextView = view.findViewById(R.id.movie_release_date)
    private val runningTimeView: TextView = view.findViewById(R.id.movie_running_time)
    private val reservationButton: View = view.findViewById(R.id.movie_reservation_button)

    fun bind(
        screeningModel: ScreeningModel,
        onClickButton: (ScreeningModel) -> Unit,
    ) {
        posterView.setImageResource(screeningModel.poster)
        titleView.text = screeningModel.title
        releaseDateView.text = getScreeningDate(screeningModel.reservationModel)
        runningTimeView.text = view.context.getString(R.string.movie_running_time).format(screeningModel.runTime)
        reservationButton.setOnClickListener { onClickButton(screeningModel) }
    }

    private fun getScreeningDate(reservationModel: ReservationModel): String {
        return "${reservationModel.startDate.format(dateTimeFormatter)} ~ ${reservationModel.endDate.format(dateTimeFormatter)}"
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}
