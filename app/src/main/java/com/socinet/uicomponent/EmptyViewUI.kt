package com.socinet.uicomponent

import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import org.jetbrains.anko.*

class EmptyViewUI : AnkoComponent<ViewGroup> {
    lateinit var relativeLayout: RelativeLayout

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
        relativeLayout {
            relativeLayout = relativeLayout{}
        }
    }
}