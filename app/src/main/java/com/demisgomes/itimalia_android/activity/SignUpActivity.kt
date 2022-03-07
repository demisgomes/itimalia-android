package com.demisgomes.itimalia_android.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.demisgomes.itimalia_android.R
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.Gender
import com.demisgomes.itimalia_android.domain.user.NewUser
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import com.demisgomes.itimalia_android.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModel()

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var spinnerGender: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        editTextName = findViewById(R.id.edit_text_sign_up_name)
        editTextEmail = findViewById(R.id.edit_text_sign_up_email)
        editTextPassword = findViewById(R.id.edit_text_sign_up_password)
        editTextConfirmPassword = findViewById(R.id.edit_text_sign_up_confirm_password)
        editTextPhone = findViewById(R.id.edit_text_sign_up_phone)
        spinnerGender = findViewById(R.id.spinner_sign_up_gender)

        spinnerGender.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, Gender.values().map { it.description })

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
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val confirmPassword = editTextConfirmPassword.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val gender = Gender.valueOf(spinnerGender.selectedItem.toString())

            if (isFormValid(name, email, password, confirmPassword, phone)){
                viewModel.signUp(NewUser(email, password, getValidDate(), gender, name, phone))
            }

        }

    }

    private fun setErrorsOnEditTexts(errorResponse: ErrorResponse) {

        errorResponse.details?.forEach {
            if (it.key.contains("name")) {
                editTextName.error = it.value.first()
                editTextName.requestFocus()
            }

            if (it.key.contains("email")) {
                editTextEmail.error = it.value.first()
                editTextEmail.requestFocus()
            }

            if (it.key.contains("password")) {
                editTextPassword.error = it.value.first()
                editTextPassword.requestFocus()
            }

            if (it.key.contains("phone")) {
                editTextPhone.error = it.value.first()
                editTextPhone.requestFocus()
            }
        }

        if (errorResponse.apiError == "EMAIL_ALREADY_EXISTS_ERROR"){
            editTextEmail.error = errorResponse.message
            editTextEmail.requestFocus()
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
            editTextName.error = getString(R.string.field_required, "Name")
            editTextName.requestFocus()
            formValid = false
        }

        if (email.isEmpty()) {
            editTextEmail.error = getString(R.string.field_required, "Email")
            editTextEmail.requestFocus()
            formValid = false
        }

        if (password.isEmpty()) {
            editTextPassword.error = getString(R.string.field_required, "Password")
            editTextPassword.requestFocus()
            formValid = false
        }

        if (password != confirmPassword) {
            editTextPassword.error = getString(R.string.password_confirmation_not_match)
            editTextConfirmPassword.error = getString(R.string.password_confirmation_not_match)
            editTextConfirmPassword.requestFocus()
            formValid = false
        }

        if (phone.isEmpty()) {
            editTextPhone.error = getString(R.string.field_required, "Phone")
            editTextPhone.requestFocus()
            formValid = false
        }

        return formValid
    }

    private fun resetErrorsFromEditTexts() {
        editTextName.error = null
        editTextEmail.error = null
        editTextPassword.error = null
        editTextConfirmPassword.error = null
        editTextPhone.error = null
    }

    private fun getValidDate(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 1999)

        return cal.time
    }
}