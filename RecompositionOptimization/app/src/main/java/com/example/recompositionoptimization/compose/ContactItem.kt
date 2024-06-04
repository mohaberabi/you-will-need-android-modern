package com.example.recompositionoptimization.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


@Composable
fun ContactItem(

    /**
    this will never be recomposed the [Checkbox] only will be recomposed
     */

    name: ContactState,


    ) {

    /**
     * look now you have a [Text] and [Checkbox] composables function
     * the [Text] is not have any stateful behaviours
     * but [Checkbox]  has which is pretty clear to you [checked] boolean value
     * that changed each time the box is clicked
     * now what will happen is if you inspected the recompoitions of this composeable
     * you will notice that [Text] and [Checkbox] Both Were Recomposed
     * Consuming Memory And Processor
     * this can be done using the [Stable] annotatation which is can be done on a class
     * this means that it's a promise from us that , we will never pass mutable objects to this
     * specific use case that will notify the compose compiler that this is stable and
     * can not be mutated so do not recompose it as it never changed
     */


    val checked = remember {
        mutableStateOf(false)
    }
    Row {
        Text(text = name.name)
        Checkbox(checked = checked.value, onCheckedChange = { checked.value = !checked.value })
    }
}

/**
 * this [Stable] will tell the compiler that we are promise you this will never change
 * Note : [List] [String] [Collections] [Objects] kotlin can not mark them as a stable by default as
 *but [Int],[Double] ,[Float] etc.. if they are the only memebers of the class kotlin compiler marks the
 * class [Stable] by default but you can annotate by yourself
 */

@Stable
data class ContactState(val name: String)

/**
 *
 *
 */
@Immutable
data class ContactStateImmutable(val name: String)

/**

[Immutable] annotation is little similar to the [Stable]annotation, BUT
if you marked a class with [Immutable] this is Stronger promise this means than nothing in this object
 *will never changed , and if it was changed we will pass a complete new instance for it
 * this the compose compiler will use in the recomposition if the instance has same memory address this means it's not created
 *so do not recompose it , Make sure to use [val] modifier as you fulfill the immutability promise
 */


/**
 *
 * NOTE [Immutable] [Stable] both of applying those two is not a very noticeable improvements
 * do not spend a lot of time applying them unnecessary
 * Premature Optimization Is the Root of All Evil
 */