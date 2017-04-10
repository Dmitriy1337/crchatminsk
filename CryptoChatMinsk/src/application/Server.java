package application;

import java.net.ServerSocket; 

import java.net.Socket; 
import java.io.*; 
import java.math.BigInteger; 
import java.util.concurrent.BlockingQueue; 
import java.util.concurrent.LinkedBlockingQueue; 







/** 
* Класс сервера. Сидит тихо на порту, принимает сообщение, создает SocketProcessor на каждое сообщение 
*/ 
public class Server { 
boolean sr = false; 
BufferedReader UL; 
Socket s; 
private ServerSocket ss; // сам сервер-сокет 
private Thread serverThread; // главная нить обработки сервер-сокета 
private int port; // порт сервер сокета. 
//очередь, где храняться все SocketProcessorы для рассылки 
BlockingQueue<SocketProcessor> q = new LinkedBlockingQueue<SocketProcessor>(); 
String fcouple = ""; 
String res = ""; 
String password = ""; 
String Usersline; 
String us2; 
int chislo = 0; 
int shetchik = 0; 
int mcount = 1; 
int massiv[] =new int[12]; 
InputStream sin1; 
DataInputStream in1; 
BufferedReader line; 
int kascii[]; 
int j; 
String userString=""; 
String mch=""; 
String decode =""; 
String userLine; 
/** 
* Конструктор объекта сервера 
* @param port Порт, где будем слушать входящие сообщения. 
* @throws IOException Если не удасться создать сервер-сокет, вылетит по эксепшену, объект Сервера не будет создан 
*/ 
public Server(int port) throws IOException { 
ss = new ServerSocket(port); // создаем сервер-сокет 
this.port = port; // сохраняем порт. 
line = new BufferedReader(new InputStreamReader(System.in)); 
} 

/** 
* главный цикл прослушивания/ожидания коннекта. 
*/ 






void run() { 
serverThread = Thread.currentThread(); // со старта сохраняем нить (чтобы можно ее было interrupt()) 
while (true) { //бесконечный цикл, типа... 
Socket s = getNewConn(); // получить новое соединение или фейк-соедиение 
if (serverThread.isInterrupted()) { // если это фейк-соединение, то наша нить была interrupted(), 
// надо прерваться 
break; 
} else if (s != null){ // "только если коннект успешно создан"... 
try { 
final SocketProcessor processor = new SocketProcessor(s); // создаем сокет-процессор 
final Thread thread = new Thread(processor); // создаем отдельную асинхронную нить чтения из сокета 
thread.setDaemon(true); //ставим ее в демона (чтобы не ожидать ее закрытия) 
thread.start(); //запускаем 
q.offer(processor); //добавляем в список активных сокет-процессоров 
//OutputStream sout = s.getOutputStream(); 
//DataOutputStream out = new DataOutputStream(sout); 



try { 

InputStream sin1 = s.getInputStream(); 
DataInputStream in1 = new DataInputStream(sin1); 
OutputStream sout1 = s.getOutputStream(); 
OutputStream sout2 = s.getOutputStream(); 
OutputStream sout3 = s.getOutputStream(); 
DataOutputStream out1 = new DataOutputStream(sout1); 
DataOutputStream out2 = new DataOutputStream(sout2); 
DataOutputStream out3 = new DataOutputStream(sout3); 


int a = (int)(Math.random()*199999+1000);//Генерируем 3 числа 
int g = (int)(Math.random()*99999999+10000000); 
int p = (int)(Math.random()*99999999+10000000); 
long g1 = g; 
long p1 = p; 
BigInteger p2 = BigInteger.valueOf(p1); 
BigInteger A = BigInteger.valueOf(g1).pow(a).mod(p2);//На основе трех чисел создаем число А 
String A1= A.toString(); 
out1.writeInt(g);//Отправляем два числа и само А клиенту 
out2.writeInt(p); 
out3.writeUTF(A1); 

String B = in1.readUTF();//Полученное в клиенте В 
int B1 = Integer.parseInt(B); 
long B2 = B1; 


BigInteger K = BigInteger.valueOf(B2).pow(a).mod(p2);//Создание одинакового числа и его преобразования в ключ 

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

shetchik+=chislo; 


chislo = 0; 

} 


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
res = (res + (char)pres);//Полученный ключ 

} 
sr = true; 
System.out.println(res); 
} catch (IOException e1) { 
// TODO Auto-generated catch block 
e1.printStackTrace(); 
} 
} 

catch (IOException ignored) {} 
} 
} 
} 

/** 
* Ожидает новое подключение. 
* @return Сокет нового подключения 
*/ 
private Socket getNewConn() { 
Socket s = null; 
try { 
s = ss.accept(); 
} catch (IOException e) { 

} 
return s; 
} 

String shifr(String userString){ 

kascii = new int[res.length()]; 


for(int a = 0;a<res.length();a++){ 

kascii[a] = (int)res.charAt(a); 

}//перевод ключа в ascii код 
for (int c = 0; c <userString.length();c++){ 

j = c%kascii.length; 
if(userString.charAt(c)==32){ 
int shmessage = 32; 


mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==44){ 
int shmessage = 44; 


mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==46){ 
int shmessage = 46; 


mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==63){ 
int shmessage = 63; 
System.out.println(shmessage); 

mch = mch + (char)( shmessage); 
} 
if(userString.charAt(c)==33){ 
int shmessage = 33; 


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

return mch; 

} 

public void decode(){ 
String kk = mch; 


for (int c = 0; c <kk.length();c++){ 

j = c%kascii.length; 

if(kk.charAt(c)==32){ 
int shmessage = 32; 


decode = decode + (char)( shmessage); 
} 
if(kk.charAt(c)==44){ 
int shmessage = 44; 


decode = decode + (char)( shmessage); 
} 
if(kk.charAt(c)==46){ 
int shmessage = 46; 


decode = decode + (char)( shmessage); 
} 
if(kk.charAt(c)==63){ 
int shmessage = 63; 


decode = decode + (char)( shmessage); 
} 
if(kk.charAt(c)==33){ 
int shmessage = 33; 


decode = decode + (char)( shmessage); 
} 
if(kk.charAt(c)==96){ 
int shmessage = 96; 


decode = decode + (char)( shmessage); 
} 
//loppomxfkhj//wca//rst 


if((kk.charAt(c)>=65)&(kk.charAt(c)<90)){ 
int shmessage = (kk.charAt(c)- kascii[j]+66)%(26+65); 


if(shmessage<=65){ 
shmessage= shmessage +26; 



} 
if(shmessage>=90){ 
shmessage = shmessage%26+65; 
} 


decode = decode + (char)( shmessage); // 

} 
if(kk.charAt(c)>96){ 
int shmessage = (kk.charAt(c)-kascii[j]+97)%(26+96); 


if(shmessage<=96){ 
shmessage= shmessage +96 ; 
} 
if(shmessage>122){ 
shmessage = shmessage-71;//%121+96; 
} 

System.out.println(shmessage); 

decode = decode + (char)( shmessage); // 

} 


} 
} 


private synchronized void shutdownServer() { 
// обрабатываем список рабочих коннектов, закрываем каждый 
for (SocketProcessor s: q) { 
s.close(); 
} 
if (!ss.isClosed()) { 
try { 
ss.close(); 
} catch (IOException ignored) {} 
} 
} 



/** 
* входная точка программы 
* @param args 
* @throws IOException 
*/ 
public static void main(String[] args) throws IOException { 
new Server(45536).run(); // если сервер не создался, программа 
// вылетит по эксепшену, и метод run() не запуститься 
} 

/** 
* вложенный класс асинхронной обработки одного коннекта. 
*/ 
private
class SocketProcessor implements Runnable{ 
Socket s; // наш сокет 
BufferedReader br; // буферизировнный читатель сокета 
BufferedWriter bw; // буферизированный писатель в сокет 


/** 
* Сохраняем сокет, пробуем создать читателя и писателя. Если не получается - вылетаем без создания объекта 
* @param socketParam сокет 
* @throws IOException Если ошибка в создании br || bw 
*/ 
SocketProcessor(Socket socketParam) throws IOException { 
s = socketParam; 
br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")); 
bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8") ); 
UL = new BufferedReader(new InputStreamReader(System.in)); 

} 

/** 
* Главный цикл чтения сообщений/рассылки 
*/ 
public void run() { 

while (!s.isClosed()) { // пока сокет не закрыт... 
if(sr==true){ 
String line = null; 
try { 
line = br.readLine(); // пробуем прочесть. 
System.out.println(line); 

} catch (IOException e) { 
close(); // если не получилось 
//- закрываем сокет. 
} 

if (line == null) { // если строка null - клиент отключился в штатном режиме. 
close(); // то закрываем сокет 
} else if ("shutdown".equals(line)) { // если поступила команда "погасить сервер", то... 
serverThread.interrupt(); // сначала возводим флаг у северной нити о необходимости прерваться. 
try { 
new Socket("localhost", port); // создаем фейк-коннект (чтобы выйти из .accept()) 
} catch (IOException ignored) { //ошибки неинтересны 
} finally { 
shutdownServer(); // а затем глушим сервер вызовом его метода shutdownServer(). 
} 
} else { // иначе - банальная рассылка по списку сокет-процессоров 
for (SocketProcessor sp:q) { 
sp.send(us2); 
} 
} 
} 
} 
} 
public synchronized void close() { 
q.remove(this); //убираем из списка 
if (!s.isClosed()) { 
try { 
s.close(); // закрываем 
} catch (IOException ignored) {} 
} 
} 
public synchronized void send( String us2) { 
try { 

Usersline = UL.readLine(); 
us2 = shifr(Usersline); 
mch = "";
bw.write(us2); // пишем строку 
bw.write("\n"); // пишем перевод строки 
bw.flush(); // отправляем 
} catch (IOException e) { 
close(); //если глюк в момент отправки - закрываем данный сокет. 
} 
} 

} 
}