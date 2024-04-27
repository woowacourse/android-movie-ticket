package woowacourse.movie.presentation.ui.screen.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.ScreenView
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler

sealed class ScreenRecyclerViewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    class ScreenViewHolder(
        val view: View,
        private val screenActionHandler: ScreenActionHandler,
    ) : ScreenRecyclerViewViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.iv_poster)
        val title: TextView = view.findViewById(R.id.tv_title)
        private val date: TextView = view.findViewById(R.id.tv_screen_date)
        private val runningTime: TextView = view.findViewById(R.id.tv_screen_running_time)
        private val reserveButton: Button = view.findViewById(R.id.btn_reserve_now)

        fun bind(screen: ScreenView.Screen) {
            initView(screen)
            initClickListener(screen)
        }

        private fun initView(screen: ScreenView.Screen) {
            with(screen) {
                poster.setImageResource(movie.imageSrc)
                title.text = movie.title
                date.text = view.context.getString(R.string.screening_period, startDate, endDate)
                runningTime.text = view.context.getString(R.string.running_time, movie.runningTime)
            }
        }

        private fun initClickListener(screen: ScreenView.Screen) {
            reserveButton.setOnClickListener {
                screenActionHandler.onScreenClick(screen.id)
            }
        }
    }

    class AdsViewHolder(val view: View) : ScreenRecyclerViewViewHolder(view) {
        private val poster: ImageView = view.findViewById(R.id.iv_ads)

        fun bind(ads: ScreenView.Ads) {
            poster.setImageResource(ads.poster)
        }
    }
}
