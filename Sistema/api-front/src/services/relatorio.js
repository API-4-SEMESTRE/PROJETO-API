import { http } from "./config";

export default {
    gerar_relatorio: (data,email) => {
        return http.get(`evento/report?data=${data}&email=${email}`);
    },
};
