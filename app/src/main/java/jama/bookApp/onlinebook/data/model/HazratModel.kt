package jama.bookApp.onlinebook.data.model

class HazratModel {
    var id:Int = 0
    var title:String = ""
    var text:String = ""
    constructor()
    constructor(id: Int, title: String, text: String) {
        this.id = id
        this.title = title
        this.text = text
    }

}