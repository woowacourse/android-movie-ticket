package woowacourse.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.dto.MovieInfo

class MovieListAdapter(
    context: Context,
    items: MutableList<MovieInfo>,
    val function: (MovieInfo) -> Unit,
) : ArrayAdapter<MovieInfo>(context, 0, items) {
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(context)
                .inflate(R.layout.movie_list_item, parent, false)

        val item =
            getItem(position) ?: MovieInfo(
                R.drawable.sample_poster,
                "샘플입니다",
                "2024-1-1",
                "2025-1-1",
                99,
            )
        val image = view.findViewById<ImageView>(R.id.movie_image)
        val title = view.findViewById<TextView>(R.id.title)
        val movieDate = view.findViewById<TextView>(R.id.movie_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)

        item.let {
            image.setImageResource(it.poster)
            title.text = it.title
            movieDate.text =
                context.resources.getString(
                    R.string.movie_date,
                    it.startDate,
                    it.endDate,
                )
            runningTime.text =
                String.format(context.resources.getString(R.string.running_time), it.runningTime)
        }

        val button = view.findViewById<Button>(R.id.reservation_button)
        button.setOnClickListener {
            function(item)
        }
        return view
    }
}
