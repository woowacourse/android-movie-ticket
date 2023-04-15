package woowacourse.movie.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

abstract class BackKeyActionBarActivity : AppCompatActivity() {

    @Deprecated("deprecated", ReplaceWith("onCreateView"), DeprecationLevel.WARNING)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onCreateView(savedInstanceState)
    }

    abstract fun onCreateView(savedInstanceState: Bundle?)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    protected fun keyNoExistError(key: String) {
        Toaster.showToast(this, "$key 에 해당하는 value를 전달받지 않았습니다")
        finish()
    }
}
