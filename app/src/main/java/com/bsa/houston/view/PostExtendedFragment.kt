package com.bsa.houston.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bsa.houston.PostsApplication

import com.bsa.houston.R
import com.bsa.houston.adapters.PostAdapter
import com.bsa.houston.adapters.PostExtendedAdapter
import com.bsa.houston.viewmodel.PostExtendedViewModel
import kotlinx.android.synthetic.main.post_extended_fragment.*

class PostExtendedFragment(private val postId: Long, private val userId: Long) : Fragment() {
    private val postExtendedViewModel: PostExtendedViewModel =
        PostsApplication.injectExtendedPostFactory(
            userId = userId,
            postId = postId
        )
            .create(PostExtendedViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_extended_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        postExtendedViewModel.collectedById.observe(this, Observer {
            view?.findViewById<TextView>(R.id.author_name)?.text = it.user?.name
            view?.findViewById<TextView>(R.id.author_username)?.text = it.user?.username
            view?.findViewById<TextView>(R.id.author_email)?.text = it.user?.email
            view?.findViewById<TextView>(R.id.post_title_extended)?.text = it.post?.title
            view?.findViewById<TextView>(R.id.post_body_expanded)?.text = it.post?.body
            post_extended_comments.adapter = context?.let { it1 ->
                it.comments?.let { it2 ->
                    PostExtendedAdapter(
                        it1,
                        it2
                    )
                }
            }

        })
    }
}

