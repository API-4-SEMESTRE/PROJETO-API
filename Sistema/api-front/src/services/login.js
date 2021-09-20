import { http } from './config'

export default {

    login: (usuario) => {
        return http.post('usuario/login', usuario)
    }

}
