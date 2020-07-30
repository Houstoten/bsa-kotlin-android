package com.bsa.houston.posts

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import com.bsa.houston.PostsApplication

import com.bsa.houston.R
import com.bsa.houston.data.Post
import kotlinx.android.synthetic.main.posts_fragment.*
import java.net.ConnectException
import java.net.UnknownHostException

class PostsFragment : ListFragment() {
    private val viewModel = PostsApplication.injectViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.posts.observe(this, Observer {
            list.adapter = context?.let { it1 ->
                ArrayAdapter<String>(
                    it1,
                    android.R.layout.simple_list_item_1, it.map { x -> x.title }
                )
            }
        });
    }
}
