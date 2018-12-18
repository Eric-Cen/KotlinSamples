package com.mcarving.swiptewithtabs


import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager

import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var mDemoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewPager and its adapters use support library
        // fragments, so use supportFragmentManager.
        mDemoCollectionPagerAdapter = DemoCollectionPagerAdapter(supportFragmentManager)
        mViewPager = findViewById<ViewPager>(R.id.pager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        // Specify that tabs should be displayed in the action bar.
        supportActionBar?.navigationMode = ActionBar.NAVIGATION_MODE_TABS

        // Create a tab listener that is called when the user changes tabs.
        val tabListener = object : ActionBar.TabListener {

            override fun onTabSelected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // show the given tab
                mViewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // hide the given tab
            }

            override fun onTabReselected(tab: ActionBar.Tab, ft: FragmentTransaction) {
                // probably ignore this event
            }
        }

        val TAG = "MainActivity"
        Log.d(TAG, "onCreate: ${tabListener.toString()}")
        // Add 3 tabs, specifying the tab's text and TabListener
        for (i in 0 until 3) {
            supportActionBar?.addTab(
                supportActionBar?.newTab()
                    ?.setText("Tab " + (i + 1))
                     ?.setTabListener(tabListener)
            )
        }


        mViewPager.setOnPageChangeListener(
            object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    // When swiping between pages, select the
                    // corresponding tab.
                    supportActionBar?.setSelectedNavigationItem(position)
                }
            })
    }
}
