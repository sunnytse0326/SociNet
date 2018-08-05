package com.socinet.uicomponent

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView

class UserRecyclerViewHolder(mainUI: UserRecyclerViewUI, itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name: TextView
    var email: TextView
    var phone: TextView
    var cardView: CardView
    var userInfo: Button
    var content: Button

    init {
        name = mainUI.name
        email = mainUI.email
        phone = mainUI.phone
        cardView = mainUI.cardView
        userInfo = mainUI.userInfo
        content = mainUI.content
    }

}