package az.azerconnect.bakcell.ui.base

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.utils.dialogs.ErrorDialog
import az.azerconnect.bakcell.utils.dialogs.ProgressDialog


open class BaseDialogFragment : DialogFragment() {
    private val _progressDialog = ProgressDialog(lifecycle)
    private val _errorDialog = ErrorDialog(lifecycle)

    val progressDialog by lazy(LazyThreadSafetyMode.NONE) {
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

    open val viewModel: BaseViewModel? = null

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        val dialog = object : Dialog(activity as AppCompatActivity, R.style.Theme_Bakcell_Dialog) {
            override fun onBackPressed() {
                this@BaseDialogFragment.onBackPressed()
            }
        }

        val window = dialog.window
        window?.attributes?.windowAnimations = getWindowAnimation()
        window?.decorView?.setBackgroundResource(R.color.transparent)

        viewModel?.showErrorDialog
            ?.observe(viewLifecycleOwner) { errorDialog.show(context, it) }

        viewModel?.showProgressDialog
            ?.observe(viewLifecycleOwner) { progressDialog.setUiState(it) }

        return dialog
    }

    open fun getWindowAnimation(): Int {
        return R.style.Theme_Bakcell_Dialog
    }

    open fun onBackPressed() {
        dismiss()
    }
}