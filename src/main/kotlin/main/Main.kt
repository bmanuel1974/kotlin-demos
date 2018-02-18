package main

import main.org.demo.kotlin.FunctionDemo

fun main(args: Array<String>) {
    println("Function demo - let, apply, also, run, takeIf, takeUnless, use, with")

    /**
     * apply
     * inline fun <T> T.apply(block: T.() -> Unit): T (source)
     *
     * Here we are using apply to modify a value of demo.
     * Apply is non transformational, it takes 'this' as the receiver
     * and returns 'this'.  'this' is the demo object.
     */
    println()
    println("apply")
    println(FunctionDemo().apply {
        this.value1 = "changed through apply"
        println("apply returns 'this' and is NON transformational")
    }.value1)

    /**
     * also
     * inline fun <T> T.also(block: (T) -> Unit): T (source)
     *
     * Here we are using 'also' to modify a value of demo.
     * 'also' is non transformational, it takes 'this' as an argument
     * and returns 'this'.  'this' is the demo object.
     */
    println()
    println("also")
    println(FunctionDemo().also {
        it.value1 = "changed through also"
        println("'also' returns 'this' and is NON transformational")
    }.value1)

    /**
     * with
     * inline fun <T, R> with(receiver: T, block: T.() -> R): R (source)
     * With would be used to operate on an object and return the result.  You could return
     * something else from the with
     *
     */
    println()
    val withDemo = FunctionDemo().apply {
        value1 = "some value"
    }

    val withTransform = with(withDemo, {
        this.value1
    })

    println("withTransform: $withTransform")


    /**
     * let
     * let *is* transformational.  You can modify
     * the original object, but let will also require something
     * to be returned.  In the example below, we are just transforming
     * the demo object value1 to a string
     * The demo object here becomes 'it'.
     * let does not return the original object, although you could.
     * If you were to return the original object, consider using also/apply
     */
    println()
    println("let")
    val demo2 = FunctionDemo().let {
        it.value1 = "changed using let"
        "Who cares!  I was returned from a let"
    }
    println("demo2='$demo2'")


    /**
     * run
     * inline fun <R> run(block: () -> R): R (source)
     *
     * There are 2 forms of 'run'.  The first calls a specified block of code and returns it's result
     */
    println()
    println("run1")
    var result = run {
        println("I am running stuff")
        "I ran"
    }
    println("result: $result")

    /**
     * run
     * inline fun <T, R> T.run(block: T.() -> R): R (source)
     *
     * The second form of run calls the block passing the receiver as 'this' and returns
     * the result.  This is a transformational call
     */
    println()
    println("run2")
    var runDemoValue1 = FunctionDemo().run {
        value2 = "doesn't matter, won't even get returned"
        "I created an object for no reason!"
    }
    println("run demo2 $runDemoValue1")


    /**
     * takeIf
     * inline fun <T> T.takeIf(predicate: (T) -> Boolean): T? (source)
     *
     * returns this value if the predicate is true
     *
     */
    println()
    println("takeIf")
    var takeIfDemo = FunctionDemo().apply { value1 = "something" }.takeIf { it.value1 == "something" }
    println("takeIfDemo.value1 = ${takeIfDemo?.value1}")
    takeIfDemo = FunctionDemo().apply { value1 = "something" }.takeIf { it.value1 == "something1" }
    println("takeIfDemo not something1: $takeIfDemo")

    /**
     * takeUnless
     * inline fun <T> T.takeUnless(predicate: (T) -> Boolean): T? (source)
     *
     * Returns this value if it does not satisfy the given predicate or null, if it does.
     */
    println()
    println("takeUnless")
    var takeUnless = FunctionDemo().apply { value1 = "something" }.takeUnless { it.value1 == "somethingElse" }
    println("takeUnlessDemo.value1 = ${takeUnless?.value1}")

    takeUnless = FunctionDemo().apply { value1 = "something" }.takeUnless { it.value1 == "something" }
    println("takeUnlessDemo1.value1 = ${takeUnless?.value1}")
}