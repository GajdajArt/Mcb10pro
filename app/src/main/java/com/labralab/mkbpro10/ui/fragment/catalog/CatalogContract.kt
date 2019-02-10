package com.labralab.mkbpro10.ui.fragment.catalog

import com.labralab.mkbpro10.model.entity.Section
import com.labralab.mkbpro10.model.entity.User
import com.labralab.mkbpro10.ui.base.BaseContract

interface CatalogContract {

    interface View: BaseContract.View {

        fun showList(list: List<Section>)

        fun showLastSection(section: Section)

        fun showUserData(user: User?)
    }

    interface Presenter: BaseContract.Presenter<View> {

        fun onItemClick(section: Section)

        fun onAccountHeaderClick()

        fun setParent(parent: String)
    }
}