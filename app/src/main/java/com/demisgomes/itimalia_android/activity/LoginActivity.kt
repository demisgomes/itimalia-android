package com.demisgomes.itimalia_android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.databinding.ActivityLoginBinding
import com.demisgomes.itimalia_android.databinding.ActivitySignUpBinding
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.responseViewModel.observe(this) { responseViewModel ->
            binding.progressBarSignIn.visibility = View.GONE

            val user = responseViewModel.response

            if (user !== null){
                Toast.makeText(this, getString(R.string.welcome_message, user.name), Toast.LENGTH_LONG).show()
                finish()
            }

            else {
                Toast.makeText(this, getString(R.string.invalid_username_password), Toast.LENGTH_LONG).show()

                binding.editTextEmailAddress.error = getString(R.string.invalid_field, "email")
                binding.editTextEmailAddress.requestFocus()
                binding.editTextPassword.error = getString(R.string.invalid_field, "password")

            }

        }


        binding.buttonSignIn.setOnClickListener {

            var isFormValid = true

            val email = binding.editTextEmailAddress.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.editTextEmailAddress.error = getString(R.string.field_required, "Email")
                binding.editTextEmailAddress.requestFocus()
                isFormValid = false
            }

            if (password.isEmpty()) {
                binding.editTextPassword.error = getString(R.string.field_required, "Password")
                binding.editTextPassword.requestFocus()
                isFormValid = false
            }


            if(isFormValid){
                binding.progressBarSignIn.visibility = View.VISIBLE
                viewModel.login(UserLogin(email, password))
            }
        }

        binding.buttonSignInSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}