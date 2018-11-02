/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.service;

/**
 *
 * @author brari
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EnvoyerEmail {
private String username = "ousseema.ibbrari@gmail.com";
private String password = "geniecivil1111";
public void envoyer(String s ,String k ,String mail) {
// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("oussama.brari@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(mail));
message.setSubject(s);
message.setText(k);
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} }}
//Etape 4 : Tester la méthode
/*public static void main(String[] args) {
EnvoyerEmail test = new EnvoyerEmail();
test.envoyer();
} }*/