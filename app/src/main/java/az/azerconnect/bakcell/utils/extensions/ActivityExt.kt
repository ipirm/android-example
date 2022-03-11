package az.azerconnect.bakcell.utils.extensions

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHost
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.ui.base.BaseActivity
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import setColorFilter

fun AppCompatActivity.findNavHostNavController(id:Int): NavController {
    val navHost = supportFragmentManager.findFragmentById(id) as NavHost
    return navHost.navController
}

fun BaseActivity.changeWindowBackground(drawable: Drawable?) {
    getContentView().background = drawable
}

fun BaseActivity.changeActionBarBackground(drawable: Drawable?) {
    getToolbar().background = drawable
}

fun BaseActivity.changeToolbarTextColor(isLightText: Boolean) {
    val color = if (isLightText) getColorInt(R.color.white) else getColorInt(R.color.black_high_emphasis)

    getToolbar().setTitleTextColor(color)
    getToolbar().navigationIcon?.setColorFilter(color)
}

fun Activity.hideSoftKeyboard() {
    if (KeyboardVisibilityEvent.isKeyboardVisible(this))
        window.decorView.hideSoftKeyboard()
}