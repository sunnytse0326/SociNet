package com.socinet.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.socinet.model.User
import com.socinet.uicomponent.EmptyViewHolder
import com.socinet.uicomponent.EmptyViewUI
import com.socinet.uicomponent.UserRecyclerViewHolder
import com.socinet.uicomponent.UserRecyclerViewUI
import org.jetbrains.anko.AnkoContext

class UserRecyclerViewAdapter constructor(mListener: OnClickListener, mUsers: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var headerView: View? = null
    private var footerView: View? = null
    var listener: OnClickListener = mListener
    var users: List<User> = mUsers

    private enum class Type {
        Header, Content, Footer
    }

    fun setHeaderView(view: View) {
        headerView = view;
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
            val ui = UserRecyclerViewUI()
            UserRecyclerViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserRecyclerViewHolder) {
            holder.name.text = users[position].name
            holder.email.text = users[position].email
            holder.phone.text = users[position].phone
            holder.cardView.setOnClickListener {
                listener?.onBackgroundClicked(position)
            }
            holder.userInfo.setOnClickListener {
                listener?.onUserInfoClicked(position)
            }
            holder.content.setOnClickListener {
                listener?.onContentClicked(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return users.size + (if(headerView != null) 1 else 0) + (if(footerView != null) 1 else 0)
    }

    interface OnClickListener {
        fun onBackgroundClicked(position: Int)
        fun onUserInfoClicked(position: Int)
        fun onContentClicked(position: Int)
    }

}