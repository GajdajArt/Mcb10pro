package com.labralab.mkbpro10.ui.fragment.catalog

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.labralab.mkbpro10.R
import com.labralab.mkbpro10.model.entity.Section
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_catalog.*
import java.lang.reflect.Array
import javax.inject.Inject

private const val PARAMS = "PARAMS"

class CatalogFragment: BaseFragment(), CatalogContract.View {

    @Inject
    lateinit var presenter: CatalogContract.Presenter

    private val adapter = CatalogAdapter()

    override val layoutRes: Int
        get() = R.layout.fragment_catalog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(PARAMS, "")?.let {
            presenter.setParent(it)
        } ?: run { presenter.setParent("")}

        intRecyclerView()
        initHeader()
    }

    override fun onStart() {
        super.onStart()
        presenter.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbind()
    }

    override fun showList(list: List<Section>) {
        adapter.list = ArrayList(list)
        adapter.listener = object : CatalogListItemHolder.OnItemClickListener {
            override fun onClick(section: Section) {
                presenter.onItemClick(section)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showUserData(user: User?) {
        user?.let {
            accountHeader.visibility = VISIBLE

            accountName.text = "${it.firstName} ${it.secondName}"
            accountPoints.text = it.point
        } ?: run {
            accountHeader.visibility = GONE
        }
    }

    private fun initHeader() {
        accountHeader.setOnClickListener {
            presenter.onAccountHeaderClick()
        }
    }

    private fun intRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)

        catalogListRV.layoutManager = layoutManager
        catalogListRV.adapter = adapter
    }

    companion object {
        fun getInstance(data: Any): CatalogFragment {
            val fragment = CatalogFragment()

            val args = Bundle()
            args.putString(PARAMS, data as String)
            fragment.arguments = args
            return fragment
        }
    }
}