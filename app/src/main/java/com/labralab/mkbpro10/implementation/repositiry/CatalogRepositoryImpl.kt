package com.labralab.mkbpro10.implementation.repositiry

import com.labralab.mkbpro10.model.entity.Section
import com.labralab.mkbpro10.ui.fragment.catalog.CatalogRepository
import io.reactivex.Single
import javax.inject.Inject

class CatalogRepositoryImpl
@Inject constructor(): CatalogRepository {



    override fun getSections(): Single<Section> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}