package com.patrickchow.oopsprintchallenge.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.patrickchow.oopsprintchallenge.R

import com.patrickchow.oopsprintchallenge.dummy.DummyContent
import com.patrickchow.oopsprintchallenge.model.*
import com.patrickchow.oopsprintchallenge.model.Unit
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    var aoeApiObjects = mutableListOf<AoEApiObject>()
    private var viewAdapter: SimpleItemRecyclerViewAdapter ?= null

    lateinit var aoeAPI: AoEAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        aoeApiObjects = mutableListOf()

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        aoeAPI = AoEAPI.Factory.create()

        setupRecyclerView(item_list as RecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewAdapter = SimpleItemRecyclerViewAdapter(this, aoeApiObjects, twoPane)
        recyclerView.adapter = viewAdapter

        if(isNetworkConnected()){
            //getData()
        }
        else{
            Toast.makeText(this@ItemListActivity, "No Network", Toast.LENGTH_SHORT).show()
        }
    }

    //Method to retrive all the data

    fun getCivilization(id: Int){
        aoeAPI.getCivilization(id).enqueue(object: Callback<Civilization>{
            override fun onFailure(call: Call<Civilization>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "Failure on Enqueue", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Civilization>, response: Response<Civilization>) {
                if(response.isSuccessful){
                    val civilization = response.body()
                    civilization?.let {
                        it.id = id
                        aoeApiObjects.add(civilization)
                        viewAdapter?.notifyItemInserted(aoeApiObjects.size -1)
                    }
                }
            }

        })
    }

    fun getUnit(id: Int){
        aoeAPI.getUnit(id).enqueue(object: Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "Failure on Enqueue", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    val unit = response.body()
                    unit?.let {
                        it.id = id
                        aoeApiObjects.add(unit)
                        viewAdapter?.notifyItemInserted(aoeApiObjects.size -1)
                    }
                }
            }

        })
    }

    fun getStructure(id: Int){
        aoeAPI.getStructure(id).enqueue(object: Callback<Structure>{
            override fun onFailure(call: Call<Structure>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "Failure on Enqueue", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Structure>, response: Response<Structure>) {
                if(response.isSuccessful){
                    val structure = response.body()
                    structure?.let {
                        it.id = id
                        aoeApiObjects.add(structure)
                        viewAdapter?.notifyItemInserted(aoeApiObjects.size -1)
                    }
                }
            }

        })
    }

    fun getTechnology(id: Int){
        aoeAPI.getTechnology(id).enqueue(object: Callback<Technology>{
            override fun onFailure(call: Call<Technology>, t: Throwable) {
                Toast.makeText(this@ItemListActivity, "Failure on Enqueue", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Technology>, response: Response<Technology>) {
                if(response.isSuccessful){
                    val technology = response.body()
                    technology?.let {
                        it.id = id
                        aoeApiObjects.add(technology)
                        viewAdapter?.notifyItemInserted(aoeApiObjects.size -1)
                    }
                }
            }

        })
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<AoEApiObject>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as DummyContent.DummyItem
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(ItemDetailFragment.ARG_ITEM_ID, item.id)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.contentView.text = item.content

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
