package pl.edu.uwr.pum.lists

data class Task(val nameTask: String, val nameList: String) {
    var id: Int = 0
    constructor(id: Int, nameTask: String, nameList: String) : this(nameTask, nameList) {
        this.id = id
    }
}