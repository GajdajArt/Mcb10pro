package com.labralab.mkbpro10.ui.fragment.catalog

import com.labralab.mkbpro10.model.entity.Section
import io.reactivex.Single

interface CatalogRepository {

    fun getSections(): Single<Section>
}
