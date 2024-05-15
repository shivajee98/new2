import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

String angle = "";
String distance = "";
String data = "";
String noObject;
float pixsDistance;
int iAngle, iDistance;
int index1 = 0;
int index2 = 0;
PFont orcFont;

Socket socket;
BufferedReader input;

void setup() {
  size(1500, 900);
  smooth();
  try {
    // Connect to the server socket
    socket = new Socket("YOUR_SERVER_IP_ADDRESS", 8888); // Replace with your server's IP address
    input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  } catch (IOException e) {
    e.printStackTrace();
  }
}

void draw() {
  fill(98, 245, 31);
  noStroke();
  fill(0, 4);
  rect(0, 0, width, height-height*0.065);

  fill(98, 245, 31);
  drawRadar();
  drawLine();
  drawObject();
  drawText();
}

void drawRadar() {
  // Your existing drawRadar() code
}

void drawObject() {
  // Your existing drawObject() code
}

void drawLine() {
  // Your existing drawLine() code
}

void drawText() {
  try {
    if (input.ready()) {
      // Read data from the socket
      data = input.readLine();

      // Process the received data
      index1 = data.indexOf(",");
      angle = data.substring(0, index1);
      distance = data.substring(index1 + 1, data.length());
      iAngle = int(angle);
      iDistance = int(distance);
    }
  } catch (IOException e) {
    e.printStackTrace();
  }

  // Your existing drawText() code
}
