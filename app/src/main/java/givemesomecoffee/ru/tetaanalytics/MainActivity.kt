package givemesomecoffee.ru.tetaanalytics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import givemesomecoffee.ru.tetaanalytics.analytics.AnalyticsManager
import givemesomecoffee.ru.tetaanalytics.analytics.analyticsTest
import givemesomecoffee.ru.tetaanalytics.ui.theme.TetaAnalyticsTheme


class MainActivity : ComponentActivity() {

    private val analytics: AnalyticsManager by analyticsTest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TetaAnalyticsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(analytics = analytics)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TetaAnalyticsTheme {
        MainScreen(analytics = null)
    }
}
