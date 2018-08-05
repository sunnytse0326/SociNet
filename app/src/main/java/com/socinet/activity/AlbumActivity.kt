package com.socinet.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.socinet.adapter.AlbumAdapter
import com.socinet.uicomponent.AlbumUI
import org.jetbrains.anko.setContentView

class AlbumActivity : AppCompatActivity() {
    private lateinit var mainUI: AlbumUI
    private lateinit var adapter: AlbumAdapter

    companion object {
        val USERID = "USERID"
        val ALBUMID = "ALBUMID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainUI = AlbumUI()
        mainUI.setContentView(this)

        adapter = AlbumAdapter(supportFragmentManager, intent.getIntExtra(USERID, 0), intent.getIntExtra(ALBUMID, 0))
        mainUI.viewPager.adapter = adapter
    }
}
