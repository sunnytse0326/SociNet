package com.socinet.uicomponent

import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TodoRecyclerViewUI : AnkoComponent<ViewGroup> {
    lateinit var title: TextView
    lateinit var body: TextView
    lateinit var cardView: CardView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            padding = dip(5)
            lparams(matchParent, wrapContent)
            cardView = cardView {
                cardElevation = dip(6).toFloat()
                radius = dip(5).toFloat()
                useCompatPadding = true
                verticalLayout {
                    padding = dip(15)
                    title = textView {
                        textSize = 22f
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                    body = textView {
                        textSize = 16f
                    }.lparams {
                        width = matchParent
                        height = wrapContent
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