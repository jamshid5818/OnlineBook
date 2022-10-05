package jama.bookApp.onlinebook.data.model

class PdfBooksModel {
    var nameBook:String?=null
    var imageBook:String?=null
    var costBook:String?=null
    var authorBook:String?=null
    var muqaddima:String?=null
    var randomKey:String?=null
    var amountSold:Int=0
    constructor()

    constructor(
        nameBook: String?,
        imageBook: String?,
        costBook: String?,
        authorBook: String?,
        muqaddima: String?,
        randomKey:String,
        amountSold:Int
    ) {
        this.nameBook = nameBook
        this.imageBook = imageBook
        this.costBook = costBook
        this.authorBook = authorBook
        this.muqaddima = muqaddima
        this.randomKey = randomKey
        this.amountSold = amountSold
    }

}