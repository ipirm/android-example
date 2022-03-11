package az.azerconnect.bakcell.utils.bindingAdapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.semid.maskedittext.MaskUtil

@BindingAdapter("bind:mask")
fun setMask(
    editText: TextInputEditText,
    mask: String?
) {
    mask?.let {
        val maskUtil = MaskUtil()
        maskUtil.config("", mask, false)
        maskUtil.hideKeyboardWhenMaskComplete(false)
        maskUtil.setup(editText)
    }
}