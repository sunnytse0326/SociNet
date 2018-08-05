package com.socinet.uicomponent

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import org.jetbrains.anko.*

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