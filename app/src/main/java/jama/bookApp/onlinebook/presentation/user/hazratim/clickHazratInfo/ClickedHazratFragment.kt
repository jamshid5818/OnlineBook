package jama.bookApp.onlinebook.presentation.user.hazratim.clickHazratInfo

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.database.database.Database
import jama.bookApp.onlinebook.databinding.FragmentClickedHazratBinding
import jama.bookApp.onlinebook.presentation.user.BaseFragment

@AndroidEntryPoint
class ClickedHazratFragment : BaseFragment<FragmentClickedHazratBinding>(FragmentClickedHazratBinding::inflate){
    private val adapter by lazy {
        ClickedHazratAdapter()
    }
    override fun onViewCreate() {
        adapter.setData(Database.getDatabase().hazratAbout)
        binding.listhazratInfo.layoutManager = LinearLayoutManager(requireContext())
        binding.listhazratInfo.adapter = adapter
        adapter.setItemClickListener {
            val bundle = Bundle();
            bundle.putString("HAZRATINFO",it)
            navController.navigate(R.id.action_clickedHazratFragment_to_itemClickFragment, bundle)
        }
    }
}