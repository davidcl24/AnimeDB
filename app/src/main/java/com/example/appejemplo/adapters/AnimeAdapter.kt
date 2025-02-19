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
import com.example.appejemplo.models.Anime
import com.example.appejemplo.models.Animes

class AnimeAdapter (var listAnimes: List<Anime>,
    var listener: AnimeOnClickDetail) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>(){
    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAdapter.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_anime, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeAdapter.ViewHolder, position: Int) {
        val item = listAnimes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listAnimes.count()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAnimeBinding.bind(view)

        fun bind (anime: Anime) {
            binding.textViewName.text = anime.title
            binding.textViewRating.text = anime.score.toString()

            Glide.with(mContext)
                .load(Uri.parse(anime.imageUri))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.chikojuapo)
                .error(R.drawable.gato)
                .centerCrop()
                .into(binding.imageBackGround)
        }

        fun setListener(anime: Anime) {
            binding.root.setOnClickListener { listener.onClickAnime(anime.id) }
        }
    }

}