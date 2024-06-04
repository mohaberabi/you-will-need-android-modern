package com.example.importntanotations.jvmfieldexplain

import android.provider.ContactsContract.RawContacts.Data
import java.util.Date

data class Person(


    /**
     * anotation the field of a class in kotlin with [@JvmField] basically
     * tells the java compiler when you call a kotlin method inside of it
     * hey ..... this can not be accessed using setters or getter it can be accessed as simple a
     * [String name = person.name]
     *
     * but if removed ?
     * you will do such [String name = person.getName()]
     */
    @JvmField
    val name: String,

    val dob: Date,
)
