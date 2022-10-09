package com.example.csgo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.csgo.R
import com.example.csgo.model.Matches
import com.example.csgo.model.Players


class AdapterPlayersFirst: RecyclerView.Adapter<AdapterPlayersFirst.MyViewHolder>() {
    var players = mutableListOf<Players>()

    fun setPlayersList(players: List<Players>) {
        this.players = players.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPlayersFirst.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player_first_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterPlayersFirst.MyViewHolder, position: Int) {
       val player = players[position]
        holder.namePlayerFirst.text = player.firstName
        holder.nickNameFirst.text = player.name
        Glide.with(holder.itemView.context).load(player.imageURL).into(holder.imagePlayerFirst)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nickNameFirst: TextView = itemView.findViewById(R.id.nickNameFirst)
        var namePlayerFirst: TextView = itemView.findViewById(R.id.namePlayerFirst)
        var imagePlayerFirst: ImageView = itemView.findViewById(R.id.imagePlayerFirst)
    }
}