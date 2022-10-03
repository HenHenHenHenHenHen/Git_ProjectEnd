 import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
public class Blob 
{

	String content;
	String sha1;
	String originalFileName;
	
	public Blob(String inputFile) throws IOException
	{
		originalFileName = inputFile;
		BufferedReader reader = new BufferedReader(new FileReader(inputFile));
		StringBuilder sb = new StringBuilder("");
		
		// Reads off the file and creates a StringBuilder storing the content.
		while(reader.ready())
		{
			char nextLetter = (char)reader.read();
			sb.append(nextLetter);
			
		}
		reader.close();
		
		// Generate SHA-1 based on the content.
		content = sb.toString();
		sha1 = generateSHA1(content);
		
		// Create file with the SHA-1 name and write the content.
		Path p = Paths.get("./objects/" + sha1);
        try {
            Files.writeString(p, content, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

	public static String generateSHA1(String input)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(input.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	public static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	public String getsha1() {
		return sha1;
	}
	
	public String getOriginalFileName () {
		return originalFileName;
	}
	}
