package com.example.testapp1

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.textclassifier.TextLanguage
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testapp1.databinding.ActivityGeneratedCodeBinding

class GeneratedCodeActivity : AppCompatActivity() {
    lateinit var counter: TextView
    lateinit var textViewBlikCode: TextView
    private lateinit var binding: ActivityGeneratedCodeBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_code)
        setTitle("Blik Code Generator");


        var randomBlikCode = (111111..999999).random()
        //val blikJSON = intent.getStringExtra("blikJSON")

        binding= ActivityGeneratedCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        counter = findViewById(R.id.timeRemaining)
        textViewBlikCode = findViewById(R.id.blikCodeTextView)

        val editLogin = intent.getStringExtra("editLogin")
        val editPassword = intent.getStringExtra("editPassword")
        var url = "http://192.168.1.107:8080/api/generateBlik/" + editLogin + "/" + editPassword
        //http://192.168.1.107:8080/api/generateBlik/Jan Matacz/1111
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show()

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                textViewBlikCode.text = "code:\n ${response.toString()}"
                Log.d("answer",response)
            },
            Response.ErrorListener { textViewBlikCode.text = "That didn't work!" })

        queue.add(stringRequest)


        //textViewBlikCode.setText("code:\n " + randomBlikCode) //change ranomBlikCode to blikJSON



        var newCodeBtn = findViewById<Button>(R.id.newCodeBtn)
        newCodeBtn.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        object : CountDownTimer(120000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                counter.setText("seconds remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                counter.setText("Your code has expired!")
                textViewBlikCode.setText("------")
            }

        }.start()

    }
}