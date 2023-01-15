package br.com.adalbertofjr.dogs.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.adalbertofjr.dogs.databinding.FragmentListBinding
import br.com.adalbertofjr.dogs.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListViewModel
    private val dogListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_list, container, false)
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.dogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogListAdapter
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.apply {
                dogList.visibility = View.GONE
                listError.visibility = View.GONE
                loadingView.visibility = View.VISIBLE
                viewModel.refreshBypassCache()
                refreshLayout.isRefreshing = false
            }

        }

        observeViewModel()
    }

    fun observeViewModel() {
        binding.apply {
            viewModel.dogs.observe(viewLifecycleOwner, Observer { dogs ->
                dogs?.let {
                    dogList.visibility = View.VISIBLE
                    dogListAdapter.updateDogList(it)
                }
            })

            viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer { isError ->
                isError?.let {
                    listError.visibility = if (it) View.VISIBLE else View.GONE
                }
            })

            viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
                isLoading?.let {
                    loadingView.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        listError.visibility = View.GONE
                        dogList.visibility = View.GONE
                    }
                }
            })
        }
    }
}