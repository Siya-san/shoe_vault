package com.example.myapplication.ui.category

import android.os.Parcel
import android.os.Parcelable

data class Category(val category_name: String?= null, val category_description: String?= null, val category_size: String?= null) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category_name)
        parcel.writeString(category_description)
        parcel.writeString(category_size)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(parcel: Parcel): Category {
            return Category(parcel)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}
