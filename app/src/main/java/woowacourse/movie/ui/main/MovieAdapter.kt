package woowacourse.movie.ui.main

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
import woowacourse.movie.ui.uiModel.Movie

class MovieAdapter(context: Context, private val clickBook: (Long) -> Unit) : BaseAdapter() {
    private val movies = mutableListOf<Movie>()
    private val layoutInflater = LayoutInflater.from(context)
    private val viewHolders = mutableMapOf<Int, MovieViewHolder>()

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        onBindViewHolder(getItem(position), view.hashCode(), view)
        return view
    }

    private fun onBindViewHolder(movie: Movie, key: Int, view: View) {
        if (viewHolders[key] == null) {
            viewHolders[key] = MovieViewHolder(view)
        }
        viewHolders[key]?.onBind(movie)
        viewHolders[key]?.clickBookButton(movie, clickBook)
    }

    fun initMovies(items: List<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val view: View) {
        private val thumbnail: ImageView = view.findViewById(R.id.imageItemThumbnail)
        private val title: TextView = view.findViewById(R.id.textItemTitle)
        private val date: TextView = view.findViewById(R.id.textBookingScreeningDate)
        private val runningTime: TextView = view.findViewById(R.id.textBookingRunningTime)
        private val button: Button = view.findViewById(R.id.buttonItemBook)

        fun onBind(movie: Movie) {
            thumbnail.setImageResource(movie.thumbnail)
            title.text = movie.title
            date.text = view.context.getString(
                R.string.screening_date,
                movie.startDate.formatScreenDate(),
                movie.endDate.formatScreenDate(),
            )
            runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
        }

        fun clickBookButton(movie: Movie, clickBook: (Long) -> Unit) {
            button.setOnClickListener {
                clickBook(movie.id)
            }
        }
    }
}
