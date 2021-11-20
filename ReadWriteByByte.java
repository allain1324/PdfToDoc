package writefile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;

public class ReadWriteByByte {
	public static void main(String[] args) {
		try {
			File f = new File("/home/alain/Downloads/test/sample.pdf");			
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			File f1 = new File("/home/alain/Downloads/test/sample2.docx");
			FileOutputStream fos = new FileOutputStream(f1);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			int c; 
			while((c = fis.read())!=-1){
				System.out.println(c);
				fos.write(c);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
