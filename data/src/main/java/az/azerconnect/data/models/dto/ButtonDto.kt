package az.azerconnect.data.models.dto

import android.view.View
import androidx.annotation.StringRes
import az.azerconnect.data.R

data class ButtonDto(
    @StringRes var text: Int = R.string.ok,
    var onClickListener: View.OnClickListener? = null
)