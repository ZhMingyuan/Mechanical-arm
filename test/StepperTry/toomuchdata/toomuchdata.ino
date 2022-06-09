String comdata = "";

void setup() {
  // 初始化串口程序
  Serial.begin(9600);

}
void loop() {

  if (comdata.length() > 0)
  {
    Serial.println(comdata);
    comdata = "";
  }
}

void serialEvent()
{
  comdata = "";
  while (Serial.available())   //时刻读取硬件串口数据
  {
    comdata += char(Serial.read());
    delay(2);
  }
  while (Serial.read() >= 0) {} //清除串口缓存
}
//这是一个等待循环，当缓冲区没数据的时候Serial.read() 返回-1，
//串口缓冲区中最多能缓冲128个字节
