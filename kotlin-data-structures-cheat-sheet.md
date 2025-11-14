# Kotlin Data Structures Cheat Sheet

## **Arrays**

```kotlin
// Fixed-size array
val numbers = arrayOf(1, 2, 3, 4, 5)
val strings = arrayOf("a", "b", "c")

// Primitive arrays (better performance)
val intArray = intArrayOf(1, 2, 3)
val doubleArray = doubleArrayOf(1.5, 2.5, 3.5)

// Access and modify
numbers[0] = 10
println(numbers[2]) // 3

// Create with size and initializer
val squares = Array(5) { i -> i * i } // [0, 1, 4, 9, 16]
```

## **Lists**

```kotlin
// Immutable List (read-only)
val fruits = listOf("Apple", "Banana", "Orange")
println(fruits[0]) // Apple
println(fruits.size) // 3

// Mutable List
val mutableFruits = mutableListOf("Apple", "Banana")
mutableFruits.add("Orange")
mutableFruits.removeAt(0)
mutableFruits[0] = "Mango"

// ArrayList (specific implementation)
val arrayList = ArrayList<String>()
arrayList.add("Item")

// Common operations
fruits.contains("Apple") // true
fruits.indexOf("Banana") // 1
fruits.filter { it.startsWith("A") }
fruits.map { it.uppercase() }
```

## **Sets**

```kotlin
// Immutable Set (no duplicates, unordered)
val numbers = setOf(1, 2, 3, 3, 2) // [1, 2, 3]

// Mutable Set
val mutableSet = mutableSetOf(1, 2, 3)
mutableSet.add(4)
mutableSet.remove(2)

// HashSet
val hashSet = HashSet<Int>()
hashSet.add(1)

// LinkedHashSet (maintains insertion order)
val linkedSet = linkedSetOf(3, 1, 2) // maintains order

// Operations
numbers.contains(2) // true
val set1 = setOf(1, 2, 3)
val set2 = setOf(3, 4, 5)
set1.union(set2) // [1, 2, 3, 4, 5]
set1.intersect(set2) // [3]
set1.subtract(set2) // [1, 2]
```

## **Maps**

```kotlin
// Immutable Map (key-value pairs)
val ages = mapOf("Alice" to 25, "Bob" to 30, "Charlie" to 35)
println(ages["Alice"]) // 25

// Mutable Map
val mutableAges = mutableMapOf("Alice" to 25)
mutableAges["Bob"] = 30
mutableAges.put("Charlie", 35)
mutableAges.remove("Alice")

// HashMap
val hashMap = HashMap<String, Int>()
hashMap["key"] = 100

// LinkedHashMap (maintains insertion order)
val linkedMap = linkedMapOf("a" to 1, "b" to 2)

// Operations
ages.keys // [Alice, Bob, Charlie]
ages.values // [25, 30, 35]
ages.containsKey("Alice") // true
ages.getOrDefault("David", 0) // 0
ages.forEach { (key, value) -> println("$key: $value") }
```

## **Queues & Deques**

```kotlin
import java.util.*

// Queue (FIFO)
val queue: Queue<Int> = LinkedList()
queue.offer(1)
queue.offer(2)
queue.poll() // removes and returns 1
queue.peek() // returns 2 without removing

// Deque (double-ended queue)
val deque: Deque<String> = ArrayDeque()
deque.addFirst("a")
deque.addLast("b")
deque.removeFirst() // "a"
deque.removeLast() // "b"

// Stack behavior with Deque
val stack: Deque<Int> = ArrayDeque()
stack.push(1)
stack.push(2)
stack.pop() // 2
```

## **Stack**

```kotlin
import java.util.Stack

val stack = Stack<Int>()
stack.push(1)
stack.push(2)
stack.push(3)
stack.pop() // removes and returns 3
stack.peek() // returns 2 without removing
stack.isEmpty() // false
stack.search(1) // position from top (3)
```

## **Priority Queue**

```kotlin
import java.util.PriorityQueue

// Min heap (default)
val minHeap = PriorityQueue<Int>()
minHeap.offer(5)
minHeap.offer(2)
minHeap.offer(8)
minHeap.poll() // 2

// Max heap
val maxHeap = PriorityQueue<Int>(compareByDescending { it })
maxHeap.offer(5)
maxHeap.offer(2)
maxHeap.offer(8)
maxHeap.poll() // 8

// Custom comparator
data class Task(val name: String, val priority: Int)
val taskQueue = PriorityQueue<Task>(compareBy { it.priority })
```

## **Sequences**

```kotlin
// Lazy evaluation (better for large collections)
val sequence = sequenceOf(1, 2, 3, 4, 5)

val result = (1..1000000).asSequence()
    .filter { it % 2 == 0 }
    .map { it * 2 }
    .take(10)
    .toList()

// Generate sequence
val fibonacci = generateSequence(1 to 1) { (a, b) -> b to a + b }
    .map { it.first }
    .take(10)
    .toList()
```

## **Pairs & Triples**

```kotlin
// Pair
val pair = Pair("Alice", 25)
val (name, age) = pair // destructuring
println(pair.first) // Alice
println(pair.second) // 25

// Using 'to' infix function
val pair2 = "Bob" to 30

// Triple
val triple = Triple("Alice", 25, "Engineer")
val (name2, age2, job) = triple
```

## **Ranges**

```kotlin
// Integer range
val range = 1..10 // inclusive
val rangeUntil = 1..<10 // or 1 until 10, exclusive end

// Check containment
5 in range // true
15 in range // false

// Iterate
for (i in 1..5) {
    println(i)
}

// Step and downTo
for (i in 10 downTo 1 step 2) {
    println(i) // 10, 8, 6, 4, 2
}

// Character range
for (c in 'a'..'z') {
    print(c)
}
```

## **Common Collection Operations**

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)

// Transformations
numbers.map { it * 2 } // [2, 4, 6, 8, 10]
numbers.filter { it > 2 } // [3, 4, 5]
numbers.flatMap { listOf(it, it * 2) } // [1, 2, 2, 4, 3, 6...]

// Aggregations
numbers.sum() // 15
numbers.average() // 3.0
numbers.max() // 5
numbers.min() // 1
numbers.count() // 5

// Grouping
val words = listOf("apple", "banana", "avocado", "berry")
words.groupBy { it.first() } // {a=[apple, avocado], b=[banana, berry]}

// Sorting
numbers.sorted() // ascending
numbers.sortedDescending()
words.sortedBy { it.length }

// Take/Drop
numbers.take(3) // [1, 2, 3]
numbers.drop(2) // [3, 4, 5]

// Distinct
listOf(1, 2, 2, 3, 3, 3).distinct() // [1, 2, 3]

// Zip
val names = listOf("Alice", "Bob")
val ages = listOf(25, 30)
names.zip(ages) // [(Alice, 25), (Bob, 30)]
```

---

## Quick Reference Table

| Data Structure | Mutable | Ordered | Duplicates | Key-Value |
|----------------|---------|---------|------------|-----------|
| Array | ✓ | ✓ | ✓ | ✗ |
| List | ✗ | ✓ | ✓ | ✗ |
| MutableList | ✓ | ✓ | ✓ | ✗ |
| Set | ✗ | ✗ | ✗ | ✗ |
| MutableSet | ✓ | ✗ | ✗ | ✗ |
| LinkedHashSet | ✓ | ✓ | ✗ | ✗ |
| Map | ✗ | ✗ | ✗ | ✓ |
| MutableMap | ✓ | ✗ | ✗ | ✓ |
| LinkedHashMap | ✓ | ✓ | ✗ | ✓ |
| Queue | ✓ | ✓ | ✓ | ✗ |
| Deque | ✓ | ✓ | ✓ | ✗ |
| Stack | ✓ | ✓ | ✓ | ✗ |
| PriorityQueue | ✓ | Priority | ✓ | ✗ |

---

**Note:** This cheat sheet covers the most commonly used data structures in Kotlin. For more advanced usage and additional methods, refer to the [official Kotlin documentation](https://kotlinlang.org/docs/collections-overview.html).
