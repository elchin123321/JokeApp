package com.ei.android.jokeapp.example.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity:FragmentActivity):FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int) = if(position == 0)
        JokesFragment()
    else
        QuotesFragment()
}