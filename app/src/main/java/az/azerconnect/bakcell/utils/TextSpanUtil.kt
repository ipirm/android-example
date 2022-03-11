package az.azerconnect.bakcell.utils

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.utils.extensions.getColorAttrs
import java.util.*


fun TextView.setOnSpanClickListener(
    @StringRes clickableTextRes: Int,
    @ColorInt spanColor: Int = context.getColorAttrs(R.attr.colorOnBackground),
    isUnderlineText: Boolean = false,
    isBold: Boolean = true,
    onClick: (() -> Unit)?
) {
    setOnSpanClickListener(
        clickableText = context.getString(clickableTextRes),
        spanColor = spanColor,
        isUnderlineText = isUnderlineText,
        isBold = isBold,
        onClick = onClick
    )
}

fun TextView.setOnSpanClickListener(
    clickableText: String?,
    @ColorInt spanColor: Int = context.getColorAttrs(R.attr.colorOnBackground),
    isUnderlineText: Boolean = false,
    isBold: Boolean = true,
    onClick: (() -> Unit)?
) {
    clickableText?.let {
        val startIndex =
            text.toString().lowercase(Locale.getDefault())
                .indexOf(clickableText.lowercase(Locale.getDefault()))

        val endIndex = startIndex + clickableText.length

        if (startIndex >= 0) {
            val spannable = SpannableString(text)
            val clickableSpan: ClickableSpan = object : ClickableSpan() {
                override fun onClick(textView: View) {

                    onClick?.invoke()
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)

                    ds.isUnderlineText = isUnderlineText
                    highlightColor = Color.TRANSPARENT
                }
            }
            spannable.setSpan(
                clickableSpan, startIndex,
                endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(spanColor), startIndex,
                endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            if (isBold) {
                spannable.setSpan(
                    StyleSpan(Typeface.BOLD), startIndex,
                    endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            movementMethod = LinkMovementMethod.getInstance()
            text = spannable
        }
    }
}