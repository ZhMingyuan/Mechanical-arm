#include <SoftwareSerial.h>
/*
 * 使用arduino uno板做测试
 * 串口（0,1）作为PC连接uno通讯串口
 * 将串口4,5引脚使用软串口处理连接BLE蓝牙模块
 */
SoftwareSerial BT_Serial(4,5);// 使用软串口设置蓝牙连接串口
String str="";

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  BT_Serial.begin(9600);
  
}

void loop() {
  // put your main code here, to run repeatedly:
  
  //监控串口，将串口接收到的字符串传送给蓝牙模块
  //将蓝牙模块返回的内容串口打印到串口监视器进行显示
  str = "";
  while(Serial.available()>0)
  {
    str += char(Serial.read()); 
    delay(10);
  }
  if(str.length()>0)
  {
    Serial.print(F("命令行发送内容："));
    Serial.println(str);
    BT_Serial.print(str);
    delay(10);
    str = "";
    while(BT_Serial.available()>0)
    {
      str += char(BT_Serial.read()); 
      delay(10);
    }
    if(str.length()>0)
    {
      Serial.print(F("BT 返回内容："));
      Serial.println(BT_Serial.read());
    }
    else
    {
      Serial.println(F("BT 无返回内容："));
    }
  }
}
