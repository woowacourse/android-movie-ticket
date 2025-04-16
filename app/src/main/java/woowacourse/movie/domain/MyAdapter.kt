package woowacourse.movie.domain

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.CompleteActivity
import woowacourse.movie.R

class MyAdapter(private val items: List<Movie>) : BaseAdapter() {
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item, parent, false)

        val imageView: ImageView = view.findViewById(R.id.movie_image)
        val titleTextView: TextView = view.findViewById(R.id.movie_title)
        val dateTextView: TextView = view.findViewById(R.id.movie_date)
        val timeTextView: TextView = view.findViewById(R.id.movie_time)
        val reserveButton: Button = view.findViewById(R.id.reserve_button)

        imageView.setImageResource(items[position].image)
        titleTextView.text = items[position].title
        dateTextView.text = parent?.context?.getString(R.string.movieDate, items[position].date)
        timeTextView.text = parent?.context?.getString(R.string.movieTime, items[position].time)
        reserveButton.setOnClickListener {
            val intent = Intent(parent?.context, CompleteActivity::class.java)
            intent.putExtra("movieTitle", items[position].title)
            intent.putExtra("movieDate", items[position].date)
            parent?.context?.startActivity(intent)
        }

        return view
    }
}
