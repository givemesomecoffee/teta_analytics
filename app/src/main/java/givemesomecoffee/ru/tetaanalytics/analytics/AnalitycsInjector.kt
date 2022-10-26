package givemesomecoffee.ru.tetaanalytics.analytics

import androidx.activity.ComponentActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

fun ComponentActivity.analyticsTest(): AnalyticsManager {
    val analytics = AnalyticsManagerImpl()
    this.lifecycle.addObserver(object: DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            with(analytics) { this@analyticsTest.init() }
        }
    })
    return analytics
}
