@file:Suppress("NOTHING_TO_INLINE")

package org.dzair.mobile.utils.extensions

import androidx.annotation.CheckResult
import org.dzair.mobile.utils.Constants

@get:CheckResult
val IntRange.width: Int
    get() = endInclusive - start

@CheckResult
fun IntRange.scaleInRange(percent: Int): Int {
    return start + width * percent / Constants.PERCENT_MAX
}
