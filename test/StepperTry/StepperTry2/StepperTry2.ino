#include "AccelStepper.h"
 
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
 
// 定义两个步进电机对象
// 定义中ULN2003驱动板引脚顺序为 in1-in3-in2-in4
// 1号电机设置为全步进运行，2号电机设置为全步进运行
AccelStepper stepper1(FULLSTEP, motor1Pin1, motor1Pin3, motor1Pin2, motor1Pin4);//电机驱动方式为全步进驱动
AccelStepper stepper2(FULLSTEP, motor2Pin1, motor2Pin3, motor2Pin2, motor2Pin4);
 
void setup()
{  
  stepper1.setMaxSpeed(500);   // 设置电机最大速度为500
  stepper1.setSpeed(300);      // 初始化电机速度为300
  
  stepper2.setMaxSpeed(500);   // 设置电机最大速度为500
  stepper2.setSpeed(300);      // 初始化电机速度为300
  
  Serial.begin(9600);
  Serial.println(F("++++++++++++++++++++++++++++++++++"));     
  Serial.println(F("+         Stepper Trying         +"));   
  Serial.println(F("+                                +"));  
  Serial.println(F("++++++++++++++++++++++++++++++++++"));  
  Serial.println(F(""));  
  Serial.println(F("Please input motor command:"));   
}
 
void loop()
{  
   if (Serial.available()) {     //返回串口缓冲区中当前剩余的字符个数,检查串口缓存是否有数据等待传输 
   int data = Serial.parseInt();  
    
    Serial.print("Motor 'setSpeed' ");
    Serial.println(data);
    //设置步进电机转速
    stepper1.setSpeed(data);  
    stepper2.setSpeed(data);    
  }
  
   stepper1.runSpeed();
   stepper1.runSpeed();
//   Serial.print("步进电机1开始旋转，转速为: ");
//  Serial.println(data);

//    Serial.print("步进电机2开始旋转，转速为: ");
//   Serial.println(data);

   
}

//紧急中断
//  volatile int state = 0; 
//  void setup()
//  {
//  pinMode(13, OUTPUT);
//  attachInterrupt(0, blink, CHANGE);//当int.0电平改变时,触发中断函数led
//  } 
//  void loop()
//  {
//  digitalWrite(13, state);
//  } 
//  void led()//中断函数
//  {
//  state = !state;
//  }
