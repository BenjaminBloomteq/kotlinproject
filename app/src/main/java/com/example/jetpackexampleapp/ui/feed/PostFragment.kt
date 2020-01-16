package com.example.jetpackexampleapp.ui.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.fragment_post.*
import java.util.*
import javax.inject.Inject

class PostFragment : Fragment() {

    private lateinit var viewModel: PostViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        notLikedImageView.tag = "unliked"
        notLikedImageView.setImageResource(R.mipmap.unliked)
        return inflater.inflate(R.layout.fragment_post, container, false)
    }


}