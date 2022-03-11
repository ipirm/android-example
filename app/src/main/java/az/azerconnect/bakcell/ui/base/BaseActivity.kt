package az.azerconnect.bakcell.ui.base

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import az.azerconnect.data.persistence.SessionManager
import az.azerconnect.bakcell.utils.DeepLinkUtil
import az.azerconnect.bakcell.utils.LocaleWrapper
import az.azerconnect.bakcell.utils.extensions.hideSoftKeyboard
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        DeepLinkUtil.handleDeepLink(this, intent.data.toString())
    }

    //For deepLink
    override fun onNewIntent(intent: Intent?) {
        DeepLinkUtil.handleDeepLink(this, intent?.data.toString())
        super.onNewIntent(intent)
    }

    override fun recreate() {
        super.recreate()

        overridePendingTransition(0, 0)
    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()

        onBackPressed()
        return true
    }

    override fun attachBaseContext(newBase: Context) {
        val localeToSwitchTo = Locale(SessionManager.language)
        val localeUpdatedContext: ContextWrapper =
            LocaleWrapper.updateLocale(newBase, localeToSwitchTo)
        super.attachBaseContext(localeUpdatedContext)
    }

    abstract fun getContentView(): View
    abstract fun getToolbar(): MaterialToolbar
}