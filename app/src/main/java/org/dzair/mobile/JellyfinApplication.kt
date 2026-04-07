package org.dzair.mobile

import android.app.Application
import android.webkit.WebView
import org.dzair.mobile.app.apiModule
import org.dzair.mobile.app.applicationModule
import org.dzair.mobile.data.databaseModule
import org.dzair.mobile.utils.JellyTree
import org.dzair.mobile.utils.isWebViewSupported
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import timber.log.Timber

@Suppress("unused")
class JellyfinApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Setup logging
        Timber.plant(JellyTree())

        if (BuildConfig.DEBUG) {
            // Enable WebView debugging
            if (isWebViewSupported()) {
                WebView.setWebContentsDebuggingEnabled(true)
            }
        }

        startKoin {
            androidContext(this@JellyfinApplication)
            fragmentFactory()

            modules(
                applicationModule,
                apiModule,
                databaseModule,
            )
        }
    }
}
