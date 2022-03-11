package az.azerconnect.bakcell.utils.bindingAdapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import az.azerconnect.bakcell.R
import az.azerconnect.bakcell.utils.extensions.dpToPx
import az.azerconnect.bakcell.utils.extensions.getColorAttrs
import setColorFilter


@BindingAdapter("bind:glideLoadImg")
fun ImageView.glideLoadImg(url: String?) {
    glideLoadImgWithDefault(url, null)
}

@BindingAdapter("bind:glideLoadImg", "bind:glideLoadImgDefaultDrawable", requireAll = true)
fun ImageView.glideLoadImgWithDefault(url: String?, defaultDrawable: Drawable?) {
    val circularProgressDrawable = CircularProgressDrawable(context.applicationContext)
    circularProgressDrawable.strokeWidth = 2.dpToPx().toFloat()
    circularProgressDrawable.centerRadius = 10.dpToPx().toFloat()
    circularProgressDrawable.setColorFilter(context.getColorAttrs(R.attr.colorOnBackground))
    circularProgressDrawable.start()

    Glide.with(this.context.applicationContext)
        .load(url)
        .placeholder(circularProgressDrawable)
        .addListener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any?,
                target: Target<Drawable>?, isFirstResource: Boolean
            ): Boolean {
                setImageDrawable(defaultDrawable)
                return true
            }

            override fun onResourceReady(
                resource: Drawable?, model: Any?, target: Target<Drawable>?,
                dataSource: DataSource?, isFirstResource: Boolean
            ) = false
        })
        .into(this)
}

@BindingAdapter("bind:bitmapLoadImg")
fun ImageView.bitmapLoadImg(bitmap: Bitmap?) {
    setImageBitmap(bitmap)
}