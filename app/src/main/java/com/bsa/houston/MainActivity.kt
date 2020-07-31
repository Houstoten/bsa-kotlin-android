package com.bsa.houston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import com.bsa.houston.view.PostsFragment

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
