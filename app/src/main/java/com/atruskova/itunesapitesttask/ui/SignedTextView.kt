package com.atruskova.itunesapitesttask.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.atruskova.itunesapitesttask.R
import kotlinx.android.synthetic.main.signed_textview.view.*

// custom view for textview with caption
class SignedTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var layout: LinearLayout
    init {
        layout = LayoutInflater.from(context).inflate(R.layout.signed_textview, this, true) as LinearLayout

        if (attrs != null) {
            var typedArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.SignedTextView)

            var signedText = typedArray.getString(R.styleable.SignedTextView_signText) ?: ""
            layout.sign_text.text = signedText
        }
    }


    var textValue: String? = ""
        set(value) {
            field = value
            layout.value_text.text = textValue
        }

}