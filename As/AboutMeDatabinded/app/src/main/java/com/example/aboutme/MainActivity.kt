package com.example.aboutme

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Pylance")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myName
        binding.doneButton.setOnClickListener{
            addNickname(it)
        }
        binding.nicknameText.setOnClickListener {
            updateNick(it)
        }
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

    }
    private fun addNickname(view : View){
        val editText = binding.nicknameEdit
        val nicknameTextView = binding.nicknameText
        nicknameTextView.text=editText.text
        editText.visibility= View.GONE
        view.visibility= View.GONE
        nicknameTextView.visibility= View.VISIBLE
        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

    }
    private  fun updateNick(view:View){
        val editText =binding.nicknameEdit
        val donebut = binding.doneButton
        editText.visibility= View.VISIBLE
        donebut.visibility=View.VISIBLE
        view.visibility=View.GONE
        editText.requestFocus()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText,0)

    }
}