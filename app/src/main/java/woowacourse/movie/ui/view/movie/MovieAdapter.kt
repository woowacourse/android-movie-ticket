package woowacourse.movie.ui.view.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ViewType
import woowacourse.movie.domain.model.ads.Ads
import woowacourse.movie.ui.model.movie.MovieUiModel

class MovieAdapter(
    val movieUiModels: List<MovieUiModel>,
    val onSelectMovieListener: OnSelectMovieListener,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val ads = Ads()

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % ADS_CYCLE == 0) {
            ViewType.ADS.typeNumber
        } else {
            ViewType.MOVIE.typeNumber
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): RecyclerView.ViewHolder {
        return when (ViewType.from(viewType)) {
            ViewType.MOVIE ->
                MovieViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_list_item, parent, false),
                    onSelectMovieListener,
                )

            ViewType.ADS ->
                AdsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.ads_list_item, parent, false),
                )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is MovieViewHolder -> holder.bind(movieUiModel = movieUiModels[position])
            is AdsViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = movieUiModels.size + ads.count(movieUiModels.size, ADS_CYCLE)

    companion object {
        private const val ADS_CYCLE = 4
    }
}
