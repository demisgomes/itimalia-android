package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.user.Gender
import com.demisgomes.itimalia_android.domain.user.NewUser
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import com.demisgomes.itimalia_android.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val editTextName: EditText = findViewById(R.id.edit_text_sign_up_name)
        val editTextEmail: EditText = findViewById(R.id.edit_text_sign_up_email)
        val editTextPassword: EditText = findViewById(R.id.edit_text_sign_up_password)
        val editTextConfirmPassword: EditText = findViewById(R.id.edit_text_sign_up_confirm_password)
        val editTextPhone: EditText = findViewById(R.id.edit_text_sign_up_phone)
        val spinnerGender: Spinner = findViewById(R.id.spinner_sign_up_gender)

        spinnerGender.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Gender.values().map { it.description })

        val buttonSignUp: Button = findViewById(R.id.button_sign_up)

        viewModel.responseViewModel.observe(this) {
            val user = it.response

            if (user !== null){
                Toast.makeText(this, "Cadastro efetuado com sucesso, ${user.name}!", Toast.LENGTH_LONG).show()
                finish()
            }

            else {
                Toast.makeText(this, "Ocorreu um erro: ${it.errorMessage}", Toast.LENGTH_LONG).show()
            }

        }

        buttonSignUp.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editTextConfirmPassword.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val gender = Gender.valueOf(spinnerGender.selectedItem.toString())

            var formValid = true
            if (name.isEmpty()) {
                editTextName.error = "Name must not be empty"
                formValid = false
            }

            if (email.isEmpty()) {
                editTextEmail.error = "Email must not be empty"
                formValid = false
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password must not be empty"
                formValid = false
            }

            if (password != confirmPassword) {
                editTextPassword.error = "Password and confirmation not match"
                editTextConfirmPassword.error = "Password and confirmation not match"
                formValid = false
            }

            if (formValid){
                viewModel.signUp(NewUser(email, password, getValidDate(), gender, name, phone))
            }

        }

    }

    private fun getValidDate(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 1999)

        return cal.time
    }
}