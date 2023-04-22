package woowacourse.movie.presentation

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.presentation.model.MovieModel
import woowacourse.movie.util.formatScreenDate

class MovieViewHolder(private val view: View, clickBook: (Long) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.textItemTitle)
    private val screeningDate: TextView = view.findViewById(R.id.textBookingScreeningDate)
    private val runningTime: TextView = view.findViewById(R.id.textBookingRunningTime)
    private val itemBook: Button = view.findViewById(R.id.buttonItemBook)
    private val thumbnail: ImageView = view.findViewById(R.id.imageItemThumbnail)

    init {
        itemBook.setOnClickListener { clickBook(adapterPosition.toLong()) }
    }

    fun bind(movie: MovieModel) {
        title.text = movie.title
        runningTime.text = view.context.getString(R.string.running_time)
            .format(movie.runningTime)
        movie.thumbnail?.let { thumbnail.setImageResource(it) }

        screeningDate.apply {
            text = context.getString(R.string.screening_date)
                .format(
                    movie.screeningStartDate.formatScreenDate(),
                    movie.screeningEndDate.formatScreenDate(),
                )
        }
    }
}
