package com.bsa.houston.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.bsa.houston.R
import com.bsa.houston.repository.data.Comment

class PostExtendedAdapter(
    private val context: Context,
    private val comments: List<Comment>,
    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = layoutInflater.inflate(R.layout.comment_row_extended, parent, false)
        }
        val p: Comment = comments[position]
        (view?.findViewById<View>(R.id.row_comment_name) as TextView).text = p.name
        (view.findViewById<View>(R.id.row_comment_email) as TextView).text = p.email
        (view.findViewById<View>(R.id.row_comment_body) as TextView).text = p.body
        return view
    }

    override fun getItem(position: Int): Comment {
        return comments[position]
    }

    override fun getItemId(position: Int): Long {
        return comments[position].id
    }

    override fun getCount(): Int {
        return comments.size
    }

}