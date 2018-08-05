package com.socinet.uicomponent

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class PostRecyclerViewHolder(mainUI: PostRecyclerViewUI, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView
    var body: TextView
    var cardView: CardView

    init {
        title = mainUI.title
        body = mainUI.body
        cardView = mainUI.cardView
    }

}