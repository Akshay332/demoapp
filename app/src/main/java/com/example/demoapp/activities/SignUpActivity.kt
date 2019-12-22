package com.example.demoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.example.demoapp.helper.InputValidation
import com.example.demoapp.model.User
import com.example.demoapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@SignUpActivity

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        iv_backbtn.setOnClickListener(this)
        signupBtn.setOnClickListener(this)
        txtv_alreadyaccount.setOnClickListener(this)
        initObjects()
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {

        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.iv_backbtn -> {
                startActivity(
                    Intent(
                        this,
                        LoginScreenActivity::class.java
                    )
                )
            }
            R.id.signupBtn -> postDataToSQLite()
            R.id.txtv_alreadyaccount -> finish()
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(
                editText_firstname,
                text_input_layout_firstname,
                getString(R.string.error_message_name)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                editText_lastname,
                text_input_layout_lastname,
                getString(R.string.error_message_name)
            )
        ) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(
                editText_Email,
                text_input_layout_email,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(
                editText_Email,
                text_input_layout_email,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                editText_Paswrd,
                text_input_layout_password,
                getString(R.string.error_message_password)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(
                editText_Paswrd, editText_confirmPaswrd,
                text_input_layout_confirmpaswd, getString(R.string.error_password_match)
            )
        ) {
            return
        }
        if (!databaseHelper!!.checkUser(editText_Email!!.text.toString().trim())) {
            var user = User(
                Firstname = editText_firstname!!.text.toString().trim(),
                Lastname = editText_lastname!!.text.toString().trim(),
                email = editText_Email!!.text.toString().trim(),
                password = editText_Paswrd!!.text.toString().trim()
            )

            databaseHelper!!.addUser(user)
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(ConstraintLayoutSignup!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()
        }else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(ConstraintLayoutSignup!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }

    }
    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        editText_firstname!!.text = null
        editText_lastname!!.text = null
        editText_Email!!.text = null
        editText_Paswrd!!.text = null
        editText_confirmPaswrd!!.text = null
    }
}
