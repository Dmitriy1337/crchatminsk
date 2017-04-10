package application;

import java.net.ServerSocket; 

import java.net.Socket; 
import java.io.*; 
import java.math.BigInteger; 
import java.util.concurrent.BlockingQueue; 
import java.util.concurrent.LinkedBlockingQueue; 







/** 
* ����� �������. ����� ���� �� �����, ��������� ���������, ������� SocketProcessor �� ������ ��������� 
*/ 
public class Server { 
boolean sr = false; 
BufferedReader UL; 
Socket s; 
private ServerSocket ss; // ��� ������-����� 
private Thread serverThread; // ������� ���� ��������� ������-������ 
private int port; // ���� ������ ������. 
//�������, ��� ��������� ��� SocketProcessor� ��� �������� 
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
* ����������� ������� ������� 
* @param port ����, ��� ����� ������� �������� ���������. 
* @throws IOException ���� �� �������� ������� ������-�����, ������� �� ���������, ������ ������� �� ����� ������ 
*/ 
public Server(int port) throws IOException { 
ss = new ServerSocket(port); // ������� ������-����� 
this.port = port; // ��������� ����. 
line = new BufferedReader(new InputStreamReader(System.in)); 
} 

/** 
* ������� ���� �������������/�������� ��������. 
*/ 






void run() { 
serverThread = Thread.currentThread(); // �� ������ ��������� ���� (����� ����� �� ���� interrupt()) 
while (true) { //����������� ����, ����... 
Socket s = getNewConn(); // �������� ����� ���������� ��� ����-��������� 
if (serverThread.isInterrupted()) { // ���� ��� ����-����������, �� ���� ���� ���� interrupted(), 
// ���� ���������� 
break; 
} else if (s != null){ // "������ ���� ������� ������� ������"... 
try { 
final SocketProcessor processor = new SocketProcessor(s); // ������� �����-��������� 
final Thread thread = new Thread(processor); // ������� ��������� ����������� ���� ������ �� ������ 
thread.setDaemon(true); //������ �� � ������ (����� �� ������� �� ��������) 
thread.start(); //��������� 
q.offer(processor); //��������� � ������ �������� �����-����������� 
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


int a = (int)(Math.random()*199999+1000);//���������� 3 ����� 
int g = (int)(Math.random()*99999999+10000000); 
int p = (int)(Math.random()*99999999+10000000); 
long g1 = g; 
long p1 = p; 
BigInteger p2 = BigInteger.valueOf(p1); 
BigInteger A = BigInteger.valueOf(g1).pow(a).mod(p2);//�� ������ ���� ����� ������� ����� � 
String A1= A.toString(); 
out1.writeInt(g);//���������� ��� ����� � ���� � ������� 
out2.writeInt(p); 
out3.writeUTF(A1); 

String B = in1.readUTF();//���������� � ������� � 
int B1 = Integer.parseInt(B); 
long B2 = B1; 


BigInteger K = BigInteger.valueOf(B2).pow(a).mod(p2);//�������� ����������� ����� � ��� �������������� � ���� 

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
res = (res + (char)pres);//���������� ���� 

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
* ������� ����� �����������. 
* @return ����� ������ ����������� 
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

}//������� ����� � ascii ��� 
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
// ������������ ������ ������� ���������, ��������� ������ 
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
* ������� ����� ��������� 
* @param args 
* @throws IOException 
*/ 
public static void main(String[] args) throws IOException { 
new Server(45536).run(); // ���� ������ �� ��������, ��������� 
// ������� �� ���������, � ����� run() �� ����������� 
} 

/** 
* ��������� ����� ����������� ��������� ������ ��������. 
*/ 
private
class SocketProcessor implements Runnable{ 
Socket s; // ��� ����� 
BufferedReader br; // ��������������� �������� ������ 
BufferedWriter bw; // ���������������� �������� � ����� 


/** 
* ��������� �����, ������� ������� �������� � ��������. ���� �� ���������� - �������� ��� �������� ������� 
* @param socketParam ����� 
* @throws IOException ���� ������ � �������� br || bw 
*/ 
SocketProcessor(Socket socketParam) throws IOException { 
s = socketParam; 
br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")); 
bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8") ); 
UL = new BufferedReader(new InputStreamReader(System.in)); 

} 

/** 
* ������� ���� ������ ���������/�������� 
*/ 
public void run() { 

while (!s.isClosed()) { // ���� ����� �� ������... 
if(sr==true){ 
String line = null; 
try { 
line = br.readLine(); // ������� ��������. 
System.out.println(line); 

} catch (IOException e) { 
close(); // ���� �� ���������� 
//- ��������� �����. 
} 

if (line == null) { // ���� ������ null - ������ ���������� � ������� ������. 
close(); // �� ��������� ����� 
} else if ("shutdown".equals(line)) { // ���� ��������� ������� "�������� ������", ��... 
serverThread.interrupt(); // ������� �������� ���� � �������� ���� � ������������� ����������. 
try { 
new Socket("localhost", port); // ������� ����-������� (����� ����� �� .accept()) 
} catch (IOException ignored) { //������ ����������� 
} finally { 
shutdownServer(); // � ����� ������ ������ ������� ��� ������ shutdownServer(). 
} 
} else { // ����� - ��������� �������� �� ������ �����-����������� 
for (SocketProcessor sp:q) { 
sp.send(us2); 
} 
} 
} 
} 
} 
public synchronized void close() { 
q.remove(this); //������� �� ������ 
if (!s.isClosed()) { 
try { 
s.close(); // ��������� 
} catch (IOException ignored) {} 
} 
} 
public synchronized void send( String us2) { 
try { 

Usersline = UL.readLine(); 
us2 = shifr(Usersline); 
mch = "";
bw.write(us2); // ����� ������ 
bw.write("\n"); // ����� ������� ������ 
bw.flush(); // ���������� 
} catch (IOException e) { 
close(); //���� ���� � ������ �������� - ��������� ������ �����. 
} 
} 

} 
}