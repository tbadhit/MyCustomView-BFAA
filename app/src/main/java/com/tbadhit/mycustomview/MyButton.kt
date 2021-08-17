package com.tbadhit.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import java.util.jar.Attributes

// Extend AppCompatButton (1)
class MyButton : AppCompatButton {

    // (2)
    private var enableBackground: Drawable? = null
    private var disableBackground: Drawable? = null
    private var txtColor: Int = 0
    //-----

    // (1)
    constructor(context: Context) : super(context) {
        // (2)
        init()
    }

    // (1)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        // (2)
        init()
    }

    // (1)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        // (2)
        init()
    }

    // (1)
    override fun onDraw(canvas: Canvas?) {
        // (2)
        super.onDraw(canvas)
        background = when {
            isEnabled -> enableBackground
            else -> disableBackground
        }

        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = when {
            isEnabled -> "Submit"
            else -> "Isi dulu"
        }
        //-----
    }

    // (2)
    private fun init() {
        // (2)
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        enableBackground = ResourcesCompat.getDrawable(resources, R.drawable.bg_button, null)
        disableBackground = ResourcesCompat.getDrawable(resources, R.drawable.bg_button_disable, null)
        //-----
    }
}