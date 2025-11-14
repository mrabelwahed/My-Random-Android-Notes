# Kotlin Cheat Sheet

## Table of Contents
1. [Basics](#basics)
2. [Variables & Types](#variables--types)
3. [Control Flow](#control-flow)
4. [Functions](#functions)
5. [Classes & Objects](#classes--objects)
6. [Collections](#collections)
7. [Null Safety](#null-safety)
8. [Extension Functions](#extension-functions)
9. [Lambdas & Higher-Order Functions](#lambdas--higher-order-functions)
10. [Coroutines](#coroutines)

---

## Basics

### Hello World
```kotlin
fun main() {
    println("Hello, World!")
}
```

### Comments
```kotlin
// Single-line comment

/*
   Multi-line
   comment
*/

/** 
 * Documentation comment
 * @param name The name parameter
 */
```

---

## Variables & Types

### Variable Declaration
```kotlin
// Immutable (read-only)
val name = "John"          // Type inference
val age: Int = 25          // Explicit type

// Mutable
var score = 100
score = 200                // Can be reassigned

// Compile-time constants
const val PI = 3.14159
```

### Basic Types
```kotlin
// Numbers
val byte: Byte = 127
val short: Short = 32767
val int: Int = 2147483647
val long: Long = 9223372036854775807L
val float: Float = 3.14f
val double: Double = 3.14159

// Boolean
val isActive: Boolean = true

// Characters & Strings
val letter: Char = 'A'
val text: String = "Hello"

// Type conversion
val x: Int = 10
val y: Long = x.toLong()
val z: Double = x.toDouble()
```

### String Templates
```kotlin
val name = "Alice"
val age = 30
println("Name: $name, Age: $age")
println("Next year: ${age + 1}")

// Multi-line strings
val text = """
    Line 1
    Line 2
    Line 3
""".trimIndent()
```

---

## Control Flow

### If Expression
```kotlin
// If as expression (returns value)
val max = if (a > b) a else b

// Traditional if-else
if (score > 90) {
    println("Excellent")
} else if (score > 75) {
    println("Good")
} else {
    println("Keep trying")
}
```

### When Expression (Switch)
```kotlin
val day = 3
when (day) {
    1 -> println("Monday")
    2 -> println("Tuesday")
    3, 4 -> println("Mid-week")
    in 5..7 -> println("Weekend approaching")
    else -> println("Invalid day")
}

// When with return value
val result = when (x) {
    0, 1 -> "small"
    in 2..10 -> "medium"
    else -> "large"
}

// When without argument
when {
    age < 18 -> println("Minor")
    age < 65 -> println("Adult")
    else -> println("Senior")
}
```

### For Loops
```kotlin
// Range
for (i in 1..5) {
    println(i)  // 1, 2, 3, 4, 5
}

// Until (exclusive)
for (i in 1 until 5) {
    println(i)  // 1, 2, 3, 4
}

// Step
for (i in 0..10 step 2) {
    println(i)  // 0, 2, 4, 6, 8, 10
}

// Downward
for (i in 5 downTo 1) {
    println(i)  // 5, 4, 3, 2, 1
}

// Iterating collections
val list = listOf("a", "b", "c")
for (item in list) {
    println(item)
}

// With index
for ((index, value) in list.withIndex()) {
    println("$index: $value")
}
```

### While Loops
```kotlin
var x = 0
while (x < 5) {
    println(x)
    x++
}

do {
    println(x)
    x--
} while (x > 0)
```

### Ranges
```kotlin
val range = 1..10           // 1 to 10 inclusive
val range2 = 1 until 10     // 1 to 9
val charRange = 'a'..'z'

if (5 in range) {
    println("5 is in range")
}

if (100 !in range) {
    println("100 is not in range")
}
```

---

## Functions

### Basic Functions
```kotlin
// Simple function
fun greet() {
    println("Hello!")
}

// With parameters
fun greet(name: String) {
    println("Hello, $name!")
}

// With return type
fun add(a: Int, b: Int): Int {
    return a + b
}

// Single-expression function
fun multiply(a: Int, b: Int) = a * b
```

### Default Parameters
```kotlin
fun greet(name: String = "Guest", age: Int = 0) {
    println("Hello $name, age $age")
}

greet()                    // Hello Guest, age 0
greet("Alice")             // Hello Alice, age 0
greet("Bob", 25)           // Hello Bob, age 25
greet(age = 30, name = "Charlie")  // Named arguments
```

### Varargs
```kotlin
fun sum(vararg numbers: Int): Int {
    return numbers.sum()
}

val result = sum(1, 2, 3, 4, 5)  // 15

// Spread operator
val nums = intArrayOf(1, 2, 3)
val result2 = sum(*nums)
```

### Infix Functions
```kotlin
infix fun Int.times(str: String) = str.repeat(this)

val result = 3 times "Ha"  // "HaHaHa"
```

---

## Classes & Objects

### Basic Class
```kotlin
class Person(val name: String, var age: Int) {
    // Secondary constructor
    constructor(name: String) : this(name, 0)
    
    // Init block
    init {
        println("Person created: $name")
    }
    
    // Methods
    fun greet() {
        println("Hello, I'm $name")
    }
}

// Usage
val person = Person("Alice", 30)
person.greet()
```

### Data Classes
```kotlin
data class User(val id: Int, val name: String, val email: String)

val user = User(1, "Alice", "alice@example.com")

// Auto-generated methods
println(user)                    // toString()
val user2 = user.copy(name = "Bob")  // copy()
val (id, name, email) = user     // Destructuring
```

### Sealed Classes
```kotlin
sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}

fun handleResult(result: Result) {
    when (result) {
        is Result.Success -> println(result.data)
        is Result.Error -> println(result.message)
        is Result.Loading -> println("Loading...")
    }
}
```

### Object (Singleton)
```kotlin
object Database {
    val name = "MyDatabase"
    fun connect() {
        println("Connected to $name")
    }
}

Database.connect()
```

### Companion Object
```kotlin
class MyClass {
    companion object {
        const val CONSTANT = "value"
        fun create(): MyClass = MyClass()
    }
}

val instance = MyClass.create()
```

### Inheritance
```kotlin
open class Animal(val name: String) {
    open fun makeSound() {
        println("Some sound")
    }
}

class Dog(name: String) : Animal(name) {
    override fun makeSound() {
        println("Woof!")
    }
}
```

### Interfaces
```kotlin
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")  // Default implementation
}

class Button : Clickable {
    override fun click() {
        println("Button clicked")
    }
}
```

### Properties
```kotlin
class Rectangle(val width: Int, val height: Int) {
    val area: Int
        get() = width * height
    
    var color: String = "white"
        get() = field.uppercase()
        set(value) {
            field = value.lowercase()
        }
}
```

---

## Collections

### Lists
```kotlin
// Immutable list
val list = listOf(1, 2, 3, 4, 5)
val first = list[0]
val size = list.size

// Mutable list
val mutableList = mutableListOf(1, 2, 3)
mutableList.add(4)
mutableList.remove(2)
mutableList[0] = 10
```

### Sets
```kotlin
// Immutable set
val set = setOf(1, 2, 3, 2)  // Duplicates removed
println(set)  // [1, 2, 3]

// Mutable set
val mutableSet = mutableSetOf(1, 2, 3)
mutableSet.add(4)
```

### Maps
```kotlin
// Immutable map
val map = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3
)
val value = map["one"]  // 1

// Mutable map
val mutableMap = mutableMapOf<String, Int>()
mutableMap["four"] = 4
mutableMap.put("five", 5)

// Iteration
for ((key, value) in map) {
    println("$key -> $value")
}
```

### Collection Operations
```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)

// Filter
val evens = numbers.filter { it % 2 == 0 }  // [2, 4, 6]

// Map
val doubled = numbers.map { it * 2 }  // [2, 4, 6, 8, 10, 12]

// Find
val firstEven = numbers.find { it % 2 == 0 }  // 2

// Any, All, None
val hasEven = numbers.any { it % 2 == 0 }  // true
val allPositive = numbers.all { it > 0 }    // true
val noNegative = numbers.none { it < 0 }    // true

// Reduce & Fold
val sum = numbers.reduce { acc, i -> acc + i }  // 21
val product = numbers.fold(1) { acc, i -> acc * i }  // 720

// GroupBy
val grouped = numbers.groupBy { it % 2 == 0 }
// {false=[1, 3, 5], true=[2, 4, 6]}

// Partition
val (even, odd) = numbers.partition { it % 2 == 0 }

// FlatMap
val nested = listOf(listOf(1, 2), listOf(3, 4))
val flat = nested.flatMap { it }  // [1, 2, 3, 4]

// Take & Drop
val firstThree = numbers.take(3)  // [1, 2, 3]
val withoutFirstThree = numbers.drop(3)  // [4, 5, 6]

// Distinct
val duplicates = listOf(1, 2, 2, 3, 3, 3)
val unique = duplicates.distinct()  // [1, 2, 3]

// Sort
val unsorted = listOf(3, 1, 4, 1, 5, 9)
val sorted = unsorted.sorted()  // [1, 1, 3, 4, 5, 9]
val sortedDesc = unsorted.sortedDescending()
```

---

## Null Safety

### Nullable Types
```kotlin
var name: String = "Alice"
// name = null  // Compilation error

var nullableName: String? = "Bob"
nullableName = null  // OK
```

### Safe Call Operator
```kotlin
val length = nullableName?.length  // null if nullableName is null

// Chaining
val city = person?.address?.city
```

### Elvis Operator
```kotlin
val length = nullableName?.length ?: 0  // 0 if null

val name = nullableName ?: "Default Name"
```

### Not-Null Assertion
```kotlin
val length = nullableName!!.length  // Throws NPE if null
```

### Safe Casts
```kotlin
val result = obj as? String  // null if cast fails
```

### Let Function
```kotlin
nullableName?.let {
    // This block only executes if nullableName is not null
    println("Name is $it")
}
```

---

## Extension Functions

### Basic Extension
```kotlin
fun String.addExclamation(): String {
    return "$this!"
}

val result = "Hello".addExclamation()  // "Hello!"
```

### Extension Properties
```kotlin
val String.firstChar: Char
    get() = this[0]

println("Kotlin".firstChar)  // K
```

### Practical Examples
```kotlin
fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = this % 2 != 0

println(4.isEven())  // true
println(5.isOdd())   // true

fun List<Int>.second(): Int = this[1]
fun List<Int>.lastOrDefault(default: Int): Int = 
    if (isEmpty()) default else last()
```

---

## Lambdas & Higher-Order Functions

### Lambda Syntax
```kotlin
val sum = { a: Int, b: Int -> a + b }
println(sum(3, 5))  // 8

// With type inference
val numbers = listOf(1, 2, 3)
numbers.filter { it > 1 }  // 'it' is implicit parameter
```

### Higher-Order Functions
```kotlin
fun calculate(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

val result = calculate(10, 5) { x, y -> x + y }  // 15
val result2 = calculate(10, 5) { x, y -> x * y }  // 50
```

### Standard Library Functions

#### Let
```kotlin
val name: String? = "Alice"
name?.let {
    println("Name length is ${it.length}")
}
```

#### Apply
```kotlin
val person = Person("Bob", 25).apply {
    age = 26
    // 'this' refers to the object
}
```

#### Also
```kotlin
val numbers = mutableListOf(1, 2, 3).also {
    println("Adding 4")
    it.add(4)
}
```

#### Run
```kotlin
val result = "Hello".run {
    println(this)
    length
}  // Returns 5
```

#### With
```kotlin
val result = with(person) {
    println(name)
    age
}
```

---

## Coroutines

### Basic Coroutine
```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}
```

### Async/Await
```kotlin
suspend fun fetchUser(): User {
    delay(1000)
    return User(1, "Alice")
}

suspend fun fetchPosts(): List<Post> {
    delay(1000)
    return listOf(Post(1, "Title"))
}

fun main() = runBlocking {
    val user = async { fetchUser() }
    val posts = async { fetchPosts() }
    
    println("User: ${user.await()}")
    println("Posts: ${posts.await()}")
}
```

### Coroutine Scope
```kotlin
class MyViewModel {
    private val scope = CoroutineScope(Dispatchers.Main)
    
    fun loadData() {
        scope.launch {
            val data = withContext(Dispatchers.IO) {
                // Background work
                fetchFromNetwork()
            }
            // Update UI with data
        }
    }
    
    fun cleanup() {
        scope.cancel()
    }
}
```

### Flow
```kotlin
import kotlinx.coroutines.flow.*

fun simpleFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking {
    simpleFlow().collect { value ->
        println(value)
    }
}

// Flow operators
val flow = (1..5).asFlow()
    .filter { it % 2 == 0 }
    .map { it * it }
    .collect { println(it) }  // 4, 16
```

---

## Bonus: Common Patterns

### Lazy Initialization
```kotlin
val lazyValue: String by lazy {
    println("Computing...")
    "Hello"
}
```

### Delegation
```kotlin
interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { println(x) }
}

class Derived(b: Base) : Base by b

val base = BaseImpl(10)
Derived(base).print()  // 10
```

### Type Aliases
```kotlin
typealias UserMap = Map<String, User>
typealias ClickHandler = (View) -> Unit
```

### Destructuring
```kotlin
val (name, age) = person
val (_, second, third) = list  // Underscore to skip
```

### Scope Functions Summary
```kotlin
// let: null safety, transformations
value?.let { /* use it */ }

// apply: object configuration
Person().apply { name = "Alice" }

// also: additional operations
list.also { println("Size: ${it.size}") }

// run: execute block and return result
val result = run { /* computation */ }

// with: multiple operations on object
with(person) { println(name) }
```

---

## Quick Reference

| Feature | Syntax |
|---------|--------|
| Variable (immutable) | `val x = 10` |
| Variable (mutable) | `var x = 10` |
| Function | `fun name() { }` |
| Nullable type | `String?` |
| Safe call | `obj?.method()` |
| Elvis operator | `obj ?: default` |
| String template | `"Value: $x"` |
| Range | `1..10` |
| Lambda | `{ x -> x * 2 }` |
| Extension | `fun String.method() { }` |
| Data class | `data class User(val name: String)` |
| Singleton | `object MySingleton { }` |

---

**Happy Kotlin Coding! 🚀**
