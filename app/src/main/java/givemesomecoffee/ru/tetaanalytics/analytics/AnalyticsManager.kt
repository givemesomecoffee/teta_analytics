package givemesomecoffee.ru.tetaanalytics.analytics

import androidx.activity.ComponentActivity
import kotlin.reflect.KProperty

interface AnalyticsManager {
    fun onEvent(event: AnalyticsEvent)
    operator fun getValue(mainActivity: ComponentActivity, property: KProperty<*>): AnalyticsManager
}