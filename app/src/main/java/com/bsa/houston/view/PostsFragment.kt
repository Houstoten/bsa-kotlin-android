package com.bsa.houston.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import com.bsa.houston.PostsApplication

import com.bsa.houston.R
import com.bsa.houston.adapters.PostAdapter
import com.bsa.houston.repository.data.Post
import kotlinx.android.synthetic.main.posts_fragment.*

class PostsFragment : ListFragment() {
    private val viewModel = PostsApplication.injectViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.posts_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.posts.observe(this, Observer {
            list.adapter = context?.let { it1 -> PostAdapter(it1, it) }
            list.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
                Toast.makeText(
                    context,
                    (parent.getItemAtPosition(position) as Post).userId.toString(),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }
}
