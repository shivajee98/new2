import processing.serial.*; // imports library for serial communication
import java.awt.event.KeyEvent; // imports library for reading the data from the serial port
import java.io.IOException;
Serial myPort; // defines Object Serial
// defines variables
String angle="";
String distance="";
String data="";
String noObject;
float pixsDistance;
int iAngle, iDistance;
int index1=0;
int index2=0;
PFont orcFont;
void setup() {
  
 size (1500, 900); // *CHANGE THIS TO YOUR SCREEN RESOLUTION*
 smooth();
 myPort = new Serial(this,"COM3", 9600); // starts the serial communication
}
void draw() {
  
  fill(98,245,31);
  // simulating motion blur and slow fade of the moving line
  noStroke();
  fill(0,4); 
  rect(0, 0, width, height-height*0.065); 
  
  fill(98,245,31); // green color
  // calls the functions for drawing the radar
  drawRadar(); 
  drawLine();
  drawObject();
  drawText();
}
void serialEvent (Serial myPort) { // starts reading data from the Serial Port
  // reads the data from the Serial Port
  char inChar = myPort.readChar();
  
  if (inChar != '.') {
    data += inChar;
  } else {
    processData(data);
    data = "";
  }
}

void processData(String data) {
  // Split the data string into angle and distance parts
  String[] parts = data.split(",");
  angle = parts[0];
  distance = parts[1];
  
  // converts the String variables into Integer
  iAngle = int(angle);
  iDistance = int(distance);
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
