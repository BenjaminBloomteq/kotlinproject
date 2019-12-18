package com.example.jetpackexampleapp.ui.add

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.jetpackexampleapp.JetpaclExampleApp
import com.example.jetpackexampleapp.R
import com.example.jetpackexampleapp.data.model.People
import kotlinx.android.synthetic.main.fragment_add_people.*

class AddPeopleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_people, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_add_people, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_add -> {
                savePeopleInfo()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Saves people info from user input and returns to PeopleListActivity
     */
    private fun savePeopleInfo() {
        val people = People(
            textInputName.editText?.text.toString(),
            textInputMetAt.editText?.text.toString(),
            textInputContact.editText?.text.toString(),
            textInputEmail.editText?.text.toString(),
            textInputFacebook.editText?.text.toString(),
            textInputTwitter.editText?.text.toString()
        )
       // (activity?.application as JetpaclExampleApp).getPeopleRepository().insertPeople(people)

        Navigation.findNavController(view!!).navigateUp()
    }

}