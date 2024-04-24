package woowacourse.movie.presentation.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import woowacourse.movie.R
import woowacourse.movie.domain.model.Ads
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.ScreenViewType
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler

class ScreenRecyclerViewAdapter(
    private val screenActionHandler: ScreenActionHandler,
    private var screens: MutableList<ScreenViewType> = mutableListOf(),
) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return when (screens[position]) {
            is Screen -> VIEW_TYPE_SCREEN
            is Ads -> VIEW_TYPE_ADS
            else -> VIEW_TYPE_ADS
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

    fun updateScreens(newScreens: List<ScreenViewType>) {
        screens.clear()
        screens.addAll(newScreens)
        notifyDataSetChanged()
    }

    inner class ScreenViewHolder(val view: View) : ViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.iv_poster)
        private val title: TextView = view.findViewById(R.id.tv_title)
        private val date: TextView = view.findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

        fun bind(screen: Screen) {
            initView(screen)
            initClickListener(screen)
        }

        private fun initView(screen: Screen) {
            with(screen) {
                poster.setImageResource(movie.imageSrc)
                title.text = movie.title
                date.text = view.context.getString(R.string.screening_period, startDate, endDate)
                runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
            }
        }

        private fun initClickListener(screen: Screen) {
            reserveButton.setOnClickListener {
                screenActionHandler.onScreenClick(screen.id)
            }
        }
    }

    inner class AdsViewHolder(val view: View) : ViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.iv_ads)

        fun bind(ads: Ads) {
            poster.setImageResource(ads.poster)
        }
    }

    companion object {
        private const val VIEW_TYPE_SCREEN = 0
        private const val VIEW_TYPE_ADS = 1
    }
}
