import { http } from './config'

export default {

    listar_usuarios: () => {
        return http.get(`usuario/all`)
    }

}