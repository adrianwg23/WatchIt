package com.example.adrianwong.watchit.common

import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}

fun ImageView.load(url: String, context: Fragment) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.load(url: String, context: Activity) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun Activity.makeToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()