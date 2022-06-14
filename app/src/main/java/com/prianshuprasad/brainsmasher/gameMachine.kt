package com.prianshuprasad.brainsmasher

import kotlin.random.Random

class gameMachine {




    private val list : MutableList<Box> = ArrayList();
    private var remainingIndex : MutableList<Int> = ArrayList();
    private var availableIndex : MutableList<Int> = ArrayList();

    private  var indexOne:Int=0
    private  var indexTwo:Int=0



    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }




    private fun Intiliaze(){

        for(i in 0..15)
        {
            list.add(Box(9))
            remainingIndex.add(i)
            availableIndex.add(i)

        }
    }

    fun modify(index:Int)
    {
        list[index].label++;
    }

   private fun getIndex(){
       var size= remainingIndex.size
      var index:Int = rand(0,size-1)
     indexOne= remainingIndex[index]
       remainingIndex.remove(indexOne)
     size= size-1;
       index=rand(0,size-1)
       indexTwo= remainingIndex[index]
       remainingIndex.remove(indexTwo)



    }

   fun create()
   {
       Intiliaze()

       while(remainingIndex.size!=0)
       {
         val label = rand(1,6)
           getIndex()

           var tempBox = Box(label)
           var tempBox1 = Box(label)
           list[indexOne]= tempBox
           list[indexTwo] = tempBox1


       }


   }

fun getList(): MutableList<Box>
{
    return list
}


    fun chooseCardAI():Int
    {

        val size = availableIndex.size
        if(size==0)
            return -1;
        val index = rand(0,size-1)

        return availableIndex[index]

    }


    fun validiateUserChoice(AIChoice:Int,userChoice:Int):Boolean
    {
        var temp = false;
        if(list[AIChoice].label== list[userChoice].label)
        {
            temp=true
            list[AIChoice].label=0
            list[userChoice].label=0

            availableIndex.remove(AIChoice)
            availableIndex.remove(userChoice)


        }else
            temp=false


        return temp

    }

    fun isGameFinish():Boolean
    {
        var nullCount=0;
        for(i in 0..15)
        {
            if(list[i].label==0)
                nullCount++
        }

        if(nullCount==16)
            return true
        return false

    }








}