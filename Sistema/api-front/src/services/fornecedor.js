import { http } from './config'

export default {

    listar_fornecedor: () => {
        return http.get(`fornecedor/listaFornecedores`)
    },
    listar_fornecedor_all: () => {
        return http.get(`fornecedor/all`)
    },
    salvar_fornecedor: (fornecedor) => {
        return http.post(`fornecedor/add`, fornecedor)
    },
    atualizar_fornecedor: (fornecedor) => {
        return http.put(`fornecedor/update`, fornecedor);
    },
    excluir_fornecedor: (fornecedor) => {
        return http.delete(`fornecedor/delete`, { data: fornecedor });
    },

}