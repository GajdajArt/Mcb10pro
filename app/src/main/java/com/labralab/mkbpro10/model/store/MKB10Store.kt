package com.labralab.mkbpro10.model.store

import com.labralab.mkbpro10.model.entity.Section
import io.reactivex.Single

interface MKB10Store {

    fun getMKBList(parent: String): Single<List<Section>>
}