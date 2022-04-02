import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
    
public class MyClient {

    
    public static void main(String[] args) {
        try{
            //variables
            Socket s=new Socket("localhost", 50000);
            String[] bigServer = {""};
            boolean largestFound = false;
            String currentMessage = "";

         //   handshake(s);
            // todo

            //While there are jobs to do

            while (!currentMessage.contains("NONE")){
                //client tell the server that it is ready and reads

                //todo sendMessage(s, "REDY")
             // TODO  currentMessage = readMessage(s);
                if (currentMessage.contains("JOBN")) {
                    String[] JOBNSplit = currentMessage.split(" ");

                    //asks for servers available to run a job
                    //todo
                    //Reads the message saying what data is being sent and responds
                    //todo currentMessage = <readmessage(s)
                    //respond sendmessage(s, "OK\n");

                    //reads the servers data and responds
                    //todo currentMessage = readmessage(s);
                    //todo sendmessage(s,"OK\n");

                    //checksif the biggest server is found
                    if(largestFound == false) {
                        //largestFound = TODO findLargestServer(current message);
                        largestFound = true; 
                    }

                    //reads from the server TODo
                    //currentMessage = readMessage(s);

                    //Schedule the current job to biggest server
                    //sendMessage(s, "SCHD " + JOBNSplit[2] + " " + largetServer[0] + " " + largestServer[1] + "\n");)


                    //reads next job
                    // to do currentMessage = readMessage(s);

                    System.out.println("SCHD: " + currentMessage);
                }
                else if (currentMessage.contains("DATA")){
                    //sendMessage(s, "OK\n");
                }
            }
            //sends quit to the server to gracefully end connection
            //sendMessage(s, "QUIT\n");
            s.close();
            }catch(Exception e){System.out.println(e); 
            }
    }  


//to read a message from the server
public static synchronized String readMessage(Socket s) {
    String currentMessage = "FAIL";
    try {
        DataInputStream dis = new DataInputStream(s.getInputStream());
        byte[] byteArray = new byte[dis.available()];

        //reset byte array to have no elements so its ready to recieve a message
    
        byteArray = new byte[0];
        while(byteArray.length == 0) {
            //reads the bytestream
            byteArray = new byte[dis.available()];
            dis.read(byteArray);
            // make a string using incoming bytes and print
            currentMessage = new String(byteArray, StandardCharsets.UTF_8);

        } 
    }
        catch(IOException e) {
            e.printStackTrace();
        }
        //return the message from the server
        return currentMessage;
    }
    

}
