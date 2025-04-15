package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieListAdapter(context: Context, items: MutableList<MovieItem>): ArrayAdapter<MovieItem>(context, 0, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)

        val item = getItem(position)
        val image = view.findViewById<ImageView>(R.id.movie_image)
        val title = view.findViewById<TextView>(R.id.title)
        val date = view.findViewById<TextView>(R.id.show_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)

        item?.let {
            image.setImageResource(it.poster)
            title.text = it.title
            date.text = it.date
            runningTime.text = it.runningTime
        }

        return view
    }
}
