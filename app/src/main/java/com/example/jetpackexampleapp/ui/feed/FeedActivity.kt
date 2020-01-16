package com.example.jetpackexampleapp.ui.feed

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.Post
import com.example.jetpackexampleapp.di.ViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_feed.*
import java.util.*
import javax.inject.Inject


class FeedActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        val GOOGLE_ACCOUNT = "google_account"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var viewModel: FeedViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_feed)
        postButton.setOnClickListener {
            newPost()
        }

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)
        postsRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PostRecyclerViewAdapter(this) { post, like ->
            viewModel.toggleLiked(post, like)
        }
        postsRecyclerView.adapter = adapter
        viewModel.posts.observe(this, androidx.lifecycle.Observer {posts ->
            posts?.let {
                adapter.setPosts(posts)
            }
        })
    }


    private fun newPost() {
        if (newPostEditText.text.trim().toString().isBlank()) {
            newPostEditText.error = "Enter some content first!"
        } else {
            val post = Post(
                newPostEditText.text.toString(),
                0,
                Date(System.currentTimeMillis()).toString(),
                1500,
                0
            )
            viewModel.post(post)
            newPostEditText.text = null
        }
    }
}
