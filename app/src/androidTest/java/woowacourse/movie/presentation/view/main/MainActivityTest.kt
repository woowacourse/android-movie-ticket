package woowacourse.movie.presentation.view.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R

class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 네번째_아이템마다_광고를_가져온다() {
        var viewType = 0
        activityRule.scenario.onActivity {
            viewType =
                it.findViewById<RecyclerView>(R.id.rv_movie_list).adapter!!.getItemViewType(3)
        }
        assertEquals(0, viewType)
    }

    @Test
    fun 영화_데이터를_가져온다() {
        var viewType = 0
        activityRule.scenario.onActivity {
            viewType =
                it.findViewById<RecyclerView>(R.id.rv_movie_list).adapter!!.getItemViewType(2)
        }
        assertEquals(1, viewType)
    }
}
