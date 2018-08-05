package com.socinet.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.socinet.model.Todo
import com.socinet.uicomponent.*
import org.jetbrains.anko.AnkoContext

class TodoRecyclerViewAdapter constructor(mListener: OnClickListener, mTodos: MutableList<Todo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var headerView: View? = null
    private var footerView: View? = null
    var listener: OnClickListener = mListener
    var todos: MutableList<Todo> = mTodos

    private enum class Type {
        Header, Content, Footer
    }

    fun setHeaderView(view: View) {
        headerView = view;
    }

    fun addPosts(mPosts: List<Todo>){
        todos.addAll(mPosts)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && headerView != null) {
            Type.Header.ordinal
        } else if (position == itemCount - 1 && footerView != null) {
            Type.Footer.ordinal
        } else {
            Type.Content.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Type.Header.ordinal) {
            val ui = EmptyViewUI()
            EmptyViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)), headerView)
        } else if (viewType == Type.Footer.ordinal) {
            val ui = EmptyViewUI()
            EmptyViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)), footerView)
        } else {
            val ui = TodoRecyclerViewUI()
            TodoRecyclerViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodoRecyclerViewHolder) {
            holder.title.text = todos[position].title
            holder.body.text = todos[position].completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size + (if(headerView != null) 1 else 0) + (if(footerView != null) 1 else 0)
    }

    interface OnClickListener {
        fun onBackgroundClicked(position: Int)
    }

}