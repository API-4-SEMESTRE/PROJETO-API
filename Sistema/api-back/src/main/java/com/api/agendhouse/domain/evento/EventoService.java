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

    public Map<String, Integer> eventsByWeek () {
        return null;
    }

    public static File generateFullCsv(Date data) throws FileNotFoundException {
        Calendar c  = Calendar.getInstance();
        c.setTime(data);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        StringBuilder sb = new StringBuilder();
        sb.append("/Mensal/,Eventos,Aprovados,Reprovados,SMB,Enterprise,Workshop,Palestra,Segunda,Terca,Quarta,Quinta,Sexta,Sabado,Domingo\nTotais:,");
        int aproved = 0; int reproved = 0;
        int smb = 0; int enterprise = 0; int workshop = 0; int palestra = 0;
        int sun = 0; int mon = 0; int tue = 0; int wed = 0; int thu = 0; int fri = 0; int sat = 0;
        List<Evento> eventsMatch = new ArrayList<>();
        var allEvents = eventoRepository.findAllByOrderByDataevenAsc();
        for (Evento evento : allEvents) {
            c.setTime(evento.getDataeven());
            if ((c.get(Calendar.MONTH) + 1) == month && c.get(Calendar.YEAR) == year) {
                eventsMatch.add(evento);
                if (evento.getStatus().equals(EventoStatus.APROVADO)) {
                    aproved ++;
                }
                else if (evento.getStatus().equals(EventoStatus.REPROVADO)) {
                    reproved ++;
                }
                if (evento.getTipo().equals(EventoTipo.SMB)) {
                    smb ++;
                }
                else if (evento.getTipo().equals(EventoTipo.Enterprise)) {
                    enterprise ++;
                }
                else if (evento.getTipo().equals(EventoTipo.Workshop)) {
                    workshop ++;
                }
                else if (evento.getTipo().equals(EventoTipo.Palestra)) {
                    palestra ++;
                }
                if (c.get(Calendar.DAY_OF_WEEK) == 1) {
                    sun ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 2) {
                    mon ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 3) {
                    tue ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 4) {
                    wed ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 5) {
                    thu ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 6) {
                    fri ++;
                }
                else if (c.get(Calendar.DAY_OF_WEEK) == 7) {
                    sat ++;
                }
            }
        }
        sb.append(String.valueOf(eventsMatch.size() + "," + aproved + "," + reproved + "," +
                smb + "," + enterprise + "," + workshop + "," + palestra + ", " +
                mon + ", " + tue + ", " + wed + ", " + thu + ", " + fri + ", " + sat + "," + sun + "\n\n"));
        sb.append("id,Criacao,Status,Data,Hora Inicial,Hora Final,Tipo,Formato,Criador\n");

        for (Evento evento : eventsMatch) {
            Usuario criador = usuarioRepository.findByCod(evento.getUsucodcria());
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            String dateCreate = String.valueOf(evento.getDatacria().getDayOfMonth() + "/" + evento.getDatacria().getMonthValue() + "/" + evento.getDatacria().getYear());
            sb.append(String.valueOf((eventsMatch.indexOf(evento) + 1) + "," + dateCreate + "," + evento.getStatus() + "," +
                    df.format(evento.getDataeven()) + "," + evento.getHorainicio().toString().substring(0, 5) + "," + evento.getHorafim().toString().substring(0, 5) + "," +
                    evento.getTipo().toString() + "," + evento.getFormato() + "," + criador.getTipo() + "\n"));
        }

        PrintWriter excel = new PrintWriter("AgendHouse" + String.valueOf(year + "-" + month) + ".csv");
        excel.print(sb.toString());
        excel.close();


        //POR MES
        //quantidade de eventos APROVADOS & REPROVADOS
        //quantidade de eventos e seus tipos
        //quantidade de enventos por dia da semana


        return new File("AgendHouse" + String.valueOf(year + "-" + month) + ".csv");
    }
}
