package br.com.euatendo.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.euatendo.databinding.FragmentLoginBinding
import br.com.euatendo.data.network.APIService
import br.com.euatendo.data.repository.AuthRepository
import br.com.euatendo.ui.base.BaseFragment
import br.com.euatendo.util.Resource

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Senha/email incorrecto", Toast.LENGTH_LONG).show()
                }
            }
        })
        binding.mtButtonLogin.setOnClickListener {
            val email = binding.tilLoginEmail.editText.toString().trim()
            val password = binding.tilLoginPassword.editText.toString().trim()
            //@todo add input validations
            viewModel.login(email, password)
        }
    }
    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(APIService::class.java))

}