package com.example.testapp1

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testapp1.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.URL
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Blik Code Generator");

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editLogin = findViewById<EditText>(R.id.editLogin)
        val editPassword = findViewById<EditText>(R.id.editPassword)

        val btnClick = findViewById<Button>(R.id.generateCodeBtn)
        btnClick.setOnClickListener {

            if(editLogin.text.isNotEmpty() && editPassword.text.isNotEmpty()){
                val intent = Intent(this@MainActivity, GeneratedCodeActivity::class.java)

                var url = "http://192.168.1.107:8080/api/generateBlik/" + editLogin.text.toString() + "/" + editPassword.text.toString()
                //val apiResponse = URL(url).readText(Charset.forName("UTF-8"))


                intent.putExtra("editLogin", editLogin.text.toString())
                intent.putExtra("editPassword", editPassword.text.toString())
                startActivity(intent)
                Toast.makeText(this, "Welcome " + editLogin.text.toString() + "!", Toast.LENGTH_SHORT).show()

            }
            else {
                Toast.makeText(this, "Fill all gaps!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}

//http://192.168.1.107:8080/api/generateBlik/Jan Matacz/1111