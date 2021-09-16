import { http } from './config'

export default {

    listar_usuarios: () => {
        return http.get(`usuario/all`)
    },
    salvar_usuario: (usuario) => {
        return http.post(`usuario/add`, usuario)
    }

}