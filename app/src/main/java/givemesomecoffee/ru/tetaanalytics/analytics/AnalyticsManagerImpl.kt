package givemesomecoffee.ru.tetaanalytics.analytics

import androidx.activity.ComponentActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import kotlin.reflect.KProperty

class AnalyticsManagerImpl : AnalyticsManager {
    private var fb: FirebaseAnalytics? = null

    override fun onEvent(event: AnalyticsEvent) {
        when (event) {
            is AnalyticsEvent.CrashEvent -> onCrash(event)
            is AnalyticsEvent.DefaultEvent -> sendEvent(event)
            is AnalyticsEvent.Login -> changeUserId(event.userId)
            AnalyticsEvent.Logout -> changeUserId(null)
        }
    }

    private fun changeUserId(userId: String?) {
        fb?.setUserId(userId)
        YandexMetrica.setUserProfileID(userId)
    }

    override fun getValue(
        mainActivity: ComponentActivity,
        property: KProperty<*>
    ): AnalyticsManager {
        return this
    }

    private fun sendEvent(event: AnalyticsEvent.DefaultEvent) {
        val eventName = if (event.name.isNullOrEmpty()) DEFAULT_EVENT_NAME else event.name
        YandexMetrica.reportEvent(eventName)
        fb?.logEvent(eventName, null)
    }

    private fun onCrash(event: AnalyticsEvent.CrashEvent) {
        YandexMetrica.reportUnhandledException(event.error)
        Firebase.crashlytics.recordException(event.error)
    }

    fun ComponentActivity.init() {
        val config =
            YandexMetricaConfig.newConfigBuilder(APP_METRICA_KEY).withUserProfileID(null).build()
        YandexMetrica.activate(this.application.applicationContext, config)
        YandexMetrica.enableActivityAutoTracking(this.application)
        fb = Firebase.analytics
    }

    companion object{
        const val DEFAULT_EVENT_NAME = "default"
        const val APP_METRICA_KEY = ""
    }
}
