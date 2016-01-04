import java.awt.*;  //abstract windowing toolkit 
import java.net.*;  //networking 
import java.io.*;   //DataIOStreams 
public class Client extends Frame 
{TextArea display=new TextArea("tekst\n"); 
  
   public Client()  
    {setLayout(null);  
     setSize(400,400); 
     setTitle("Client"); 
     super.setVisible(true); 
     this.addWindowListener(new MyWindow()); 
     display.setBounds(10,200,380,150); 
     add(display); 
    }  

   public void runClient() 
    {Socket client; 
     DataOutputStream output; 
     DataInputStream input; 
     try{System.out.println("Client is running"); 
//         client=new Socket(InetAddress.getLocalHost(),5500); //create socket 
//       client=new Socket(InetAddress.getByName("demeter.cs.utwente.nl"),5500); 
       client=new Socket("192.168.1.102",5500); //create socket 
         display.append("To: "+client.getInetAddress().getHostName()+"\n"); 
         input=new DataInputStream(client.getInputStream()); 
         output=new DataOutputStream(client.getOutputStream()); 
         display.append("Received: "+input.readUTF()+"\n"); 
         output.writeUTF("Bye and see you again soon"); 
         client.close(); 
        } 
     catch (IOException ex) 
       {ex.printStackTrace(); 
       }  
     display.append("bye\n"); 
    } 

  class MyWindow extends java.awt.event.WindowAdapter 
     {public void windowClosing(java.awt.event.WindowEvent event) 
        {System.out.println("Client shut down"); 
         setVisible(false); 
         dispose();  
         System.exit(0);  
        } 
     } 

  public static void main(String[] args) 
  {System.out.println("eerst de server runnen, daarna de client"); 
   Client c =new Client(); 
   c.runClient(); 
  } 
}
