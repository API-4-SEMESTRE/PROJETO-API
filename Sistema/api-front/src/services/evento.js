import { http } from "./config";

export default {
    listar_eventos: () => {
        return http.get(`evento/all`);
    },
    // salvar_usuario: (usuario) => {
    //     return http.post(`usuario/add`, usuario);
    // },
    // atualizar_usuario: (usuario) => {
    //     return http.put(`usuario/update`, usuario);
    // },
    // excluir_usuario: (usuario) => {
    //     return http.delete(`usuario/delete`, { data: usuario });
    // },
};
