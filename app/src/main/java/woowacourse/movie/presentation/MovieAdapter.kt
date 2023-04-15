package woowacourse.movie.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.formatScreenDate
import woowacourse.movie.presentation.model.MovieModel

class MovieAdapter(context: Context, private val clickBook: (Long) -> Unit) :
    BaseAdapter() {
    private val movies = mutableListOf<MovieModel>()
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): MovieModel = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        initView(position, view)
        clickBookButton(view, position)
        return view
    }

    private fun clickBookButton(view: View, position: Int) {
        view.findViewById<Button>(R.id.buttonItemBook).setOnClickListener {
            clickBook(movies[position].id)
        }
    }

    private fun initView(position: Int, view: View) {
        view.findViewById<ImageView>(R.id.imageItemThumbnail)
            .setImageResource(movies[position].thumbnail)
        view.findViewById<TextView>(R.id.textItemTitle).text = movies[position].title
        view.findViewById<TextView>(R.id.textBookingScreeningDate).apply {
            text = context.getString(R.string.screening_date)
                .format(
                    movies[position].screeningStartDate.formatScreenDate(),
                    movies[position].screeningEndDate.formatScreenDate(),
                )
        }
        view.findViewById<TextView>(R.id.textBookingRunningTime).apply {
            text = context.getString(R.string.running_time).format(movies[position].runningTime)
        }
    }

    fun initMovies(items: List<MovieModel>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }
}
