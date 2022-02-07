package com.example.motivation01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation01.infra.MotivationConstants
import com.example.motivation01.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_splash2.*

class SplashActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)

        mSecurityPreferences = SecurityPreferences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        saveBotton.setOnClickListener(this)

        varifyName()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.saveBotton) {
            handleSave()
        }
    }

    private fun varifyName(){
       val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if (name != ""){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun handleSave() {
        val name = editName.text.toString()

        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstants.KEY.PERSON_NAME, name)
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }

    }
}