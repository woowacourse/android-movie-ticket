// package woowacourse.movie.ui.main
//
// import android.widget.ListView
// import org.junit.Assert
// import org.junit.Test
// import org.junit.runner.RunWith
// import org.robolectric.Robolectric
// import org.robolectric.RobolectricTestRunner
// import woowacourse.movie.R
// import woowacourse.movie.model.MovieState
//
// @RunWith(RobolectricTestRunner::class)
// internal class MainActivityUnitTest {
//    @Test
//    fun `사용자가 버튼을 한 번 누르면 Hello World가 표시된다`() {
//        val activityController = Robolectric.buildActivity(MainActivity::class.java)
//
//        activityController.use { controller ->
//            controller.setup() // resume 이후의 액티비티에 대해서
//            val activity = controller.get()
//
//            // when
//            val actual =
//                activity.findViewById<ListView>(R.id.listView).adapter.getItem(1) as MovieState
//
//            val expected = "더 퍼스트 슬램덩크 1"
//
//            // then
//            Assert.assertEquals(expected, actual.title)
//        }
//    }
// }
