import { http } from './config'

export default {

    listar_endereco: () => {
        return http.get(`endereco/all`)
    },
    salvar_endereco: (endereco) => {
        return http.post(`endereco/add`, endereco)
    },
    atualizar_endereco: (endereco) => {
        return http.put(`endereco/update`, endereco);
    },
    excluir_endereco: (endereco) => {
        return http.delete(`endereco/delete`, { data: endereco });
    },

}