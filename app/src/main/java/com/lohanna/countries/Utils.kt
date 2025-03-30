package com.lohanna.countries

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.URLSpan
import android.widget.TextView
import java.io.InputStream

fun getBitmapFromAssets(fileName: String, context: Context): Bitmap {
    val assetManager: AssetManager = context.assets
    val inputStream: InputStream = assetManager.open(fileName)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    inputStream.close()

    return bitmap
}

fun TextView.removeLinksUnderline() {
    val spannable = SpannableString(text)
    for (urlSpan in spannable.getSpans(0, spannable.length, URLSpan::class.java)) {
        spannable.setSpan(object : URLSpan(urlSpan.url) {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, spannable.getSpanStart(urlSpan), spannable.getSpanEnd(urlSpan), 0)
    }
    text = spannable
}