package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.databinding.ActivitySignUpBinding
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.Gender
import com.demisgomes.itimalia_android.domain.user.NewUser
import com.demisgomes.itimalia_android.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModel()

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerSignUpGender.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, Gender.values().map { it.description })

        val buttonSignUp: Button = findViewById(R.id.button_sign_up)

        viewModel.responseViewModel.observe(this) { responseViewModel ->
            val user = responseViewModel.response

            if (user !== null){
                Toast.makeText(this, getString(R.string.sign_up_message, user.name), Toast.LENGTH_LONG).show()
                finish()
            }

            else {
                Toast.makeText(this, getString(R.string.sign_up_error_message), Toast.LENGTH_LONG).show()
                responseViewModel.errorResponse?.let { setErrorsOnEditTexts(it) }
            }

        }

        buttonSignUp.setOnClickListener {
            val name = binding.editTextSignUpName.text.toString().trim()
            val email = binding.editTextSignUpEmail.text.toString().trim()
            val password = binding.editTextSignUpPassword.text.toString().trim()
            val confirmPassword = binding.editTextSignUpConfirmPassword.text.toString().trim()
            val phone = binding.editTextSignUpPhone.text.toString().trim()
            val gender = Gender.valueOf(binding.spinnerSignUpGender.selectedItem.toString())

            if (isFormValid(name, email, password, confirmPassword, phone)){
                viewModel.signUp(NewUser(email, password, getValidDate(), gender, name, phone))
            }

        }

    }

    private fun setErrorsOnEditTexts(errorResponse: ErrorResponse) {

        errorResponse.details?.forEach {
            if (it.key.contains("name")) {
                binding.editTextSignUpName.error = it.value.first()
                binding.editTextSignUpName.requestFocus()
            }

            if (it.key.contains("email")) {
                binding.editTextSignUpEmail.error = it.value.first()
                binding.editTextSignUpEmail.requestFocus()
            }

            if (it.key.contains("password")) {
                binding.editTextSignUpPassword.error = it.value.first()
                binding.editTextSignUpPassword.requestFocus()
            }

            if (it.key.contains("phone")) {
                binding.editTextSignUpPhone.error = it.value.first()
                binding.editTextSignUpPhone.requestFocus()
            }
        }

        if (errorResponse.apiError == "EMAIL_ALREADY_EXISTS_ERROR"){
            binding.editTextSignUpEmail.error = errorResponse.message
            binding.editTextSignUpEmail.requestFocus()
        }


    }

    // think in a better name
    private fun isFormValid(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String
    ): Boolean {
        var formValid = true

        resetErrorsFromEditTexts()

        if (name.isEmpty()) {
            binding.editTextSignUpName.error = getString(R.string.field_required, "Name")
            binding.editTextSignUpName.requestFocus()
            formValid = false
        }

        if (email.isEmpty()) {
            binding.editTextSignUpEmail.error = getString(R.string.field_required, "Email")
            binding.editTextSignUpEmail.requestFocus()
            formValid = false
        }

        if (password.isEmpty()) {
            binding.editTextSignUpPassword.error = getString(R.string.field_required, "Password")
            binding.editTextSignUpPassword.requestFocus()
            formValid = false
        }

        if (password != confirmPassword) {
            binding.editTextSignUpPassword.error = getString(R.string.password_confirmation_not_match)
            binding.editTextSignUpConfirmPassword.error = getString(R.string.password_confirmation_not_match)
            binding.editTextSignUpConfirmPassword.requestFocus()
            formValid = false
        }

        if (phone.isEmpty()) {
            binding.editTextSignUpPhone.error = getString(R.string.field_required, "Phone")
            binding.editTextSignUpPhone.requestFocus()
            formValid = false
        }

        return formValid
    }

    private fun resetErrorsFromEditTexts() {
        binding.editTextSignUpName.error = null
        binding.editTextSignUpEmail.error = null
        binding.editTextSignUpPassword.error = null
        binding.editTextSignUpConfirmPassword.error = null
        binding.editTextSignUpPhone.error = null
    }

    private fun getValidDate(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 1999)

        return cal.time
    }
}