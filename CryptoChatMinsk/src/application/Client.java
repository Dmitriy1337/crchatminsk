package application;

import java.net.Socket; 
import java.util.Scanner; 



import java.io.*; 
import java.math.BigInteger; 

/** 
* Класс-клиент чат-сервера. Работает в консоли. Командой с консоли shutdown посылаем сервер в оффлайн 
*/ 
public class Client implements Runnable { 
final Socket s; // это будет сокет для сервера 
final BufferedReader socketReader; // буферизированный читатель с сервера 
final BufferedWriter socketWriter; // буферизированный писатель на сервер 
final BufferedReader userInput; // буферизированный читатель пользовательского ввода с консоли 
OutputStream sout5; 
DataOutputStream out5; 
InputStream sin; 
DataInputStream in; 
int p; 
boolean kn =false; 
String fcouple = ""; 
String res = ""; 
String password = ""; 
int chislo = 0; 
int shetchik = 0; 
int mcount = 1; 
int massiv[] =new int[12]; 
BufferedReader line; 
int kascii[]; 
int j; 
String userString= ""; 
String mch =""; 
String userLine; 
private int usmesh = (int)'A';//смещение алфавита относительно таблицы юникодов
private int smesh = (int)'a';//смещение алфавита относительно таблицы юникодов
/** 
* Конструктор объекта клиента 
* @param host - IP адрес или localhost или доменное имя 
* @param port - порт, на котором висит сервер 
* @throws java.io.IOException - если не смогли приконнектиться, кидается исключение, чтобы 
* предотвратить создание объекта 
*/ 
public Client(String host, int port) throws IOException { 
s = new Socket(host, port); // создаем сокет 
// создаем читателя и писателя в сокет с дефолной кодировкой UTF-8 
socketReader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")); 
socketWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8")); 
// создаем читателя с консоли (от пользователя) 
userInput = new BufferedReader(new InputStreamReader(System.in)); 
new Thread(new Receiver()).start(); 
line = new BufferedReader(new InputStreamReader(System.in)); 
sout5 = s.getOutputStream(); 
out5= new DataOutputStream(sout5); 
//new Thread(new Receiver()).start();// создаем и запускаем нить асинхронного чтения из сокета 
} 

/** 
* метод, где происходит главный цикл чтения сообщений с консоли и отправки на сервер 
*/ 

public void df(){ 


try { 


InputStream sin1 = s.getInputStream(); 
DataInputStream in1 = new DataInputStream(sin1); 
InputStream sin2 = s.getInputStream(); 
DataInputStream in2 = new DataInputStream(sin2); 
OutputStream sout1 = s.getOutputStream(); 
DataOutputStream out1 = new DataOutputStream(sout1); 
InputStream sin3 = s.getInputStream(); 
DataInputStream in3 = new DataInputStream(sin3); 
int g = in1.readInt();//Принимаем пересланные числа 
int p = in2.readInt(); 
String A = in3.readUTF(); 
//System.out.println(g); 
//System.out.println(p); 
//System.out.println(A); 
int b = (int)(Math.random()*199999+1000); 
long g1= g; 
long p1 = p; 
BigInteger p2 = BigInteger.valueOf(p1); 
int A1 = Integer.parseInt(A); 
long A2 = A1; 
BigInteger B = BigInteger.valueOf(g1).pow(b).mod(p2); //Генерируем В 
BigInteger K= BigInteger.valueOf(A2).pow(b).mod(p2);//Создаем общее число, оно идентично числу на сервере 
String B1 = B.toString(); 
out1.writeUTF(B1);//Отправляем число В для создания общего числа на сервере 
//out1.close(); 
//System.out.println(K); 
K= K.pow(11); 
password = K.toString(); 

for(int i = 1;i<password.length();i++){ 
char char1 = password.charAt(i); 

// System.out.println(char1); 

fcouple = fcouple + char1; 
if(i%4==0){ 


for(int z = 0;z<4;z++){ 
char bc = (fcouple.charAt(z)); 

chislo+=bc-48; 
//massiv[shetchik] = chislo; 

//System.out.println(bc); 
//shetchik++; 
//System.out.print(chislo); 
shetchik+=chislo; 

//shetchik = 0; 
chislo = 0; 

} 


//System.out.println(shetchik); 
//System.out.println(fcouple); 
massiv[mcount] =shetchik; 
if(mcount<=10){mcount++;} 

fcouple =""; 
shetchik = 0; 

} 

} 
int pres = 0; 
for(int sh = 1;sh<12;sh++){ 

pres =(massiv[sh]+92); 
if(pres>=91&&pres<97){ 
pres+=6; 

} 
else if(pres>122){ 
pres=pres-26; 
} 
System.out.println(pres); 
res = (res +
(char)pres);// Получаем общий ключ 

} 
kn = true; 
System.out.println(res); 
} catch (IOException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 

} 


public String encrypt(String text, String keyWord)
{
    StringBuilder ans = new StringBuilder();
    for(int i = 0; i < text.length();i++)
    {
        int num = ((text.charAt(i) + keyWord.charAt(i % keyWord.length()) - 2 * smesh) % 26);
        //в num лежит номер буквы в алфавите
     char c;
     System.out.print(text.charAt(i)+"/"+num+"//");

     if(text.charAt(i)>96){
    	  c = (char)(num + smesh);
      }
      else{
    	   c = (char)(num + usmesh);
      } 
//      char c = (char)(num + smesh);//получаем нужный символ
        ans.append(c);
    }
    return ans.toString();
}


String shifr( String userString ){ 

kascii = new int[res.length()]; 


for(int a = 0;a<res.length();a++){ 

kascii[a] = (int)res.charAt(a); 

}//перевод ключа в ascii код 
for (int c = 0; c <userString.length();c++){ 

j = c%kascii.length; 
if(userString.charAt(c)==32){ 
int shmessage = 32; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==44){ 
int shmessage = 44; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==46){ 
int shmessage = 46; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==63){ 
int shmessage = 63; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==33){ 
int shmessage = 33; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==96){ 
int shmessage = 96; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 


if((userString.charAt(c)>=65)&(userString.charAt(c)<=90)){ 
int shmessage = (userString.charAt(c)+kascii[j]-97)%(26+96); 


if(shmessage<=96){ 
shmessage= shmessage +97 ; 

} 
if(shmessage>122){ 
shmessage = shmessage-70;//%121+96; 
} 
shmessage = shmessage+32; 
System.out.println(shmessage); 
mch = mch + (char)( shmessage); 


} 

if(userString.charAt(c)>96){ 
int shmessage = (userString.charAt(c)+kascii[j]-97)%(26+96); 


if(shmessage<=96){ 
shmessage= shmessage +97 ; 
} 
if(shmessage>122){ 
shmessage = shmessage-70;//%121+96; 
} 

mch = mch + (char)( shmessage); 



}} 
userString = ""; 
return mch; 
} 








public void run() { 
df(); 
System.out.println("Type phrase(s) (hit Enter to exit):"); 
while (true) { 
String userString = null; 
String us1 = null; 
try { 
userString = userInput.readLine(); // читаем строку от пользователя 
us1 = encrypt(userString,res); 
mch = "";
} catch (IOException ignored) {} // с консоли эксепшена не может быть в принципе, игнорируем 
//если что-то не так или пользователь просто нажал Enter... 
if (userString == null || userString.length() == 0 || s.isClosed()) { 
close(); // ...закрываем коннект. 
break; // до этого break мы не дойдем, но стоит он, чтобы компилятор не ругался 
} else { //...иначе... 
try { 
socketWriter.write(us1); //пишем строку пользователя 
socketWriter.write("\n"); //добавляем "новою строку", дабы readLine() сервера сработал 
socketWriter.flush(); // отправляем 
} catch (IOException e) { 
close(); // в любой ошибке - закрываем. 
} 
} 
} 
} 

public synchronized void close() {//метод синхронизирован, чтобы исключить двойное закрытие. 
if (!s.isClosed()) { // проверяем, что сокет не закрыт... 
try { 
s.close(); // закрываем... 
System.exit(0); // выходим! 
} catch (IOException ignored) { 
ignored.printStackTrace(); 
} 
} 
} 




public static void main(String[] args) { // входная точка программы 
try { 
new Client("localhost", 45536).run(); // Пробуем приконнетиться... 
} catch (IOException e) { // если объект не создан... 
System.out.println("Unable to connect. Server not running?"); // сообщаем... 
} 
} 
private class Receiver implements Runnable{ 
/** 
* run() вызовется после запуска нити из конструктора клиента чата. 
*/ 
//String line =""; 
public void run() { 

while (!s.isClosed()) { //сходу проверяем коннект. 
if(kn==true){ 
String line = null; 
try { 
line = socketReader.readLine(); // пробуем прочесть 
} catch (IOException e) { // если в момент чтения ошибка, то... 
// проверим, что это не банальное штатное закрытие сокета сервером 
if ("Socket closed".equals(e.getMessage())) { 
break; 
} 
System.out.println("Connection lost"); // а сюда мы попадем в случае ошибок сети. 
close(); // ну и закрываем сокет (кстати, вызвается метод класса ChatClient, есть доступ) 
} 
if (line ==
null) { // строка будет null если сервер прикрыл коннект по своей инициативе, сеть работает 
System.out.println("Server has closed connection"); 
close(); // ...закрываемся 
} 
else { // иначе печатаем то, что прислал сервер. 
System.out.println("Server:" + decrypt(line,res)); 
} 
} 
} 
} 
} 

public String decrypt(String shifr, String keyWord)
{
    StringBuilder ans = new StringBuilder();
    for(int i = 0; i < shifr.length();i++)
    {
        int num = ((shifr.charAt(i)  - keyWord.charAt(i % keyWord.length()) + 26) % 26);
        //обратные преобразования с номером буквы в алфавите
        char c;
       
        System.out.print(shifr.charAt(i)+"/"+num+"//");
        if(shifr.charAt(i)>96){
         c = (char)(num + smesh);
       }
       else{
    	   c = (char)(num + usmesh);   
    	   
       }
        ans.append(c);
    }
    return ans.toString();
}
}