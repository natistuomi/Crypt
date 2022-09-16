import java.io.*;
import java.util.ArrayList;

public class CryptModel {
     String message;
     String key;
     String crypt;

     public void makeEncryption(String m, String k){
          message = readFile(m);
          key = readFile(k);
          crypt = cryptString(message, key, key.length());
          System.out.println(crypt);
          writeEncryptedFile(crypt);
          System.out.println(cryptString(crypt, key, key.length()));
     }

     public void writeEncryptedFile(String c){
          PrintWriter outfile = openTextFileForWriting("crypt.txt");
          outfile.println(c);
     }

     public PrintWriter openTextFileForWriting(String filename) {
          PrintWriter outfile = null;
          try {
               outfile = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
          } catch (IOException e) {
               e.printStackTrace();
          }
          return outfile;
     }

     public int cryptChar(char m, char k) {
          return m^k;
     }

     public String cryptString(String m, String k, int keyLength){
          String s = "";
          for(int i = 0; i < m.length(); i++){
               char x = m.charAt(i);
               char y = k.charAt(i%keyLength);
               s = s + (char)cryptChar(x,y);
          }
          return s;
     }

     public String readFile(String filename){
          BufferedReader infile = openTextFileForReading(filename);
          ArrayList<String> filecontent = readAllText(infile);
          String content = String.valueOf(filecontent);
          System.out.println(content);
          return content;
     }

     public BufferedReader openTextFileForReading(String filename) {
          BufferedReader in = null;
          try {
               in = new BufferedReader(new FileReader(filename));
          } catch (FileNotFoundException e) {
               System.out.println("File not found, use keyboard instead!");
               in = new BufferedReader(new InputStreamReader(System.in));
          }
          return in;
     }

     public ArrayList<String> readAllText(BufferedReader infile) {
          ArrayList<String> text = new ArrayList<>();
          String line = null;
          try {
               line = infile.readLine();
               while(line != null){
                    text.add(line);
                    line = infile.readLine();
               }
          } catch (IOException e) {
               e.printStackTrace();
          }
          return text;
     }

}

