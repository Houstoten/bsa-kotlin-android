package com.bsa.houston

import android.app.Application
import androidx.room.Room
import com.bsa.houston.repository.CommentRepository
import com.bsa.houston.repository.api.Api
import com.bsa.houston.repository.db.AppDatabase
import com.bsa.houston.repository.PostRepository
import com.bsa.houston.repository.UserRepository
import com.bsa.houston.viewmodel.PostExtendedViewModel
import com.bsa.houston.viewmodel.PostExtendedViewModelFactory
import com.bsa.houston.viewmodel.PostsViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsApplication : Application() {
    companion object {
        private lateinit var retrofit: Retrofit
        private lateinit var api: Api
        private lateinit var postsRepository: PostRepository
        private lateinit var userRepository: UserRepository
        private lateinit var commentRepository: CommentRepository
        private lateinit var postsViewModel: PostsViewModel
        private lateinit var appDatabase: AppDatabase

        fun injectApi() = api

        fun injectViewModel() = postsViewModel

        fun injectExtendedPostFactory(
            pRepository: PostRepository = postsRepository,
            uRepository: UserRepository = userRepository,
            cRepository: CommentRepository = commentRepository,
            userId: Long,
            postId: Long
        ): PostExtendedViewModelFactory {
            return PostExtendedViewModelFactory(
                pRepository,
                uRepository,
                cRepository,
                userId,
                postId
            )
        }

        fun injectUserRepo() = userRepository
        fun injectPostRepo() = postsRepository
        fun injectCommentRepo() = commentRepository

        fun injectPostDao() = appDatabase.postDao()
        fun injectUserDao() = appDatabase.userDao()
        fun injectCommentDao() = appDatabase.commentDao()

    }

    override fun onCreate() {

        retrofit = Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        api = retrofit.create(Api::class.java)
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "mvvm-database"
        ).build()

        postsRepository = PostRepository(appDatabase.postDao(), api)
        userRepository = UserRepository(appDatabase.userDao(), api)
        commentRepository = CommentRepository(appDatabase.commentDao(), api)
        postsViewModel = PostsViewModel(postsRepository)
        super.onCreate()

    }
}