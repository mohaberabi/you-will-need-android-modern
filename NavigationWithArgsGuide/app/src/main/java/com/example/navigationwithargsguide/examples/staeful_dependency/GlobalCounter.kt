package com.example.navigationwithargsguide.examples.staeful_dependency

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


/**
 * create a singleton object that holds the data to be shared in mutiple screens
 * this is dangerous why?
 * 1 - does not handle process death . needs very very complex work to handle
 * 2- a singleton witch is also stateful ? -> you will need manually clear or reset this again as it's singleton
 *
 */
object GlobalCounter {


    private val _count = MutableStateFlow(0)

    val count: StateFlow<Int>
        get() = _count

    fun increment() {
        _count.update {
            it + 1
        }
    }
}