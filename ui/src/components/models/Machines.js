export default class Machines {
    constructor(data) {
        this.id = data["id"]
        this.name = data["name"]
        this.address = data["address"]
        this.start = data["start"]
        this.ends = data["ends"]
        this.enabled = data["enabled"]
        this.protected = data["protected"]
    }
}
