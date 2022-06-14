package com.prianshuprasad.brainsmasher

import Adapter
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_playground.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.concurrent.thread


class playground : AppCompatActivity() {

    private lateinit var gridView:GridView
private lateinit var timer:TextView
private lateinit var llinear:LinearLayout
    var isGameFinished=0
    var time:Long=0;

    private lateinit var score:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)
        supportActionBar?.hide()

timer= findViewById(R.id.timer)




score = findViewById(R.id.score)
        llinear= findViewById(R.id.linearLayout)

        gridView = findViewById(R.id.gridView)
        var gm = gameMachine()
        gm.create()

        val adapter = Adapter(this, this,gm.getList())

        adapter.create()

       gridView.adapter= adapter


        Handler().postDelayed({

            StartTime.setText("GO!")

            adapter.AI()



        },10000)

        Handler().postDelayed({
            StartTime.visibility= View.GONE
        },12000)


gridView.setOnItemClickListener { parent, view, position, id ->


    if(adapter.AIIndex!=-1)
    adapter.userIndex = position

        adapter.update()


}


        thread {
            Thread.sleep(10000)
             time= System.currentTimeMillis()
            while(isGameFinished==0)
            {

               var time2= System.currentTimeMillis()


           runOnUiThread {
               updateTimer("Time: ${getTime(time,time2)}")
           }
                Thread.sleep(1000)

            }

        }

        restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    fun gameOver()
    {
gridView.visibility= View.GONE
        isGameFinished=1;
        llinear.visibility = View.VISIBLE

        val animation: Animation =
            AnimationUtils.loadAnimation(applicationContext, R.anim.move)
        llinear.startAnimation(animation)

val currTime = System.currentTimeMillis()
        val timeS= getTime(time,currTime)

        score.setText("Your Score : ${timeS}")





    }


    fun updateTimer(time:String)
    {
        timer.setText(time)
    }

    fun getTime(time:Long,time1:Long):String{

        var time2= time1-time
        time2/=1000;

        val second = time2%60
        val minute= time2/60
        var zeroM=""
        var  zeroS=""
        if(second<=9)
            zeroS="0"
        if(minute<=9)
            zeroM="0"


        var s = "${zeroM}${minute}:${zeroS}${second}"

        return s;


    }

fun Toast(s:String)
{
    android.widget.Toast.makeText(this,"$s", android.widget.Toast.LENGTH_SHORT).show()
}



}