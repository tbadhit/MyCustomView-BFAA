package com.tbadhit.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText: AppCompatEditText, View.OnTouchListener {

    // (2)
    internal lateinit var mClearButtonImage: Drawable

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

    // (2)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukan nama anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    // (2)
    private fun init() {
        // (2)
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
        setOnTouchListener(this)

        // (2)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    // (2)
    private fun showClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, mClearButtonImage,null)
    }

    // (2)
    private fun hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    // (2)
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    if (event != null) {
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                }

                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    if (event != null) {
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
            }

            when {
                isClearButtonClicked -> if (event != null) {
                    when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
                            showClearButton()
                            return true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_24, null) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return true
                        }
                        else -> return false
                    }
                } else -> return false
            }
        }
        return false
    }
}