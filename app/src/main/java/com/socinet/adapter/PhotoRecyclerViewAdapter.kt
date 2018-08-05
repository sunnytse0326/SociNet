package com.socinet.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.socinet.model.Photo
import com.socinet.model.User
import com.socinet.uicomponent.*
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext

class PhotoRecyclerViewAdapter constructor(mListener: OnClickListener, mPhotos: MutableList<Photo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var headerView: View? = null
    private var footerView: View? = null
    var listener: OnClickListener = mListener
    var photos: MutableList<Photo> = mPhotos

    private enum class Type {
        Header, Content, Footer
    }

    fun setHeaderView(view: View) {
        headerView = view;
    }

    fun addPhotos(mPhotos: List<Photo>){
        photos.addAll(mPhotos)
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
            val ui = PhotoRecyclerViewUI()
            PhotoRecyclerViewHolder(ui, ui.createView(AnkoContext.create(parent.context, parent)))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PhotoRecyclerViewHolder) {
            Picasso.get().load(photos[position].thumbnailUrl).into( holder.image)
            holder.image.setOnClickListener {
                listener?.onBackgroundClicked(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return photos.size + (if(headerView != null) 1 else 0) + (if(footerView != null) 1 else 0)
    }

    interface OnClickListener {
        fun onBackgroundClicked(position: Int)
    }

}