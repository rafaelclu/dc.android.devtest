package dc.android.devtest.common.extension

import android.content.Context
import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat

fun Resources.getDrawableByName(context: Context, weatherIcon: String) = this.run {
    val drawableId =
        getIdentifier(weatherIcon, "drawable", context.packageName)
    ResourcesCompat.getDrawable(this, drawableId, null)!!
}