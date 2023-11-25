/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 *
 */

import java.util.Stack;
fun main() {
  val list = listOf(1,2,3)
  val res1 = list.forEach {
      println(it)
  }
  println(res1)
  val res2 = list.onEach{
      println(it)
  }
    println(res2)
}


// common design patterns
// 
// design patterns types
// 1 - creatonal : how to create object
// 2 - structural : how to compose objects
// 3 - behavioral : how coordinate object interactions
// 


// creational

// 1- singleton : only one instance in the app
// 
object Logger {
    init{
        println("created first time only")
    }
    fun log(msg: String){
        println("data is $msg")
    }
}

// 2 - builder: when you have a complex object with alot of params 
// and you do not need to use telescope constuctors also some parms are optional
// ex: Alert Dialog class in Android


class MailBuilder {
    private var recepients: List<String> =listOf() 
    private var title: String = ""
    private var message: String = ""
    
    class Mail internal constructor(
        val to: List<String>,
        val title: String?,
        val message: String?
    ) 
    
    fun build() : Mail {
        if(recepients.isEmpty())
          throw RuntimeException("To property is empty")
          
       return Mail(recepients,title,message)
    }
    
    fun message(message: String = ""): MailBuilder {
        this.message = message
        return this
    }
    
    fun title(title: String): MailBuilder {
        this.title = title
        return this
    }
    
    fun recepients(recepients: List<String>): MailBuilder{
        this.recepients = recepients
        return this
    }
}


// 3 - factory method : when you have multiple classes implement the same interface and you need to create 
// new instance based on the type for example

enum class Type() {
    FIREBASE,
    AWS
}

interface FileUploader {
    fun uploadFile(file: String): String
}



class AwsFileUploader: FileUploader {
   override fun uploadFile(file: String): String {
      println("file is uploaded to AWS")
      return "aws file url"
    }
}


class FirebaseFileUploader: FileUploader {
  override fun uploadFile(file: String): String {
      println("file is uploaded to Firebase")
      return "firebase file url"
    }
}

class FileUploaderFactory {
    companion object {
         fun create(type: Type): FileUploader{
             
          return when(type){
             Type.AWS -> AwsFileUploader()
             Type.FIREBASE -> FirebaseFileUploader()
            else -> throw IllegalArgumentException("type is not handled")
        }
     }
         
    }
   
}
// Strategy Pattern: use it when you have different implementations or logic per provider 
// and use client class like ScoreBoard with composition (instance of interface) to assign object based on the selection of concrete objects
fun main() {

    val scoreBoard = ScoreBoard()
    println("balloon is tapped")
    scoreBoard.scoreBoardBase = Balloon()
    scoreBoard.showScore(10,5)
    println("square balloon is tapped")
    scoreBoard.scoreBoardBase = SquareBalloon()
    scoreBoard.showScore(10,)

}

class ScoreBoard {
    var scoreBoardBase: ScoreBoardBase? = null
    fun showScore(taps: Int, multiplier: Int) {
        println(scoreBoardBase?.calcScore(taps,multiplier))
    }
}

interface ScoreBoardBase {
    fun calcScore(taps: Int, multiplier: Int): Int
}

class Balloon: ScoreBoardBase {
    override fun calcScore(taps: Int, multiplier: Int): Int {
        return (taps * multiplier) + 20
    }
}

class SquareBalloon: ScoreBoardBase {
    override fun calcScore(taps: Int, multiplier: Int): Int {
        return (taps * multiplier) - 20
    }
}
// example2: payment

fun main() {
    val product1 = Product("t-shirt", 30)
    val product2 = Product("pantis", 10)
    val list = mutableListOf(product1,product2)
    val cart = ShoppingCart(list)
    cart.pay(PayPalProvider("Mahmoud", "test@go.com"))
    cart.pay(CreditCardProvider("Mahmoud", "4444444444444444"))
}

data class Product (val upcCode: String, val price: Int)

interface Payment {
    fun pay(amount: Int)
}

class PayPalProvider(val name: String, val email: String): Payment {
    override fun pay(amount: Int) {
        println("payment is done using Paypal.....$amount")
    }
}

class CreditCardProvider(val name: String, val number: String): Payment {
    override fun pay(amount: Int) {
        println("payment is done using Credit.....$amount")
    }
}

class ShoppingCart(val cart: MutableList<Product>) {
    fun addTocart(product: Product) {
        cart.add(product)
    }

    fun removeFromCart(product: Product) {
        cart.remove(product)
    }

    fun pay(payment: Payment){
        val amount = cart.sumOf{it.price}
        payment.pay(amount)
    }


    //============= observer design pattern ======================
     // use this pattern if you need to publish event/info to certain listeners
     // like news topic, ... and so on

    fun main() {

        val topic = EmailTopic()
        val observer1 = EmailTopicSubscriber("first observer")
        val observer2 = EmailTopicSubscriber("second observer")
        val observer3 = EmailTopicSubscriber("third observer")

        topic.registerObserver(observer1)
        topic.registerObserver(observer2)
        topic.registerObserver(observer3)

        observer1.subscribe(topic)
        observer2.subscribe(topic)
        observer3.subscribe(topic)


        //observer1.update()
        //observer2.update()
        //observer3.update()

        topic.postMessage("Hello Observers....")
    }

    interface Subject {
        fun postMessage(msg: String)
        fun registerObserver(observer: Observer)
        fun unregisterObserver(observer: Observer)
        fun notifyObservers()
        fun getUpdate(observer: Observer): Any?

    }

    interface Observer {
        fun update()
        fun subscribe(subject: Subject)
    }

    class EmailTopic(): Subject {
        val observers = ArrayList<Observer>()
        var message: String? = null

        override fun postMessage(msg: String){
            message = msg
            println("message...$msg")
            notifyObservers()
        }

        override fun registerObserver(observer: Observer){
            require(observer != null) {
                println("observer should be not null ")
            }

            if(!observers.contains(observer))
                observers.add(observer)
        }

        override fun unregisterObserver(observer: Observer){
            observers.remove(observer)
        }

        override fun notifyObservers(){
            observers.forEach { observer ->
                observer.update()
            }
        }
        override fun getUpdate(observer: Observer): Any?{
            return this.message
        }
    }


    class EmailTopicSubscriber(val name: String): Observer {

        var subject: Subject? =  null

        override fun update() {
            val msg = subject?.getUpdate(this) as? String
            if(msg == null)
                println("$name no new message on this subject")
            else
                println("$name receiving message : $msg")
        }

        override fun subscribe(subject: Subject){
            this.subject = subject
        }
    }

    //==============================================================================//