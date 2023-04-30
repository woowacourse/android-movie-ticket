package woowacourse.movie.ui.home.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieInfo
import woowacourse.movie.formatScreenDate

class MovieViewHolder(
    itemView: View,
    private val onClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(itemView) {
    private val thumbNailView by lazy { itemView.findViewById<ImageView>(R.id.imageItemThumbnail) }
    private val titleView by lazy { itemView.findViewById<TextView>(R.id.textItemTitle) }
    private val dateView by lazy { itemView.findViewById<TextView>(R.id.textBookingScreeningDate) }
    private val runningTimeView by lazy { itemView.findViewById<TextView>(R.id.textBookingRunningTime) }
    private val bookButton: Button by lazy { itemView.findViewById(R.id.buttonItemBook) }

    init {
        bookButton.setOnClickListener { onClick(adapterPosition) }
    }

    fun onBind(movieUnit: MovieInfo.MovieUnit) {
        initView(movieUnit.movie)
    }

    private fun initView(movieData: Movie) {
        with(movieData) {
            thumbNailView.setImageResource(poster)
            titleView.text = title
            dateView.apply {
                text = dateView.context.getString(R.string.screening_date)
                    .format(
                        screeningStartDate.formatScreenDate(),
                        screeningEndDate.formatScreenDate(),
                    )
            }
            runningTimeView.text =
                runningTimeView.context.getString(R.string.running_time).format(runningTime)
        }
    }
}
