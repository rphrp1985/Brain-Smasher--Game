package com.prianshuprasad.brainsmasher

import android.app.Notification
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

 private lateinit var brainIcon:ImageView
    private lateinit var text:TextView
    private lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide();

        button = findViewById(R.id.startButton)
brainIcon= findViewById(R.id.brainIcon)

        text= findViewById(androidx.core.R.id.text)
        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
        val animation2: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.fade)
        brainIcon.startAnimation(animation)
   text.startAnimation(animation)
button.visibility= View.GONE
Handler().postDelayed({
button.visibility = View.VISIBLE
    button.startAnimation(animation2)

},2000)

        val animation3: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.zoom)

        button.setOnClickListener {

            button.visibility= View.GONE
            brainIcon.startAnimation(animation3)
            text.startAnimation(animation3)
//            button.startAnimation(animation2)
//button.visibility = View.GONE

            Handler().postDelayed({


              val intent = Intent(this,playground::class.java)
              startActivity(intent)
              finish()
          },2000)
        }





    }


    fun onDraw(canvas: Canvas) {
        val path = Path()
        var _paintText: Paint

        _paintText =  Paint(Paint.ANTI_ALIAS_FLAG);
        _paintText.setStyle(Paint.Style.FILL_AND_STROKE);
        _paintText.setColor(Color.WHITE);
        _paintText.setTextSize(20f);


        path.addCircle(20F, 20F, 200F, Path.Direction.CW)
        canvas.drawTextOnPath("Brain Smasher", path, 0F, 0F, _paintText)



    }






}