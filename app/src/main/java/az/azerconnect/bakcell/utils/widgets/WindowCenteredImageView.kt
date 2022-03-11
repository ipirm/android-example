package az.azerconnect.bakcell.utils.widgets

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import az.azerconnect.bakcell.R

/**
 * Simply draws the given drawable in the center of the current window with no scaling. This is not
 * very sophisticated, so cannot handle a lot of the features that a regular [ImageView] can. If
 * these features are required, you might want to try subclassing [ImageView].
 */
class WindowCenteredImageView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val d: Drawable?

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.WindowCenteredImageView, 0, 0
        ).apply {
            d = getDrawable(R.styleable.WindowCenteredImageView_src)
            recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (d != null) {
            // We have a drawable, so try to draw it!
            if (d.intrinsicWidth >= 0 && d.intrinsicHeight >= 0) {
                // Our drawable doesn't fill the screen, so we should calculate its bounds. This is
                // where we take into account the current view's offset within the window...
                val viewTopLeftRelativeToWindow = onDrawCachedObjects.point1.apply {
                    val array = onDrawCachedObjects.array.apply { getLocationInWindow(this) }
                    set(array[0], array[1])
                }

                // And the window's dimensions...
                // (Casting here can't fail as views are always given activity context.)
                val window = (context as Activity).window.decorView
                val windowHeight = window.height
                val windowWidth = window.width

                // Do the calculations.
                // First, find out where we want the image to go in the window
                val imageTopLeftRelativeToWindow = onDrawCachedObjects.point2.apply {
                    set(
                        (windowWidth / 2) - (d.intrinsicWidth / 2),
                        (windowHeight / 2) - (d.intrinsicHeight / 2)
                    )
                }

                // Now we can calculate where the image should go in the view
                val imageTopLeftRelativeToView = onDrawCachedObjects.point3.apply {
                    set(
                        imageTopLeftRelativeToWindow.x - viewTopLeftRelativeToWindow.x,
                        imageTopLeftRelativeToWindow.y - viewTopLeftRelativeToWindow.y
                    )
                }

                // We have all the information needed to set the image bounds
                d.setBounds(
                    imageTopLeftRelativeToView.x,
                    imageTopLeftRelativeToView.y,
                    imageTopLeftRelativeToView.x + d.intrinsicWidth,
                    imageTopLeftRelativeToView.y + d.intrinsicHeight
                )
            }

            // Draw the drawable onto the canvas
            d.draw(canvas)
        }
    }

    /** Optimization: Objects to use in [onDraw] to avoid instantiations */
    private val onDrawCachedObjects = object {
        val array = IntArray(2)
        val point1 = Point()
        val point2 = Point()
        val point3 = Point()
    }
}