package woowacourse.movie.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.commit
import woowacourse.movie.view.fragments.MovieListFragment
import woowacourse.movie.view.fragments.TicketingFragment
import woowacourse.movie.view.fragments.TicketingResultFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager) {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
            addOnBackStackChangedListener {
                supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
            }

            savedInstanceState?.run {
                val a = getFragment(this, MovieListFragment::class.java.name)
                val b = getFragment(this, TicketingFragment::class.java.name)
                val c = getFragment(this, TicketingResultFragment::class.java.name)
                commit {
                    a?.let {
                        if (!it.isAdded) {
                            add(R.id.fragment_movie, it, MovieListFragment::class.java.name)
                            addToBackStack(null)
                        }
                    }
                    b?.let {
                        if (!it.isAdded) {
                            add(R.id.fragment_movie, it, TicketingFragment::class.java.name)
                            addToBackStack(null)
                        }
                    }
                    c?.let {
                        if (!it.isAdded) {
                            add(R.id.fragment_movie, it, TicketingResultFragment::class.java.name)
                            addToBackStack(null)
                        }
                    }
                }
            } ?: commit {
                add(R.id.fragment_movie, MovieListFragment(), MovieListFragment::class.java.name)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(supportFragmentManager) {
            findFragmentByTag(MovieListFragment::class.java.name)?.let {
                if (it.isAdded) putFragment(outState, MovieListFragment::class.java.name, it)
            }
            findFragmentByTag(TicketingFragment::class.java.name)?.let {
                if (it.isAdded) putFragment(outState, TicketingFragment::class.java.name, it)
            }
            findFragmentByTag(TicketingResultFragment::class.java.name)?.let {
                if (it.isAdded) putFragment(outState, TicketingResultFragment::class.java.name, it)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
