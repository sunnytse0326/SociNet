package com.socinet.uicomponent

import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.socinet.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class UserRecyclerViewUI : AnkoComponent<ViewGroup> {
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var phone: TextView
    lateinit var cardView: CardView

    lateinit var userInfo: Button
    lateinit var content: Button

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            padding = dip(5)
            lparams(matchParent, wrapContent)
            cardView = cardView {
                cardElevation = dip(6).toFloat()
                radius = dip(5).toFloat()
                useCompatPadding = true
                isClickable = true

                verticalLayout {
                    padding = dip(15)
                    name = textView {
                        textSize = 22f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    email = textView {
                        textSize = 16f
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    phone = textView {
                        textSize = 16f
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    userInfo = button {
                        textSize = 16f
                        textResource = R.string.user_info
                    }.lparams {
                        topMargin = dip(10)
                        width = org.jetbrains.anko.matchParent
                        height = org.jetbrains.anko.wrapContent
                    }
                    content = button {
                        textSize = 16f
                        textResource = R.string.content
                    }.lparams {
                        gravity = Gravity.CENTER
                        width = org.jetbrains.anko.matchParent
                        height = org.jetbrains.anko.wrapContent
                    }
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }.lparams {
                width = matchParent
                height = wrapContent
            }
        }
    }
}