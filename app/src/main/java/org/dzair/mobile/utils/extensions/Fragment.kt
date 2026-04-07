@file:Suppress("NOTHING_TO_INLINE")

package org.dzair.mobile.utils.extensions

import androidx.fragment.app.Fragment
import org.dzair.mobile.MainActivity

inline fun Fragment.requireMainActivity(): MainActivity = requireActivity() as MainActivity
