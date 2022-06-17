package com.ei.android.jokeapp.gender_detection_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.gender_detection_app.viewModel.TextCallback
import com.ei.android.jokeapp.gender_detection_app.viewModel.ViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class GenderDetectionActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_app)
        viewModel = (application as DetectionApp).viewModel
        val textView = findViewById<TextView>(R.id.genderTextView)
        val textInputLayout = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputEditText = textInputLayout.editText as TextInputEditText
        textInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val regex = Regex("[a-z]+",RegexOption.IGNORE_CASE)
                val valid = regex.matches(p0.toString())
                textInputLayout.isErrorEnabled = !valid
                val error = if(valid) "" else getString(R.string.invalid_name)
                textInputLayout.error = error
            }

        })
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.INVISIBLE
        val button = findViewById<Button>(R.id.detectButton)
        button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            button.isEnabled = false
            viewModel.detectGender(textInputEditText.text.toString())
        }
        viewModel.init(object: TextCallback{
            override fun provideText(name: String) = runOnUiThread{
                progressBar.visibility=View.INVISIBLE
                textView.text = name
                button.isEnabled=true
            }

        } )

    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }
}