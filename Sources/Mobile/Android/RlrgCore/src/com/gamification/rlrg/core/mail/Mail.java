package com.gamification.rlrg.core.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.gamification.rlrg.gen.BuildConfig;

public class Mail extends Authenticator
{

    private String mUsername;
    private String mPassword;

    private String[] mTos;
    private String mFrom;

    private String mPort;
    private String mSport;

    private String mHost;

    private String mSubject;
    private String mBody;

    private boolean mIsAuth;

    private Multipart mMultipart;

    public Mail()
    {
        mHost = "smtp.gmail.com"; // default smtp server
        mPort = "465"; // default smtp port
        mSport = "465"; // default socketfactory port

        mUsername = ""; // username
        mPassword = ""; // password
        mFrom = ""; // email sent from
        mSubject = ""; // email subject
        mBody = ""; // email body

        mIsAuth = true; // smtp authentication - default on

        mMultipart = new MimeMultipart();

        // There is something wrong with MailCap, javamail can not find a
        // handler for the multipart/mixed part, so this bit needs to be added.
        MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mailcapCommandMap);
    }

    public Mail(String user, String pass)
    {
        this();

        mUsername = user;
        mPassword = pass;
    }

    public boolean send() throws Exception
    {
        Properties props = setProperties();

        mFrom = mUsername;
        if (!mUsername.equals("") && !mPassword.equals("") && mTos.length > 0
                && !mSubject.equals("")
                && !mBody.equals(""))
        {
            Session session = Session.getInstance(props, this);

            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(mFrom));

            InternetAddress[] addressTo = new InternetAddress[mTos.length];
            for (int i = 0; i < mTos.length; i++)
            {
                addressTo[i] = new InternetAddress(mTos[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            msg.setSubject(mSubject);
            msg.setSentDate(new Date());

            // setup message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(mBody);
            mMultipart.addBodyPart(messageBodyPart);

            // Put parts in message
            msg.setContent(mMultipart);

            // send email
            Transport.send(msg);

            return true;
        } else
        {
            return false;
        }
    }

    public void addAttachment(String filename) throws Exception
    {
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        mMultipart.addBodyPart(messageBodyPart);
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(mUsername, mPassword);
    }

    private Properties setProperties()
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", mHost);

        if (BuildConfig.DEBUG)
        {
            props.put("mail.debug", "true");
        }

        if (mIsAuth)
        {
            props.put("mail.smtp.auth", "true");
        }

        props.put("mail.smtp.port", mPort);
        props.put("mail.smtp.socketFactory.port", mSport);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        return props;
    }

    public String getUsername()
    {
        return mUsername;
    }

    public void setUsername(String username)
    {
        mUsername = username;
    }

    public String getPassword()
    {
        return mPassword;
    }

    public void setPassword(String password)
    {
        mPassword = password;
    }

    public String[] getTo()
    {
        return mTos;
    }

    public void setTos(String[] tos)
    {
        mTos = tos;
    }

    public String getSubject()
    {
        return mSubject;
    }

    public void setSubject(String subject)
    {
        mSubject = subject;
    }

    public String getBody()
    {
        return mBody;
    }

    public void setBody(String body)
    {
        mBody = body;
    }

}
