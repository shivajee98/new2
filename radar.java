import java.io.*;
import java.net.*;

Socket socket;
DataInputStream input;
String data;

void setup() {
  size(500, 500);
  try {
    socket = new Socket("YOUR_RASPBERRY_PI_IP_ADDRESS", 8888);  // Change to your Raspberry Pi IP address
    input = new DataInputStream(socket.getInputStream());
  } 
  catch (IOException e) {
    e.printStackTrace();
  }
}

void draw() {
  background(255);
  try {
    if (input.available() > 0) {
      data = input.readUTF();
      println("Received data: " + data);
      // Process your received data here
    }
  } 
  catch (IOException e) {
    e.printStackTrace();
  }
}
