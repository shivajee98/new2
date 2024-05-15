import java.io.*;
import java.net.*;
import processing.serial.*; // imports library for serial communication

Serial myPort; // defines Object Serial
String angle = "";
String distance = "";
int iAngle, iDistance;
boolean newData = false;

void setup() {
  size(1500, 900);
  smooth();
  
  try {
    ServerSocket serverSocket = new ServerSocket(8888); // Change the port if needed
    println("Waiting for connection...");
    Socket socket = serverSocket.accept();
    println("Connection established: " + socket.getInetAddress());
    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
    String line = input.readLine();
    println("Received data: " + line);
    String[] dataParts = line.split(",");
    if (dataParts.length == 2) {
      angle = dataParts[0];
      distance = dataParts[1];
      iAngle = int(angle);
      iDistance = int(distance);
      newData = true;
    }
    
    input.close();
    socket.close();
    serverSocket.close();
  } catch (IOException e) {
    e.printStackTrace();
  }
}

void draw() {
  if (newData) {
    background(255);
    drawRadar();
    drawLine();
    drawObject();
    drawText();
    newData = false;
  }
}

void drawRadar() {
  // Draw radar visuals here
}

void drawObject() {
  // Draw object visuals here
}

void drawLine() {
  // Draw line visuals here
}

void drawText() {
  // Draw text visuals here
}
