import { http } from "./config";

export default {
    // ENDPOINT EVENTO
    listar_eventos: () => {
        return http.get(`evento/all`);
    },
    salvar_evento: (evento) => {
        return http.post(`evento/add`, evento);
    },
    atualizar_evento: (evento) => {
        return http.put(`evento/update`, evento);
    },
    excluir_evento: (evento) => {
        return http.delete(`evento/delete`, { data: evento });
    },
    // ENDPOINT FORNECEDOR EVENTO
    listar_fornecedor_eventos: () => {
        return http.get(`fornecedorEvento/all`);
    },
    salvar_fornecedor_evento: (fornecedor_evento) => {
        return http.post(`fornecedorEvento/add`, fornecedor_evento);
    },
    atualizar_fornecedor_evento: (fornecedor_evento) => {
        return http.put(`fornecedorEvento/update`, fornecedor_evento);
    },
};
