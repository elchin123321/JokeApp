package com.ei.android.jokeapp.example.views

import com.google.android.material.tabs.TabLayout

private class TabListener(private val tabChosen:(Boolean)->Unit):
    TabLayout.OnTabSelectedListener{
    override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position == 0)

    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
}