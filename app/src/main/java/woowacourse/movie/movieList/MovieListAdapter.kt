package woowacourse.movie.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import model.AdModel
import model.ItemRecycler
import model.ReservationModel
import model.ScreeningModel
import woowacourse.movie.R
import java.time.format.DateTimeFormatter

class MovieListAdapter(
    private val items: List<ItemRecycler>,
    private val onClickButton: (ScreeningModel) -> Unit,
    private val onAdClick: (AdModel) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val posterView: ImageView = view.findViewById(R.id.movie_poster)
        private val titleView: TextView = view.findViewById(R.id.movie_title)
        private val releaseDateView: TextView = view.findViewById(R.id.movie_release_date)
        private val runningTimeView: TextView = view.findViewById(R.id.movie_running_time)
        private val reservationButton: View = view.findViewById(R.id.movie_reservation_button)

        fun bind(screeningModel: ScreeningModel) {
            posterView.setImageResource(screeningModel.poster)
            titleView.text = screeningModel.title
            releaseDateView.text = getScreeningDate(screeningModel.reservationModel)
            runningTimeView.text = view.context.getString(R.string.movie_running_time).format(screeningModel.runTime)
            reservationButton.setOnClickListener { onClickButton(screeningModel) }
        }

        private fun getScreeningDate(reservationModel: ReservationModel): String {
            return "${reservationModel.startDate.format(dateTimeFormatter)} ~ ${reservationModel.endDate.format(dateTimeFormatter)}"
        }
    }

    inner class AdViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.ad_banner)
        fun bind(adModel: AdModel) {
            imageView.setImageResource(adModel.image)
            imageView.setOnClickListener { onAdClick(adModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_MOVIE) {
            MovieViewHolder(
                layoutInflater.inflate(R.layout.item_movie_list, parent, false),
            )
        } else {
            AdViewHolder(
                layoutInflater.inflate(R.layout.item_ad_movie_list, parent, false),
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> holder.bind(items[position] as ScreeningModel)
            is AdViewHolder -> holder.bind(items[position] as AdModel)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is ScreeningModel -> TYPE_MOVIE
        is AdModel -> TYPE_AD
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        private const val TYPE_MOVIE = 1
        private const val TYPE_AD = 2
    }
}
