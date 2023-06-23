package stm.virgo.journal

import com.google.gson.annotations.SerializedName

data class ResultJournal(
    @field:SerializedName("pesan")
    val pesan: String? = null,
    @field:SerializedName("journal")
    val journal: List<DataItem>? = null,
    @field:SerializedName("status")
    val status: Int? = null
)