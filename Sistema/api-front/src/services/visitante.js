import { http } from "./config";

export default {
    listar_visitante: () => {
        return http.get(`visitante/all`);
    },
    salvar_visitante: (visitante) => {
        return http.post(`visitante/add`, visitante);
    },
    // atualizar_visitante: (usuario) => {
    //     return http.put(`usuario/update`, usuario);
    // },
    // excluir_visitante: (usuario) => {
    //     return http.delete(`usuario/delete`, { data: usuario });
    // },
};
