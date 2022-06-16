

import android.R
import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import com.prianshuprasad.brainsmasher.R.layout
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import com.prianshuprasad.brainsmasher.Box
import com.prianshuprasad.brainsmasher.gameMachine
import com.prianshuprasad.brainsmasher.playground

class Adapter(private val listener: playground,context: Context, BoxArray: MutableList<Box>) :
    ArrayAdapter<Box?>(listener, 0, BoxArray!! as List<Box?>) {

    var Isinitial=0;
    val gm = gameMachine()
    val ViewList:MutableList<View?> = ArrayList()
    var AIIndex=-1;
    var userIndex=-1;
    var isClicked=-1;


    fun create()
    {
        gm.create()
    }


    // selects a random Card
    fun AI()
    {
        AIIndex=gm.chooseCardAI()

        if(AIIndex!=-1)
        update()
        else
        listener.gameOver()

    }





    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        if(Isinitial>17)
        {
            if(userIndex>=0)
            {

            return ViewUserCard(position,convertView,parent)
            }

            return ViewAICard(position,convertView,parent)

        }

        Isinitial++;

        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(com.prianshuprasad.brainsmasher.R.layout.grid_support, parent, false)
        }
        val box: Box? = gm.getList()[(position)]

        val image= listitemView!!.findViewById<ImageView>(com.prianshuprasad.brainsmasher.R.id.card)

        when (box!!.label){
            0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
            1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
            2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
            3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
            4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
            5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
            6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)


        }

        var scale = context.resources.displayMetrics.density

        Handler().postDelayed({
            image.cameraDistance = 8000 * scale

            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipfront
                ) as AnimatorSet
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipback
                ) as AnimatorSet


            flipOutAnimatorSet.setTarget(image)
            flipOutAnimatorSet.start()
            Handler().postDelayed({
                if(box?.label!=0)
                image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.f)
                flipInAnimatorSet.setTarget(image)
                flipInAnimatorSet.start()
            },500)




        },((5000+100*position).toLong()))

        convertView?.setOnClickListener {



            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipfront
                ) as AnimatorSet
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    com.prianshuprasad.brainsmasher.R.anim.flipback
                ) as AnimatorSet


            flipOutAnimatorSet.setTarget(image)
            flipOutAnimatorSet.start()
            Handler().postDelayed({
                when (box!!.label){
                    0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                    1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                    2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                    3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                    4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                    5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                    6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)


                }

                flipInAnimatorSet.setTarget(image)
                flipInAnimatorSet.start()
            },500)


            var temp= false

            if(AIIndex!=-1)
            temp=gm.validiateUserChoice(AIIndex,0)
            if(userIndex==-1)
            {
                Handler().postDelayed({
                    update()
                },2000)
            }

            Handler().postDelayed({

                if(temp==true) {
                    AI()
                }

            },2500)



        }

        return listitemView!!
    }





     fun ViewAICard(position: Int, convertView: View?, parent: ViewGroup): View{



         var listitemView = convertView
         if (listitemView == null) {
             // Layout Inflater inflates each item to be displayed in GridView.
             listitemView = LayoutInflater.from(context).inflate(com.prianshuprasad.brainsmasher.R.layout.grid_support, parent, false)
         }
         val box: Box? = gm.getList()[(position)]

         val image= listitemView!!.findViewById<ImageView>(com.prianshuprasad.brainsmasher.R.id.card)


         if(position!=AIIndex)
         {
             if(box?.label!=0)
             image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.f)

             else
             image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)

             return listitemView
         }

         val flipOutAnimatorSet =
             AnimatorInflater.loadAnimator(
                 context,
                 com.prianshuprasad.brainsmasher.R.anim.flipfront
             ) as AnimatorSet
         val flipInAnimatorSet =
             AnimatorInflater.loadAnimator(
                 context,
                 com.prianshuprasad.brainsmasher.R.anim.flipback
             ) as AnimatorSet


         flipOutAnimatorSet.setTarget(image)
         flipOutAnimatorSet.start()
         Handler().postDelayed({
             when (box!!.label){
                 0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                 1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                 2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                 3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                 4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                 5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                 6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

             }

             flipInAnimatorSet.setTarget(image)
             flipInAnimatorSet.start()
         },500)




         return listitemView

     }


    fun ViewUserCard(position: Int, convertView: View?, parent: ViewGroup): View{




        var listitemView = convertView
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(context).inflate(com.prianshuprasad.brainsmasher.R.layout.grid_support, parent, false)
        }
        val box: Box? = gm.getList()[(position)]

        val image= listitemView!!.findViewById<ImageView>(com.prianshuprasad.brainsmasher.R.id.card)


        if(position!=AIIndex && userIndex!=position)
        {
            if(box?.label!=0)
            image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.f)
            else
                image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)


            return listitemView
        }

        if(position==AIIndex)
        {
            when (box!!.label){
                0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

            }
            return  listitemView
        }



        val flipOutAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                com.prianshuprasad.brainsmasher.R.anim.flipfront
            ) as AnimatorSet
        val flipInAnimatorSet =
            AnimatorInflater.loadAnimator(
                context,
                com.prianshuprasad.brainsmasher.R.anim.flipback
            ) as AnimatorSet


        flipOutAnimatorSet.setTarget(image)
        flipOutAnimatorSet.start()
        Handler().postDelayed({
            when (box!!.label){
                0-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.empty)
                1-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.a)
                2-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.b)
                3-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.c)
                4-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.d)
                5-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.e)
                6-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.g)
//            7-> image.setImageResource(com.prianshuprasad.brainsmasher.R.drawable.)

            }

            flipInAnimatorSet.setTarget(image)
            flipInAnimatorSet.start()
        },500)


        var temp=gm.validiateUserChoice(AIIndex,userIndex)
//        val user=userIndex
        userIndex=-1;
        isClicked=-1

        Handler().postDelayed(
            {
                update()
                Handler().postDelayed({

                    if(temp==true) {
                        AI()
                    }

                },1000)

            },1500)


        return listitemView

    }



    fun update()
    {
        notifyDataSetChanged();
    }



}