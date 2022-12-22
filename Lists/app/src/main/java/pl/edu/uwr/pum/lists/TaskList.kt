package pl.edu.uwr.pum.lists

data class TaskList(val nameList: String) {
    var id: Int = 0
    constructor(id: Int, nameList: String) : this(nameList) {
        this.id = id
    }
}