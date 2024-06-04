package com.example.importntanotations.jvmstatic


/**
 * A singleton in kolint as we know is defiend using the keyword [object]
 * this is quievlant to public staic muyField ="anyValue "; in java
 *no if you tried to call the [whoIsMohabAnnotated] function inside a myClass.java
 * it will be called like Constants.INSTANCE.whoIsMohab()
 * if you annotated it with [JvmStatic] it will make you call it like normal java static
 */
object Constants {


    @JvmStatic
    fun whoIsMohabAnnotated(): String {
        return "Mohab is the most loser person i have ever met , he thinks himslef a software engineer " +
                "but he never realized that he is such a dummy asshole loser "
    }


    fun whoIsMohabNotAnnotated(): String {
        return "Mohab is the most loser person i have ever met , he thinks himslef a software engineer " +
                "but he never realized that he is such a dummy asshole loser "
    }
}