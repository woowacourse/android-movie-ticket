package woowacourse.movie

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources

class MovieAdapter(
    private val items: List<Movie>
) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Movie = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val movieViewHolder: MovieViewHolder
        val view: View
        if (convertView == null) {
            Log.d("james", "new")
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie, parent, false)
            movieViewHolder = MovieViewHolder(view)
            view.tag = movieViewHolder
        } else {
            Log.d("james", "convertView")
            view = convertView
            movieViewHolder = convertView.tag as MovieViewHolder
        }


        val item = items[position]
        movieViewHolder.imageView.background = AppCompatResources.getDrawable(view.context, item.poster)
        movieViewHolder.title.text = item.title
        movieViewHolder.date.text = item.date
        movieViewHolder.runtime.text = item.runTime

        return view
    }

    class MovieViewHolder(view: View) {
        val imageView: ImageView = view.findViewById(R.id.poster)
        val title: TextView = view.findViewById(R.id.movie_title)
        val date: TextView = view.findViewById(R.id.movie_date)
        val runtime: TextView = view.findViewById(R.id.movie_runtime)
    }
}
