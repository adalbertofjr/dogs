package br.com.adalbertofjr.dogs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.com.adalbertofjr.dogs.databinding.ItemDogBinding
import br.com.adalbertofjr.dogs.model.DogBreed
import br.com.adalbertofjr.dogs.util.getProgressDrawable
import br.com.adalbertofjr.dogs.util.loadImage

class DogListAdapter(val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    fun updateDogList(newDogsList: List<DogBreed>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(inflater, parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogsList.get(position)
        var dogUuid = 0
        dog.breedId.let {
            dogUuid = it.toInt()
        }

        holder.bind(dog)
        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(
                ListFragmentDirections.actionDetailFragment(dogUuid)
            )
        }
    }

    override fun getItemCount() = dogsList.size

    class DogViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: DogBreed) {
            binding.apply {
                name.text = dog.dogBreed
                lifespan.text = dog.lifeSpan
                imageView.loadImage(dog.imageUrl, getProgressDrawable(imageView.context))
            }
        }
    }
}