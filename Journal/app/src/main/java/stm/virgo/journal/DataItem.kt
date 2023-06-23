package stm.virgo.journal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable {
    @field:SerializedName("title")
    val title: String? = null

    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("content")
    val content: String? = null

    @field:SerializedName("updated_at")
    val updated_at: String? = null

}