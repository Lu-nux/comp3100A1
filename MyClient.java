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

            handshake(s);
        

            //While there are jobs to do

            while (!currentMessage.contains("NONE")){
                //client tell the server that it is ready and reads

                sendMessage(s, "REDY");
                currentMessage = readMessage(s);
                if (currentMessage.contains("JOBN")) {
                    String[] JOBNSplit = currentMessage.split(" ");

                    //asks for servers available to run a job
                    //todo
                    //Reads the message saying what data is being sent and responds
                    currentMessage = readMessage(s);
                    sendMessage(s, "OK\n");

                    //reads the servers data and responds
                    currentMessage = readMessage(s);
                    sendMessage(s,"OK\n");

                    //checksif the biggest server is found
                    if(largestFound == false) {
                        //largestFound = TODO findLargestServer(current message);
                        largestFound = true; 
                    }

                    //reads from the server 
                    currentMessage = readMessage(s);

                    //Schedule the current job to biggest server
                    //TODO


                    //reads next job
                     currentMessage = readMessage(s);

                    System.out.println("SCHD: " + currentMessage);
                }
                else if (currentMessage.contains("DATA")){
                    sendMessage(s, "OK\n");
                }
            }
            //sends quit to the server to gracefully end connection
            //TO DO sendMessage(s, "QUIT\n");
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
        catch(IOException e) {    e.printStackTrace();  }
        //return the message from the server
        return currentMessage;
    }

    //send a message to the server
    public static synchronized void sendMessage(Socket s, String currentMessage){
        try{
            //converts string to a byte array and sends the array to the server
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            byte[] byteArray = currentMessage.getBytes();
            dout.write(byteArray);
            dout.flush();
        }catch (IOException e) { e.printStackTrace(); }
    }
    
    public static void handshake(Socket s) {
        String currentMessage = "";

        //initiate handshake
        sendMessage(s, "HELO\n");

        //check for response from server
        currentMessage = readMessage(s);
        System.out.println("RCVD: " + currentMessage);

        //Authenticate with a username ""lu""
        //System.out.println("the user name is: " + System.getProperty("user.name"));
        sendMessage(s, System.getProperty("user.name") + "\n");

        //check if server has approved clients authentication 
        currentMessage = readMessage(s);
        System.out.println("RCVD: " + currentMessage);
    }
    //check loops for errors
    //find the biggest server
    public static String[] findLargestServer(String currentMessage){
        int mostCores = 0;
        String[] currentServer = {""};
        
        //All the servers in the string split into an array
        String[] servers = currentMessage.split("\n");
        
        //searches for server with the most cores
        for(int i = 0; i < servers.length; i++) {
            currentServer = servers[i].split(" ");
            int cores = Integer.valueOf(currentServer[4]);

            if(cores > mostCores){
                mostCores = cores;
            }

        }
        //finds and returns the server with the most cores
        for (int i = 0; i < servers.length; i++) {
            currentServer = servers[i].split(" ");
            int cores = Integer.valueOf(currentServer[4]);
            if(cores == mostCores){
                return currentServer;
            }
        }
        return currentServer;
    }

}
