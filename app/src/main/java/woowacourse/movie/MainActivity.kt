package woowacourse.movie

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import woowacourse.movie.databinding.ActivityMainBinding

@BindingAdapter("imgRes")
fun setImageViewResource(imageView: ImageView, resId: Bitmap) {
    imageView.setImageBitmap(resId)
}

@BindingAdapter("imgResPath")
fun setImageViewResourcePath(imageView: ImageView, @DrawableRes resId: Int) {
    imageView.setImageResource(resId)
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var number = 0
    lateinit var poster1: Bitmap
    var poster2: Drawable? = null
    var poster3: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this

        poster1 = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.poster)
        poster2 = ContextCompat.getDrawable(this, R.drawable.poster)
        poster3 = R.drawable.poster
    }

    fun add() {
        number++
        binding.invalidateAll()
    }

    fun sub() {
        number--
        binding.invalidateAll()
    }
}
