package lt.todo.messenger_application

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun showToast(context: Context,
              message: String,
              length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, length).show()
}

fun etToString(editText: EditText): String = editText.text.toString()