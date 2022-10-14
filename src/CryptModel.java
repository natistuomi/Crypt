import java.io.*;
import java.util.ArrayList;

public class CryptModel {
    private String encryption;
    private String message;
    private String key;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEncryption(){
        return encryption;
    }

    public String getMessage() { return message;}

    public String getKey() { return key;}

    public void makeEncryption(){
        String fileortext = makeIsItFileOrText(message, key);
        message = makeString(message, fileortext);
        key = makeString(key, fileortext);
        encryption = encryptString();
        writeEncryptedFile(encryption);
    }

    public String makeString(String s, String fs){
        int x = 0;
        if(fs.length() != 2){
            x = 1;
        }
        return makeMessageOrKeyString(s, fs, x);
    }

    public String makeMessageOrKeyString(String s, String fs, int x){
        fs = fs + ' ';
        if(fs.charAt(x) == 'f'){
            s = readFile(s);
        }
        return s;
    }

    public void writeEncryptedFile(String c){
        PrintWriter outfile = openTextFileForWriting("encryption.txt");
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

    public String isItFileOrString(String x){
        String goal = "txt.";
        int length = x.length();
        if(length > 4){
            for(int i = 1; i < 5; i++){
                if(x.charAt(length-i) != goal.charAt(i)){
                    return "s";
                }
            }
        }
        return "f";
    }

    public String makeIsItFileOrText(String m, String k){
        String answer = isItFileOrString(m) + isItFileOrString(k);
        return answer;
    }

}

