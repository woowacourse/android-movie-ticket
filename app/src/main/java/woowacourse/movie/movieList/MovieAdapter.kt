package woowacourse.movie.movieList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.MovieDisplayData

class MovieAdapter(
    context: Context,
    items: List<Any>, // 'Any' type to handle both movies and ads
    private val onClick: (Int) -> Unit
) : ArrayAdapter<Any>(context, 0, items) {

    private val ITEM_VIEW_TYPE_MOVIE = 0
    private val ITEM_VIEW_TYPE_AD = 1

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 4 == 0) ITEM_VIEW_TYPE_AD else ITEM_VIEW_TYPE_MOVIE
    }

    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewType = getItemViewType(position)
        return when (viewType) {
            ITEM_VIEW_TYPE_MOVIE -> {
                val listItemView = convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.movie_list_item, parent, false)
                val movieData = getItem(position) as MovieDisplayData
                listItemView.findViewById<TextView>(R.id.movie_title).text = movieData.title
                listItemView.findViewById<TextView>(R.id.movie_release_date).text =
                    movieData.releaseDate
                listItemView.findViewById<TextView>(R.id.movie_duration).text =
                    movieData.runningTime
                listItemView.findViewById<Button>(R.id.movie_details_button).setOnClickListener {
                    onClick(position)
                }
                listItemView
            }

            ITEM_VIEW_TYPE_AD -> {
                convertView ?: LayoutInflater.from(context)
                    .inflate(R.layout.ad_layout, parent, false)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getCount(): Int {
        val originalCount = super.getCount()
        return originalCount + originalCount / 3
    }

    override fun getItem(position: Int): Any? {
        val adjustedPosition = position - position / 4
        return if (getItemViewType(position) == ITEM_VIEW_TYPE_MOVIE) super.getItem(adjustedPosition) else null
    }

    fun updateItems(items: List<MovieDisplayData>) {
        clear()
        addAll(items)
        notifyDataSetChanged()
    }
}
