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
		
		String file1Contents = "";
		BufferedReader reader = new BufferedReader(new FileReader("objects/31719f883d0a9f2410bf8120e48a62de8172dd05"));
		while (reader.ready()) {
			file1Contents += (char) reader.read();
		}
		assertTrue (file1Contents.equals("blob : 94e66df8cd09d410c62d9e0dc59d3a884e458e05 file1.txt\n"
				+ "blob : 4ca8deacbe9ea18450248727171dae4fd03a1e50 file2.txt"));
		reader.close();
		
		
		i.add("file3.txt");
		i.edit("file1.txt", c1.getTree().getTreeString(), "I changed this!");
		i.remove("file2.txt", c1.getTree().getTreeString());
		Commit c2 = new Commit ("Did more things", "Henry", c1);
		
		String file2Contents = "";
		BufferedReader reader2 = new BufferedReader(new FileReader("objects/85a825fe25b9276839b2e17cba27dbeb650883b9"));
		while (reader2.ready()) {
			file2Contents += (char) reader2.read();
		}
		assertTrue (file2Contents.equals("blob : 65858e36d6f736988f1a7b6ec0c6d4958855f291 file3.txt\n"
				+ "blob : 09d8510c8d53140e90c64d319f533ec2ec20e347 *edited*file1.txt\n"
				+ "blob : 4ca8deacbe9ea18450248727171dae4fd03a1e50 *deleted*file2.txt\n"
				+ "tree : 31719f883d0a9f2410bf8120e48a62de8172dd0"));
		reader2.close();
		
		i.add("file4.txt");
		i.edit("file1.txt", c1.getTree().getTreeString(), "I changed this again!");
		Commit c3 = new Commit ("Did even more things", "Henry", c2);
		
		String file3Contents = "";
		BufferedReader reader3 = new BufferedReader(new FileReader("objects/1a07028014a7d79844266ea2d1459c7f78329f63"));
		while (reader3.ready()) {
			file3Contents += (char) reader3.read();
		}
		assertTrue (file3Contents.equals("blob : 398b78d9bd837ef34a7f96d81a554dcac0bb4fe0 file4.txt\n"
				+ "blob : 1fa4474a55ffc3a3f704c06c39596c153b9294e7 *edited*file1.txt\n"
				+ "tree : 85a825fe25b9276839b2e17cba27dbeb650883b"));
		reader3.close();
		
		i.add("file5.txt");
		i.remove("file3.txt", c2.getTree().getTreeString());
		Commit c4 = new Commit ("Did a lot more things", "Henry", c3);
		
		String file4Contents = "";
		BufferedReader reader4 = new BufferedReader(new FileReader("objects/991d45f39830f426892f84c50b2830f30bacb674"));
		while (reader4.ready()) {
			file4Contents += (char) reader4.read();
		}
		assertTrue (file4Contents.equals("blob : a862845d24b7b343d7cb0709b471ce2c16efc2b8 file5.txt\n"
				+ "blob : 65858e36d6f736988f1a7b6ec0c6d4958855f291 *deleted*file3.txt\n"
				+ "tree : 1a07028014a7d79844266ea2d1459c7f78329f6"));
		reader4.close();
		
		i.add("file6.txt");
		Commit c5 = new Commit ("Did a lot more things", "Henry", c4);
		
		String file5Contents = "";
		BufferedReader reader5 = new BufferedReader(new FileReader("objects/e0c1b0777155eb20ad43642db9d61f82d064b546"));
		while (reader5.ready()) {
			file5Contents += (char) reader5.read();
		}
		assertTrue (file5Contents.equals("blob : d5fa149316c55c6b5b403ffc0f2292ea4d3f1a21 file6.txt\n"
				+ "tree : 991d45f39830f426892f84c50b2830f30bacb67"));
		reader5.close();
		

	}
	
}
