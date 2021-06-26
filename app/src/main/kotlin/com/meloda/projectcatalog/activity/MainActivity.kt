package com.meloda.projectcatalog.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.meloda.projectcatalog.R
import com.meloda.projectcatalog.dialog.SearchDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            showSearchDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showSearchDialog() {
        val dialog = SearchDialog()
        dialog.show(supportFragmentManager, null)
    }
}