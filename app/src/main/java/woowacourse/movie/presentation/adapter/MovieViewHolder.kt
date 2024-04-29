package woowacourse.movie.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.uimodel.MovieUiModel

class MovieViewHolder(
    itemView: View,
    private val buttonClickedListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    val posterImage: ImageView = itemView.findViewById(R.id.posterImage)
    val title: TextView = itemView.findViewById(R.id.title)
    val screeningDate: TextView = itemView.findViewById(R.id.screeningDate)
    val runningTime: TextView = itemView.findViewById(R.id.runningTime)
    val reserveButton: TextView = itemView.findViewById(R.id.reserveButton)

    init {
        reserveButton.setOnClickListener { buttonClickedListener(adapterPosition) }
    }

    fun bind(movie: MovieUiModel) {
        posterImage.setImageResource(movie.posterImageId)
        title.text = movie.title
        screeningDate.text =
            itemView.context.getString(
                R.string.screening_date_format,
                movie.screeningStartDate,
                movie.screeningEndDate,
            )
        runningTime.text =
            itemView.context.getString(
                R.string.running_time_format,
                movie.runningTime,
            )
    }
}
