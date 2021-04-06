package lt.todo.messenger_application

import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*

class LoginViewModel: ViewModel() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    var authResult: MutableLiveData<Boolean> = MutableLiveData()
    var authErrorMsg: MutableLiveData<String> = MutableLiveData()

    private fun initFirebaseAuth(){
        auth = FirebaseAuth.getInstance()
    }

    private fun initFirebaseUser(){
        firebaseUser = auth.currentUser!!
    }


    fun login(emailText: String, passwordText: String){

        initFirebaseAuth()
        initFirebaseUser()

        val email: String = emailText
        val password: String = passwordText

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    authResult.value = true
                }else{
                    authErrorMsg.value = task.exception!!.message.toString()
                }

            }

    }

}