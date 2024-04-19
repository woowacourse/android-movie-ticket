package woowacourse.movie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.activity.MovieDetailActivity
import woowacourse.movie.model.theater.Theater

class MovieAdapter : BaseAdapter() {

    private var theaters: List<Theater> = listOf()

    var onClick: ((Int) -> Unit)? = null
    fun setTheaters(theaters: List<Theater>) {
        this.theaters = theaters
    }

    override fun getCount(): Int = theaters.size

    override fun getItem(position: Int): Theater = theaters[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView =
                LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        }

        val theater: Theater? = getItem(position)
        val movie = theater?.movie

        listItemView?.findViewById<TextView>(R.id.movie_title)?.text = movie?.title.toString()
        listItemView?.findViewById<TextView>(R.id.movie_release_date)?.text =
            "상영일: ${movie?.releaseDate}"
        listItemView?.findViewById<TextView>(R.id.movie_duration)?.text =
            "러닝타임: ${movie?.runningTime}분"
        val detailsButton = listItemView?.findViewById<Button>(R.id.movie_details_button)

        detailsButton?.setOnClickListener {
            onClick?.let { it(position) }
        }
        return listItemView
    }
}
