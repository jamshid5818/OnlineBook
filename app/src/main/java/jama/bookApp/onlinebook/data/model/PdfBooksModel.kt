package jama.bookApp.onlinebook.data.model

class PdfBooksModel {
    var nameBook:String?=null
    var imageBookUri: String?=null
    var imageAuthorUri:String?=null
    var pdfUriBook:String?=null
    var costBook:String?=null
    var authorBook:String?=null
    var muqaddima:String?=null
    var randomKey:String?=null
    var amountSold:Int=0
    var isMuslimBook:Boolean?=null
    var isAudioBook:Boolean?=null
    constructor()

    constructor(
        nameBook: String?,
        imageBookUri: String,
        pdfUriBook:String,
        imageAuthorUri: String,
        costBook: String?,
        authorBook: String?,
        muqaddima: String?,
        randomKey:String,
        amountSold:Int,
        isMuslimBook:Boolean,
        isAudioBook:Boolean
    ) {
        this.nameBook = nameBook
        this.imageBookUri = imageBookUri
        this.imageAuthorUri = imageAuthorUri
        this.costBook = costBook
        this.authorBook = authorBook
        this.muqaddima = muqaddima
        this.randomKey = randomKey
        this.amountSold = amountSold
        this.isMuslimBook = isMuslimBook
        this.isAudioBook = isAudioBook
        this.pdfUriBook = pdfUriBook
    }
    constructor(
        nameBook: String?,
        imageBookUri: String,
        pdfUriBook:String,
        costBook: String?,
        authorBook: String?,
        muqaddima: String?,
        randomKey:String,
        amountSold:Int,
        isMuslimBook:Boolean,
        isAudioBook:Boolean
    ) {
        this.nameBook = nameBook
        this.imageBookUri = imageBookUri
        this.costBook = costBook
        this.authorBook = authorBook
        this.muqaddima = muqaddima
        this.randomKey = randomKey
        this.amountSold = amountSold
        this.isMuslimBook = isMuslimBook
        this.isAudioBook = isAudioBook
        this.pdfUriBook = pdfUriBook
    }

}