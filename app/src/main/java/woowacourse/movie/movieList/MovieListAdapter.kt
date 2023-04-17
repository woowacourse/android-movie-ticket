package woowacourse.movie.movieList

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import entity.Screening
import woowacourse.movie.R

class MovieListAdapter(
    private val items: List<Screening>,
    private val onClickButton: (Screening) -> Unit,
) : BaseAdapter() {
    private val viewHolder: MutableMap<View, MovieListViewHolder> = mutableMapOf()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Screening {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: initItemView(parent)
        val screening = items[position]

        bindViewHolder(view, screening)
        return view
    }

    private fun initItemView(parent: ViewGroup?): View = View.inflate(
        parent?.context,
        R.layout.item_movie_list,
        null,
    )

    private fun bindViewHolder(view: View, screening: Screening) {
        viewHolder.getOrPut(view) { MovieListViewHolder(view) }
            .bind(
                posterResource = screening.poster,
                title = screening.title,
                date = screening.getReserveDateRange(),
                runningTime = view.context.getString(R.string.movie_running_time).format(screening.runningTime),
                onClickButton = { onClickButton(screening) },
            )
    }
}
