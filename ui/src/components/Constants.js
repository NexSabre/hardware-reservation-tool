const protocol = "http://"
const localhost = "localhost"
const default_port = "8080"

const build = (uri) => {
    return `${protocol}${localhost}:${default_port}${uri}`
};

const machinesURI = build('/api/v1/machines');
const reservationsURI = build('/api/v1/reservations');
const infoURI = build('/api/v1/info')

export default {
    machinesURI,
    reservationsURI,
    infoURI
}

