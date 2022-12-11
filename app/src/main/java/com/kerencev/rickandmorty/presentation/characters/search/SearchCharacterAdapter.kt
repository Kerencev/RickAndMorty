package com.kerencev.rickandmorty.presentation.characters.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kerencev.rickandmorty.R
import com.kerencev.rickandmorty.databinding.ItemRvCharacterSearchBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.utils.loadCharacter

class SearchCharacterAdapter :
    ListAdapter<Character, SearchCharacterAdapter.CharacterViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRvCharacterSearchBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CharacterViewHolder(
        private val binding: ItemRvCharacterSearchBinding,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(character: Character) = with(binding) {
            itemCharacterSearchName.text = character.name
            itemCharacterSearchSpecies.text =
                "${context.resources.getString(R.string.species)} ${character.species}"
            itemCharacterSearchStatus.text =
                "${context.resources.getString(R.string.status)} ${character.status}"
            itemCharacterSearchGender.text =
                "${context.resources.getString(R.string.gender)} ${character.gender}"
            itemCharacterSearchIv.loadCharacter(character.image)
            root.setOnClickListener {}
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}