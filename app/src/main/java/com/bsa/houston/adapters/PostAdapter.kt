package com.bsa.houston.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bsa.houston.R
import com.bsa.houston.repository.data.Post


class PostAdapter(
    private val context: Context,
    private val posts: List<Post>,
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_item_post, parent, false)
        }
        val p:Post = posts[position]
        (view?.findViewById<View>(R.id.post_title) as TextView).text = p.title
        (view.findViewById<View>(R.id.post_body) as TextView).text = p.body
        return view
    }

    override fun getItem(position: Int): Post {
        return posts[position]
    }

    override fun getItemId(position: Int): Long {
        return posts[position].id
    }

    override fun getCount(): Int {
        return posts.size
    }

}