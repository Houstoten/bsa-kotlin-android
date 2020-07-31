package com.bsa.houston.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bsa.houston.R
import com.bsa.houston.viewmodel.PostExtendedViewModel

class PostExtendedFragment : Fragment() {

    companion object {
        fun newInstance() = PostExtendedFragment()
    }

    private lateinit var viewModel: PostExtendedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_extended_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostExtendedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
