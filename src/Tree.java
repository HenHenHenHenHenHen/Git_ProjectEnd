import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tree {
	private String hashedContents;
	private Tree parentTree;
	private String treeString;
	public Tree () throws IOException {
		
		File index = new File ("index");
		StringBuilder treeStringBuilder = new StringBuilder ();
		ArrayList<String> indexContents = new ArrayList<String>();
		Scanner indexScanner = new Scanner(index); 
		while (indexScanner.hasNextLine()) {
			indexContents.add(indexScanner.nextLine());
		}
		indexScanner.close();
		for (int i = 0; i < indexContents.size(); i++) {
			treeStringBuilder.append("blob : " + prepForTreeString(indexContents.get(i)) + "\n");
		}
		
		FileWriter fw = new FileWriter (index);
		fw.close();
		
		treeString = treeStringBuilder.toString().substring(0, treeStringBuilder.toString().length() - 1);
		File treeStringFile = new File ("objects/" + Blob.generateSHA1(treeString));
		FileWriter treeStringFileWriter = new FileWriter (treeStringFile);
		treeStringFileWriter.append(treeString);
		treeStringFileWriter.close();
	}
	
public Tree (Tree parentTree) throws IOException {
		
		File index = new File ("index");
		StringBuilder treeStringBuilder = new StringBuilder ();
		ArrayList<String> indexContents = new ArrayList<String>();
		Scanner indexScanner = new Scanner(index); 
		while (indexScanner.hasNextLine()) {
			indexContents.add(indexScanner.nextLine());
		}
		indexScanner.close();
		for (int i = 0; i < indexContents.size(); i++) {
			treeStringBuilder.append("blob : " + prepForTreeString(indexContents.get(i)) + "\n");

		treeStringBuilder.append("tree : " + Blob.generateSHA1(parentTree.getTreeString()));
		
		FileWriter fw = new FileWriter (index);
		fw.close();
		
		treeString = treeStringBuilder.toString().substring(0, treeStringBuilder.toString().length() - 1);
		File treeStringFile = new File ("objects/" + Blob.generateSHA1(treeString));
		FileWriter treeStringFileWriter = new FileWriter (treeStringFile);
		treeStringFileWriter.append(treeString);
		treeStringFileWriter.close();
		}
	}
	// If given a String input (fileName : SHA), will turn into (SHA : fileName)
	public String prepForTreeString (String input) {
		int counter = 0;
		while (input.charAt(counter) != ' ') {
			counter++;
		}
		String fileName = input.substring(0, counter);
		String shaString = input.substring(counter + 3);
		
		return (shaString + " " + fileName);
	}
	
	public void setParentTree (Tree parent) {
		parentTree = parent;
	}
	
	public Tree getParentTree () {
		return parentTree;
	}
	
	public String getTreeString () {
		return treeString;
	}
	
	public String genHash (String input) {
		try {
	           
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(input.getBytes());
 

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
 
            return hashtext;
	     
	     }
		catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}
	
	public Tree getParent () {
		return parentTree;
	}
	
	
	public String getsha1 () {
		return hashedContents;
	}
}
