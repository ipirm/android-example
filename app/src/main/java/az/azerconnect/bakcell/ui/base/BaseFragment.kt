package az.azerconnect.bakcell.ui.base

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.utils.StatusBarUtil.changeStatusBarMode
import az.azerconnect.bakcell.utils.dialogs.ErrorDialog
import az.azerconnect.bakcell.utils.dialogs.ProgressDialog
import az.azerconnect.bakcell.utils.extensions.*


abstract class BaseFragment : Fragment() {
    private val _progressDialog = ProgressDialog(lifecycle)
    private val _errorDialog = ErrorDialog(lifecycle)

    private val progressDialog by lazy(LazyThreadSafetyMode.NONE) {
        return@lazy _progressDialog.createDialog(
            requireContext(),
            layoutInflater
        )
    }
    val errorDialog by lazy(LazyThreadSafetyMode.NONE) {
        return@lazy _errorDialog.createDialog(
            requireContext(),
            layoutInflater
        )
    }

    protected open val viewModel: BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.showErrorDialog
            ?.observe(viewLifecycleOwner) { errorDialog.show(context, it) }

        viewModel?.showProgressDialog
            ?.observe(viewLifecycleOwner) { progressDialog.setUiState(it) }
    }

    override fun onStart() {
        super.onStart()

        initActionBar()
    }

    private fun initActionBar() {
        if (parentFragment is NavHostFragment) {
            val mainActivity = appCompatActivity() as BaseActivity
            mainActivity.changeWindowBackground(drawable = getWindowBackground())
            mainActivity.changeActionBarBackground(drawable = getActionBarBackground())
            mainActivity.changeToolbarTextColor(isLightText = isLightStatusBarText())

            changeStatusBarMode()

            if (hideActionBar())
                supportActionBar()?.hide()
            else
                supportActionBar()?.show()
        }
    }

    open fun isLightStatusBarText(): Boolean {
        return true
    }

    open fun hideActionBar(): Boolean {
        return false
    }

    open fun getActionBarBackground(): Drawable? {
        return ColorDrawable(context.getColorAttrs(R.attr.colorPrimary))
    }

    open fun getWindowBackground(): Drawable? {
        return ColorDrawable(context.getColorAttrs(android.R.attr.colorBackground))
    }
}