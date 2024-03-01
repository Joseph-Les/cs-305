package com.snhu.sslserver;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{    
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Hello, this is the check sum for Joseph Les!";
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] digest = md.digest(data.getBytes());
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, digest);
        // Convert message digest into hex value
        String shaHex = number.toString(16);
        while (shaHex.length() < 64) {
            shaHex = "0" + shaHex;
        }
        return "<p>data: " + data + "</p><p>Checksum (SHA-256): " + shaHex + "</p>";
    } catch (NoSuchAlgorithmException e) {
        return "<p>Error generating SHA-256 checksum</p>";
    }
   }
}