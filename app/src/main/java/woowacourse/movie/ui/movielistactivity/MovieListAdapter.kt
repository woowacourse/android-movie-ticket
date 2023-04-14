package woowacourse.movie.ui.movielistactivity

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.MovieData
import woowacourse.movie.R
import woowacourse.movie.ui.DateTimeFormatters.hyphenDateFormatter
import woowacourse.movie.ui.moviebookingactivity.MovieBookingActivity
import woowacourse.movie.util.setOnSingleClickListener

class MovieListAdapter(private val movies: List<MovieData>) : BaseAdapter() {
    private lateinit var inflater: LayoutInflater

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: MovieViewHolder? = null
        var itemLayout: View? = convertView

        if (convertView == null) {
            if (!::inflater.isInitialized) {
                inflater = LayoutInflater.from(parent?.context)
            }
            itemLayout = inflater.inflate(R.layout.movie_list_item, null)

            viewHolder = MovieViewHolder(
                ivPoster = itemLayout.findViewById(R.id.iv_poster),
                tvMovieName = itemLayout.findViewById(R.id.tv_movie_name),
                tvScreeningDay = itemLayout.findViewById(R.id.tv_screening_day),
                tvRunningTime = itemLayout.findViewById(R.id.tv_running_time),
                btnBooking = itemLayout.findViewById(R.id.btn_booking)
            )
            itemLayout.tag = viewHolder
        } else {
            viewHolder = itemLayout?.tag as MovieViewHolder
        }

        val item = movies[position]
        viewHolder.ivPoster.setImageResource(item.posterImage)
        viewHolder.tvMovieName.text = item.title
        viewHolder.tvScreeningDay.text =
            viewHolder.tvScreeningDay.context.getString(R.string.screening_date_format)
                .format(
                    item.screeningDay.start.format(hyphenDateFormatter),
                    item.screeningDay.end.format(hyphenDateFormatter)
                )
        viewHolder.tvRunningTime.text =
            viewHolder.tvRunningTime.context.getString(R.string.running_time_format)
                .format(item.runningTime)

        viewHolder.btnBooking.setOnSingleClickListener {
            val intent = Intent(parent?.context, MovieBookingActivity::class.java).putExtra(
                "movieData",
                item
            )
            parent?.context?.startActivity(intent)
        }

        return itemLayout ?: throw IllegalStateException(NULL_ITEM_LAYOUT_ERROR)
    }

    companion object {
        private const val NULL_ITEM_LAYOUT_ERROR = "itemLayout이 null 값으로 반환되었습니다."
    }
}
