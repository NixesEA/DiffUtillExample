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
import com.nixesea.diffutillexample.model.ListItemsType
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
                val newItems = responseData.contentList
                newItems.addAll(
                    arrayListOf(
                        ListItemsType.SecondType(
                            id = 1,
                            name = "A Some name #1"
                        ),
                        ListItemsType.SecondType(
                            id = 2,
                            name = "AA Some name #2"
                        ),
                        ListItemsType.SecondType(
                            id = 3,
                            name = "AAA Some name #3"
                        ),
                        ListItemsType.SecondType(
                            id = 4,
                            name = "Some name #4"
                        )
                    )
                )
                adapter.updateItems(newItems)
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