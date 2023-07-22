package com.ntk.testforstarwars.acts

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import org.json.JSONObject
import kotlin.coroutines.suspendCoroutine

suspend inline fun <reified T> addVolleyRequestApi(
    queue: RequestQueue,
    url: String,
    list: SnapshotStateList<T>,
    crossinline onCompleted: () -> Unit = {}

) = suspendCoroutine<String> {

    val stringReq = StringRequest(
        Request.Method.GET, url,

        { response ->
            val jsonResp = JSONObject(response)
            val jsonArray = jsonResp.getJSONArray("results")

            for (i in 0 until jsonArray.length()) {
                val singleObject = jsonArray.getJSONObject(i)
                val parsedObject = Gson().fromJson(singleObject.toString(), T::class.java)
                list.add(parsedObject)
            }

            onCompleted()
        },

        {

        }
    )
    queue.add(stringReq)
}
