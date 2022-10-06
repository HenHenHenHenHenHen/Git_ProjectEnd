import java.io.BufferedReader;
import java.io.File;
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
public class Index {
	
	public static HashMap<String, String> blobMap = new HashMap<String, String>();
	String location;
	public static File index;
	
	public Index()
	{
		
	}
	
	public void initialize()
	{
		File directory = new File("./objects/");
		directory.mkdir();
		index = new File("index");
	}
	
	public void add(String fileName) throws IOException
	{
		Blob blob = new Blob(fileName);
		blobMap.put(fileName, blob.sha1);
		PrintWriter out = new PrintWriter(new FileWriter(index));
		for(String key : blobMap.keySet())
		{
			out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.close();
	}
	
	public void remove(String fileName) throws IOException
	{
		File toDelete = new File("./objects/" + blobMap.get(fileName)); 
		toDelete.delete();
	    blobMap.remove(fileName);
	    PrintWriter out = new PrintWriter(new FileWriter(index));
		for(String key : blobMap.keySet())
		{
			out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.close();

	}
	
	public static void clear () throws IOException {
		blobMap.clear();
		PrintWriter out = new PrintWriter(new FileWriter(index));
		out.close();
	}


}
