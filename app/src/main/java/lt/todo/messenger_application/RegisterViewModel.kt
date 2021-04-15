package lt.todo.messenger_application

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterViewModel: ViewModel() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    var authResult: MutableLiveData<Boolean> = MutableLiveData()
    var authErrorMessage: MutableLiveData<String> = MutableLiveData()

    private fun initAuth(){
        auth = FirebaseAuth.getInstance()
    }

    fun register(emailText: String, passwordText: String, nameText: String){
        val email: String = emailText
        val password: String = passwordText
        val name: String = nameText

            initAuth()
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if(task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            authResult.value = true
                            val user: FirebaseUser? = auth.currentUser
                            val userId: String = user!!.uid

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap["userId"] = userId
                            hashMap["name"] = name
                            hashMap["email"] = email
                            hashMap["password"] = password
                            hashMap["profileImage"] = ""

                            databaseReference.setValue(hashMap)

                        }else{
                            authErrorMessage.value = task.exception!!.message.toString()
                        }
                    }
            )
    }
}