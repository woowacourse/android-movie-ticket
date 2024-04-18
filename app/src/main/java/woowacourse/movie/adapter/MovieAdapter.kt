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

//Adapter가 View에 속하는지 Presenter에 속하는지 헷갈립니다. 저희는 우선 presenter라고 생각해서 구현을했는데
// 그러다 보니 View로직이 속해있어 어떻게 해야 할지 모르겠습니다.
// 다시한번 말해 findViewById와 Text값을 바꾸는 로직이 Presenter에 들어가야 된다고 생각했습니다.
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
            "상영일: ${movie?.releaseDate}"
        listItemView?.findViewById<TextView>(R.id.movie_duration)?.text =
            "러닝타임: ${movie?.runningTime}분"
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
