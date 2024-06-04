package com.example.importntanotations.jfmoverloadsexplain


/**
 *
 *as we know in kotlin we have named parameters as well as default values
 * like [age] if we created an instance of [User] the compiler will only complain about
 * not passing the name parameter to the constructor but it will  not complain about the age
 * as the[age] has already a default value if not passed to it this only happens inside kotlin
 * but what if we wanted to create an instance of [User] inside of a java file
 *
 * this basically will not work you have to pass two values for the [name] and [age]
 * but when annotated with [JvmOverloads]
 * this basically will make use of java method overloading like
 * Look at the example below [UserTest] that is what is created internally when you make
 * use of [JvmOverloads]
 *  class UserTest {
 *         String name ;
 *         int age ;
 *         public  UserTest(String name ){
 *             this.name= name ;
 *             this.age=25;
 *         }
 *         public UserTest(String name , int  age){
 *             this.name=name;
 *             this.age= age;
 *         }
 *     }
 */
data class User @JvmOverloads constructor(
    val name: String,

    val age: Int = 25
)


fun main() {
    val user = User("Mohab")
}