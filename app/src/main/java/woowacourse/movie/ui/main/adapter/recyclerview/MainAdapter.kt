package woowacourse.movie.ui.main.adapter.recyclerview

import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.model.main.AdvertisementUiModel
import woowacourse.movie.model.main.MainData
import woowacourse.movie.ui.main.adapter.MainViewType

class MainAdapter(
    private val clickBook: (movieId: Long) -> Unit,
    private val clickAd: (Intent) -> Unit,
) : RecyclerView.Adapter<MainViewHolder>() {
    private val movies = mutableListOf<MainData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val mainViewType = MainViewType.getMainViewType(viewType)
        return when (mainViewType) {
            MainViewType.CONTENT -> MovieViewHolder(parent) { clickBook(movies[it].id) }
            MainViewType.ADVERTISEMENT ->
                AdvertisementViewHolder(parent) { clickAd((movies[it] as AdvertisementUiModel).getIntent()) }
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
