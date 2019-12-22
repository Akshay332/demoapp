package com.example.demoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.demoapp.R
import com.example.demoapp.helper.InputValidation
import com.example.demoapp.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@LoginScreenActivity

   // private lateinit var ConstraintLayoutLogin: ConstraintLayout

   // private lateinit var txtinputlayoutemail: TextInputLayout
   // private lateinit var edittxt_username: EditText

   // private lateinit var textinputlayoutpaswrd: TextInputLayout
   // private lateinit var edittext_pswd: EditText

  //  private lateinit var txtv_forgtpswd: TextView

   // private lateinit var login_btn: Button

    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        txtv_forgtpswd.setOnClickListener(this)
        login_btn.setOnClickListener(this)

        // initializing the objects
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
            R.id.txtv_forgtpswd -> {
                // Navigate to signupActivity
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            R.id.login_btn -> verifyFromSQLite()


        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(
                edittxt_username!!,
                txtinputlayoutemail!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(
                edittxt_username!!,
                txtinputlayoutemail!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(
                edittext_pswd!!,
                textinputlayoutpaswrd!!,
                getString(R.string.error_message_email)
            )
        ) {
            return
        }
        if (databaseHelper!!.checkUser(
                edittxt_username!!.text.toString().trim { it <= ' ' },
                edittext_pswd!!.text.toString().trim { it <= ' ' })
        ) {

            val accountsIntent = Intent(activity, USersListActivity::class.java)
            accountsIntent.putExtra("EMAIL", edittxt_username!!.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(accountsIntent)
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(
                ConstraintLayoutLogin!!,
                getString(R.string.error_valid_email_password),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        edittxt_username!!.text = null
        edittext_pswd!!.text = null
    }
}
