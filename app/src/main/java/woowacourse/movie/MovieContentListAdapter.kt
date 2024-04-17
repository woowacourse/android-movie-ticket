package woowacourse.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import woowacourse.movie.constants.MovieContentKey
import woowacourse.movie.dao.MovieContentsImpl
import woowacourse.movie.model.MovieContent
import woowacourse.movie.ui.DateUi

class MovieContentListAdapter(
    private val context: Context,
) : BaseAdapter() {
    private val movieContents = MovieContentsImpl.findAll()

    override fun getCount(): Int = movieContents.size

    override fun getItem(position: Int): MovieContent = movieContents[position]

    override fun getItemId(position: Int): Long = getItem(position).id

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
        val reservationButton = view.findViewById<Button>(R.id.reservation_button)

        getItem(position).run {
            posterImage.setImageResource(imageId)
            titleText.text = title
            screeningDateText.text = DateUi.format(screeningDate, context)
            runningTimeText.text = context.resources.getString(R.string.running_time).format(runningTime)
        }

        reservationButton.setOnClickListener {
            Intent(context, MovieReservationActivity::class.java).apply {
                putExtra(MovieContentKey.ID, getItemId(position))
                startActivity(context, this, null)
            }
        }

        return view
    }
}
