package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        txtv_forgtpswd.setOnClickListener (this)
        login_btn.setOnClickListener (this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.txtv_forgtpswd ->{
                startActivity(Intent(this,SignUpActivity::class.java))
            }
            R.id.login_btn ->{
                startActivity(Intent(this,HomeActivity::class.java))
            }

        }
    }
}
