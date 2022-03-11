package az.azerconnect.bakcell.utils.bindingAdapters

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import az.azerconnect.bakcell.utils.OnSingleClickListener
import az.azerconnect.bakcell.utils.extensions.dpToPx
import az.azerconnect.bakcell.utils.getStatusBarHeight
import az.azerconnect.bakcell.utils.widgets.DividerItemDecoration
import az.azerconnect.bakcell.utils.widgets.TextInputLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator


@BindingAdapter("bind:itemDecoration")
fun RecyclerView.setItemDecoration(spaceDp: Int) {
    addItemDecoration(DividerItemDecoration(spaceDp.dpToPx()))
}

@BindingAdapter("bind:showFab")
fun FloatingActionButton.showFab(show: Boolean?) {
    if (show == true)
        show()
    else
        hide()
}

@BindingAdapter("bind:visibility")
fun View.setVisibility(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:onSingleClick")
fun View.setOnSingleClickListener(clickListener: View.OnClickListener?) {
    clickListener?.also {
        setOnClickListener(OnSingleClickListener(it))
    } ?: setOnClickListener(null)
}

@BindingAdapter("bind:textRes")
fun TextView.setText(resId: Int) {
    if (resId > 0)
        this.setText(resId)
}

@BindingAdapter("bind:selected")
fun View.isSelected(selected: Boolean?) {
    this.isSelected = selected == true
}

@BindingAdapter("bind:fitsSystemWindowsToolbar")
fun MaterialToolbar.fitsSystemWindowsToolbar(boolean: Boolean?) {
    setPadding(this.marginLeft, context.getStatusBarHeight(), this.marginLeft, this.marginBottom)
}


@BindingAdapter("bind:nextFocusView", "bind:nextFocusWhen", requireAll = true)
fun TextInputLayout.nextFocusView(nextEdt: TextInputLayout?, next: Boolean?) {
    if (next == true)
        nextEdt?.requestFocus()
}

@BindingAdapter("bind:showSoftKeyboard")
fun EditText.showSoftKeyboard(boolean: Boolean?) {
    context?.let {
        postDelayed({
            requestFocus()
            setSelection(text.length)

            val imm = ContextCompat.getSystemService(context, InputMethodManager::class.java)
            imm?.showSoftInput(this, 0)
        }, 10)
    }
}


@BindingAdapter("bind:isVisible")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("bind:isVisibleElseGone")
fun View.setVisibleElseGone(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("bind:enabled")
fun View.isEnabled(enabled: Boolean?) {
    this.isEnabled = enabled == true
}

@BindingAdapter("bind:smoothProgress")
fun LinearProgressIndicator.setSmoothProgress(progress: Int) {
    val animation = ObjectAnimator.ofInt(this, "progress", progress)
    animation.duration = 300
    animation.interpolator = LinearInterpolator()
    animation.start()
}