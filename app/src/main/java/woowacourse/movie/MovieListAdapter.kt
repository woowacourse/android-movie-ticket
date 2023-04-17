package woowacourse.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import woowacourse.movie.BundleKeys.MOVIE_DATA_KEY

class MovieListAdapter(private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: MovieListViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_movie_list, parent, false)
            viewHolder = MovieListViewHolder()
            viewHolder.initView(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as MovieListViewHolder
            view = convertView
        }
        viewHolder.setData(getItem(position))
        viewHolder.bookButton?.setOnClickListener {
            val intent = Intent(parent?.context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_DATA_KEY, getItem(position))
            parent?.context?.startActivity(intent)
        }
        return view
    }
}
