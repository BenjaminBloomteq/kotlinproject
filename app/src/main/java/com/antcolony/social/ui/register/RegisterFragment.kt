package com.antcolony.social.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.antcolony.social.R
import com.antcolony.social.data.model.User
import com.antcolony.social.ui.MainActivity
import com.antcolony.social.ui.signin.SignInFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_sign_up.*
import javax.inject.Inject


class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUpButton.setOnClickListener {
            onSignUpBtnClick()
        }
        signInTextView.setOnClickListener {
            toSignIn()
        }

        (activity as? MainActivity)?.findViewById<ImageView>(R.id.backArrowImageView)
            ?.visibility = View.INVISIBLE

    }


    private fun onSignUpBtnClick() {
        if (validateFields()) {
            // username equals to email for now
            val user = User(
                fullNameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString(),
                emailEditText.text.toString(),
                0
            )
            if (!viewModel.userExists(user.username) && viewModel.validateEmail(user.email)) {
                viewModel.registerUser(user)
                Toast.makeText(this.activity, "User registered", Toast.LENGTH_SHORT).show()
                toSignIn()
            } else if (!viewModel.validateEmail(user.username)) {
                Toast.makeText(this.activity, "Email format must be valid!", Toast.LENGTH_SHORT)
                    .show()
            } else if (viewModel.userExists(user.username)) {
                Toast.makeText(
                    this.activity,
                    "User with this username already exists",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun toSignIn() { // sets fragment to sign in fragment view
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentFrameLayout, SignInFragment(), "signInFragment")?.commit()
        (activity as? MainActivity)?.changeBackButtonState()
    }

    private fun validateFields(): Boolean {

        var fieldsValid = true

        val fullNameText = fullNameEditText.text.trim().toString()
        val emailText = emailEditText.text.trim().toString()
        val passwordText = passwordEditText.text.trim().toString()

        if (fullNameText.isBlank()) {
            fieldsValid = false
            fullNameEditText.error = "Name field should be atleast 3 characters long!"
        } else {
            fullNameEditText.error = null
        }

        if (emailText.isBlank()) {
            fieldsValid = false
            emailEditText.error = "Email field cannot be empty!"
        } else {
            emailEditText.error = null
        }

        if (passwordText.isBlank() || passwordText.length < 6) {
            fieldsValid = false
            passwordEditText.error = "Password must be atleast 6 characters long!"
        } else {
            passwordEditText.error = null
        }

        if (!termsCheckBox.isChecked) {
            fieldsValid = false
            termsAndConditionsTextView.error = "You must accept the terms and conditions!"
        } else {
            termsAndConditionsTextView.error = null
        }

        return fieldsValid
    }

}