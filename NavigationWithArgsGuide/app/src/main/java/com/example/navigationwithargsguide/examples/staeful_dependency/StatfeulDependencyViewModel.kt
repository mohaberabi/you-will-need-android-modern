package com.example.navigationwithargsguide.examples.staeful_dependency

import androidx.lifecycle.ViewModel

/**
 * get the singelton object here and listen for it's data and call the global methods of it inside it
 * with this way you have reached the goal of sharing a data between multiple screens in a simple manner
 */
class StatfeulDependencyViewModel : ViewModel() {
    val count = GlobalCounter.count
    fun increment() {
        GlobalCounter.increment()
    }
}