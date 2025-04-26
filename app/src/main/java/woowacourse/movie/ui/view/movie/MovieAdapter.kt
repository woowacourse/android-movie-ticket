package woowacourse.movie.ui.view.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage

class MovieAdapter(
    val movieUiModels: List<MovieUiModel>,
    val onSelectMovieListener: OnSelectMovieListener,
) : BaseAdapter() {
    private inner class ViewHolder(view: View, private val context: Context?) {
        private val poster = view.findViewById<ImageView>(R.id.img_poster)
        private val title = view.findViewById<TextView>(R.id.tv_movie_title)
        private val screeningDate = view.findViewById<TextView>(R.id.tv_movie_screening_date)
        private val runningTime = view.findViewById<TextView>(R.id.tv_movie_running_time)
        private val reserveButton = view.findViewById<Button>(R.id.btn_reserve)

        fun bind(movieUiModel: MovieUiModel) {
            poster.setPosterImage(movieUiModel.poster)
            title.text = movieUiModel.title
            val screeningStartDate = movieUiModel.screeningStartDate
            val screeningEndDate = movieUiModel.screeningEndDate
            screeningDate.text =
                context?.getString(
                    R.string.screening_date_period, screeningStartDate, screeningEndDate,
                )
            runningTime.text = context?.getString(R.string.minute_text, movieUiModel.runningTime)

            reserveButton.setOnClickListener {
                onSelectMovieListener.onSelect(movieUiModel)
            }
        }
    }

    override fun getCount(): Int {
        return movieUiModels.size
    }

    override fun getItem(position: Int): Any {
        return movieUiModels[position]
    }

    override fun getItemId(position: Int): Long {
        return movieUiModels[position].id
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val itemView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            itemView =
                LayoutInflater.from(parent?.context)
                    .inflate(R.layout.movie_list_item, parent, false)
            viewHolder = ViewHolder(itemView, parent?.context)
            itemView.tag = viewHolder
        } else {
            itemView = convertView
            viewHolder = itemView.tag as ViewHolder
        }

        val movieUiModel = movieUiModels[position]
        viewHolder.bind(movieUiModel)

        return itemView
    }
}
