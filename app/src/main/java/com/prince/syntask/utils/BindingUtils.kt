package com.prince.syntask.utils

import android.view.View
import android.view.View.GONE
import androidx.databinding.BindingAdapter


/**
 * set the views visibility to View.GONE if value is false, else View.VISIBLE
 *
 * @param view  View
 * @param value hide true or false
 */
@BindingAdapter("gone")
fun setGone(view: View, value: Boolean?) {

    view.visibility = value?.let {
        if (it) View.VISIBLE
        else GONE
    } ?: GONE
}




