package br.com.euatendo.ui.auth

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import br.com.euatendo.databinding.FragmentLoginBinding
import br.com.euatendo.data.network.APIService
import br.com.euatendo.data.repository.AuthRepository
import br.com.euatendo.ui.base.BaseFragment
import br.com.euatendo.data.network.Resource
import com.irozon.sneaker.Sneaker
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    when (it.value.status) {
                        200 -> {
                            lifecycleScope.launch {
                                userPreferences.saveAuthToken(it.value.token)
                            }
                            Sneaker.with(this) // Activity, Fragment or ViewGroup
                                .setTitle("Eu atendo você")
                                .setMessage(it.value.message)
                                .sneakSuccess()
                        }
                        404 -> {
                            Sneaker.with(this) // Activity, Fragment or ViewGroup
                                .setTitle("Eu atendo você")
                                .setMessage(it.value.message)
                                .sneakWarning()
                        }
                    }

                }
                is Resource.Failure -> {
                    Sneaker.with(this) // Activity, Fragment or ViewGroup
                        .setTitle("Erro")
                        .setMessage("De momento não é possivel aceder a aplicação, reveja as suas credenciais")
                        .sneakError()
                }
            }
        })
        binding.mtButtonLogin.setOnClickListener {
            val email = binding.tilLoginEmail.editText?.text.toString().trim()
            val password = binding.tilLoginPassword.editText?.text.toString().trim()
            //@todo add input validations
            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(APIService::class.java))

}