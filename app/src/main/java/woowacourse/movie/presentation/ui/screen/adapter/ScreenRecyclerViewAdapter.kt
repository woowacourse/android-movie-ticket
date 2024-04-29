package woowacourse.movie.presentation.ui.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenView
import woowacourse.movie.domain.model.ScreenView.Ads
import woowacourse.movie.domain.model.ScreenView.Screen
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler
import woowacourse.movie.presentation.ui.screen.adapter.ScreenRecyclerViewViewHolder.AdsViewHolder
import woowacourse.movie.presentation.ui.screen.adapter.ScreenRecyclerViewViewHolder.ScreenViewHolder

class ScreenRecyclerViewAdapter(
    private val screenActionHandler: ScreenActionHandler,
    private val screens: MutableList<ScreenView> = mutableListOf(),
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (screens[position]) {
            is Screen -> VIEW_TYPE_SCREEN
            is Ads -> VIEW_TYPE_ADS
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SCREEN ->
                ScreenViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.holder_screen, parent, false),
                    screenActionHandler,
                )

            VIEW_TYPE_ADS ->
                AdsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.holder_ads, parent, false),
                )

            else ->
                AdsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.holder_ads, parent, false),
                )
        }
    }

    override fun getItemCount(): Int = screens.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (holder) {
            is ScreenViewHolder -> holder.bind(screens[position] as Screen)
            is AdsViewHolder -> holder.bind(screens[position] as Ads)
        }
    }

    fun updateScreens(newScreens: List<ScreenView>) {
        screens.clear()
        screens.addAll(newScreens)
        notifyDataSetChanged()
    }

    fun updateScreen(
        position: Int,
        newScreen: ScreenView,
    ) {
        screens[position] = newScreen
        notifyItemChanged(position)
    }

    companion object {
        private const val VIEW_TYPE_SCREEN = 0
        private const val VIEW_TYPE_ADS = 1
    }
}
