import java.io.*;
import java.util.ArrayList;

public class CryptModel {
    private String encryption;
    private String message;
    private String key;
    private String fileortext;



    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEncryption(){
        return encryption;
    }



    public void makeEncryption(){
        fileortext = makeIsItFileOrText();
        message = makeString(message);
        key = makeString(key);
        encryption = encryptString();
        writeEncryptedFile(encryption);
    }



    public String makeIsItFileOrText(){
        return isItFileOrString(message) + isItFileOrString(key);
    }

    public String isItFileOrString(String s){
        String goal = "txt.";
        int length = s.length();
        if(length > 4){
            for(int i = 1; i < 5; i++){
                if(s.charAt(length-i) != goal.charAt(i-1)){
                    return "s";
                }
            }
            return "f";
        }
        return "s";
    }



    public String makeString(String s){
        int n = 0;
        if(fileortext.length() != 2){
            n = 1;
        }
        return makeMessageOrKeyString(s, n);
    }

    public String makeMessageOrKeyString(String s, int n){
        fileortext = fileortext + ' ';
        if(fileortext.charAt(n) == 'f'){
            s = readFile(s);
        }
        return s;
    }

    public String readFile(String fs){
        BufferedReader infile = openTextFileForReading(fs);
        ArrayList<String> filecontent = readAllText(infile);
        String content = String.valueOf(filecontent);
        return content;
    }

    public BufferedReader openTextFileForReading(String fs) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fs));
        } catch (FileNotFoundException e) {
            System.out.println("Error with file!");
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



    public String encryptString(){
        int kl = key.length();
        String s = "";
        for(int i = 0; i < message.length(); i++){
            char x = message.charAt(i);
            char y = key.charAt(i%kl);
            s = s + (char)cryptChar(x,y);
        }
        return s;
    }

    public int cryptChar(char m, char k) {
        return m^k;
    }



    public void writeEncryptedFile(String s){
        PrintWriter outfile = openTextFileForWriting("encryption.txt");
        outfile.print(s);
        outfile.flush();
        outfile.close();
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
}

