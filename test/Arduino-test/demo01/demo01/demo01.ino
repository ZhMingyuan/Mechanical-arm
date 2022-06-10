//String comdata="";
//本示例程序使用AccelStepper库
#include <AccelStepper.h>  //驱动步进电机头文件
#include <MultiStepper.h>  //驱动步进电机头文件


//***************************定义mega2560引脚*************************

//机械臂1轴(57步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL1_PIN 39 
#define DIR1_PIN 37

//机械臂2轴(57步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL2_PIN 43
#define DIR2_PIN 41

//机械臂3轴(42步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL3_PIN 47
#define DIR3_PIN 45

//机械臂4轴(28步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL4_PIN 46
#define DIR4_PIN 48

//5轴(28步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL5_PIN A6
#define DIR5_PIN A7

//机械臂6轴(20步进电机) 定义Mega2560输出脉冲和旋转方向的引脚
#define PUL6_PIN A0
#define DIR6_PIN A1

//定义机械臂123轴使能引脚 使用串联共用一个引脚
#define EN321_PIN 32 

//定义机械臂4轴使能引脚 
#define EN4_PIN A8

//定义机械臂5轴使能引脚 
#define EN5_PIN A2

//定义机械臂6轴使能引脚 
#define EN6_PIN 38


// ********************* 定义步进电机对象 *************************
// 电机步进方式定义 参数未使用
#define FULLSTEP 4 //全步进参数
#define HALFSTEP 8 //半步进参数


//AccelStepper函数 创建电机对象 
//参一:步进模式 参二:电机PUL脉冲引脚 
//参三:电机DIR旋转方向引脚
AccelStepper stepper1(1,39,37);
AccelStepper stepper2(1,43,41);
AccelStepper stepper3(1,47,45);
AccelStepper stepper4(1,46,48);
AccelStepper stepper5(1,A6,A7);
AccelStepper stepper6(1,A0,A1);


/* 电机初始化设置 */
void setup()
{

  Serial.begin(9600);  //设置串口通讯波特率
  pinMode(11, OUTPUT); //设置11号端口作为输出端口 可用于led或电磁铁
  digitalWrite(11,HIGH); //电磁铁通电可以吸住金属 HIGH表示电磁铁通电 LOW表示电磁铁不通电

  //***************** 定义mega2560步进电机引脚初始化参数 ***********************
  //321轴的使能使用同一个引脚
  pinMode(EN321_PIN, OUTPUT);  //使能引脚定义输出模式
  digitalWrite(EN321_PIN, LOW);  //关闭使能 低电平电机通电 高电平电机断电 
 
  pinMode(PUL1_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR1_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  digitalWrite(PUL1_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR1_PIN, LOW); //LOW逆时针旋转 HIGH顺时针旋转

  pinMode(PUL2_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR2_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  digitalWrite(PUL2_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR2_PIN, LOW); //LOW逆时针旋转 HIGH顺时针旋转

  pinMode(PUL3_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR3_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  digitalWrite(PUL3_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR3_PIN, LOW); //LOW逆时针旋转 HIGH顺时针旋转

  pinMode(PUL4_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR4_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  pinMode(EN4_PIN, OUTPUT);  //使能引脚定义输出模式
  digitalWrite(PUL4_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR4_PIN, LOW); //LOW逆时针旋转 HIGH顺时针旋转
  digitalWrite(EN4_PIN, LOW);  //关闭使能 低电平时可以驱动电机旋转 高电平不行 引脚如未定义则默认为低电平

  pinMode(PUL5_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR5_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  pinMode(EN5_PIN, OUTPUT);  //使能引脚定义输出模式
  digitalWrite(PUL5_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR5_PIN, LOW); //LOW逆时针旋转 HIGH顺时针旋转
  digitalWrite(EN5_PIN, LOW);  //关闭使能 低电平时可以驱动电机旋转 高电平不行 引脚如未定义则默认为低电平

  pinMode(PUL6_PIN, OUTPUT);  //脉冲引脚定义输出模式
  pinMode(DIR6_PIN, OUTPUT);  //旋转方向引脚定义输出模式
  pinMode(EN6_PIN, OUTPUT);  //使能引脚定义输出模式
  digitalWrite(PUL6_PIN, LOW); //一个高电平+一个低电平等一个脉冲 
  digitalWrite(DIR6_PIN, HIGH); //LOW逆时针旋转 HIGH顺时针旋转
  digitalWrite(EN6_PIN, LOW);  //关闭使能 低电平时可以驱动电机旋转 高电平不行 引脚如未定义则默认为低电平
  
  //电机最大速度 非匀速 配合run函数使用 最大值30000
  stepper1.setMaxSpeed(1500.0);  
  //电机加速度 非匀速 配合run函数使用 最大值30000
  stepper1.setAcceleration(30000.0);  
  
  stepper2.setMaxSpeed(1500.0); //推荐30000
  stepper2.setAcceleration(30000.0); //推荐30000
  
  stepper3.setMaxSpeed(1500.0); //推荐30000
  stepper3.setAcceleration(30000.0); //推荐30000

  stepper4.setMaxSpeed(1500.0); //推荐30000
  stepper4.setAcceleration(30000.0); //推荐30000

  stepper5.setMaxSpeed(1500.0); //推荐30000
  stepper5.setAcceleration(30000.0); //推荐30000
 
  stepper6.setMaxSpeed(1500.0); //推荐30000
  stepper6.setAcceleration(30000.0); //推荐30000
  
    //G指令暂时无法接收负数 通过设置电机1和电机6初始状态为非0状态 免于使用负数参数
  stepper1.setCurrentPosition(6400);  //setCurrentPosition 设置输出轴当前位置为指定步进值
  stepper2.setCurrentPosition(32);  //setCurrentPosition 设置输出轴当前位置为指定步进值
  stepper3.setCurrentPosition(32);  //setCurrentPosition 设置输出轴当前位置为指定步进值
  stepper4.setCurrentPosition(4500);
  stepper6.setCurrentPosition(6500);
  
}

void loop(){      
  
  if (Serial.available()>0){ //检查串口缓存是否有数据等待传输 

    int inChar = Serial.read(); //读取内存中的字符类型数据

//电机1
    //*********************  监听到A按下 驱动电机1正向匀速转动***********************
    
    if((char)inChar == 'A'){ //代码未定
     Serial.println("驱动电机1正向匀速转动!");  
     stepper1.setSpeed(900);
     delay(100);
    }//to if ((char)inChar == 'A')

    //*********************  监听到D按下 驱动电机1反向匀速转动***********************
    
    if((char)inChar == 'D'){ //代码未定
     Serial.println("驱动电机1反向匀速转动!");  
     stepper1.setSpeed(-900);
     delay(100);
    }//to if ((char)inChar == 'D')
  delay(100);//stepper1 end  

    
  //电机2
    //*********************  监听到W按下 驱动电机2正向匀速转动***********************
    
    if((char)inChar == 'W'){ //
     Serial.println("驱动电机2正向匀速转动!");  
     stepper2.setSpeed(550);
     delay(100);
    }//to if ((char)inChar == 'W')

    //*********************  监听到S按下 驱动电机2反向匀速转动***********************
    
    if((char)inChar == 'S'){ //
     Serial.println("驱动电机2反向匀速转动!");  
     stepper2.setSpeed(-550);
     delay(100);
    }//to if ((char)inChar == 'S')    
  delay(100);//stepper2 end  

  //电机3
    //*********************  监听到Q按下 驱动电机3正向匀速转动***********************
    
    if((char)inChar == 'Q'){ //
     Serial.println("驱动电机3正向匀速转动!");  
     stepper3.setSpeed(800);
     delay(100);
    }//to if ((char)inChar == 'Q')

    //*********************  监听到E按下 驱动电机3反向匀速转动***********************
    
    if((char)inChar == 'E'){ //
     Serial.println("驱动电机3反向匀速转动!");  
     stepper3.setSpeed(-800);
     delay(100);
    }//to if ((char)inChar == 'E')    
  delay(100);//stepper3 end

  //电机4
    //*********************  监听到I按下 驱动电机4正向匀速转动***********************
    
    if((char)inChar == 'I'){ //
     Serial.println("驱动电机4正向匀速转动!");  
     stepper4.setSpeed(200);
     delay(100);
    }//to if ((char)inChar == 'I')

    //*********************  监听到K按下 驱动电机4反向匀速转动***********************
    
    if((char)inChar == 'K'){ //
     Serial.println("驱动电机4反向匀速转动!");  
     stepper4.setSpeed(-200);
     delay(100);
    }//to if ((char)inChar == 'K')    
  delay(100);//stepper4 end


  //电机5
    //*********************  监听到J按下 驱动电机5正向匀速转动***********************
    
    if((char)inChar == 'J'){ //
     Serial.println("驱动电机5正向匀速转动!");  
     stepper5.setSpeed(200);
     delay(100);
    }//to if ((char)inChar == 'J')

    //*********************  监听到L按下 驱动电机5反向匀速转动***********************
    
    if((char)inChar == 'L'){ //
     Serial.println("驱动电机5反向匀速转动!");  
     stepper5.setSpeed(-200);
     delay(100);
    }//to if ((char)inChar == 'L')    
  delay(100);//stepper5 end

  //电机6
    //*********************  监听到U按下 驱动电机6正向匀速转动***********************
    
    if((char)inChar == 'U'){ //
     Serial.println("驱动电机6正向匀速转动!");  
     stepper6.setSpeed(200);
     delay(100);
    }//to if ((char)inChar == 'U')

    //*********************  监听到O按下 驱动电机6反向匀速转动***********************
    
    if((char)inChar == 'O'){ //
     Serial.println("驱动电机6反向匀速转动!");  
     stepper6.setSpeed(-200);
     delay(100);
    }//to if ((char)inChar == 'O')    
  delay(100);//stepper6 end


//  四大功能 启动Y 暂停B 终止H 复位R 查看电机位置P 保存预设文件C

//**********************  机械臂暂停  ***********************
  if((char)inChar == 'B'){  
      Serial.println(F("stop 机械臂暂停..."));
      stepper1.stop();
      stepper2.stop();
      stepper3.stop();
      stepper4.stop();
      stepper5.stop();
      stepper6.stop();
      stepper1.setSpeed(0);
      stepper2.setSpeed(0);
      stepper3.setSpeed(0);
      stepper4.setSpeed(0);
      stepper5.setSpeed(0);
      stepper6.setSpeed(0);
      Serial.println(F("-------- -------- -------- -------- -------->"));
    }

    if((char)inChar == 'M'){  
      Serial.println(F(" 机械臂目标..."));
      stepper1.targetPosition();
      stepper2.targetPosition();
      stepper3.targetPosition();
      stepper4.targetPosition();
      stepper5.targetPosition();
      stepper6.targetPosition();
      Serial.println(F("-------- -------- -------- -------- -------->"));
    }
      
    if((char)inChar == 'R'){  
      Serial.println(F("reset 机械臂复位..."));
      stepper1.runToNewPosition (6400);  //打印电机当前步进值0);
      
      stepper2.runToNewPosition (32);
      
      stepper3.runToNewPosition (32);
      
      stepper4.moveTo(4500);
      stepper5.moveTo(0);
      stepper6.moveTo(6500); 
      Serial.println(F("-------- -------- -------- -------- -------->"));
    }//to ((char)inChar == 'R'){

    //********************** 打印所有电机当前步进值 ***********************
    if((char)inChar == 'P'){ 
      //调用查看当前所有步进电机步进值函数
      Position();
    }//to if((char)inChar == 'P')


    //**********************  打印所有电机当前步进值 用于保存指令命令到本地文件 ***********************
    if((char)inChar == 'C'){ 
      
      Serial.print(F("preservation")); //preservation表示保存电磁开启指令M>
      Serial.print(F(","));
      Serial.print(stepper1.currentPosition());  //打印电机当前步进值
      Serial.print(F(","));
      Serial.print(stepper2.currentPosition());  //打印电机当前步进值
      Serial.print(F(","));
      Serial.print(stepper3.currentPosition());  //打印电机当前步进值
      Serial.print(F(","));
      Serial.print(stepper4.currentPosition());  //打印电机当前步进值
      Serial.print(F(","));
      Serial.print(stepper5.currentPosition());  //打印电机当前步进值
      Serial.print(F(","));
      Serial.println(stepper6.currentPosition());  //打印电机当前步进值
      Serial.println(F("-------- -------- -------- -------- -------->"));
    }//to  if((char)inChar == 'C')

  }//to if(Serial.available()>0)

  stepper1.runSpeed();  //驱动步进电机按照指定速度匀速转动
  stepper2.runSpeed();
  stepper3.runSpeed();




}//for loop



//void serialEvent()
//{
//  comdata = "";
//  while(Serial.available())   //时刻读取硬件串口数据
//  {
//    comdata += char(Serial.read());
//    delay(2);
//  }
//  while (Serial.read() >= 0) {} //清除串口缓存
//}
//

void Position(){
      //当前所有电机步进值
      Serial.print(F("当前所有电机步进值:")); //F()符串会放到flash memory中 不会占用SRAM(动态内存)
      Serial.print("机械臂第1轴： ");Serial.print(stepper1.currentPosition()); //打印电机当前步进值
      Serial.print(",机械臂第2轴： ");Serial.print(stepper2.currentPosition());
      Serial.print(",机械臂第3轴： ");Serial.print(stepper3.currentPosition());
      Serial.print(",机械臂第4轴： ");Serial.print(stepper4.currentPosition());
      Serial.print(",机械臂第5轴： ");Serial.print(stepper5.currentPosition());
      Serial.print(",机械臂第6轴： ");Serial.println(stepper6.currentPosition());
      Serial.println(F("-------- -------- -------- -------- -------->"));
}
