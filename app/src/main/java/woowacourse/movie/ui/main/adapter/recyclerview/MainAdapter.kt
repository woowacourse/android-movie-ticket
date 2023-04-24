package woowacourse.movie.ui.main.adapter.recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.model.main.MainData
import woowacourse.movie.ui.main.adapter.MainViewType

class MainAdapter(
    context: Context,
    private val clickBook: (Long) -> Unit,
    private val clickAd: (Intent) -> Unit,
) : RecyclerView.Adapter<MainViewHolder>() {
    private val movies = mutableListOf<MainData>()
    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val mainViewType = MainViewType.getMainViewType(viewType)
        val viewHolder: MainViewHolder = when (mainViewType) {
            MainViewType.CONTENT -> {
                MovieViewHolder(layoutInflater.inflate(R.layout.movie_list_item, parent, false))
            }
            MainViewType.ADVERTISEMENT -> {
                AdvertisementViewHolder(
                    layoutInflater.inflate(R.layout.advertisement_list_item, parent, false),
                )
            }
        }
        setViewHolderClick(viewHolder)
        return viewHolder
    }

    private fun setViewHolderClick(viewHolder: MainViewHolder) {
        when (viewHolder) {
            is MovieViewHolder -> viewHolder.setBookingClick(clickBook)
            is AdvertisementViewHolder -> viewHolder.setAdvertisementClick(clickAd)
        }
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
    override fun getItemViewType(position: Int): Int = movies[position].mainViewType.ordinal

    fun initMovies(items: List<MainData>) {
        movies.clear()
        movies.addAll(items)
        notifyDataSetChanged()
    }
}
