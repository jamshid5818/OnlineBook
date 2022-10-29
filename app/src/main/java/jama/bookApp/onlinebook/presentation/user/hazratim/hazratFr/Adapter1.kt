package jama.bookApp.onlinebook.presentation.user.hazratim.hazratFr

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jama.bookApp.onlinebook.R
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.databinding.ItemBookBinding

class Adapter1(var context: Context) : RecyclerView.Adapter<Adapter1.MyViewHolder>() {
    var list = ArrayList<PdfBooksModel>()

    fun setData(list: ArrayList<PdfBooksModel>){
        this.list.clear()
        list.let { this.list.addAll(it) }
        notifyDataSetChanged()
    }
    inner class MyViewHolder(var binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root)   {
        fun bind(data: PdfBooksModel){
            binding.cost.text = data.costBook
            binding.authorNameAdmin.text = data.authorBook
            binding.bookNameAdmin.text = data.nameBook
            Glide.with(context).load(data.imageBookUri).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(binding.imageBookItem)
            binding.root.setOnClickListener {
                itemClickListener?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =  MyViewHolder(
        ItemBookBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int =list.size

    private var itemClickListener: ((data: PdfBooksModel) -> Unit)? = null
    fun setItemClickListener(f: (data: PdfBooksModel) -> Unit) {
        itemClickListener = f
    }
}