package com.attendance.myproject.begory.presentationLayer.main.shop

import android.content.ContentValues
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.attendance.myproject.begory.R
import com.attendance.myproject.begory.databinding.FragmentShopBinding
import com.attendance.myproject.begory.presentationLayer.main.profile.ProfileViewModel
import com.attendance.myproject.begory.presentationLayer.main.setting.SettingsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shop.*
import java.util.ArrayList

@AndroidEntryPoint
class ShopFragment : Fragment(R.layout.fragment_shop), UpdatePoints {
    private lateinit var binding: FragmentShopBinding
    private var recyclerView: RecyclerView? = null
    private lateinit var mList: ArrayList<Int>
    var recyclerViewAdapter: ShopAdapter? = null
    private var savedRecyclerLayoutState: Parcelable? = null
    private  val shopViewModel: ShopViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO :change layout
        val root: View = inflater.inflate(R.layout.fragment_shop, container, false)
        binding = FragmentShopBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.shopViewModel=shopViewModel
        binding.lifecycleOwner = this
        subscribeToNavigationChanges(shopViewModel)
        // TODO: change id
        recyclerView = root.findViewById(R.id.rv_menu)
        recyclerView!!.setHasFixedSize(true)
        (binding.rvMenu as (RecyclerView)).setHasFixedSize(true)
        //TODO: Choose your layout
        //GridLayout
        val gridLayoutManager: GridLayoutManager = GridLayoutManager(activity, 1)
        binding.rvMenu.layoutManager = gridLayoutManager
        if (savedInstanceState != null) {
            displayDataFromInstanceState(savedInstanceState)
        }
        return root
    }

    private fun subscribeToNavigationChanges(shopViewModel: ShopViewModel) {
        val activity = this@ShopFragment
        shopViewModel.run {
            snackbarMessage.observe(viewLifecycleOwner, Observer { showMessage(getString(it.getContentIfNotHandled()!!)) })
            snackbarMessage2.observe(viewLifecycleOwner, Observer { showMessage(it) })
            mGiftListListener.observe(viewLifecycleOwner, Observer {if (it == true) initRecyclerView()})
//            isopenLogin.observe(viewLifecycleOwner,
//                    Observer {
//                        if (it == true) openLoginActivity()
//                    })
//            isDataAvailable.observe(viewLifecycleOwner,
//                    Observer {
//                        if (it == true) dataChanged()
//                    })
        }
    }



    private fun dataChanged() {
//        //binding.notifyPropertyChanged(BR.p)
//        binding.tvName.text=profileViewModel.name
//        binding.tvLevel.text=profileViewModel.studentLevel
//        binding.paymentBegory.text=profileViewModel.balanceBegory
//        binding.eqlomatDesc.text=profileViewModel.balanceEqlomat
//        binding.tiName
//        mobile=usertmp.mobile!!
//        mobile2=usertmp.mobile2!!
//        balanceBegory=usertmp.balanceBegory.toString()
//        balanceEqlomat=usertmp.balanceEqlomat.toString()
//        password=usertmp.password!!
//        address=usertmp.address!!
//        studentLevel=usertmp.studentLevel!!.toString()
    }
    fun showMessage(string: String){
        Toast.makeText(binding.root.context, string, Toast.LENGTH_SHORT).show()
        //Log.d(ContentValues.TAG, "showMessage: "+string)
    }
    private fun initRecyclerView() {
        recyclerViewAdapter = ShopAdapter(requireContext(), shopViewModel.mGiftList,shopViewModel.user!!)
        binding.rvMenu.adapter = recyclerViewAdapter
        recyclerViewAdapter!!.notifyDataSetChanged()
        binding.rvMenu.smoothScrollToPosition(0)
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



    override fun onUpdate(points: Double) {

    }
}