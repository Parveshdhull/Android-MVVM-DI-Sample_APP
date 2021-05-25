package com.parvesh.pixasearch.network.response

import com.google.gson.annotations.SerializedName
import com.parvesh.pixasearch.network.models.PostDTO

data class RetrofitSearchResponse(

    @SerializedName("total")
    var total: Int,

    @SerializedName("totalHits")
    var totalHits: Int,

    @SerializedName("hits")
    var hits: List<PostDTO>

)