package az.azerconnect.bakcell.utils.extensions

import android.graphics.drawable.Drawable
import androidx.activity.OnBackPressedCallback
import androidx.annotation.DrawableRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import az.azerconnect.bakcell.ui.base.BaseActivity
import com.google.android.material.appbar.MaterialToolbar

fun Fragment.appCompatActivity(): AppCompatActivity {
    return activity as AppCompatActivity
}

fun Fragment.supportActionBar(): ActionBar? {
    return appCompatActivity().supportActionBar
}

fun Fragment.onBackPressedCallback(onBackPressed: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        })
}

fun Fragment.getToolbar(): MaterialToolbar? {
    val mActivity = activity as? BaseActivity
    return mActivity?.let { return it.getToolbar() }
}

fun Fragment.toast(message: Any?) {
    context?.toast(message)
}

fun Fragment.getDrawable(@DrawableRes resId: Int): Drawable? {
    context?.let {
        return ContextCompat.getDrawable(it, resId)
    }

    return null
}

inline fun <reified T : Fragment> newInstance(vararg params: Pair<String, Any?>) =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }


fun Fragment.hideSoftKeyboard() {
    activity?.hideSoftKeyboard()
}