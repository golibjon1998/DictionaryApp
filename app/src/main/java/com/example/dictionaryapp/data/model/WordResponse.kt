package com.example.dictionaryapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class WordResponse : ArrayList<WordResponseItem>()

@Parcelize
data class WordResponseItem(
    val license: License?,
    val meanings: List<Meaning>?,
    val phonetic: String?,
    val phonetics: MutableList<Phonetic>?,
    val sourceUrls: MutableList<String>?,
    val word: String?
) : Parcelable

@Parcelize
data class License(
    val name: String?,
    val url: String?
) : Parcelable

@Parcelize
data class Meaning(
    val antonyms: List<String>?,
    val definitions: MutableList<Definition>?,
    val partOfSpeech: String?,
    val synonyms: List<String>?
) : Parcelable

@Parcelize
data class Phonetic(
    val audio: String?,
    val license: LicenseX?,
    val sourceUrl: String?,
    val text: String?,
    var isPlaying: Boolean = false
) : Parcelable

@Parcelize
data class Definition(
    val antonyms: List<String>?,
    val definition: String?,
    val synonyms: List<String>?,
    val example: String?
) : Parcelable

@Parcelize
data class LicenseX(
    val name: String?,
    val url: String?
) : Parcelable
