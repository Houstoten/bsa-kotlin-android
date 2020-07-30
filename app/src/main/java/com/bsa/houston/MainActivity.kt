package com.bsa.houston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bsa.houston.data.Post
import com.bsa.houston.data.PostRepository
import com.bsa.houston.posts.PostsFragment
import com.bsa.houston.posts.PostsViewModel
import com.bsa.houston.posts.PostsViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.posts_fragm_container, PostsFragment()).commit()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(this@MainActivity, R.id.main_activity_host).navigateUp()
    }

}
