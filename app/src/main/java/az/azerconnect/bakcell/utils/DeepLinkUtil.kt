package az.azerconnect.bakcell.utils

import android.app.Activity
import android.content.Context
import android.net.Uri
import az.azerconnect.bakcell.R


const val KEY_DEEP_LINK = "deepLink"

fun Context?.deepLinkMain() = this?.getString(R.string.deep_link_main)
class DeepLinkUtil {

    companion object {
        fun handleDeepLink(activity: Activity?, data: String?) {
            if (hasDeepLink(data) == true) {
                activity?.let {
                    activity.intent.data = null
                    activity.intent.putExtra(KEY_DEEP_LINK, data)
                }
            }
        }

        fun hasDeepLink(activity: Activity?): Boolean {
            return activity?.intent?.getStringExtra(KEY_DEEP_LINK) != null
        }

        fun getDeepLink(activity: Activity?): Uri {
            return if (activity?.intent?.hasExtra(KEY_DEEP_LINK) == true)
                Uri.parse(activity.intent?.getStringExtra(KEY_DEEP_LINK))
            else Uri.parse("https://pulpal.az")
        }

        fun deleteDeepLink(activity: Activity?) {
            activity?.intent?.removeExtra(KEY_DEEP_LINK)
        }

        fun hasDeepLink(uri: String?): Boolean? {
            return uri?.contains("://")
        }
    }
}