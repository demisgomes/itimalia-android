package com.demisgomes.itimalia_android.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail: EditText = findViewById(R.id.editTextEmailAddress)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonSignIn: Button = findViewById(R.id.buttonSignIn)

        val buttonSignUp: Button = findViewById(R.id.button_sign_in_sign_up)

        viewModel.responseViewModel.observe(this) { responseViewModel ->
            val user = responseViewModel.response

            if (user !== null){
                Toast.makeText(this, "Login succeeded, ${user.name}!", Toast.LENGTH_LONG).show()
                finish()
            }

            else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show()

                editTextEmail.error = "Please fill with a valid email"
                editTextEmail.requestFocus()
                editTextPassword.error = "Please fill with a valid password"

            }

        }


        buttonSignIn.setOnClickListener {

            var isFormValid = true

            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                editTextEmail.error = "Email must not be empty"
                editTextEmail.requestFocus()
                isFormValid = false
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password must not be empty"
                editTextPassword.requestFocus()
                isFormValid = false
            }


            if(isFormValid){
                viewModel.login(UserLogin(email, password))
            }
        }

        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}