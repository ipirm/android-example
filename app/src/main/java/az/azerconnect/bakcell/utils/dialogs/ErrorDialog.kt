package az.azerconnect.bakcell.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.databinding.DialogErrorBinding
import az.azerconnect.bakcell.utils.extensions.isNetworkAvailable
import az.azerconnect.data.models.dto.ErrorDialogDto

class ErrorDialog(lifecycle: Lifecycle) : LifecycleEventObserver {
    private lateinit var binding: DialogErrorBinding

    private var model: ErrorDialogDto? = null
    private var dialog: Dialog? = null

    init {
        lifecycle.addObserver(this)
    }

    fun createDialog(context: Context, layoutInflater: LayoutInflater): ErrorDialog {
        binding = DialogErrorBinding.inflate(layoutInflater)

        dialog = Dialog(context, R.style.Theme_Bakcell_Dialog).apply {
            setContentView(binding.root)
            setOnCancelListener {
                model?.showDialog = false
            }
            setOnDismissListener {
                model?.showDialog = false
            }

            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes?.windowAnimations = R.style.Theme_Bakcell_Dialog
            }
        }
        return this
    }

    fun show(context: Context?, model: ErrorDialogDto?) {
        if (!context.isNetworkAvailable()) {
            model?.message = "No internet connection"

            if (model?.showNoInternetDialog == false)
                return
        }

        this.model = model
        binding.model = model

        if (model?.positiveButton?.onClickListener == null)
            model?.positiveButton?.onClickListener = View.OnClickListener { dismiss() }

        if (model?.negativeButton?.onClickListener == null)
            model?.negativeButton?.onClickListener = View.OnClickListener { dismiss() }

        dialog?.setCancelable(model?.cancelable == true)

        show()
    }

    private fun show() {
        if (model?.showDialog == true)
            dialog?.show()
    }

    fun dismiss() {
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