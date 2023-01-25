package br.com.adalbertofjr.dogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import br.com.adalbertofjr.dogs.databinding.ItemDogBinding
import br.com.adalbertofjr.dogs.model.DogBreed

class DogListAdapter(val dogsList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdapter.DogViewHolder>(),
    DogClickListener {

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
        holder.view.dog = dogsList.get(position)
        holder.view.listener = this
    }

    override fun getItemCount() = dogsList.size

    override fun onDogClicked(v: View) {
        val view = DataBindingUtil.bind<ItemDogBinding>(v)
        view?.let {
            val uuid = view.dogId.text.toString().toInt()
            Navigation.findNavController(v).navigate(
                ListFragmentDirections.actionDetailFragment(uuid)
            )
        }
    }

    class DogViewHolder(val view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}