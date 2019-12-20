package com.example.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        iv_backbtn.setOnClickListener(this)
        signupBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_backbtn->{
                startActivity(Intent(this,LoginScreenActivity::class.java))
            }
            R.id.signupBtn->{
                startActivity(Intent(this,HomeActivity::class.java))

            }
        }
    }
}
