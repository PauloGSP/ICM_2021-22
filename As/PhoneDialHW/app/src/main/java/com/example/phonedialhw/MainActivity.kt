package com.example.phonedialhw



import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.phonedialhw.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var cont1: Button? = null
    var cont2:Button? = null
    var cont3:Button? = null
    var cone: Contact? = null
    var ctwo:Contact? = null
    var cthree:Contact? = null
    var contactnumber =""
    val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val data: Intent?=it.data
                onres(data)

            }
        }

    private fun onres(data:Intent? ) {
        val spDial: String = data!!.getStringExtra("SpDial")!!
        val name: String = data!!.getStringExtra("Contact_name")!!
        val number: String = data!!.getStringExtra("Contact_number")!!
        contactnumber=number
        if (spDial == "Cont1") {
            cone!!.number = number
            cone!!.name = name
            cont1!!.text = name
        } else if (spDial == "Cont2") {
            ctwo!!.number = number
            ctwo!!.name = name
            cont2!!.text = name
        } else {
            cthree!!.number = number
            cthree!!.name = name
            cont3!!.text = name
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var text=""
        val tv = binding.textView7

        binding.deletebtn.setOnClickListener {
            tv.setText("")
            text=""
        }
        binding.callbtn.setOnClickListener { dial() }
        binding.btn0.setOnClickListener{
            text += binding.btn0.text
            tv.setText(text)

        }
        binding.btn1.setOnClickListener{
            text += binding.btn1.text
            tv.setText(text)
        }
        binding.btn2.setOnClickListener{
            text += binding.btn2.text
            tv.setText(text)

        }
        binding.btn3.setOnClickListener{
            text += binding.btn3.text
            tv.setText(text)
        }
        binding.btn4.setOnClickListener{
            text += binding.btn4.text
            tv.setText(text)
        }
        binding.btn5.setOnClickListener{
            text += binding.btn5.text
            tv.setText(text)
        }
        binding.btn6.setOnClickListener{
            text += binding.btn6.text
            tv.setText(text)
        }
        binding.btn7.setOnClickListener{
            text += binding.btn7.text
            tv.setText(text)
        }
        binding.btn8.setOnClickListener{
            text += binding.btn8.text
            tv.setText(text)
        }
        binding.btn9.setOnClickListener{
            text += binding.btn9.text
            tv.setText(text)
        }
        binding.mem1.setOnClickListener { tv.setText(contactnumber)}
        binding.mem2.setOnClickListener { tv.setText(contactnumber)}
        binding.mem3.setOnClickListener { tv.setText(contactnumber)}

        cont1 = binding.mem1
        cont2 = binding.mem2
        cont3 = binding.mem3
        cone = Contact()
        ctwo = Contact()
        cthree = Contact()
        val spdial = OnLongClickListener { v ->
            addSpeedDial(v)
            true
        }

        cont1!!.setOnLongClickListener(spdial)
        cont2!!.setOnLongClickListener(spdial)
        cont3!!.setOnLongClickListener(spdial)

    }
    fun SpeedDial(id: View) {
        val pn = binding.textView7
        if (id === cont1) {
           pn.setText(cone!!.number)
            binding.mem1.setText(cone!!.name)
       } else if (id === cont2) {
            pn.setText(ctwo!!.number)
            binding.mem2.setText(ctwo!!.name)
        } else if (id === cont3) {
            pn.setText(cthree!!.number)
            binding.mem3.setText(cthree!!.name)
        }
    }


    fun addSpeedDial(id: View) {
        val it = Intent(this, addContact::class.java)
        val pn = binding.textView7
        val phoneNumber = pn.text.toString()
        if (id === cont1) {
            it.putExtra("Cont", "Cont1")
            it.putExtra("Name", cone!!.name)
            if (cone!!.name != "Name") {
                it.putExtra("Phone_Number", cone!!.number)
            } else if (phoneNumber.length != 0) {
                it.putExtra("Phone_Number", phoneNumber)
            } else {
                it.putExtra("Phone_Number", cone!!.number)
            }
        } else if (id === cont2) {
            it.putExtra("Cont", "Cont2")
            it.putExtra("Name", ctwo!!.name)
            if (ctwo!!.name != "Name") {
                it.putExtra("Phone_Number", ctwo!!.number)
            } else if (phoneNumber.length != 0) {
                it.putExtra("Phone_Number", phoneNumber)
            } else {
                it.putExtra("Phone_Number", ctwo!!.number)
            }
        } else if (id === cont3) {
            it.putExtra("Cont", "Cont3")
            it.putExtra("Name", cthree!!.name)
            if (ctwo!!.name != "Name") {
                it.putExtra("Phone_Number", cthree!!.number)
            } else if (phoneNumber.length != 0) {
                it.putExtra("Phone_Number", phoneNumber)
            } else {
                it.putExtra("Phone_Number", cthree!!.number)
            }
        }

        getResult.launch(it)
    }



    fun dial() {
        val pn = binding.textView7
        val phoneNumber = pn.text.toString()
        if (phoneNumber.length == 9) {
            val uri: Uri = Uri.parse("tel:$phoneNumber")
            val intent = Intent(Intent.ACTION_DIAL, uri)
            startActivity(intent)
        } else {
        }
    }

}