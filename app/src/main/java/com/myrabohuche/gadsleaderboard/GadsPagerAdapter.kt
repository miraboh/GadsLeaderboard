package com.myrabohuche.gadsleaderboard

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GadsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LearningFragment.newInstance()
            else -> {
                return SkillIQFragment.newInstance()
            }
        }
    }

}