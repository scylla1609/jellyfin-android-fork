package org.dzair.mobile.player.ui

import org.dzair.mobile.utils.Constants

data class DisplayPreferences(
    val skipBackLength: Long = Constants.DEFAULT_SEEK_TIME_MS,
    val skipForwardLength: Long = Constants.DEFAULT_SEEK_TIME_MS,
)
