package woowacourse.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieListAdapter(private val movies: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int = movies.size

    override fun getItem(position: Int): Movie = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_movie_list, parent, false)
            viewHolder = ViewHolder()
            viewHolder.initView(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
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

    companion object {
        const val MOVIE_DATA_KEY = "movieData"
    }

    private class ViewHolder {
        var poster: ImageView? = null
        var title: TextView? = null
        var releaseDate: TextView? = null
        var runningTime: TextView? = null
        var bookButton: Button? = null

        fun initView(view: View) {
            poster = view.findViewById(R.id.iv_movie_poster)
            title = view.findViewById(R.id.tv_movie_title)
            releaseDate = view.findViewById(R.id.tv_movie_release_date)
            runningTime = view.findViewById(R.id.tv_movie_running_time)
            bookButton = view.findViewById(R.id.bt_book_now)
        }

        fun setData(movieData: Movie) {
            poster?.setImageResource(movieData.poster)
            title?.text = movieData.title
            releaseDate?.text = movieData.releaseDate
            runningTime?.text = movieData.runningTime
        }
    }
}
