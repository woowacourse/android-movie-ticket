package woowacourse.movie.presentation.ui.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.presentation.ui.screen.ScreenActionHandler

class ScreenRecyclerViewAdapter(
    private val screenActionHandler: ScreenActionHandler,
    private var screens: MutableList<Screen> = mutableListOf(),
) : RecyclerView.Adapter<ScreenRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = screens.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        holder.bind(screens[position])
    }

    fun updateScreens(newScreens: List<Screen>) {
        screens.clear()
        screens.addAll(newScreens)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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
}
