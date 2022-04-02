import java.io.*;
import java.net.*;
    
public class MyClient {
    public static void main(String[] args) {
        try{
            Socket s=new Socket("localhost", 50000);
            DataInputStream dis= new DataInputStream(s.getInputStream());
            DataOutputStream dout=new DataOutputStream(s.getOutputStream());
            
            BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
           
            String str="",str2="";

            //while(!str.equals("stop")){
            str=(String)br.readLine();
            dout.write(("HELO\n").getBytes());
            dout.flush();
            str2=(String)br.readLine();
            System.out.println("Server says:" +str2);

           // }

            dout.write(("HELO\n").getBytes());
          
           // String str=(String)dis.readLine();
            System.out.println("recv: " + str);
            dout.write(("BYE.\n").getBytes());;

           // String str2=(String)dis.readUTF();
            System.out.println("recv: "+ str2);
            dout.flush();
            dout.close();
            s.close();
            }catch(Exception e){System.out.println(e);}
    }
}