package woowacourse.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.R
import woowacourse.movie.adapter.MovieViewHolder.Companion.TAG_KEY
import woowacourse.movie.domain.Movie
import woowacourse.movie.ui.MovieUiModel

class MovieListAdapter(
    private var items: List<Movie>,
    private val onButtonClick: (Movie) -> Unit,
) : BaseAdapter() {
    fun setItems(newItems: List<Movie>) {
        items = newItems
    }

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view: View
        val viewHolder: MovieViewHolder

        if (convertView == null) {
            view =
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.movie_list_item, parent, false)
            viewHolder =
                MovieViewHolder(
                    view.findViewById(R.id.movie_image),
                    view.findViewById(R.id.title),
                    view.findViewById(R.id.start_date),
                    view.findViewById(R.id.end_date),
                    view.findViewById(R.id.running_time),
                    view.findViewById(R.id.reservation_button),
                )
            view.setTag(TAG_KEY, viewHolder)
        } else {
            view = convertView
            viewHolder = view.getTag(TAG_KEY) as MovieViewHolder
        }

        val item = getItem(position)

        val movieUiModel = MovieUiModel.fromDomain(item)

        viewHolder.apply {
            image.setImageResource(movieUiModel.poster)
            title.text = movieUiModel.title
            startDate.text = movieUiModel.startDate
            endDate.text = movieUiModel.endDate
            runningTime.text = movieUiModel.runningTime

            button.setOnClickListener {
                onButtonClick(item)
            }
        }

        return view
    }
}
