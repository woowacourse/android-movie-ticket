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
        val startDate = view.findViewById<TextView>(R.id.start_date)
        val endDate = view.findViewById<TextView>(R.id.end_date)
        val runningTime = view.findViewById<TextView>(R.id.running_time)

        item?.let {
            image.setImageResource(it.poster)
            title.text = it.title
            startDate.text = it.startDate
            endDate.text = it.endDate
            runningTime.text = it.runningTime
        }

        val button = view.findViewById<Button>(R.id.reservation_button)
        button.setOnClickListener {
            val intent = Intent(context,BookingActivity::class.java).apply {
                putExtra("POSTER",item?.poster)
                putExtra("TITLE",title.text)
                putExtra("START_DATE",startDate.text)
                putExtra("END_DATE",endDate.text)
                putExtra("RUNNING_TIME",runningTime.text)
            }
            context.startActivity(intent)
        }

        return view

    }
}
