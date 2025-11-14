# Kotlin Standard Library Functions Cheat Sheet

## **Scope Functions**

### **let**
Use when you want to execute a lambda on non-null values or create a local scope.

```kotlin
// Null safety
val name: String? = "Alice"
name?.let {
    println("Name length: ${it.length}")
}

// Transform and use result
val result = "Hello".let {
    it.uppercase()
} // "HELLO"

// Multiple operations
listOf(1, 2, 3).firstOrNull()?.let {
    println(it)
    it * 2
}
```

### **run**
Use when you want to execute a block of code and return a result.

```kotlin
// Object configuration and computation
val result = "Hello".run {
    println(this)
    this.length
} // 5

// Without an object
val result2 = run {
    val a = 10
    val b = 20
    a + b
} // 30
```

### **with**
Use when you want to call multiple methods on an object without repeating the object name.

```kotlin
val person = Person("Alice", 25)

val result = with(person) {
    println(name)
    println(age)
    "Processed ${name}"
}

// StringBuilder example
val message = with(StringBuilder()) {
    append("Hello")
    append(" ")
    append("World")
    toString()
}
```

### **apply**
Use for object configuration. Returns the object itself.

```kotlin
val person = Person("", 0).apply {
    name = "Alice"
    age = 25
}

// List example
val list = mutableListOf<String>().apply {
    add("Apple")
    add("Banana")
    add("Orange")
}

// View configuration
button.apply {
    text = "Click Me"
    isEnabled = true
    setOnClickListener { /* ... */ }
}
```

### **also**
Use for side effects (logging, debugging). Returns the object itself.

```kotlin
val numbers = mutableListOf(1, 2, 3)
    .also { println("Initial list: $it") }
    .apply { add(4) }
    .also { println("After adding: $it") }

// Validation and logging
fun processUser(user: User) = user
    .also { validateUser(it) }
    .also { log("Processing user: ${it.name}") }
```

### **takeIf / takeUnless**
Returns the object if condition is met, otherwise null.

```kotlin
// takeIf
val number = 42
val result = number.takeIf { it > 50 } // null
val result2 = number.takeIf { it > 40 } // 42

// takeUnless
val result3 = number.takeUnless { it > 50 } // 42
val result4 = number.takeUnless { it > 40 } // null

// Practical example
fun findUser(id: Int): User? {
    return database.getUser(id)
        .takeIf { it.isActive }
}
```

---

## **Collection Functions**

### **map / mapNotNull**
Transform each element.

```kotlin
val numbers = listOf(1, 2, 3, 4)
numbers.map { it * 2 } // [2, 4, 6, 8]
numbers.map { "Item $it" } // ["Item 1", "Item 2", ...]

// mapNotNull - filters out nulls
val strings = listOf("1", "2", "abc", "3")
strings.mapNotNull { it.toIntOrNull() } // [1, 2, 3]

// mapIndexed
numbers.mapIndexed { index, value -> 
    "Index $index: $value" 
}
```

### **filter / filterNot**
Filter elements based on a condition.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)
numbers.filter { it % 2 == 0 } // [2, 4, 6]
numbers.filterNot { it % 2 == 0 } // [1, 3, 5]

// filterNotNull
val mixed = listOf(1, null, 2, null, 3)
mixed.filterNotNull() // [1, 2, 3]

// filterIsInstance
val items = listOf(1, "two", 3, "four")
items.filterIsInstance<String>() // ["two", "four"]
```

### **find / findLast**
Find first/last element matching condition.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
numbers.find { it > 3 } // 4
numbers.findLast { it > 3 } // 5

// Returns null if not found
numbers.find { it > 10 } // null
```

### **first / last / firstOrNull / lastOrNull**
Get first/last element.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

numbers.first() // 1
numbers.last() // 5
numbers.first { it > 2 } // 3
numbers.last { it < 4 } // 3

// Safe versions (return null instead of exception)
emptyList<Int>().firstOrNull() // null
numbers.firstOrNull { it > 10 } // null
```

### **any / all / none**
Check conditions on collections.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

numbers.any { it > 3 } // true (at least one)
numbers.all { it > 0 } // true (all elements)
numbers.none { it > 10 } // true (no elements)

// Check if collection is not empty
numbers.any() // true
emptyList<Int>().any() // false
```

### **reduce / fold**
Accumulate values.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

// reduce - uses first element as initial value
numbers.reduce { acc, value -> acc + value } // 15

// fold - specify initial value
numbers.fold(0) { acc, value -> acc + value } // 15
numbers.fold(10) { acc, value -> acc + value } // 25

// Practical example
val words = listOf("Hello", "World", "Kotlin")
words.fold("") { acc, word -> "$acc $word" }.trim()
```

### **groupBy / partition**
Group or split collections.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6)

// groupBy
numbers.groupBy { it % 2 == 0 }
// {false=[1, 3, 5], true=[2, 4, 6]}

val words = listOf("apple", "banana", "avocado", "berry")
words.groupBy { it.first() }
// {a=[apple, avocado], b=[banana, berry]}

// partition - split into two lists
val (even, odd) = numbers.partition { it % 2 == 0 }
// even: [2, 4, 6], odd: [1, 3, 5]
```

### **flatMap / flatten**
Flatten nested collections.

```kotlin
val lists = listOf(listOf(1, 2), listOf(3, 4), listOf(5))

// flatten
lists.flatten() // [1, 2, 3, 4, 5]

// flatMap
val numbers = listOf(1, 2, 3)
numbers.flatMap { listOf(it, it * 2) }
// [1, 2, 2, 4, 3, 6]

// Practical example
val users = listOf(
    User("Alice", listOf("email1", "email2")),
    User("Bob", listOf("email3"))
)
users.flatMap { it.emails } // all emails in one list
```

### **distinctBy / distinct**
Remove duplicates.

```kotlin
val numbers = listOf(1, 2, 2, 3, 3, 3, 4)
numbers.distinct() // [1, 2, 3, 4]

// distinctBy
data class Person(val name: String, val age: Int)
val people = listOf(
    Person("Alice", 25),
    Person("Bob", 30),
    Person("Alice", 28)
)
people.distinctBy { it.name }
// [Person("Alice", 25), Person("Bob", 30)]
```

### **sorted / sortedBy / sortedWith**
Sort collections.

```kotlin
val numbers = listOf(3, 1, 4, 1, 5)

numbers.sorted() // [1, 1, 3, 4, 5]
numbers.sortedDescending() // [5, 4, 3, 1, 1]

// sortedBy
data class Person(val name: String, val age: Int)
val people = listOf(
    Person("Charlie", 30),
    Person("Alice", 25),
    Person("Bob", 25)
)
people.sortedBy { it.age } // by age
people.sortedBy { it.name } // by name
people.sortedByDescending { it.age }

// sortedWith - custom comparator
people.sortedWith(compareBy({ it.age }, { it.name }))
```

### **take / drop / slice**
Extract portions of collections.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)

numbers.take(3) // [1, 2, 3]
numbers.takeLast(3) // [6, 7, 8]
numbers.takeWhile { it < 5 } // [1, 2, 3, 4]

numbers.drop(3) // [4, 5, 6, 7, 8]
numbers.dropLast(3) // [1, 2, 3, 4, 5]
numbers.dropWhile { it < 5 } // [5, 6, 7, 8]

numbers.slice(1..4) // [2, 3, 4, 5]
numbers.slice(listOf(0, 2, 4)) // [1, 3, 5]
```

### **zip / unzip**
Combine or split collections.

```kotlin
val names = listOf("Alice", "Bob", "Charlie")
val ages = listOf(25, 30, 35)

// zip
val pairs = names.zip(ages)
// [(Alice, 25), (Bob, 30), (Charlie, 35)]

names.zip(ages) { name, age -> 
    "$name is $age years old"
}

// unzip
val people = listOf("Alice" to 25, "Bob" to 30)
val (nameList, ageList) = people.unzip()
```

### **chunked / windowed**
Split into chunks or sliding windows.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)

// chunked
numbers.chunked(3)
// [[1, 2, 3], [4, 5, 6], [7, 8]]

numbers.chunked(3) { it.sum() }
// [6, 15, 15]

// windowed - sliding window
numbers.windowed(3)
// [[1, 2, 3], [2, 3, 4], [3, 4, 5], ...]

numbers.windowed(3, step = 2)
// [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8]]
```

### **associate / associateBy / associateWith**
Create maps from collections.

```kotlin
val numbers = listOf(1, 2, 3, 4)

// associate - custom key and value
numbers.associate { it to it * it }
// {1=1, 2=4, 3=9, 4=16}

// associateBy - element as value
data class Person(val id: Int, val name: String)
val people = listOf(
    Person(1, "Alice"),
    Person(2, "Bob")
)
people.associateBy { it.id }
// {1=Person(1, "Alice"), 2=Person(2, "Bob")}

// associateWith - element as key
numbers.associateWith { it * it }
// {1=1, 2=4, 3=9, 4=16}
```

---

## **String Functions**

### **split / splitToSequence**
Split strings.

```kotlin
val text = "apple,banana,orange"
text.split(",") // ["apple", "banana", "orange"]

val path = "user/documents/file.txt"
path.split("/") // ["user", "documents", "file.txt"]

// Multiple delimiters
"a,b;c:d".split(",", ";", ":") // ["a", "b", "c", "d"]

// Limit splits
"a-b-c-d".split("-", limit = 2) // ["a", "b-c-d"]

// splitToSequence - lazy evaluation
"a,b,c".splitToSequence(",")
```

### **trim / trimStart / trimEnd**
Remove whitespace.

```kotlin
val text = "  Hello World  "
text.trim() // "Hello World"
text.trimStart() // "Hello World  "
text.trimEnd() // "  Hello World"

// Custom characters
"###Hello###".trim('#') // "Hello"
```

### **replace / replaceFirst**
Replace text.

```kotlin
val text = "Hello World World"
text.replace("World", "Kotlin")
// "Hello Kotlin Kotlin"

text.replaceFirst("World", "Kotlin")
// "Hello Kotlin World"

// Regex
text.replace(Regex("\\s+"), "-")
// "Hello-World-World"
```

### **substring / substringBefore / substringAfter**
Extract substrings.

```kotlin
val text = "Hello World"
text.substring(0, 5) // "Hello"
text.substring(6) // "World"

val email = "user@example.com"
email.substringBefore("@") // "user"
email.substringAfter("@") // "example.com"

email.substringBeforeLast(".") // "user@example"
email.substringAfterLast(".") // "com"
```

### **startsWith / endsWith / contains**
Check string content.

```kotlin
val text = "Hello World"
text.startsWith("Hello") // true
text.endsWith("World") // true
text.contains("lo Wo") // true

// Case insensitive
text.startsWith("hello", ignoreCase = true) // true
```

### **toIntOrNull / toDoubleOrNull**
Safe type conversion.

```kotlin
val str1 = "123"
val str2 = "abc"

str1.toIntOrNull() // 123
str2.toIntOrNull() // null

str1.toDoubleOrNull() // 123.0
"12.34".toDoubleOrNull() // 12.34
"abc".toDoubleOrNull() // null

// Other conversions
"true".toBooleanStrictOrNull() // true
"yes".toBooleanStrictOrNull() // null
```

### **repeat / padStart / padEnd**
String manipulation.

```kotlin
"Ha".repeat(3) // "HaHaHa"

"5".padStart(3, '0') // "005"
"42".padStart(5, '0') // "00042"

"5".padEnd(3, '0') // "500"
"42".padEnd(5, '0') // "42000"
```

### **lines / lineSequence**
Work with multi-line strings.

```kotlin
val multiline = """
    Line 1
    Line 2
    Line 3
""".trimIndent()

multiline.lines() // List<String>
multiline.lineSequence() // Sequence<String> - lazy

// Process lines
multiline.lines()
    .filter { it.isNotBlank() }
    .map { it.trim() }
```

---

## **Null Safety Functions**

### **?.** (Safe Call)
```kotlin
val name: String? = null
val length = name?.length // null (no exception)

// Chaining
person?.address?.city?.name
```

### **?:** (Elvis Operator)
```kotlin
val name: String? = null
val displayName = name ?: "Unknown" // "Unknown"

// With functions
val length = name?.length ?: 0

// Early return
fun process(value: String?): Int {
    val nonNull = value ?: return -1
    return nonNull.length
}
```

### **!!** (Not-null Assertion)
```kotlin
val name: String? = "Alice"
val length = name!!.length // Throws if null

// Use sparingly - prefer safe alternatives
```

### **requireNotNull / checkNotNull**
```kotlin
fun process(value: String?) {
    val nonNull = requireNotNull(value) {
        "Value must not be null"
    }
    // nonNull is String (non-nullable)
}

fun validate(value: String?) {
    checkNotNull(value) { "Value cannot be null" }
    // Continues if not null, throws IllegalStateException if null
}
```

---

## **Comparison & Range Functions**

### **coerceIn / coerceAtLeast / coerceAtMost**
Constrain values.

```kotlin
val number = 15

number.coerceIn(1, 10) // 10 (clamped to range)
number.coerceIn(1..10) // 10

number.coerceAtLeast(20) // 20 (minimum)
number.coerceAtMost(10) // 10 (maximum)

// Practical example
fun calculateProgress(current: Int, total: Int): Int {
    return (current * 100 / total).coerceIn(0, 100)
}
```

### **compareTo / compareValues**
```kotlin
val a = 5
val b = 10

a.compareTo(b) // -1 (a < b)
b.compareTo(a) // 1 (b > a)
a.compareTo(5) // 0 (equal)

// Null-safe comparison
compareValues("a", "b") // -1
compareValues(null, "b") // -1
compareValues("a", null) // 1
```

### **maxOf / minOf**
```kotlin
val max = maxOf(5, 10, 3, 8) // 10
val min = minOf(5, 10, 3, 8) // 3

// With custom comparator
val longestWord = maxOf("hi", "hello", "hey") { it.length }
```

---

## **Type Check & Cast Functions**

### **is / !is** (Type Check)
```kotlin
val obj: Any = "Hello"

if (obj is String) {
    println(obj.length) // Smart cast to String
}

if (obj !is Int) {
    println("Not an integer")
}
```

### **as / as?** (Type Cast)
```kotlin
val obj: Any = "Hello"

// Unsafe cast (throws ClassCastException if fails)
val str: String = obj as String

// Safe cast (returns null if fails)
val str2: String? = obj as? String
val num: Int? = obj as? Int // null
```

---

## **Utility Functions**

### **repeat**
Execute code multiple times.

```kotlin
repeat(3) {
    println("Hello")
}

repeat(5) { index ->
    println("Iteration $index")
}
```

### **require / check / assert**
Validation functions.

```kotlin
fun setAge(age: Int) {
    require(age > 0) { "Age must be positive" }
    // IllegalArgumentException if condition false
}

fun process() {
    check(isInitialized) { "Must be initialized" }
    // IllegalStateException if condition false
}

fun calculate(x: Int) {
    assert(x > 0) // Only in debug mode
}
```

### **TODO**
Mark unimplemented code.

```kotlin
fun notImplementedYet(): String {
    TODO("Implement this function")
    // Throws NotImplementedError
}

fun partiallyImplemented() {
    // Some code
    TODO("Finish implementation")
}
```

### **lazy**
Lazy initialization.

```kotlin
val expensiveValue: String by lazy {
    println("Computing...")
    "Result"
}
// Computed only on first access

// Thread-safe modes
val value1 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { /* ... */ }
val value2 by lazy(LazyThreadSafetyMode.PUBLICATION) { /* ... */ }
val value3 by lazy(LazyThreadSafetyMode.NONE) { /* ... */ }
```

### **use**
Auto-close resources.

```kotlin
File("data.txt").inputStream().use { input ->
    // Automatically closes stream
    input.read()
}

// Multiple resources
File("input.txt").inputStream().use { input ->
    File("output.txt").outputStream().use { output ->
        input.copyTo(output)
    }
}
```

### **measureTimeMillis / measureNanoTime**
Measure execution time.

```kotlin
val time = measureTimeMillis {
    // Code to measure
    Thread.sleep(1000)
}
println("Took $time ms")

val nanoTime = measureNanoTime {
    // Precise measurement
}
```

---

## **Math Functions**

### **abs / sign**
```kotlin
val num = -42
abs(num) // 42
sign(num) // -1

sign(0) // 0
sign(42) // 1
```

### **min / max**
```kotlin
min(5, 10) // 5
max(5, 10) // 10

// For collections, use minOrNull/maxOrNull
listOf(1, 2, 3).minOrNull() // 1
listOf(1, 2, 3).maxOrNull() // 3
```

### **pow / sqrt**
```kotlin
import kotlin.math.*

2.0.pow(3.0) // 8.0
sqrt(16.0) // 4.0
sqrt(2.0) // 1.414...

// Other math functions
ceil(4.3) // 5.0
floor(4.7) // 4.0
round(4.5) // 5.0
```

---

## **Summary Table**

| Function | Purpose | Returns |
|----------|---------|---------|
| `let` | Execute lambda, transform value | Lambda result |
| `run` | Execute block on object | Lambda result |
| `with` | Execute block with object as receiver | Lambda result |
| `apply` | Configure object | Object itself |
| `also` | Perform side effects | Object itself |
| `takeIf` | Conditional return | Object or null |
| `map` | Transform elements | New list |
| `filter` | Select elements | New list |
| `fold/reduce` | Accumulate values | Single value |
| `groupBy` | Group elements | Map |
| `flatMap` | Transform and flatten | Flat list |

---

## Best Practices

1. **Choose the right scope function:**
   - Need the result? → `let`, `run`, `with`
   - Need the object? → `apply`, `also`

2. **Prefer safe calls over `!!`:**
   - Use `?.`, `?:`, `?.let { }`

3. **Use sequences for large collections:**
   - `.asSequence()` for lazy evaluation

4. **Chain operations carefully:**
   - Keep chains readable
   - Consider breaking long chains

5. **Leverage extension functions:**
   - Kotlin stdlib is rich with extensions
   - Create your own when needed

---

For more details, visit the [official Kotlin documentation](https://kotlinlang.org/api/latest/jvm/stdlib/).
