import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.ArrayList;
import java.util.Date;

public class CommitTester {
	
	public static void main(String[] args)
	{
		ArrayList<Blob> blobList = new ArrayList<Blob>();
		blobList.add(new Blob ("testOne"));
		
		Commit c = new Commit(blobList,"Testing Commit","Author", null);
		System.out.println(c.commitSHA1());
		c.writeFile();
	}

}
