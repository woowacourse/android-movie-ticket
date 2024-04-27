import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R

class AdvertisementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val advertisementImage: ImageView = itemView.findViewById(R.id.advertisementImage)

    fun bind(advertisementLink: String) {
        advertisementImage.setImageResource(R.drawable.advertisement)
        advertisementImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(advertisementLink))
            itemView.context.startActivity(intent)
        }
    }
}
