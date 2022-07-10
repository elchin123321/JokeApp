package com.ei.android.jokeapp.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ei.android.jokeapp.R
import com.ei.android.jokeapp.example.views.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel<Int>

    lateinit var adapter:CommonDataRecyclerAdapter<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabChosen: (Boolean)->Unit = {
            jokesChosen->
            if(jokesChosen)
                show(JokesFragment(),)
            else
                show(QuotesFragment())
        }
        tabLayout.addOnTabSelectedListener(TabListener(tabChosen))



    }
    private fun show(fragment: Fragment){
            supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
    }

    private class TabListener(private val tabChosen:(Boolean)->Unit):
        TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position == 0)

        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
    }
}
interface Show<T>{
    fun show(arg:T)
}
interface ShowText:Show<String>
interface ShowImage:Show<Int>
interface ShowView:Show<Boolean>
interface EnableView{
    fun enable(enable:Boolean)
}
