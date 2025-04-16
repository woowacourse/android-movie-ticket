package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
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

        val button = view.findViewById<Button>(R.id.reservation_button)
        button.setOnClickListener {
            val intent = Intent(context,BookingResultActivity::class.java).apply {
                putExtra("TITLE",title.text)
                putExtra("DATE",date.text)
            }
            context.startActivity(intent)
        }

        return view

    }
}
