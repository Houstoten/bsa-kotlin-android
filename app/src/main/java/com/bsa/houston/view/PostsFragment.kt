package com.bsa.houston.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
            list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, _ ->
                //Navigation.createNavigateOnClickListener(R.id.postExtendedFragment)
                //Navigation.findNavController(view).navigate(R.id.postExtendedFragment) tried but no way
                fragmentManager?.beginTransaction()
                    ?.setCustomAnimations(
                        R.anim.slide_in_left,
                        R.anim.slide_exit_in_left,
                        R.anim.slide_in_right,
                        R.anim.slide_exit_in_right
                    )
                    ?.replace(
                        R.id.posts_fragm_container,
                        PostExtendedFragment(
                            (parent.getItemAtPosition(position) as Post).id,
                            (parent.getItemAtPosition(position) as Post).userId
                        )
                    )?.addToBackStack(null)?.commit()
            }
        })
    }
}
