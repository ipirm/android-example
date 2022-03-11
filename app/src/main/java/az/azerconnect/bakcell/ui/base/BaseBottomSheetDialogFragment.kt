package az.azerconnect.bakcell.ui.base


import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.utils.dialogs.ErrorDialog
import az.azerconnect.bakcell.utils.dialogs.ProgressDialog
import az.azerconnect.bakcell.utils.extensions.dpToPx
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {
    var behavior: BottomSheetBehavior<View>? = null

    private val _progressDialog = ProgressDialog(lifecycle)
    private val _errorDialog = ErrorDialog(lifecycle)

    private val progressDialog by lazy {
        return@lazy _progressDialog.createDialog(
            requireContext(),
            layoutInflater
        )
    }
    private val errorDialog by lazy {
        return@lazy _errorDialog.createDialog(
            requireContext(),
            layoutInflater
        )
    }

    open val viewModel: BaseViewModel? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState).apply {
            viewModel?.showErrorDialog
                ?.observe(this@BaseBottomSheetDialogFragment) { errorDialog.show(context, it) }

            viewModel?.showProgressDialog
                ?.observe(this@BaseBottomSheetDialogFragment) { progressDialog.setUiState(it) }

            setOnShowListener {
                val dialog = it as BottomSheetDialog

                val bottomSheet =
                    dialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)

                bottomSheet?.let {
                    if (backgroundRounded()) {
                        bottomSheet.setBackgroundResource(R.drawable.bg_bottom_sheet_dialog)

                        bottomSheet.setPadding(
                            bottomSheet.paddingLeft,
                            32.dpToPx(),
                            bottomSheet.paddingRight,
                            bottomSheet.paddingBottom
                        )
                    } else
                        bottomSheet.setBackgroundResource(R.color.transparent)

                    behavior = BottomSheetBehavior.from(bottomSheet)
                    behavior?.state = BottomSheetBehavior.STATE_EXPANDED
                    behavior?.skipCollapsed = skipCollapsed()
                    behavior?.isHideable = hideable()
                }

                onShowListener()
            }
        }

        return dialog
    }

    open fun backgroundRounded() = true

    open fun onBackPressed() {
        dismiss()
    }

    open fun hideable() = true

    open fun skipCollapsed() = true

    open fun onShowListener() {

    }
}