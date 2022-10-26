package givemesomecoffee.ru.tetaanalytics

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import givemesomecoffee.ru.tetaanalytics.analytics.AnalyticsEvent
import givemesomecoffee.ru.tetaanalytics.analytics.AnalyticsManager
import java.util.*

@Preview
@Composable
fun MainScreen(analytics: AnalyticsManager? = null) {
    Column(
        Modifier
            .fillMaxWidth()
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollState()
            )
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            analytics?.onEvent(AnalyticsEvent.Login(UUID.randomUUID().toString()))
        }) {
            Text(text = "Login")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            analytics?.onEvent(AnalyticsEvent.Logout)
        }) {
            Text(text = "Logout")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            analytics?.onEvent(AnalyticsEvent.DefaultEvent())
        }) {
            Text(text = "Send event")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            analytics?.onEvent(AnalyticsEvent.CrashEvent(Exception("test exception")))
        }) {
            Text(text = "Simulate Crash")
        }
    }
}