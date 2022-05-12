package com.example.phonedialhw

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class addContact : AppCompatActivity() {
    var set: Button? = null
    private var speedDial: String? = null
    private var contact: Contact? = null
    var it: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        it = intent
        contact = Contact()
        speedDial = it?.getStringExtra("Cont")
        contact!!.number = it?.getStringExtra("Phone_Number")!!
        contact!!.name = it?.getStringExtra("Name")!!
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        set = findViewById(R.id.button)
        val nme = findViewById<View>(R.id.editTextPersonName) as EditText
        val pn = findViewById<View>(R.id.editTextPhone) as EditText
        nme.setText(contact!!.name)
        pn.setText(contact!!.number)
    }

    fun set(v: View?) {

        val name = findViewById<View>(R.id.editTextPersonName) as EditText
        contact!!.name = name.text.toString()
        val phone = findViewById<View>(R.id.editTextPhone) as EditText
        contact!!.number = phone.text.toString()
        val returnIntent = Intent()
        returnIntent.putExtra("SpDial", speedDial)
        returnIntent.putExtra("Contact_name", contact!!.name)
        returnIntent.putExtra("Contact_number", contact!!.number)
        Log.w("ACTIVITY_RESULT", "Contact: " + returnIntent.extras)
        if (contact!!.name.length > 0 && contact!!.number.length > 0) {

            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}