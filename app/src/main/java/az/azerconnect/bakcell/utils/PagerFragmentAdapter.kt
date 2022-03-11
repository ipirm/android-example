package az.azerconnect.bakcell.utils

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerFragmentAdapter(parent: Fragment) : FragmentStateAdapter(parent) {
    private var list = arrayListOf<Fragment>()

    fun setFragments(fragments: List<Fragment>) {
        list = fragments as ArrayList<Fragment>
    }

    fun addFragment(fragment: Fragment) {
        list.add(fragment)
    }

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}