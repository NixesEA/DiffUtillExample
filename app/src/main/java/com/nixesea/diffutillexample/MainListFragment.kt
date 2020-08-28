package com.nixesea.diffutillexample

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nixesea.diffutillexample.adapter.MainListAdapter
import com.nixesea.diffutillexample.model.Content
import com.nixesea.diffutillexample.model.Response
import com.nixesea.diffutillexample.viewModel.MainListViewModel
import kotlinx.android.synthetic.main.main_list_fragment.*

class MainListFragment : Fragment(R.layout.main_list_fragment) {

    private lateinit var viewModel: MainListViewModel
    private val adapter: MainListAdapter = MainListAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(requireActivity().application)
            .create(MainListViewModel::class.java)

        viewModel.netwotkResponse.observe(viewLifecycleOwner, Observer {
            handleNewResponse(it)
        })

        main_list.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), 1))
            adapter = this@MainListFragment.adapter
        }

        sort_by_star.setOnClickListener {
            adapter.sortByStar()
        }
        sort_by_distance.setOnClickListener {
            adapter.sortByDistance()
        }
        sort_by_name.setOnClickListener {
            adapter.sortByName()
        }
    }

    private fun handleNewResponse(responseData: Response) {
        when (responseData) {
            is Response.Success -> {
                adapter.updateItems(responseData.contentList)
            }
            is Response.Error -> {
                Toast.makeText(requireContext(), responseData.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNewResponse()
    }

}