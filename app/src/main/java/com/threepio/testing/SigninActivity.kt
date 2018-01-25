package com.threepio.testing

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_signin.*

class SigninActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_signin)

    show_password.setOnClickListener{ togglePassword() }
    user_name.addTextChangedListener(toggleSignIn)
    password.addTextChangedListener(toggleSignIn)
  }

  private fun togglePassword() {
    if(show_password.isChecked) {
      password.inputType = TYPE_CLASS_TEXT
    } else {
      password.inputType = (TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD)
    }
  }

  private val toggleSignIn = object : TextWatcher {
    override fun afterTextChanged(et: Editable?) {
      sign_in.isEnabled = !user_name.text.isBlank() && !password.text.isBlank()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
  }
}
