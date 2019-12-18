package com.example.jetpackexampleapp.ui.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.People
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_peoples_list.*
import javax.inject.Inject

class PeoplesListFragment(): Fragment(),
    PeoplesListAdapter.OnItemClickListener,
    SearchView.OnQueryTextListener,
    SearchView.OnCloseListener {

    private lateinit var searchView: SearchView
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PeoplesListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PeoplesListViewModel::class.java)
        viewModel = ViewModelProviders.of(this).get(PeoplesListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_peoples_list, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_peoples_list, menu)

        // Initialize Search View
        searchView = menu?.findItem(R.id.menu_search)?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigate to add people
        addFab.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_peoplesListFragment_to_addPeopleFragment)
        }

        viewModel.getPeopleList().observe(this, Observer<List<People>> { peoples ->
            peoples?.let {
                populatePeopleList(peoples)
            }
        })
    }



    private fun populatePeopleList(peopleList: List<People>) {
        peopleRecyclerView.adapter = PeoplesListAdapter(peopleList, this)
    }
    override fun onQueryTextSubmit(p0: String?): Boolean {
        viewModel.searchPeople(p0!!)
        return true//To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        viewModel.searchPeople(p0!!)
        return true
    }

    override fun onClose(): Boolean {
        viewModel.getAllPeople()
        searchView.onActionViewCollapsed()
        return true//To change body of created functions use File | Settings | File Templates.
    }


    override fun onItemClick(people: People, itemView: View) {
        val peopleBundle = Bundle().apply {
            putInt(getString(R.string.people_id), people.id)
        }
        view?.findNavController()
            ?.navigate(R.id.action_peoplesListFragment_to_peopleDetailsFragment, peopleBundle) //To change body of created functions use File | Settings | File Templates.
    }
}
