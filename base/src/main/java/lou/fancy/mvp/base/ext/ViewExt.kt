package lou.fancy.mvp.base.ext

import android.view.View

/**
 * Created by fancylou on 10/17/17.
 */


fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}