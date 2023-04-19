package woowacourse.movie.presentation.activities.custom

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue

object RecyclerViewAssertion {
    fun matchItemCount(expected: Int): ViewAssertion = ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) throw noViewFoundException

        assertTrue(view is RecyclerView)

        val recyclerAdapter = (view as RecyclerView).adapter
        val actual = recyclerAdapter?.itemCount
        assertEquals(expected, actual)
    }
}
