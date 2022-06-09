//这个完全错误的代码可以证明为什么不能在if里写中断
String val="";//接收串口发送过来的值
int led = 13;//13号引脚为Arduino板载的LED

void setup() {
  Serial.begin(9600);//设置波特率
  Serial.println("请输入你要发送的数据:");//提示字符
  pinMode(led,OUTPUT);//设置led管脚为输出模式
}

void loop() {
  while(Serial.available()>0){//检验在串口缓存区中是否有数据，如果有则返回1，没有就是0.
    val += char(Serial.read());//Serial.read()一位一位的从串口缓存区里读数据
    delay(20);
  }
    //读取电脑传来的数据
     if(val.length()>0){
        Serial.println("val:"+val);
     
     if(val.compareTo("open")>0){//如果发送过来的指令是“open”，则打开LED
       digitalWrite(led,HIGH);
       Serial.println("LED已经点亮！");
       delay(10000);//led持续亮1秒
     }
     
     if(val.compareTo("close")>0){//如果发送过来的指令是“close”，则关闭LED
       digitalWrite(led,LOW);
       Serial.println("LED已经熄灭！");
     }
   }
   val = "";//清空，重新读取
}

  
