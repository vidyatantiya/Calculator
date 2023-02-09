 package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

 class MainActivity : AppCompatActivity() {
     var tvInput: TextView? = null
     var lastNumeric: Boolean = false
     var lastDot: Boolean = false


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         tvInput = findViewById(R.id.tvInput)

     }

     fun onDigit(view: View) {
         tvInput?.append((view as Button).text)
         lastNumeric = true
         lastDot = false
     }

     fun onClear(view: View) {
         tvInput?.text = ""
     }

     fun onDecimalPoint(view: View) {
         if (lastNumeric && !lastDot) {
             tvInput?.append(".")
             lastNumeric = false
             lastDot = true
         }
     }
     fun onEquals(view: View)
     {
      var v:String=""
         var prefix=""
         if(lastNumeric) {
              v = tvInput?.text.toString()


         }
         try {
             if (v.startsWith("-")) {
                 prefix = "-"
                 v = v.substring(1)
             }
             if (v.contains("-")) {
                 var s = v.split("-")
                 var one = s[0]
                 var two = s[1]
                 if(prefix.isNotEmpty())
                 {
                     one=prefix+one
                 }
                 tvInput?.text = remove((one.toDouble() - two.toDouble()).toString())

             }
             else if (v.contains("+")) {
                 var s = v.split("+")
                 var one = s[0]
                 var two = s[1]
                 if(prefix.isNotEmpty())
                 {
                     one=prefix+one
                 }
                 tvInput?.text = remove((one.toDouble() + two.toDouble()).toString())

             }
             else if (v.contains("*")) {
                 var s = v.split("*")
                 var one = s[0]
                 var two = s[1]
                 if(prefix.isNotEmpty())
                 {
                     one=prefix+one
                 }
                 tvInput?.text = remove((one.toDouble() * two.toDouble()).toString())

             }
             else if (v.contains("/")) {
                 var s = v.split("/")
                 var one = s[0]
                 var two = s[1]
                 if(prefix.isNotEmpty())
                 {
                     one=prefix+one
                 }
                 tvInput?.text = remove((one.toDouble() / two.toDouble()).toString())

             }


         }catch (e:ArithmeticException)
         {
             e.printStackTrace()
         }

     }
     fun remove(result:String):String{
         var value=result
         if(result.contains(".0"))
             value=result.substring(0,result.length-2)
     return value
     }
     fun onOperator(view:View)
     {
         tvInput?.text?.let {
             if(lastNumeric && !isOperatorAdded(it.toString()))
             {
               tvInput?.append((view as Button).text)
                 lastNumeric=false
                 lastDot=false
             }
         }
     }
    private fun isOperatorAdded(value: String): Boolean{
         return if(value.startsWith("-"))
         {
             false
         }else
         {
             value.contains("/")||value.contains("*")||value.contains("+")
                     ||value.contains(("-"))
         }
     }
 }