package org.dzair.mobile.bridge

import android.webkit.JavascriptInterface
import kotlinx.coroutines.channels.Channel
import org.dzair.mobile.app.AppPreferences
import org.dzair.mobile.events.ActivityEvent
import org.dzair.mobile.events.ActivityEventHandler
import org.dzair.mobile.player.interaction.PlayOptions
import org.dzair.mobile.player.interaction.PlayerEvent
import org.dzair.mobile.settings.VideoPlayerType
import org.jellyfin.sdk.model.extensions.ticks
import org.json.JSONObject
import kotlin.time.Duration.Companion.milliseconds

@Suppress("unused")
class NativePlayer(
    private val appPreferences: AppPreferences,
    private val activityEventHandler: ActivityEventHandler,
    private val playerEventChannel: Channel<PlayerEvent>,
) {

    @JavascriptInterface
    fun isEnabled() = appPreferences.videoPlayerType == VideoPlayerType.EXO_PLAYER

    @JavascriptInterface
    fun loadPlayer(args: String) {
        PlayOptions.fromJson(JSONObject(args))?.let { options ->
            activityEventHandler.emit(ActivityEvent.LaunchNativePlayer(options))
        }
    }

    @JavascriptInterface
    fun pausePlayer() {
        playerEventChannel.trySend(PlayerEvent.Pause)
    }

    @JavascriptInterface
    fun resumePlayer() {
        playerEventChannel.trySend(PlayerEvent.Resume)
    }

    @JavascriptInterface
    fun stopPlayer() {
        playerEventChannel.trySend(PlayerEvent.Stop)
    }

    @JavascriptInterface
    fun destroyPlayer() {
        playerEventChannel.trySend(PlayerEvent.Destroy)
    }

    @JavascriptInterface
    fun seekTicks(ticks: Long) {
        playerEventChannel.trySend(PlayerEvent.Seek(ticks.ticks))
    }

    @JavascriptInterface
    fun seekMs(ms: Long) {
        playerEventChannel.trySend(PlayerEvent.Seek(ms.milliseconds))
    }

    @JavascriptInterface
    fun setVolume(volume: Int) {
        playerEventChannel.trySend(PlayerEvent.SetVolume(volume))
    }
}
