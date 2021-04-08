package lt.todo.messenger_application

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners(){
        signUpButton.setOnClickListener {
            val email = etToString(emailEt)
            val password = etToString(passwordEt)
            val name = etToString(nameEt)
            val confirmPassword = etToString(passwordConfirmEt)

            if(email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && confirmPassword.isNotBlank()){
                if(password == confirmPassword){
                    registerViewModel.register(email, password, name)
                    registerViewModel.authResult.observe(viewLifecycleOwner, { result ->
                        when (result){
                            true ->{
                                Toast.makeText(
                                        requireContext(),
                                        getString(R.string.registerSuccesfullText),
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                            false ->{
                                registerViewModel.authErrorMessage.observe(viewLifecycleOwner,{ errorMsg ->
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
                            getString(R.string.registerNotSamePasswordText),
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

}