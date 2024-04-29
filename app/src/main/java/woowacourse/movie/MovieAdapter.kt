package woowacourse.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(
    private val onItemClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(
    object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }
) {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val date: TextView = itemView.findViewById(R.id.movie_date)
        val runTime: TextView = itemView.findViewById(R.id.movie_runtime)
        val poster: ImageView = itemView.findViewById(R.id.poster)
        val reservation: Button = itemView.findViewById(R.id.movie_reserve)
        val checkBox: CheckBox = itemView.findViewById(R.id.movie_checkbox)

        init {
            reservation.setOnClickListener {
                onReservationButtonClick(bindingAdapterPosition)
            }

            checkBox.setOnClickListener {
                val movie = getItem(bindingAdapterPosition)
                val updatedMovie = movie.copy(isChecked = movie.isChecked.not())
                submitList(currentList.toMutableList().apply {
                    this[bindingAdapterPosition] = updatedMovie
                })
            }
        }

        private fun onReservationButtonClick(position: Int) {
            onItemClick.invoke(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        Log.d("james", "onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.d("james", "onBindViewHolder")
        val movie = getItem(position)
        holder.title.text = movie.title
        holder.runTime.text = movie.runTime
        holder.date.text = movie.date
        holder.poster.setImageResource(movie.poster)
        holder.checkBox.isChecked = movie.isChecked
    }
}
