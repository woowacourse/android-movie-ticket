package woowacourse.movie

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(applicationContext)

        setupInsertButton()
        setupDeleteButton()
    }

    private fun setupDeleteButton() {
        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            deleteUsers()
        }
    }

    private fun setupInsertButton() {
        findViewById<Button>(R.id.insertButton).setOnClickListener {
            insertUsers()
        }
    }

    private fun insertUsers() {
        val thread = Thread {
            db.userDao().insertAll(
                User(0, "James", "Seo"),
                User(1, "Bill", "Gates")
            )
            val users = db.userDao().getAll()
            users.forEach {
                Log.d("james", "Before User: ${it.firstName} ${it.lastName}")
            }
        }
        thread.start()
        thread.join()
    }

    private fun deleteUsers() {
        val thread = Thread {
            db.userDao().delete(User(1, "Bill", "Gates"))
            val users = db.userDao().getAll()
            users.forEach {
                Log.d("james", "After User: ${it.firstName} ${it.lastName}")
            }
        }
        thread.start()
        thread.join()
    }
}
