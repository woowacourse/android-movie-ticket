package woowacourse.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.model.Date
import woowacourse.movie.model.MovieContent

class MovieContentListAdapter(
    private val movieContents: List<MovieContent>,
    private val context: Context,
) : BaseAdapter() {
    override fun getCount(): Int = movieContents.size

    override fun getItem(position: Int): MovieContent = movieContents[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_movie_content, parent, false)

        val posterImage = view.findViewById<ImageView>(R.id.poster_image)
        val titleText = view.findViewById<TextView>(R.id.title_text)
        val screeningDateText = view.findViewById<TextView>(R.id.screening_date_text)
        val runningTimeText = view.findViewById<TextView>(R.id.running_time_text)

        movieContents[position].run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text = screeningDate.format()
            runningTimeText.text = stringFromId(R.string.running_time).format(runningTime)
        }
        return view
    }

    private fun Date.format(): String {
        return stringFromId(R.string.screening_date).format(year, month, day)
    }

    private fun stringFromId(stringId: Int) = context.resources.getString(stringId)
}
