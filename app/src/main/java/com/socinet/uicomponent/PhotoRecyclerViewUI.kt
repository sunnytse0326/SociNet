package com.socinet.uicomponent

import android.graphics.Typeface
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.socinet.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PhotoRecyclerViewUI : AnkoComponent<ViewGroup> {
    lateinit var photo: ImageView

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            padding = dip(5)
            lparams(matchParent, wrapContent)
            photo = imageView {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }.lparams {
                margin = dip(10)
                width = dip(75)
                height = dip(75)
                gravity = Gravity.CENTER
            }
        }
    }
}