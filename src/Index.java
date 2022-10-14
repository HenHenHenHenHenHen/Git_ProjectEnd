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
	
	//returns sha of desired fileToDelete
	public String traverseTree (String fileToDelete, String tree) throws IOException {
		String containsFileResults = containsFile (fileToDelete, tree);
		if (!containsFileResults.equals("f")) {
			return containsFileResults;
		} else {
			return traverseTree (fileToDelete, getParentTreeString(tree));
		}
	}
	
	public String containsFile (String file, String tree) {
		Scanner treeScanner = new Scanner (tree);
		while (treeScanner.hasNextLine()) {
			String newLine = treeScanner.nextLine();
			if (newLine.substring(0, 4).equals ("blob") && newLine.substring(48).equals(file)) {
				treeScanner.close();
				return  newLine.substring(7, 47);
			}
		}
		treeScanner.close();
		return "f";
	}
	
	public String getParentTreeString (String treeString) throws IOException {
		String newLine = "";
		Scanner treeScanner = new Scanner (treeString);
		while (treeScanner.hasNextLine()) {
			newLine = treeScanner.nextLine();
		}
		treeScanner.close();
		File parentTree = new File (newLine.substring(7));
		BufferedReader parentTreeScanner = new BufferedReader (new FileReader (parentTree));
		String parentTreeString = "";
		while (parentTreeScanner.ready()) {
			parentTreeString += parentTreeScanner.read();
		}
		parentTreeScanner.close();
		return parentTreeString;
		
	}
	
	public void edit (String fileName, String treeString, String newContent) throws IOException {
		File edited = new File ("./objects/" + Blob.generateSHA1(newContent));
		FileWriter editWriter = new FileWriter (edited);
		editWriter.append(newContent);
		editWriter.close();
		String oldFile = traverseTree (fileName, treeString);
		PrintWriter out = new PrintWriter(new FileWriter(index));
		blobMap.put(fileName, Blob.generateSHA1(newContent));
		for(String key : blobMap.keySet())
		{
			if (!blobMap.get(key).equals(oldFile))
				out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.write("*edited*" + fileName + " : " + Blob.generateSHA1(newContent));
		out.close();
		
	}
	
	public void remove(String fileName, String treeString) throws IOException
	{
		String fileToDelete = traverseTree (fileName, treeString);
	    PrintWriter out = new PrintWriter(new FileWriter(index));
		for(String key : blobMap.keySet())
		{
			if (!blobMap.get(key).equals(fileToDelete))
				out.write(key + " : " + blobMap.get(key) + "\n");
		}
		out.write("*deleted*" + fileName + " : ");
		out.close();

	}
	
	public static void clear () throws IOException {
		blobMap.clear();
		PrintWriter out = new PrintWriter(new FileWriter(index));
		out.close();
	}


}
