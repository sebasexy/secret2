import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson

/**
 * Created by CLN-BRA on 05/09/2018.
 */



class UserService (val defaultUrl: String){
    val gson = Gson();

    fun createMessage(message: Message): Message{
        TODO("not implemented")
    }

    fun getMessage(id: String): Task<Message?>{
        val future = TaskCompletionSource<Message?>()
        val connection = URL("$defaultUrl/Users/$id").openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "")
        if(connection.responseCode == 200){
            val streamReader = InputStreamReader(connection.inputStream)
            future.setResult(gson.fromJson(streamReader, Message::class.java))
            streamReader.close()
            connection.disconnect()
        }

        future.setResult(null)
        return future.task
    }

    fun updateUser(user: Message): Task<Message?>{
        val future = TaskCompletionSource<Message?>()
        val connection = URL("$default/Messages").openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Authorization", "")
        val json = gson.toJson(user)
        connection.outputStream.write(json.toByteArray())
        connection.outputStream.close()

        if(connection.responseCode == 200){
            val streamReader = InputStreamReader(connection.inputStream)
            future.setResult(gson.fromJson(streamReader, Message::class.java))
            streamReader.close()
            connection.disconnect()
        }

        future.setResult(null)
        return future.task
    }
    fun getUser(): Task<Message?>{
        val future = TaskCompletionSource<Message?>()
        val connection = URL("$defaultUrl/Users").openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Authorization", "")
        if(connection.responseCode == 200){
            val streamReader = InputStreamReader(connection.inputStream)
            future.setResult(gson.fromJson(streamReader, Message::class.java))
            streamReader.close()
            connection.disconnect()
        }

        future.setResult(null)
        return future.task
    }
}