package com.example.starswarscitizen.domain.testss

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DialogAttachment(
    @SerializedName("attachment_id")val attachmentId: Int,
    @SerializedName("created_at")val createdAt: String,
    val data: AttachmentData,
    @SerializedName("message_id")val messageId: Int,
    @SerializedName("original_name")val originalName: String
): Serializable

data class AttachmentData(
    val extension: String,
    val filename: String,
    val image: AttachmentImage,
    val path: String,
    val size: Int,
    val type: String,
    val url: String
): Serializable

data class AttachmentImage(
    val height: Int,
    val width: Int
): Serializable