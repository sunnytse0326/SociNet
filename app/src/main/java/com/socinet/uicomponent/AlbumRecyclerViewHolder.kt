package com.socinet.uicomponent

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView

class AlbumRecyclerViewHolder(mainUI: AlbumRecyclerViewUI, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView
    var cardView: CardView

    init {
        title = mainUI.title
        cardView = mainUI.cardView
    }

}