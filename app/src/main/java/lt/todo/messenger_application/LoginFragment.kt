package lt.todo.messenger_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if(email.isNotBlank() && password.isNotBlank()){
                loginViewModel.login(email, password)
                loginViewModel.authResult.observe(viewLifecycleOwner,{ result ->

                    when (result){
                        true ->{
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.loginSuccessfulText),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        false ->{
                            loginViewModel.authErrorMsg.observe(viewLifecycleOwner, { errorMsg ->
                                Toast.makeText(
                                        requireContext(),
                                        errorMsg,
                                        Toast.LENGTH_SHORT
                                ).show()
                            })
                        }
                    }
                })
            }else{
                Toast.makeText(
                    requireContext(),
                    getString(R.string.emptyLoginFields),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}