package com.api.agendhouse.domain.evento;

import com.api.agendhouse.domain.usuario.Usuario;
import com.api.agendhouse.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventoService {

    private static Integer total;
    private static Integer aprovado;
    private static Integer reprovado;
    private static Integer pendente;
    private static Integer smb;
    private static Integer enterprise;
    private static Integer workshop;
    private static Integer palestra;
    private static Integer seg;
    private static Integer ter;
    private static Integer qua;
    private static Integer qui;
    private static Integer sex;
    private static Integer sab;
    private static Integer dom;

    private static EventoRepository eventoRepository;
    private static UsuarioRepository usuarioRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, UsuarioRepository usuarioRepository) {
        EventoService.eventoRepository = eventoRepository;
        EventoService.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Evento add(Evento evento) {
        evento.setDatacria(LocalDateTime.now());
        return eventoRepository.save(evento);
    }

    public List<Evento> findAll() {
        return eventoRepository.findAllByOrderByCodevenAsc();
    }

    public List<Evento> findByUsu(Long usucod) {
        return eventoRepository.findByUsucodcriaOrderByCodevenAsc(usucod);
    }

    public Evento disponivel(String datahorainicio, String datahorafim) {
        return eventoRepository.findEventoByHorainicioAndHorafimNamedParams(datahorainicio, datahorafim);
    }

    @Transactional
    public Evento update(Evento evento) {
        if (eventoRepository.findByCodeven(evento.getCodeven()) == null) {
            return null;
        }
        return eventoRepository.save(evento);
    }

    @Transactional
    public boolean delete(Evento evento) {
        try {
            eventoRepository.delete(evento);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Evento findByCod(Long codeven) {
        return eventoRepository.findByCodeven(codeven);
    }

    public static File generateFullCsv(Date data) throws FileNotFoundException {
        Calendar c  = Calendar.getInstance();
        c.setTime(data);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        StringBuilder sb = new StringBuilder();
        sb.append("/Mensal/,Eventos,Aprovados,Reprovados,SMB,Enterprise,Workshop,Palestra,Segunda,Terca,Quarta,Quinta,Sexta,Sabado,Domingo\nTotais:,");
        total = 0; aprovado = 0; reprovado = 0; pendente = 0; smb = 0; enterprise = 0; workshop = 0; palestra = 0;
        seg = 0; ter = 0; qua = 0; qui = 0; sex = 0; sab = 0; dom = 0;
        List<String> counts = new ArrayList<String>();
        List<Integer> week = new ArrayList<Integer>();
        List<Evento> eventsMatch = new ArrayList<>();
        var allEvents = eventoRepository.findAllByOrderByDataevenAsc();
        for (Evento evento : allEvents) {
            c.setTime(evento.getDataeven());
            if ((c.get(Calendar.MONTH) + 1) == month && c.get(Calendar.YEAR) == year) {
                eventsMatch.add(evento);
                counts.add(evento.getStatus().toString());
                counts.add(evento.getTipo().toString());
                week.add(c.get(Calendar.DAY_OF_WEEK));
            }
        }
        total = eventsMatch.size();
        aprovado = Collections.frequency(counts, EventoStatus.APROVADO.toString());
        reprovado = Collections.frequency(counts, EventoStatus.REPROVADO.toString());
        pendente = total - (aprovado + reprovado);
        smb = Collections.frequency(counts, EventoTipo.SMB.toString());
        enterprise = Collections.frequency(counts, EventoTipo.Enterprise.toString());
        workshop = Collections.frequency(counts, EventoTipo.Workshop.toString());
        palestra = Collections.frequency(counts, EventoTipo.Palestra.toString());
        dom = Collections.frequency(week, 1);
        seg = Collections.frequency(week, 2);
        ter = Collections.frequency(week, 3);
        qua = Collections.frequency(week, 4);
        qui = Collections.frequency(week, 5);
        sex = Collections.frequency(week, 6);
        sab = Collections.frequency(week, 7);

        sb.append(String.valueOf(eventsMatch.size() + "," + aprovado + "," + reprovado + "," +
                smb + "," + enterprise + "," + workshop + "," + palestra + ", " +
                seg + ", " + ter + ", " + qua + ", " + qui + ", " + sex + ", " + sab + "," + dom + "\n\n"));
        sb.append("id,Criacao,Status,Data,Hora Inicial,Hora Final,Tipo,Formato,Criador\n");

        for (Evento evento : eventsMatch) {
            Usuario criador = usuarioRepository.findByCod(evento.getUsucodcria());
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            String dateCreate = String.valueOf(evento.getDatacria().getDayOfMonth() + "/" + evento.getDatacria().getMonthValue() + "/" + evento.getDatacria().getYear());
            sb.append(String.valueOf((eventsMatch.indexOf(evento) + 1) + "," + dateCreate + "," + evento.getStatus() + "," +
                    df.format(evento.getDataeven()) + "," + evento.getHorainicio().toString().substring(0, 5) + "," + evento.getHorafim().toString().substring(0, 5) + "," +
                    evento.getTipo().toString() + "," + evento.getFormato() + "," + criador.getTipo() + "\n"));
        }

        PrintWriter excel = new PrintWriter("AgendHouse" + String.valueOf(year + "-" + String.format("%02d", month)) + ".csv");
        excel.print(sb.toString());
        excel.close();

        return new File("AgendHouse" + String.valueOf(year + "-" + String.format("%02d", month)) + ".csv");
    }

    public Map<String, Integer> getValues() {
        Map<String, Integer> values = new LinkedHashMap<>();
        values.put("total", total);
        values.put("aprovado", aprovado);
        values.put("reprovado", reprovado);
        values.put("pendente", pendente);
        values.put("smb", smb);
        values.put("enterprise", enterprise);
        values.put("workshop", workshop);
        values.put("palestra", palestra);
        values.put("seg", seg);
        values.put("ter", ter);
        values.put("qua", qua);
        values.put("qui", qui);
        values.put("sex", sex);
        values.put("sab", sab);
        values.put("dom", dom);

        return values;
    }
}
