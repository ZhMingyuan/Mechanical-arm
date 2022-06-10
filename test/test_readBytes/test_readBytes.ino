char Fun[64];
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);//配置串口波特率为9600
}

void loop() {
  
    Serial.readBytes(Fun,64);
    delay(10);
    Serial.println("1");
    Serial.println(Fun);
    Serial.println(Fun[0]);
    //Serial.print(Fun);
    
      
    if(Fun[0] == 'r'){ //串口读取到reset 执行复位功能 ???     
      Serial.println(F("reset 机械臂复位..."));
    
      Serial.println(F("-------- -------- -------- -------- -------->"));
    }//to if(cmd == 'reset')
   
}
