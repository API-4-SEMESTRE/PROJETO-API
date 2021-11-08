package com.api.agendhouse.domain.email;

import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.usuario.Usuario;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private String to;
    Map model = new HashMap();

    public SimpleMailMessage sendEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Novo assunto");
        message.setText(text);
        return (message);
    }

    public MimeMessage registration(Usuario usuario) {
        setTo(usuario.getEmail());
        model.put("nome", usuario.getNome());
        model.put("email", to);

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
            mimeHelper.setTo(to);
            mimeHelper.setSubject("AgendHouse - Confirmação de Cadastro");
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("registration.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeHelper.setText(html, true);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

        return message;
    }

    public List<MimeMessage> eventRequestC(List<Usuario> admins, Evento evento, Usuario criador) throws ParseException {
        List<MimeMessage> messages = new ArrayList<>();
        for (Usuario admin : admins) {
            setTo(admin.getEmail());
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            Calendar c = Calendar.getInstance();
            c.setTime(evento.getDataeven());
            c.add(Calendar.DATE, 1);
            model.put("adminNome", admin.getNome());
            model.put("cargo", criador.getTipo().toString().charAt(0) + criador.getTipo().toString().substring(1).toLowerCase());
            model.put("pessoaNome", criador.getNome());
            model.put("tipo", evento.getTipo().toString());
            model.put("formato", evento.getFormato());
            model.put("data", df.format(c.getTime()));
            model.put("horaIni", evento.getHorainicio().toString().substring(0, 5));
            model.put("horaFim", evento.getHorafim().toString().substring(0, 5));

            MimeMessage message = mailSender.createMimeMessage();
            try {
                MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
                mimeHelper.setTo(to);
                mimeHelper.setSubject("AgendHouse - Novo evento solicitado");
                Template template = freeMarkerConfigurer.getConfiguration().getTemplate("eventSolicitation.ftl");
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                mimeHelper.setText(html, true);
            } catch (MessagingException | IOException | TemplateException e) {
                e.printStackTrace();
            }
            messages.add(message);
        }

        setTo(criador.getEmail());
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
            mimeHelper.setTo(to);
            mimeHelper.setSubject("AgendHouse - Novo evento solicitado");
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("eventSolicitationC.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeHelper.setText(html, true);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
        messages.add(message);

        return messages;
    }

    public MimeMessage eventRequestA(Evento evento, Usuario admin) {
        setTo(admin.getEmail());
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar c = Calendar.getInstance();
        c.setTime(evento.getDataeven());
        c.add(Calendar.DATE, 1);
        model.put("nome", admin.getNome());
        model.put("tipo", evento.getTipo().toString());
        model.put("formato", evento.getFormato());
        model.put("data", df.format(c.getTime()));
        model.put("horaIni", evento.getHorainicio().toString().substring(0, 5));
        model.put("horaFim", evento.getHorafim().toString().substring(0, 5));
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
            mimeHelper.setTo(to);
            mimeHelper.setSubject("AgendHouse - Novo evento criado");
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("eventSolicitationA.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeHelper.setText(html, true);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

        return message;
    }

    public List<MimeMessage> eventInvite(Evento evento, Usuario criador, String[] listEmails) {
        List<MimeMessage> messages = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar c = Calendar.getInstance();
        c.setTime(evento.getDataeven());
        c.add(Calendar.DATE, 1);
        model.put("criador", criador.getNome());
        model.put("tipo", evento.getTipo().toString());
        model.put("formato", evento.getFormato());
        model.put("data", df.format(c.getTime()));
        model.put("horaIni", evento.getHorainicio().toString().substring(0, 5));
        model.put("horaFim", evento.getHorafim().toString().substring(0, 5));

        for (String email  : listEmails) {
            if (!email.equals("")) {
                setTo(email);
                MimeMessage message = mailSender.createMimeMessage();
                try {
                    MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
                    mimeHelper.setTo(to);
                    mimeHelper.setSubject("AgendHouse - Convite de evento");
                    Template template = freeMarkerConfigurer.getConfiguration().getTemplate("eventInvite.ftl");
                    String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                    mimeHelper.setText(html, true);
                } catch (MessagingException | IOException | TemplateException e) {
                    e.printStackTrace();
                }
                messages.add(message);
            }
        }


        return messages;
    }
}
