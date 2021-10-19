import { http } from './config'

export default {

    listar_fornecedor: () => {
        return http.get(`fornecedor/listaFornecedores`)
    },
    salvar_fornecedor: (fornecedor) => {
        return http.post(`fornecedor/add`, fornecedor)
    }

}