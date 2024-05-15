import RPi.GPIO as GPIO
import time
import socket

TRIG = 21
ECHO = 20
SERVO_PIN = 10

GPIO.setmode(GPIO.BCM)

# Set up socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(('0.0.0.0', 8888))  # Change the port if needed
server_socket.listen(1)
print("Waiting for connection...")
client_socket, client_address = server_socket.accept()
print("Connection from:", client_address)

# Setup servo motor
GPIO.setup(SERVO_PIN, GPIO.OUT)
servo = GPIO.PWM(SERVO_PIN, 50)  # PWM with 50Hz frequency

def calculate_distance():
    GPIO.output(TRIG, False)
    time.sleep(0.2)
    GPIO.output(TRIG, True)
    time.sleep(0.00001)
    GPIO.output(TRIG, False)

    pulse_start = time.time()
    pulse_end = time.time()

    while GPIO.input(ECHO) == 0:
        pulse_start = time.time()

    while GPIO.input(ECHO) == 1:
        pulse_end = time.time()

    pulse_duration = pulse_end - pulse_start
    distance = pulse_duration * 17150
    return round(distance, 2)

try:
    while True:
        for angle in range(15, 166):
            servo.start(8)  # Move to 15 degrees
            time.sleep(0.3)
            servo.ChangeDutyCycle(8 + (angle / 18))  # Convert angle to duty cycle
            time.sleep(0.3)
            distance = calculate_distance()
            data = str(angle) + "," + str(distance) + "."
            client_socket.send(data.encode())
        
        for angle in range(165, 14, -1):
            servo.start(8)  # Move to 15 degrees
            time.sleep(0.3)
            servo.ChangeDutyCycle(8 + (angle / 18))  # Convert angle to duty cycle
            time.sleep(0.3)
            distance = calculate_distance()
            data = str(angle) + "," + str(distance) + "."
            client_socket.send(data.encode())

except KeyboardInterrupt:
    servo.stop()
    GPIO.cleanup()
    client_socket.close()
