import { http } from "./config";

export default {
    listar_visitante: () => {
        return http.get(`visitante/all`);
    },
    salvar_visitante: (visitante) => {
        return http.post(`visitante/add`, visitante);
    },
    atualizar_visitante: (visitante) => {
        return http.put(`visitante/update`, visitante);
    },
    excluir_visitante: (visitante) => {
        return http.delete(`visitante/delete`, { data: visitante });
    },
};
