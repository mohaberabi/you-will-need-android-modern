package com.example.annotations

data class User(
    val name: String,

    /**
     * you want here the birthday of the user which is defined as a string
     * you might need that birthdate in some network calls which for sure needs the birthdate
     * in some format you need to do some work
     * but this is acutally a srting it might be passed or you might even while
     * development pass it as any string like
     * [birth] = [mohab2010] , this ofcurse is not  a birthday date will formatted or nothing
     * so what to do ?? [annotations] class comes into the picture here
     * whre you define an anotation class that give some rules that whenever called it
     * does something eg : takes the date of birth in a custom format assertions enforced
     *[Target] is also an anotation class that u use in creation of your own anotation to
     * id the annotation class of your own what should  it do on what[Class] for exmaple
     * [Repeatable] if we have it we can repatet it multiple times in same field or target
     */


    // year-month-day
    @AllowedRegEx("\\d{4}-\\d{2}-\\d{2}")
    val birth: String
) {
    init {


        /**
         * list of all fields that [User] clas has
         */
        val fields = this::class.java.declaredFields

        fields.forEach { field ->
            field.annotations.forEach { anno ->
                /**
                 * if the current looped field really has the [AllowedRegEx] annotation class
                 * annotated with
                 */

                /**
                 * now this in this example it's not make a sence to use annotation
                 *
                 * as we can use a util function to valdie epxression or validate it in the init block itself , yes this 100% right correct
                 * but this for the example only ,
                 * [annotationClass] usually used in code generations  or some subset of that eg[Room] [Retrofit]...etc
                 */
                if (field.isAnnotationPresent(AllowedRegEx::class.java)) {

                    val regex = field.getAnnotation(AllowedRegEx::class.java)?.regex

                    if (regex?.toRegex()?.matches(birth) == false) {

                        throw IllegalArgumentException("Date of Birth is not valid $birth")
                    }
                }
            }
        }

    }
}

@Target(AnnotationTarget.FIELD)
annotation class AllowedRegEx(val regex: String)