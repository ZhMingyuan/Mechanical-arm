void setup() {
  // put your setup code here, to run once:
  //初始化串口
  Serial.begin(9600);

}

void loop() {
  // 串口打印Hello Arduino
  Serial.println("Hello Arduino !");
  delay(1000);//每1000毫秒，1秒打印一次

}
