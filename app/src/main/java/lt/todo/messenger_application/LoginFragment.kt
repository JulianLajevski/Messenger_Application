package lt.todo.messenger_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){

        registerButton.setOnClickListener {

        }

        loginButton.setOnClickListener {
            loginViewModel.login()
            loginViewModel.authResult.observe(viewLifecycleOwner,{ result ->

                when (result){
                    true ->{
                        Toast.makeText(
                        requireContext(),
                        "You're logged successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    }
                    false ->{
                        Toast.makeText(
                        requireContext(),
                        //result.exception!!.message.toString(),
                            "Bad credentials!",
                        Toast.LENGTH_SHORT
                    ).show()
                    }
                }
            })
        }

    }
}