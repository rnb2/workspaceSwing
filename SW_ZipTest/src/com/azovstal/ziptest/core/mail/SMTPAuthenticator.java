package com.azovstal.ziptest.core.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {  
  
    protected PasswordAuthentication getPasswordAuthentication() {  
        //return new PasswordAuthentication("username", "password");  
    	return new PasswordAuthentication("jboss", "JavaSUN1999");  
    }  
} 