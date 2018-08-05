package com.socinet.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socinet.adapter.HomeAdapter
import com.socinet.uicomponent.HomeUI
import org.jetbrains.anko.setContentView

class HomeActivity : AppCompatActivity() {
    private lateinit var mainUI: HomeUI
    private lateinit var adapter: HomeAdapter

    companion object {
        val USERID = "USERID"
        val USERNAME = "USERNAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = HomeUI()
        mainUI.setContentView(this)
        this.setTitle(intent.getStringExtra(USERNAME))

        adapter = HomeAdapter(supportFragmentManager, this, mainUI.tabsIdx, intent.getIntExtra(USERID, 0))
        mainUI.viewPager.adapter = adapter
        mainUI.tabLty.setupWithViewPager(mainUI.viewPager)
    }
}
