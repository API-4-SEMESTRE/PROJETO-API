package com.api.agendhouse.domain.email;

import com.api.agendhouse.domain.evento.Evento;
import com.api.agendhouse.domain.evento.EventoService;
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

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
    Map<String, String> model = new HashMap<>();

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

    public List<MimeMessage> eventRequestC(List<Usuario> admins, Evento evento, Usuario criador) {
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
            setMyModel(
                    evento.getTipo().toString(),
                    evento.getFormato(),
                    df.format(c.getTime()),
                    evento.getHorainicio().toString().substring(0, 5),
                    evento.getHorafim().toString().substring(0, 5));

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
        setMyModel(
                evento.getTipo().toString(),
                evento.getFormato(),
                df.format(c.getTime()),
                evento.getHorainicio().toString().substring(0, 5),
                evento.getHorafim().toString().substring(0, 5));
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
        setMyModel(
                evento.getTipo().toString(),
                evento.getFormato(),
                df.format(c.getTime()),
                evento.getHorainicio().toString().substring(0, 5),
                evento.getHorafim().toString().substring(0, 5));
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

    public MimeMessage eventUpdate(Evento evento, Usuario criador) {
        setTo(criador.getEmail());
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar c = Calendar.getInstance();
        c.setTime(evento.getDataeven());
        c.add(Calendar.DATE, 1);
        model.put("criador", criador.getNome());
        setMyModel(
                evento.getTipo().toString(),
                evento.getFormato(),
                df.format(c.getTime()),
                evento.getHorainicio().toString().substring(0, 5),
                evento.getHorafim().toString().substring(0, 5));
        model.put("dataCria", String.valueOf(evento.getDatacria().getDayOfMonth() + "/" +
                evento.getDatacria().getMonthValue() + "/" +
                evento.getDatacria().getYear() + " " +
                evento.getDatacria().getHour() + ":" +
                evento.getDatacria().getMinute() + ":" +
                evento.getDatacria().getSecond()));
        model.put("status", evento.getStatus().toString().charAt(0) + evento.getStatus().toString().substring(1).toLowerCase());

        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
            mimeHelper.setTo(to);
            mimeHelper.setSubject("AgendHouse - Evento " + model.get("status"));
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("eventUpdate.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            mimeHelper.setText(html, true);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void setMyModel (String tipo, String formato, String data, String horaIni, String horaFim) {
        model.put("tipo", tipo);
        model.put("formato", formato);
        model.put("data", data);
        model.put("horaIni", horaIni);
        model.put("horaFim", horaFim);
    }

    public MimeMessage csv(String email, Date data) {
        DateFormat df = new SimpleDateFormat("yyyy/MM");
        model.put("data", df.format(data));
        setTo(email);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeHelper = new MimeMessageHelper(message, true, "utf-8");
            mimeHelper.setTo(to);
            mimeHelper.setSubject("AgendHouse - Relatório Mensal");
            Multipart multipart = new MimeMultipart();
            BodyPart messagePart = new MimeBodyPart();
            BodyPart attachmentPart = new MimeBodyPart();
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("report.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            messagePart.setContent(html.toString(), "text/html");
            var file = EventoService.generateFullCsv(data);
            attachmentPart.setDataHandler(new DataHandler(new FileDataSource(file)));
            attachmentPart.setFileName(file.getName());
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }

        return message;
    }
}
