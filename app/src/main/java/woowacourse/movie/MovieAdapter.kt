package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(context: Context, private val clickBook: (Long) -> Unit) :
    BaseAdapter() {
    private val movies = mutableListOf<Movie>()
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = movies[position].id

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: layoutInflater.inflate(R.layout.movie_list_item, parent, false)

        initView(position, view)

        view.findViewById<Button>(R.id.buttonBook).setOnClickListener {
            clickBook(movies[position].id)
        }

        return view
    }

    private fun initView(position: Int, view: View) {
        view.findViewById<ImageView>(R.id.imageThumbnail)
            .setImageResource(movies[position].thumbnail)

        view.findViewById<TextView>(R.id.textTitle).text = movies[position].title

        view.findViewById<TextView>(R.id.textScreeningDate).apply {
            text = context.getString(R.string.screening_date)
                .format(movies[position].screeningDate.formatScreenDate())
        }

        view.findViewById<TextView>(R.id.textRunningTime).apply {
            text = context.getString(R.string.running_time).format(movies[position].runningTime)
        }
    }

    private fun LocalDate.formatScreenDate(): String {
        return format(DateTimeFormatter.ISO_LOCAL_DATE).replace('-', '.')
    }

    fun initMovies(items: List<Movie>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }
}
