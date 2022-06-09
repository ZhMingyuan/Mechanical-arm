//const int buttonPin = 2;     //按钮先放一放
#include "AccelStepper.h"
String comdata="";
int ledPin =  13;      //13号引脚为装载LED

// 电机步进方式定义
#define FULLSTEP 4    //全步进参数
#define HALFSTEP 8    //半步进参数

// 定义步进电机引脚 
#define motor1Pin1  8     // 一号28BYJ48连接的ULN2003电机驱动板引脚 in1
#define motor1Pin2  9     // 一号28BYJ48连接的ULN2003电机驱动板引脚 in2
#define motor1Pin3  10    // 一号28BYJ48连接的ULN2003电机驱动板引脚 in3
#define motor1Pin4  11    // 一号28BYJ48连接的ULN2003电机驱动板引脚 in4
                                   
#define motor2Pin1  4     // 二号28BYJ48连接的ULN2003电机驱动板引脚 in1
#define motor2Pin2  5     // 二号28BYJ48连接的ULN2003电机驱动板引脚 in2
#define motor2Pin3  6     // 二号28BYJ48连接的ULN2003电机驱动板引脚 in3
#define motor2Pin4  7     // 二号28BYJ48连接的ULN2003电机驱动板引脚 in4
 
AccelStepper stepper1(HALFSTEP, motor1Pin1, motor1Pin3, motor1Pin2, motor1Pin4);
AccelStepper stepper2(FULLSTEP, motor2Pin1, motor2Pin3, motor2Pin2, motor2Pin4);
 

void setup() {
  pinMode(ledPin, OUTPUT);
  Serial.begin(9600);
  stepper1.setMaxSpeed(500.0);    // 1号电机最大速度500 
  stepper1.setAcceleration(50.0);  // 1号电机加速度50.0
 
  stepper2.setMaxSpeed(500.0);    // 2号电机最大速度500 
  stepper2.setAcceleration(50.0);  // 2号电机加速度50.0
}

void loop() {
  
  // read the state of the pushbutton value:
  if (comdata.length() > 0)
  {
    Serial.println(comdata);
    
  }
  // check if the pushbutton is pressed.
  // if it is, the buttonState is HIGH:
  
  if (comdata.compareTo("H")==10){
    // turn LED on:
    digitalWrite(ledPin, LOW);
    Serial.println("1号电机转动半周");
    stepper1.moveTo(2048);  
    delay(500); 
  }
 
  if (comdata.compareTo("J")==10) {
    // turn LED on:
    digitalWrite(ledPin, LOW);
    Serial.println("2号电机转动一周");
    stepper2.moveTo(2048);  
    delay(1000);
  
  }
  //补充:moveTo函数改成move函数时会变成相对值坐标驱动 电机每次停止旋转后的位置为0
  //补充:moveTo函数改成runToNewPosition函数时会变成阻塞式驱动 一个电机停止后才驱动下一个电机
  else {
    // turn LED off:
    digitalWrite(ledPin, HIGH);
  }
  comdata = "";

  stepper1.run();  
  stepper2.run();
  //这两句话如果不加会怎么样？
}

void serialEvent()
{
  comdata = "";
  while(Serial.available())   //时刻读取硬件串口数据
  {
    comdata += char(Serial.read());
    delay(2);
  }
  while (Serial.read() >= 0) {} //清除串口缓存
}
