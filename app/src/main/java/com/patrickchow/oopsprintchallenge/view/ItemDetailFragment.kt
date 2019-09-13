package com.patrickchow.oopsprintchallenge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.patrickchow.oopsprintchallenge.R
import com.patrickchow.oopsprintchallenge.dummy.DummyContent
import com.patrickchow.oopsprintchallenge.model.AoEApiObject
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: AoEApiObject ?= null //Item will be equal to the selected item in the recyclerview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                //Using the Bundle, get the object that was passed in
                item = it.getSerializable(ARG_ITEM_ID) as AoEApiObject

                val title = it.getSerializable(ARG_ITEM_ID) as AoEApiObject
                activity?.toolbar_layout?.let {
                    it.title = item?.name ?: ""
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        //Display the information of the selected item
        rootView.item_detail.text = "${item.toString()}"

        rootView.btn_info.setOnClickListener {
            Toast.makeText(activity, item.toString(), Toast.LENGTH_SHORT).show()
        }

        // Show the dummy content as text in a TextView.
        item?.let {

            //rootView.item_detail.text =
        }

        return rootView
    }

    interface DetailResponse {
        fun provideInfoForObject(info: String)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
