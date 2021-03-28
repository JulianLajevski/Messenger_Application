package lt.todo.messenger_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        initListeners()
    }

    private fun initListeners(){
        signUpButton.setOnClickListener {
            val email: String = email.text.toString()
            val password: String = password.text.toString()
            val confirmPassword: String = passwordConfirm.text.toString()
            val name: String = name.text.toString()

            if(password == confirmPassword){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if(task.isSuccessful){
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(
                                requireContext(),
                                "Registered!",
                                Toast.LENGTH_SHORT
                            ).show()

                            val user: FirebaseUser? = auth.currentUser
                            val userId: String = user!!.uid

                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("userId", userId)
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("password", password)
                            hashMap.put("profileImage", "")

                            databaseReference.setValue(hashMap)

                        }else{
                            Toast.makeText(
                                requireContext(),
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                )
            }else{
                Toast.makeText(
                    requireContext(),
                    "Password not same!",
                    Toast.LENGTH_SHORT
                ).show()
            }


        }
    }

}