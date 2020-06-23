package utilerias;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author azul-
 */

public class Utilerias {
    
    public void enviarEmail(String correoDst, String asunto, String texto){
        try{
            System.out.println(correoDst);
            System.out.println(asunto);
            System.out.println(texto);
            Properties p =new Properties();
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.setProperty("mail.smtp.starttls.required", "true");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.user", 
                    "webApDevEscom@gmail.com");
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.debug","true");
            Session s = Session.getDefaultInstance(p);
            MimeMessage mensaje= new MimeMessage(s);
            mensaje.setFrom(
                    new InternetAddress("webApDevEscom@gmail.com"));
            mensaje.addRecipients(Message.RecipientType.TO,correoDst);
            
            mensaje.setSubject(asunto);
            mensaje.setText(texto);
            
            Transport t = s.getTransport("smtp");
            t.connect("webApDevEscom@gmail.com",
                    "123webA69");
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


//public class Utilerias {
//
//    public void enviarEmail(String correoDst, String asunto, String texto) {
//        try {
//            System.out.println(correoDst);
//            System.out.println(asunto);
//            System.out.println(texto);
//            Properties p = new Properties();
//            p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//            p.setProperty("mail.smtp.host", "smtp.gmail.com");
//            p.setProperty("mail.smtp.starttls.enable", "true");
//            p.setProperty("mail.smtp.starttls.required", "true");
//            p.put("mail.smtp.socketFactory.port", "465");
//            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            p.put("mail.smtp.auth", "true");
//            p.put("mail.smtp.port", "465");
//            p.setProperty("mail.smtp.user",
//                    "escuelawebmordekaiser@gmail.com");
//            p.setProperty("mail.smtp.auth", "true");
//
////            Session session = Session.getInstance(p,
////                    new javax.mail.Authenticator() {
////                        protected PasswordAuthentication getPasswordAuthentication(){
////                            return new PasswordAuthentication("webApDevEscom@gmail.com", "123webA69");
////                        }
////                    });
////      
//            Session s = Session.getDefaultInstance(p);
//            MimeMessage mensaje = new MimeMessage(s);
//            mensaje.setFrom(
//                    new InternetAddress("escuelawebmordekaiser@gmail.com"));
//            mensaje.addRecipients(Message.RecipientType.TO, correoDst);
//
//            mensaje.setSubject(asunto);
//            mensaje.setText(texto);
//
//            Transport t = s.getTransport("smtp");
//            t.connect("escuelawebmordekaiser@gmail.com",
//                    "KilexUzi19");
//            t.sendMessage(mensaje, mensaje.getAllRecipients());
//            t.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
