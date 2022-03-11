package az.azerconnect.bakcell.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.databinding.DialogProgressBinding
import az.azerconnect.data.enums.UiState

class ProgressDialog(lifecycle: Lifecycle) : LifecycleEventObserver {
    private lateinit var binding: DialogProgressBinding

    private var uiState: UiState = UiState.SUCCESS
    private var dialog: Dialog? = null

    init {
        lifecycle.addObserver(this)
    }

    fun createDialog(context: Context, layoutInflater: LayoutInflater): ProgressDialog {
        binding = DialogProgressBinding.inflate(layoutInflater)

        dialog = Dialog(context, R.style.Theme_Bakcell_Dialog).apply {
            setContentView(binding.root)
            setCancelable(false)

            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes?.windowAnimations = R.style.Theme_Bakcell_Dialog
            }
        }
        return this
    }

    fun setUiState(uiState: UiState) {
        this.uiState = uiState

        show()
    }

    private fun show() {
        if (uiState == UiState.LOADING)
            dialog?.show()
        else
            dismiss()
    }

    private fun dismiss() {
        dialog?.dismiss()
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME ->
                show()
            Lifecycle.Event.ON_STOP ->
                dismiss()
            else -> {}
        }
    }
}