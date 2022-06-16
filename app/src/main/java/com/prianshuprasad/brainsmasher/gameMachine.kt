package com.prianshuprasad.brainsmasher

import kotlin.random.Random

class gameMachine {

    private val list : MutableList<Box> = ArrayList();
    private var remainingIndex : MutableList<Int> = ArrayList();
    private var availableIndex : MutableList<Int> = ArrayList();
    private  var indexOne:Int=0
    private  var indexTwo:Int=0


    // Generates random value (sync with time)
    private fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }



    // initialize variables
    private fun Intiliaze(){

        for(i in 0..15)
        {
            list.add(Box(9))
            remainingIndex.add(i)
            availableIndex.add(i)

        }
    }

   // generates a random Index to place card
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

    // create
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

    // returns list
    fun getList(): MutableList<Box>
    {
    return list
    }

    // Choose a random card
    fun chooseCardAI():Int
    {

        val size = availableIndex.size
        if(size==0)
            return -1;
        val index = rand(0,size-1)

        return availableIndex[index]

    }

    // validiates user choice
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



}