package givemesomecoffee.ru.tetaanalytics.analytics

sealed class AnalyticsEvent {
    class Login(val userId: String): AnalyticsEvent()
    object Logout: AnalyticsEvent()
    class DefaultEvent(val name: String? = null): AnalyticsEvent()
    class CrashEvent(val error: Throwable): AnalyticsEvent()
}
