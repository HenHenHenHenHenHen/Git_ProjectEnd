import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class CommitJUnitTester {

	@Test
	public void testCommit() throws IOException {
		Index i = new Index();
		i.initialize();
		i.add("file1.txt");
		i.add("file2.txt");
		Commit c1 = new Commit ("Did something", "Henry");
		
//		String file1Contents = "";
//		BufferedReader reader = new BufferedReader(new FileReader("objects/d259f1ce159fd12f86a4278b4fbb214ea7e9fe96"));
//		while (reader.ready()) {
//			file1Contents += (char) reader.read();
//		}
//		assertTrue (file1Contents.equals("blob : 4ca8deacbe9ea18450248727171dae4fd03a1e50 file2.txt\n"
//				+ "blob : 09d8510c8d53140e90c64d319f533ec2ec20e347 *edited*file1.txt"));
//		reader.close();
		
//		
		i.add("file3.txt");
		i.edit("file1.txt", c1.getTree().getTreeString(), "I changed this!");
		Commit c2 = new Commit ("Did more things", "Henry", c1);
		
//		String file2Contents = "";
//		BufferedReader reader2 = new BufferedReader(new FileReader("objects/09bce6ba507bdeacbeb23b5af8ec713d63ca4c72"));
//		while (reader2.ready()) {
//			file2Contents += (char) reader2.read();
//		}
//		assertTrue (file2Contents.equals("blob : 65858e36d6f736988f1a7b6ec0c6d4958855f291 file3.txt\n"
//				+ "tree : 31719f883d0a9f2410bf8120e48a62de8172dd0"));
//		reader2.close();
//		
//		i.add("file4.txt");
//		Commit c3 = new Commit ("Did even more things", "Henry", c2);
//		
//		String file3Contents = "";
//		BufferedReader reader3 = new BufferedReader(new FileReader("objects/9037883d4610ba4fb4113acdf805fa4a374dd541"));
//		while (reader3.ready()) {
//			file3Contents += (char) reader3.read();
//		}
//		assertTrue (file3Contents.equals("blob : 398b78d9bd837ef34a7f96d81a554dcac0bb4fe0 file4.txt\n"
//				+ "tree : 09bce6ba507bdeacbeb23b5af8ec713d63ca4c7"));
//		reader3.close();
//		
//		i.add("file5.txt");
//		Commit c4 = new Commit ("Did a lot more things", "Henry", c3);
//		
//		String file4Contents = "";
//		BufferedReader reader4 = new BufferedReader(new FileReader("objects/5b9132c1ba180d212f2e103b62c4074102d0cc46"));
//		while (reader4.ready()) {
//			file4Contents += (char) reader4.read();
//		}
//		assertTrue (file4Contents.equals("blob : a862845d24b7b343d7cb0709b471ce2c16efc2b8 file5.txt\n"
//				+ "tree : 9037883d4610ba4fb4113acdf805fa4a374dd54"));
//		reader4.close();
//		
//		

	}
	
}
