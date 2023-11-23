package gachon.third.umc.android

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileViewpagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int=2


    //불러올 Fragment 정의
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentProfileTab1()
            1 -> FragmentProfileTab2()
            else -> FragmentProfileTab1()
        }
    }
}