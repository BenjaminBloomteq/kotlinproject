package com.antcolony.social.ui.signin

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.antcolony.social.R
import com.antcolony.social.ui.MainActivity
import com.antcolony.social.ui.feed.FeedActivity
import com.antcolony.social.ui.register.RegisterFragment
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject


class SignInFragment : Fragment() {

    private lateinit var viewModel: SignInViewModel

    private var callbackManager = CallbackManager.Factory.create()

    @Inject
    lateinit var application: Application

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                101 -> {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(data)
                    val account: GoogleSignInAccount? = task.result
                    onLoggedIn(account)
                }
            }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun onLoggedIn(googleSignInAccount: GoogleSignInAccount?) {
        val intent = Intent(application, FeedActivity::class.java)
        intent.putExtra(FeedActivity.GOOGLE_ACCOUNT, googleSignInAccount)
        startActivity(intent)
        activity?.finish()
    }

    private fun signIn() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = context?.let { GoogleSignIn.getClient(it, gso) }!!
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 101)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.fragment = this
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {
                toPostFeed()
            }

            override fun onCancel() { // App code
            }

            override fun onError(exception: FacebookException) { // App code
            }
        })
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) { // App code
                    toPostFeed()
                }

                override fun onCancel() { // App code
                }

                override fun onError(exception: FacebookException) { // App code
                }
            })
        signInButton.setOnClickListener {
            onSignInBtnClicked()
        }
        sign_in_button.setOnClickListener {
            if (GoogleSignIn.getLastSignedInAccount(application) != null) {
                signIn()
            }
        }
        login_button.setOnClickListener {
            val accessToken = AccessToken.getCurrentAccessToken()
            val isLoggedIn = accessToken != null && !accessToken.isExpired

            if (isLoggedIn) {
                toPostFeed()
            }
        }
        signUpTextView.setOnClickListener {
            toSignUp()
        }

    }

    private fun onSignInBtnClicked() {
        if (validateFields()) {
            if (viewModel.validateUserExists(
                    usernameLoginEditText.text.toString(),
                    passwordLoginEditText.text.toString()
                )
            ) {
                viewModel.loginUser(usernameLoginEditText.text.toString())
                activity?.finish()
                toPostFeed()
            }
        }
    }

    private fun validateFields(): Boolean {

        var fieldsValid = true

        val fullNameText = usernameLoginEditText.text.trim().toString()
        val passwordText = passwordLoginEditText.text.trim().toString()

        if (fullNameText.isBlank()) {
            fieldsValid = false
            usernameLoginEditText.error = "Please enter a valid username"
        } else {
            usernameLoginEditText.error = null
        }

        if (passwordText.isBlank()) {
            fieldsValid = false
            passwordLoginEditText.error = "Please enter a password"
        } else {
            passwordLoginEditText.error = null
        }

        return fieldsValid
    }

    private fun toPostFeed() {
        val intent = Intent(this.activity, FeedActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun toSignUp() {
        if (activity is MainActivity) {
            (activity as MainActivity).changeBackButtonState()
        }
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragmentFrameLayout, RegisterFragment(), "registerFragment")?.commit()
    }
}