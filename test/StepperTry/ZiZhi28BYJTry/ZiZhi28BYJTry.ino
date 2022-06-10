#include <Stepper.h>
// 这里设置步进电机旋转一圈是多少步
#define STEPS 100
//设置步进电机的步数和引脚（就是注意点2里面说的驱动板上IN1～IN4连接的四个数字口）。
Stepper stepper(STEPS, 2, 4, 3, 5);//引脚要互换
String comdata="";
void setup()
{
  // 设置电机的转速：每分钟为90步
  stepper.setSpeed(90);
  // 初始化串口，用于调试输出信息
  Serial.begin(9600);
}

void loop()
{

    if (comdata.length() > 0)
  {
    Serial.println(comdata);
    
  }
  // check if the pushbutton is pressed.
  // if it is, the buttonState is HIGH:
  
  if (comdata.compareTo("H")==10){
    // turn LED on:
  
    Serial.println("电机顺时针转动半周");
    stepper.step(1024); //4步模式下旋转一周用2048 步。
//      stepper.run();没法测试，28电机不能用.run()启动
    delay(500); 
    
  }
  if (comdata.compareTo("J")==10){
    // turn LED on:
  
    Serial.println("电机顺时针转动半周");
    stepper.step(-1024); //4步模式下旋转一周用2048 步。
    delay(500); 
  }
  comdata = "";
//    // 逆时针旋转半周
//    Serial.println("ni");
//    stepper.step(-2048); //4步模式下旋转一周用2048 步。
//    delay(500);
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
