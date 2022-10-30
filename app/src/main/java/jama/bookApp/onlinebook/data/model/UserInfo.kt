package jama.bookApp.onlinebook.data.model

class UserInfo {
    var age: Int = 0
    var email: String = ""
    var gender: String = ""
    var name: String = ""
    var password: String = ""
    var balance:Int = 0

    constructor()
    constructor(age: Int, email: String, gender: String, name: String, password: String, balance:Int) {
        this.age = age
        this.email = email
        this.gender = gender
        this.name = name
        this.password = password
        this.balance = balance
    }

}