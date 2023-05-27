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