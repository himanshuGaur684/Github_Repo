package com.example.githubrepo.common

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity


fun Context.shareLink(link: String) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, link)
    startActivity(Intent.createChooser(intent, "Share link via"))
}