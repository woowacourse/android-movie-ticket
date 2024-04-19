package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.model.theater.Theater

class MovieAdapter(context: Context, theaters: List<Theater>) :
    ArrayAdapter<Theater>(context, 0, theaters) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView =
                LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        }

        val theater: Theater? = getItem(position)
        val movie = theater?.movie

        listItemView?.findViewById<TextView>(R.id.movie_title)?.text = movie?.title.toString()
        listItemView?.findViewById<TextView>(R.id.movie_release_date)?.text =
            context.getString(R.string.movie_release_date, movie?.releaseDate)
        listItemView?.findViewById<TextView>(R.id.movie_duration)?.text =
            context.getString(R.string.movie_duration, movie?.runningTime)

        val detailsButton = listItemView?.findViewById<Button>(R.id.movie_details_button)
        val intent = Intent(this.context, MovieDetailActivity::class.java).apply {
            putExtra("Theater", theater)
        }

        detailsButton?.setOnClickListener {
            context.startActivity(intent)
        }
        return listItemView!!
    }
}
