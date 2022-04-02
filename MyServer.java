import java.io.*;
import java.net.*;
    
public class MyServer {
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(50000);
            Socket s=ss.accept();
            DataInputStream dis= new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            //System.out.println(ss.getLocalPort()); sanity check
            String str=(String)dis.readUTF();
            System.out.println("recv: " + str);
            dout.writeUTF("G'DAY");

            String str2=(String)dis.readUTF();
            System.out.println("recv: " + str2);
            
            dout.writeUTF("BYE");
	        dout.close();
            ss.close();
        }catch(Exception e){System.out.println(e);}
    }
    
}