package woowacourse.movie.activity

import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

object InitView {
    fun initTextView(textView: TextView, text: String) {
        textView.text = text
    }

    fun initImageView(imageView: ImageView, img: Int) {
        imageView.setImageResource(img)
    }

    fun initButton(button: Button, onClickListener: OnClickListener) {
        button.setOnClickListener(onClickListener)
    }
}
