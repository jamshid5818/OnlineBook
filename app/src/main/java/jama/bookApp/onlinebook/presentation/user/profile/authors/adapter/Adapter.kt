package jama.bookApp.onlinebook.presentation.user.profile.authors.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jama.bookApp.onlinebook.data.model.PdfBooksModel
import jama.bookApp.onlinebook.databinding.ItemAuthorsBinding

class Adapter : RecyclerView.Adapter<Adapter.AuthorsHolder>() {
    private val list = mutableListOf<PdfBooksModel>()

    fun setList(list: List<PdfBooksModel>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class AuthorsHolder(val binding:ItemAuthorsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: PdfBooksModel){
//            Glide.with(binding.root).load(data.image).into(binding.image)
//            binding.name.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AuthorsHolder(
        ItemAuthorsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun onBindViewHolder(holder: AuthorsHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount() = list.size
}