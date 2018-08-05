package com.socinet.uicomponent

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PhotoRecyclerViewHolder(mainUI: PhotoRecyclerViewUI, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView

    init {
        image = mainUI.photo
    }

}