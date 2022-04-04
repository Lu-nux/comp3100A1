import java.io.*;
import java.net.*;
    
public class MyClient {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost", 10000);
            DataInputStream dis= new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            dout.writeUTF("HELO");
            
            String str=(String)dis.readUTF();
            System.out.println("recv: " + str);
            dout.writeUTF("BYE");

            String str2=(String)dis.readUTF();
            System.out.println("recv: "+ str2);
            dout.flush();
            dout.close();
            s.close();
            }catch(Exception e){System.out.println(e);}
    }
}
