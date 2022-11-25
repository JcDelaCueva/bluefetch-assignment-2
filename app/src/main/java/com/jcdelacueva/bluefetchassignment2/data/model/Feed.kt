package com.jcdelacueva.bluefetchassignment2.data.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("comments") var comments: Any? = null,
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("username") var username: String? = null,
    @SerializedName("user") var user: User? = User()
)

data class Comment(
    @SerializedName("createdAt") var createdAt: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("timestamp") var timestamp: Long? = null,
    @SerializedName("updatedAt") var updatedAt: String? = null,
    @SerializedName("username") var username: String? = null
)

data class User(
    @SerializedName("username") var username: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("profilePic") var profilePic: String? = null
)