package com.srclasses.whatsappopener_kt

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var number:String="0"

        if(intent.action==Intent.ACTION_PROCESS_TEXT){
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }

        if(number.isDigitsOnly()){
            startWhatsapp(number)

        }else{
            Toast.makeText(this,"please check the number",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startWhatsapp(number: String) {
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")

        val data:String=if(number[0]=='+'){
            number.subSequence(1,number.length).toString()
        }else if(number.length==10){
            "91"+number
        }else{
            number
        }

        intent.data= Uri.parse("https://wa.me/$data")
        if(packageManager.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }else{
            Toast.makeText(this,"Please Install Whatsapp",Toast.LENGTH_SHORT).show()
        }

    }
}