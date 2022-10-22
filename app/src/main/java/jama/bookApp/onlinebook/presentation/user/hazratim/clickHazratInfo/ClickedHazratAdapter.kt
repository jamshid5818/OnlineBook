package jama.bookApp.onlinebook.presentation.user.hazratim.clickHazratInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import jama.bookApp.onlinebook.data.model.HazratModel
import jama.bookApp.onlinebook.databinding.ItemHazratInfoBinding


class ClickedHazratAdapter : RecyclerView.Adapter<ClickedHazratAdapter.MyViewHolder>() {
    var list = ArrayList<HazratModel>()

    fun setData(list: ArrayList<HazratModel>){
        this.list.clear()
        list.let { this.list.addAll(it) }
        notifyDataSetChanged()
    }
    inner class MyViewHolder(var binding: ItemHazratInfoBinding): RecyclerView.ViewHolder(binding.root)   {
        fun bind(data: HazratModel){
            binding.textitem.text =data.title
            binding.root.setOnClickListener { 
                itemClickListener?.invoke(data.text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =  MyViewHolder(
        ItemHazratInfoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int =list.size

    private var itemClickListener: ((text: String) -> Unit)? = null
    fun setItemClickListener(f: (text: String) -> Unit) {
        itemClickListener = f
    }
}