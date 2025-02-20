package com.example.appejemplo.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.appejemplo.R
import com.example.appejemplo.databinding.ItemAnimeBinding
import com.example.appejemplo.databinding.ItemFavAnimeBinding
import com.example.appejemplo.models.Anime
import com.example.appejemplo.models.Animes

class AnimeFavAdapter (var listAnimes: MutableList<Anime>,
                       var listener: AnimeOnClickDetail) : RecyclerView.Adapter<AnimeFavAdapter.ViewHolder>(){
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeFavAdapter.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav_anime, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeFavAdapter.ViewHolder, position: Int) {
        val item = listAnimes[position]
        holder.bind(item)
        holder.setListener(item)
    }

    override fun getItemCount(): Int {
        return listAnimes.count()
    }

    fun delete(position: Int) {
        listAnimes.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemFavAnimeBinding.bind(view)

        fun bind (anime: Anime) {
            binding.textViewName.text = anime.title
            binding.textViewScore.text = anime.score.toString()

            Glide.with(mContext)
                .load(Uri.parse(anime.imageUri))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.chikojuapo)
                .error(R.drawable.gato)
                .centerCrop()
                .into(binding.imageViewPhoto)
        }

        fun setListener(anime: Anime) {
            binding.root.setOnClickListener { listener.onClickAnime(anime.id) }
        }
    }

}