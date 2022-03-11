package az.azerconnect.bakcell.utils.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RadioGroup
import android.widget.TextSwitcher
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import az.azerconnect.data.enums.UiState
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import com.google.android.material.button.MaterialButton

fun View.toBitmap(): Bitmap {
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    draw(canvas)
    return bitmap
}

fun View.isVisibleElseInVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun RadioGroup.getCheckedPosition(): Int? {
    val checkedId = checkedRadioButtonId
    return if (checkedId > 0)
        indexOfChild(findViewById(checkedId))
    else
        null
}

fun TextSwitcher.setTextIfDifferent(text: String) {
    if ((this.currentView as TextView).text != text)
        setText(text)
}

fun ViewPager2.removeOverScroll() {
    (getChildAt(0) as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER
}

fun View?.hideSoftKeyboard() {
    this?.let {
        val inputMethodManager =
            ContextCompat.getSystemService(it.context, InputMethodManager::class.java)
        inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun DrawerLayout.lockMode(locked: Boolean) {
    val lockMode =
        if (locked) DrawerLayout.LOCK_MODE_LOCKED_CLOSED else DrawerLayout.LOCK_MODE_UNLOCKED
    this.setDrawerLockMode(lockMode)
}

fun MaterialButton.handleProgress(uiState: UiState, @ColorInt progressColor: Int = Color.BLACK) {
    isEnabled = uiState != UiState.LOADING

    if (!text.isNullOrEmpty() && tag == null)
        tag = text.toString()

    when (uiState) {
        UiState.LOADING ->
            showProgress { this.progressColor = progressColor }
        else ->
            hideProgress(newText = tag?.toString())
    }
}
