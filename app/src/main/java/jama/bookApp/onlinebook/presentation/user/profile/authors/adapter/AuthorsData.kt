package jama.bookApp.onlinebook.presentation.user.profile.authors.adapter

class AuthorsData{
    var name:String = ""
    var image:String = ""
    var id : String = ""

    constructor()

    constructor(name: String, image: String, id: String) {
        this.name = name
        this.image = image
        this.id = id
    }

}
