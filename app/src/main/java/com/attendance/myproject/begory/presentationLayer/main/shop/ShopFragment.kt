package com.attendance.myproject.begory.presentationLayer.main.shop

import android.content.ContentValues
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.presentationLayer.main.setting.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shop.*
import java.util.ArrayList

@AndroidEntryPoint
class ShopFragment : Fragment(), UpdatePoints {
    private var recyclerView: RecyclerView? = null
    private lateinit var mList: ArrayList<Int>
    var recyclerViewAdapter: SettingsAdapter? = null
    private var savedRecyclerLayoutState: Parcelable? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO :change layout
        val root: View = inflater.inflate(R.layout.fragment_settings, container, false)

        // TODO: change id
        recyclerView = root.findViewById(R.id.rv_menu)
        recyclerView!!.setHasFixedSize(true)
        //TODO: Choose your layout
        //GridLayout
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(activity, 1)
        recyclerView!!.layoutManager = gridLayoutManager
        if (savedInstanceState != null) {
            displayDataFromInstanceState(savedInstanceState)
        } else {
            this.mList = fillMenu()!!
        }
        Log.d(ContentValues.TAG, "showMessage: create $mList")
        showData()
        return root
    }

    private fun showData() {
        recyclerViewAdapter = SettingsAdapter(requireContext(), this.mList)
        recyclerView!!.adapter = recyclerViewAdapter
        recyclerViewAdapter!!.notifyDataSetChanged()
        recyclerView!!.smoothScrollToPosition(0)
    }

    override fun onResume() {
        super.onResume()
        //mPresenter.start();
    }

    private fun displayDataFromInstanceState(savedInstanceState: Bundle?) {
        mList = savedInstanceState!!.getIntegerArrayList(LIST_STATE)
        savedRecyclerLayoutState = savedInstanceState!!.getParcelable(BUNDLE_RECYCLER_LAYOUT)

        // restore Layout Manager Position
        if (savedRecyclerLayoutState != null) checkNotNull(recyclerView!!.layoutManager).onRestoreInstanceState(savedRecyclerLayoutState)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putIntegerArrayList(LIST_STATE, mList)
        savedInstanceState.putParcelable(BUNDLE_RECYCLER_LAYOUT, checkNotNull(recyclerView!!.layoutManager).onSaveInstanceState())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mList = savedInstanceState.getIntegerArrayList(LIST_STATE)
            savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT)
        }
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        //save state
        private const val LIST_STATE = "list_state"
        private const val BUNDLE_RECYCLER_LAYOUT = "recycler_layout"
    }

    private fun fillMenu(): ArrayList<Int>? {
        val mItems = ArrayList<Int>()
        mItems.clear()
        mItems.add(R.string.m_add_student)
        mItems.add(R.string.m_edit_student)
        mItems.add(R.string.m_add_subadmin)
        mItems.add(R.string.m_edit_subadmin)
        mItems.add(R.string.m_add_admin)
        mItems.add(R.string.m_edit_admin)
        mItems.add(R.string.m_levels_attendance)
        return mItems
    }

    override fun onUpdate(points: Double) {
        tv_current_points.text = points.toString()
    }
}