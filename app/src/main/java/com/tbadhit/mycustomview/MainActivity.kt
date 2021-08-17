package com.tbadhit.mycustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.addTextChangedListener

// Create new kotlin class "MyButton"
// Add code in "MyButton" (1)
// Create new drawable resource file "bg_button.xml" (drawable->new->drawable resource file)
// Add code in "bg_button.xml" (1)
// Create new drawable resource file "bg_button_disable.xml" (drawable->new->drawable resource file)
// Add code in "bg_button_disable.xml" (1)
// Update code in "MyButton" (2)
// Update code in "activity_main.xml" (1)

// Create new kotlin class "MyEditText"
// Add code in "MyEditText" (1)
// Add new drawable file "ic_baseline_clean_24.xml" (new/vector asset)
// Add code in "MyEditText" (2)
// Update code in "activity_main.xml" (2)
// Add code in "MainActivity" (1)
class MainActivity : AppCompatActivity() {

    // (1)
    private lateinit var myButton: MyButton
    private lateinit var myEditText: MyEditText
    //-----

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // (1)
        myButton = findViewById(R.id.my_button)
        myEditText = findViewById(R.id.my_edit_text)

        // (1)
        setMyButtonEnable()

        // (1)
        // Pengecekan myedittext
        myEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
        //-----

        // (1)
        myButton.setOnClickListener {
            Toast.makeText(this@MainActivity, myEditText.text, Toast.LENGTH_SHORT).show()
        }
    }

    // (1)
    private fun setMyButtonEnable() {
        val result = myEditText.text
        myButton.isEnabled = result != null && result.toString().isNotEmpty()
    }
}