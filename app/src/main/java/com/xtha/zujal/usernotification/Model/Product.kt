package com.xtha.zujal.kotlin_exercise.Model


class Product constructor(
    var _productName: String = "",
    var _productAvailability: Int = 0,
    var _productPrice: Double =0.00,
    var _productQuantity: Int = 0,
    var _productPictureLink: String = "",
    var _productId: Int = 0
) {

    var productId: Int
    var productName: String
    var productAvailability: Int
    var productPrice: Double
    var productQuantity: Int
    var productPictureLink: String

    init {

        this.productId = _productId
        this.productName = _productName
        this.productAvailability = _productAvailability
        this.productPrice = _productPrice
        this.productQuantity = _productQuantity
        this.productPictureLink = _productPictureLink

    }

    constructor(pname: String, pAvailability: Int, pPrice: Double, pQuantity: Int, pPictureLink: String) : this(pname) {
        this.productId = _productId
        this.productName = pname
        this.productAvailability = pAvailability
        this.productPrice = pPrice
        this.productQuantity = pQuantity
        this.productPictureLink = pPictureLink
    }

    override fun toString(): String {
        return "productId:" + this.productId +
                " productName: " + this.productName +
                " productAvailability: " + this.productAvailability +
                " productPrice : " + this.productPrice +
                " productQuantity:" + this.productQuantity +
                " productPictureLink:" + this.productPictureLink
    }
}