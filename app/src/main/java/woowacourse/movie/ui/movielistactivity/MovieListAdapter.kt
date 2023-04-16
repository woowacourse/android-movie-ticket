package woowacourse.movie.ui.movielistactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.movie.MovieData

class MovieListAdapter(private val movies: List<MovieData>) : BaseAdapter() {
    private lateinit var inflater: LayoutInflater

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: MovieViewHolder?
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

        viewHolder.setData(movies[position])

        return itemLayout ?: throw IllegalStateException(NULL_ITEM_LAYOUT_ERROR)
    }

    companion object {
        private const val NULL_ITEM_LAYOUT_ERROR = "itemLayout이 null 값으로 반환되었습니다."
    }
}
