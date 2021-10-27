import { http } from './config'

export default {

    listar_contato: () => {
        return http.get(`contato/all`)
    },
    salvar_contato: (contato) => {
        return http.post(`contato/add`, contato)
    },
    atualizar_contato: (contato) => {
        return http.put(`contato/update`, contato);
    },
    excluir_contato: (contato) => {
        return http.delete(`contato/delete`, { data: contato });
    },

}