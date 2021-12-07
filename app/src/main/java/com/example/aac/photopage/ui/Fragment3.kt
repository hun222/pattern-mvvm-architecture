package com.example.aac.photopage.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aac.R
import com.example.aac.photopage.data.PhotoRepository
import com.example.aac.photopage.viewmodel.PhotoViewModel
import com.example.aac.photopage.viewmodel.PhotoViewModelFactory
import com.example.aac.userpage.data.UserRepository
import com.example.aac.userpage.ui.UserAdapter
import com.example.aac.userpage.viewmodel.UserViewModel
import com.example.aac.userpage.viewmodel.UserViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var photoRecyclerView: RecyclerView
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_3, container, false)

        setUi(view)

        val photoViewModelFactory = PhotoViewModelFactory(PhotoRepository.getInstance())
        val photoViewModel = ViewModelProvider(this, photoViewModelFactory).get(PhotoViewModel::class.java)

        photoViewModel.getPhotos()
        photoViewModel.mPhotos.observe(viewLifecycleOwner, Observer { users ->
            users?.let { photoAdapter.submitList(it) }
        })

        return view
    }

    fun setUi(view: View) {
        photoRecyclerView = view.findViewById(R.id.photoRecyclerview)
        photoAdapter = PhotoAdapter()
        photoRecyclerView.adapter = photoAdapter
        photoRecyclerView.layoutManager = LinearLayoutManager(activity)
        photoRecyclerViewDecoration()
    }

    fun photoRecyclerViewDecoration() {
        val decoration = DividerItemDecoration(activity, LinearLayout.VERTICAL)
        photoRecyclerView.addItemDecoration(decoration)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment3().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}