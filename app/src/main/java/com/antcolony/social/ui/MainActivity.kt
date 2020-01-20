package com.antcolony.social.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antcolony.social.R
import com.antcolony.social.ui.register.RegisterFragment
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    private lateinit var mFragment: Fragment

    private fun toSignUp() { // sets fragment to register fragment
        mFragment = RegisterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentFrameLayout, mFragment, "registerFragment").commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        FacebookSdk.sdkInitialize(application)
        AppEventsLogger.activateApp(application)
        toSignUp()
        backArrowImageView.setOnClickListener {
            toSignUp()
        }
    }

    fun changeBackButtonState() {
        val backArrow = findViewById<ImageView>(R.id.backArrowImageView)
        backArrow.visibility =
            if (backArrow.visibility == View.VISIBLE) View.INVISIBLE else View.VISIBLE
    }

}
