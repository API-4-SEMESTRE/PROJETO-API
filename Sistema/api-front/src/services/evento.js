import { http } from "./config";

export default {
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
};
