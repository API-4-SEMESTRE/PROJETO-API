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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<MimeMessage> eventRequest(List<Usuario> usuarios, Evento evento, Usuario criador) {
        List<MimeMessage> messages = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            setTo(usuario.getEmail());
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            model.put("adminNome", usuario.getNome());
            model.put("cargo", criador.getTipo().toString().charAt(0) + criador.getTipo().toString().substring(1).toLowerCase());
            model.put("pessoaNome", criador.getNome());
            model.put("tipo", evento.getTipo().toString());
            model.put("formato", evento.getFormato());
            model.put("data", df.format(evento.getDataeven()));
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

        return messages;
    }

}
