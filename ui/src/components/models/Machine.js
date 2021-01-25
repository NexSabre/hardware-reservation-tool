export default class Machine {
    constructor(data) {
        this.id = data["id"]
        this.name = data["name"]
        this.address = data["address"]
        this.start = data["start"] !== null ? data["start"] : ""
        this.ends = data["ends"] !== null ? data["ends"] : ""
        this.enabled = data["enabled"]
        this.protected = data["protected"]
    }
}
