package woowacourse.movie.view.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.data.ADData
import woowacourse.movie.domain.data.MovieListData
import woowacourse.movie.view.viewmodel.MovieUIModel
import java.time.format.DateTimeFormatter

class MovieRecyclerAdapter(
    private val listData: List<MovieListData>,
    private val onBookListener: OnClickItem,
    private val onClick: (ADData) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickItem {
        fun onClick(movie: MovieUIModel)
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val moviePosterView: ImageView = view.findViewById(R.id.movie_poster)
        private val movieTitleView: TextView = view.findViewById(R.id.movie_title)
        private val screeningDateView: TextView = view.findViewById(R.id.movieList_screening_date)
        private val runningTimeView: TextView = view.findViewById(R.id.movieList_running_time)
        private val bookBtn: Button = view.findViewById(R.id.book_button)

        val context: Context = moviePosterView.context

        fun bind(item: MovieUIModel, onClickItem: OnClickItem) {
            with(item) {
                moviePosterView.setImageResource(moviePoster)
                movieTitleView.text = title
                val startDate =
                    startDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
                val endDate =
                    endDate.format(DateTimeFormatter.ofPattern(context.getString(R.string.date_format)))
                screeningDateView.text = context.getString(R.string.screen_date, startDate, endDate)
                runningTimeView.text = context.getString(R.string.running_time, item.runningTime)
                bookBtn.setOnClickListener {
                    onClickItem.onClick(item)
                }
            }
        }
    }

    class ADHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ad = view.findViewById<ConstraintLayout>(R.id.advertisement)
        fun bind(adData: ADData, onClickListener: (ADData) -> Unit) {
            ad.setBackgroundResource(adData.id)
            ad.setOnClickListener { onClickListener(adData) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return if (viewType == 1) {
            MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, viewGroup, false))
        } else {
            ADHolder(layoutInflater.inflate(R.layout.advertisement, viewGroup, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (listData[position] is MovieUIModel) return 1
        else if (listData[position] is ADData) return 2

        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = listData[position]
        if (holder is MovieViewHolder) {
            holder.bind(item as MovieUIModel, onBookListener)
        } else if (holder is ADHolder) {
            holder.bind(item as ADData, onClick)
        }
    }

    override fun getItemCount() = listData.size
}
