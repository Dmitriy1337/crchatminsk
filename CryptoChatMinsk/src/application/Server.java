package application;
import java.net.InetAddress;
import java.net.ServerSocket; 

import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*; 
import java.math.BigInteger; 
import java.util.concurrent.BlockingQueue; 
import java.util.concurrent.LinkedBlockingQueue;


import com.mysql.jdbc.Driver;
import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;






/** 
* Eeann na?aa?a. Neaeo oeoi ia ii?oo, i?eieiaao niiauaiea, nicaaao SocketProcessor ia ea?aia niiauaiea 
*/ 
public class Server extends Application{ 
	InetAddress	ip1;
	static Stage classStage = new Stage();
	public static Connection conn;
	 int smesh = (int)'a';//niauaiea aeoaaeoa ioiineoaeuii oaaeeou ?ieeiaia
	 private int usmesh = (int)'A';
	 boolean sr = false; 
BufferedReader UL; 
Socket s; 
private ServerSocket ss; // nai na?aa?-nieao 
private Thread serverThread; // aeaaiay ieou ia?aaioee na?aa?-nieaoa 
private int port; // ii?o na?aa? nieaoa. 
//i?a?aau, aaa o?aiyouny ana SocketProcessoru aey ?annueee 
BlockingQueue<SocketProcessor> q = new LinkedBlockingQueue<SocketProcessor>(); 
String ipAdress;
String fcouple = ""; 
String res = ""; 
String password = ""; 
String Usersline; 
String us2; 
int chislo = 0; 
int shetchik = 0; 
int mcount = 1; 
int massiv[] =new int[12]; 
TextArea dlog,ulog,enter;
InputStream sin1; 
DataInputStream in1; 
BufferedReader line; 
int kascii[]; 
int j; 
Statement stat3 = null; 
ResultSet rs1 =null;
String userString=""; 
String mch=""; 
String decode =""; 
String userLine; 
ImageView bg;
TextField search;
ImageView sres;
Scene sc2;
AnchorPane ap2;
TextArea chat;
Button con;
TextArea write;
Button send;
Button find;
int k = 0;
Label lname;
Stage st1;
/** 
* Eiino?oeoi? iauaeoa na?aa?a 
* @param port Ii?o, aaa aoaai neooaou aoiayuea niiauaiey. 
* @throws IOException Anee ia oaanouny nicaaou na?aa?-nieao, aueaoeo ii yenaioaio, iauaeo Na?aa?a ia aoaao nicaai 
*/ 

/** 
* aeaaiue oeee i?ineooeaaiey/i?eaaiey eiiiaeoa. 
*/ 

AnchorPane ap3;
Scene sc3;

public void start(Stage st) throws Exception {
	
	st1 = new Stage();
	Server.classStage = st;
	ap2 = new AnchorPane();
	 sc2= new Scene(ap2,700,500);
	 ap3 = new AnchorPane();
	 sc3= new Scene(ap3,700,500);
	 ImageView bg= new ImageView("img/bckground.png");
			bg.setLayoutX(0);
			bg.setLayoutX(0);
			ap2.getChildren().add(bg);
		
			
			search = new TextField();
			search.setLayoutX(260);
			search.setLayoutY(185);
			search.setPrefWidth(350);
			search.setPrefHeight(18);
			ap2.getChildren().add(search);
		
	
			
			
			
			
			MenuBar mb = new MenuBar();
	 Menu crchat = new Menu("CryptoChat");
	 MenuItem safety = new MenuItem("Safety");
	MenuItem server = new MenuItem("Connection");
	crchat.getItems().addAll(safety,server);
	Menu user = new Menu("User");
	MenuItem pd = new MenuItem("Personal Info");	
	user.getItems().addAll(pd);
	Menu about = new Menu("About us");
	MenuItem ap = new MenuItem("About CryptoChat");
	MenuItem au = new MenuItem("Authors");
	about.getItems().addAll(ap,au);		
	
	
	
	safety.setOnAction(a1->{
		Stage stage = new Stage();
	    AnchorPane root2= new AnchorPane();
	     Scene scene2 = new Scene(root2,250,350);
	     stage.setTitle("Safety");
	     ImageView fon = new ImageView("img/safety.png");
	     fon.setLayoutX(0);
	     fon.setLayoutY(0);
	     root2.getChildren().add(fon);
	     
	     
	     stage.setScene(scene2);
	       stage.show();
		
		
	});
	
	server.setOnAction(a2->{
		Stage stage = new Stage();
	    AnchorPane root2= new AnchorPane();
	     Scene scene2 = new Scene(root2,250,350);
	     stage.setTitle("Server");
	     ImageView fon = new ImageView("img/server.png");
	     fon.setLayoutX(0);
	     fon.setLayoutY(0);
	     root2.getChildren().add(fon);
	     String ip=null;
			try {
				ip1 = InetAddress.getByName(ip);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	     Label ipl=new Label(ip1.toString());
	     ipl.setLayoutX(120);
	     ipl.setLayoutY(150);
	     ipl.setFont(new Font("Arial",14));
	     root2.getChildren().add(ipl);
	     stage.setScene(scene2);
	       stage.show();
		
		
	});
	ap.setOnAction(a2->{
		Stage stage = new Stage();
	    AnchorPane root2= new AnchorPane();
	     Scene scene2 = new Scene(root2,250,350);
	     stage.setTitle("About CryptoChat");
	     ImageView fon = new ImageView("img/ac.png");
	     fon.setLayoutX(0);
	     fon.setLayoutY(0);
	     root2.getChildren().add(fon);
	   
	     
	     stage.setScene(scene2);
	       stage.show();
		
		
	});
	au.setOnAction(a2->{
		Stage stage = new Stage();
	    AnchorPane root2= new AnchorPane();
	     Scene scene2 = new Scene(root2,250,350);
	     stage.setTitle("About CryptoChat");
	     ImageView fon = new ImageView("img/au.png");
	     fon.setLayoutX(0);
	     fon.setLayoutY(0);
	     root2.getChildren().add(fon);
	   
	     
	     stage.setScene(scene2);
	       stage.show();
		
		
	});
	
	mb.getMenus().addAll(crchat,user,about)	;
ap2.getChildren().add(mb);
		lname = new Label("");
		lname.setLayoutX(325);
		lname.setLayoutY(237);
		lname.setPrefWidth(130);
		lname.setPrefHeight(18);
		lname.setFont(new Font("Rockwell Condensed",22));
		ap2.getChildren().add(lname);
		
		
		
		Main mn = new Main();
	String elog = 	mn.getName();
	System.out.println(elog);
	st.setTitle("CryptoChat� - "+elog);	
		
	
	Label username= new Label();
	username.setLayoutX(40);
	username.setLayoutY(32);
	username.setFont(new Font("Rockwell Condensed",30));
	username.setText(mn.getNick());
	ap2.getChildren().add(username);
	
	
	con= new Button();
		con.setLayoutX(400);
		con.setLayoutY(390);
		con.setPrefWidth(120);
		con.setPrefHeight(40);
		con.setOpacity(0);
		con.setOnAction(conn1->{ 
			
			try {
				DriverManager.registerDriver(new Driver());
			 conn = DriverManager.getConnection( 
						"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
						System.out.println("connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			try {
				stat3 = conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs1= stat3.executeQuery("Select * from table2");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				while(rs1.next()){ 
					if(rs1.getString(3).equals(search.getText())&&!rs1.getString(3).equals(elog)){ 
					ipAdress=rs1.getString(4);
					Client cl = new Client();
					try {
						
						
						cl.start(Client.classStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						st.close();
					
					}}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
			
		});
		ap2.getChildren().add(con);
		
		//st.close();
		find= new Button();
		find.setLayoutX(220);
		find.setLayoutY(171);
		find.setPrefWidth(30);
		find.setPrefHeight(50);
		find.setOpacity(0);
		find.setOnAction(f->{
			
			try {
				DriverManager.registerDriver(new Driver());
			 conn = DriverManager.getConnection( 
						"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
						System.out.println("connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			try {
				stat3 = conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs1= stat3.executeQuery("Select * from table2");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				while(rs1.next()){ 
					if(rs1.getString(3).equals(search.getText())&&!rs1.getString(3).equals(elog)){ 
					 lname.setText(search.getText());
						
						//System.out.println(search.getText());	
					
					}}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		});
		ap2.getChildren().add(find);
		
		
		
		
		
	
		
		
		
		st.setScene(sc2);
	    st.show();
		new Thread(new SServer()).start();

}



/** 
* I?eaaao iiaia iiaee??aiea. 
* @return Nieao iiaiai iiaee??aiey 
*/ 
private Socket getNewConn() { 
Socket s = null; 
try { 
s = ss.accept(); 
windowGUI();
System.out.println("test");
} catch (IOException e) { 

} 
return s; 
} 

public String encrypt(String text, String keyWord)
{
	  
		StringBuilder ans = new StringBuilder();
		String letters = "";
		for(int i = 0;i<text.length();i++){
		   
		   if(text.charAt(i)<96){
			   letters = letters+1;
			    
		   }
		   else{
			   letters = letters+0;   
		   }
	   }
	  text.toLowerCase();
	   for(int i = 0; i < text.length();i++)
	    {
	       
	        //a num ea?eo iiia? aoeau a aeoaaeoa
	     char c=1;
	     System.out.println((int)text.charAt(i)+"/");
	     if(text.charAt(i)>=1040&&text.charAt(i)<1123){
		    System.out.println("sm+"+(int)smesh);	
	    	 smesh = '�';
		    }
		 else{
			 smesh = 'a';	 
		 }
	     if(text.charAt(i)>=32&&text.charAt(i)<64){
	    	c = text.charAt(i);
	    }
	    else{ 
	    int num = ((text.charAt(i) + keyWord.charAt(i % keyWord.length()) - 2 * smesh) % 26);
	   
	    	
	    	 c = (char)(num + smesh);
	    }
	     
//	      char c = (char)(num + smesh);//iieo?aai io?iue neiaie
	        ans.append(c);
	    }
	  String result = "";
	 String a = "";
	 char b; 
	 for(int i = 0;i<ans.toString().length();i++){
		if(letters.charAt(i)==49){
		//System.out.println("ssss");
		a+=ans.toString().charAt(i);
		a.toUpperCase();
		b = a.charAt(0);
		result+=b;
		a="";
		
		}   
		else{
			result+=ans.toString().charAt(i);
			
			
		}  
	 }
	System.out.println("res"+result);
	System.out.println("decr"+decrypt(result,res));
	return result;

}






public String getIp(){
	return ipAdress;
}

private synchronized void shutdownServer() { 
// ia?aaaouaaai nienie ?aai?eo eiiiaeoia, cae?uaaai ea?aue 
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
* aoiaiay oi?ea i?ia?aiiu 
* @param args 
* @throws IOException 
*/ 
public static void main(String[] args) throws IOException { 
launch(args);
} 

/** 
* aei?aiiue eeann aneio?iiiie ia?aaioee iaiiai eiiiaeoa. 
*/ 
private
class SocketProcessor implements Runnable{ 
Socket s; // iao nieao 
BufferedReader br; // aooa?ece?iaiiue ?eoaoaeu nieaoa 
BufferedWriter bw; // aooa?ece?iaaiiue ienaoaeu a nieao 


/** 
* Nio?aiyai nieao, i?iaoai nicaaou ?eoaoaey e ienaoaey. Anee ia iieo?aaony - aueaoaai aac nicaaiey iauaeoa 
* @param socketParam nieao 
* @throws IOException Anee ioeaea a nicaaiee br || bw 
*/ 
SocketProcessor(Socket socketParam) throws IOException { 
s = socketParam; 
br = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")); 
bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8") ); 
//UL = new BufferedReader(new InputStreamReader(System.in)); 

} 

/** 
* Aeaaiue oeee ?oaiey niiauaiee/?annueee 
*/ 
public void run() { 
Thread t1 = new Thread(new writeToClient());
t1.start();
while (!s.isClosed()) { // iiea nieao ia cae?uo... 
if(sr==true){ 
String line = "1"; 
try { 

line = br.readLine(); // i?iaoai i?i?anou. 

System.out.println(decrypt(line,res)); 
ulog.appendText("Name1:");
ulog.appendText(line);
ulog.appendText("\n");
dlog.appendText("Name1:");
dlog.appendText(decrypt(line,res));
dlog.appendText("\n");
} catch (IOException e) { 
close(); // anee ia iieo?eeinu 
//- cae?uaaai nieao. 
} 

//if (line == null) { // anee no?iea null - eeeaio ioee??eeny a ooaoiii ?a?eia. 
//close(); // oi cae?uaaai nieao 
//} else if ("shutdown".equals(line)) { // anee iinooieea eiiaiaa "iiaaneou na?aa?", oi... 
//serverThread.interrupt(); // nia?aea aicaiaei oeaa o naaa?iie ieoe i iaiaoiaeiinoe i?a?aaouny. 
//try { 
//new Socket("localhost", port); // nicaaai oaee-eiiiaeo (?oiau aueoe ec .accept()) 
//} catch (IOException ignored) { //ioeaee iaeioa?aniu 
//} finally { 
//shutdownServer(); // a caoai aeooei na?aa? auciaii aai iaoiaa shutdownServer(). 
//} 
//} else { // eia?a - aaiaeuiay ?annueea ii nieneo nieao-i?ioanni?ia 
//for (SocketProcessor sp:q) { 
//sp.send(us2); 
//} 
//} 
} 
} 
} 


public class writeToClient implements Runnable{

	public void run() {
		
		   
		   Platform.runLater(() ->{
		            
		            	enter.setOnKeyPressed(f->{
		            		System.out.print("key");
		            		if (f.getCode() == KeyCode.ENTER) {
		            			System.out.print("enter");
		 		        		try { 
		 		        		      
		 		        						//Usersline = UL.readLine(); 
		 		        						Usersline  = enter.getText();
		 		        						//System.out.println(Usersline);
		 		        						us2 = encrypt(Usersline,res);
		 		        						ulog.appendText("You:");
		 		        						ulog.appendText(Usersline);
		 		        						ulog.appendText("\n");
		 		        						 
		 		        					  
		 		        						bw.write(us2); // ieoai no?ieo 
		 		        						bw.write("\n"); // ieoai ia?aaia no?iee 
		 		        						bw.flush(); // ioi?aaeyai 
		                                         dlog.appendText("You:");
		 		        						dlog.appendText(decrypt(us2,res));
		 		        						dlog.appendText("\n");
		 		        						  mch = "";
		 		        						 enter.setText("");
		 		        					} catch (IOException e) { 
		 		        						close(); //anee ae?e a iiiaio ioi?aaee - cae?uaaai aaiiue nieao. 
		 		        						} 
		 		        					
		 		        					
		 		        				
		 		        			
		 		        		 
		            		 }
		            		
		            	});
		            	send.setOnAction(sen->{ 
		        		try { 
		        		      
		        						//Usersline = UL.readLine(); 
		        						Usersline  = enter.getText();
		        						us2 = encrypt(Usersline,res); 
		        						//System.out.println(Usersline);
		        						ulog.appendText("You:");
		        						ulog.appendText(us2);
		        						ulog.appendText("\n");
		        						
		        					  
		        						bw.write(us2); // ieoai no?ieo 
		        						bw.write("\n"); // ieoai ia?aaia no?iee 
		        						bw.flush(); // ioi?aaeyai 
                                        dlog.appendText("You:");
		        						dlog.appendText(decrypt(us2,res));
		        						dlog.appendText("\n");
		        						  mch = "";
		        						  enter.setText("");
		        					} catch (IOException e) { 
		        						close(); //anee ae?e a iiiaio ioi?aaee - cae?uaaai aaiiue nieao. 
		        						} 
		        					
		        					
		        				
		        			
		        		});
		        		
		        		   
		           
		            
		        });
		    
		
	
		}
		
	
	
}



public synchronized void close() { 
q.remove(this); //oae?aai ec nienea 
if (!s.isClosed()) { 
try { 
s.close(); // cae?uaaai 
} catch (IOException ignored) {} 
} 
} 
public synchronized void send( String us2) { 
try { 

Usersline = UL.readLine(); 
us2 = encrypt(Usersline,res); 
mch = "";
bw.write(us2); // ieoai no?ieo 
bw.write("\n"); // ieoai ia?aaia no?iee 
bw.flush(); // ioi?aaeyai 
} catch (IOException e) { 
close(); //anee ae?e a iiiaio ioi?aaee - cae?uaaai aaiiue nieao. 
} 
} 

} 

public String decrypt(String shifr, String keyWord)
{ StringBuilder ans = new StringBuilder();
String letters = "";
for(int i = 0;i<shifr.length();i++){
   
   if(shifr.charAt(i)<96){
	   letters = letters+1;
	    
   }
   else{
	   letters = letters+0;   
   }
}

shifr =  shifr.toLowerCase();
System.out.println("shifr LC "+shifr+"//"+letters);
for(int i = 0; i < shifr.length();i++)
{
	 char c; 
	 if(shifr.charAt(i)>=1040&&shifr.charAt(i)<1123){
	    	smesh = '�';
	    }
	 else{
		 smesh = 'a';	 
	 }
	 if(shifr.charAt(i)>=32&&shifr.charAt(i)<64){
	    	c = shifr.charAt(i);
	    }
	    else{ 
	int num = ((shifr.charAt(i)  - keyWord.charAt(i % keyWord.length()) + 26) % 26);
    //ia?aoiua i?aia?aciaaiey n iiia?ii aoeau a aeoaaeoa
   
   
   // System.out.print(shifr.charAt(i)+"/"+num+"//");
   
     c = (char)(num + smesh);
	    }
	
    ans.append(c);
   
}
String result = "";
String a = "";
char b; 
for(int j = 0;j<ans.toString().length();j++){
	if(letters.charAt(j)==49){
	System.out.println("s");
	a+=ans.toString().charAt(j);
	a = a.toUpperCase();
	b = a.charAt(0);
	result+=b;
	a="";
	
	}   
	else{
		result+=ans.toString().charAt(j);
		
		
	}  
}
System.out.println("res"+result);
return result;}



public class SServer implements Runnable{


	public void run() {
		
		try {
			ss = new ServerSocket(45536);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // nicaaai na?aa?-nieao 
		
		line = new BufferedReader(new InputStreamReader(System.in)); 
		serverThread = Thread.currentThread(); // ni noa?oa nio?aiyai ieou (?oiau ii?ii aa auei interrupt()) 
		while (true) { //aaneiia?iue oeee, oeia... 
		Socket s = getNewConn(); // iieo?eou iiaia niaaeiaiea eee oaee-niaaeaiea 
		if (serverThread.isInterrupted()) { // anee yoi oaee-niaaeiaiea, oi iaoa ieou auea interrupted(), 
		// iaai i?a?aaouny 
		break; 
		} else if (s != null){ // "oieuei anee eiiiaeo oniaoii nicaai"... 
		try { 
		final SocketProcessor processor = new SocketProcessor(s); // nicaaai nieao-i?ioanni? 
		final Thread thread = new Thread(processor); // nicaaai ioaaeuio? aneio?iiio? ieou ?oaiey ec nieaoa 
		thread.setDaemon(true); //noaaei aa a aaiiia (?oiau ia i?eaaou aa cae?uoey) 
		thread.start(); //caioneaai 
		q.offer(processor); //aiaaaeyai a nienie aeoeaiuo nieao-i?ioanni?ia 
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


		int a = (int)(Math.random()*199999+1000);//Aaia?e?oai 3 ?enea 
		int g = (int)(Math.random()*99999999+10000000); 
		int p = (int)(Math.random()*99999999+10000000); 
		long g1 = g; 
		long p1 = p; 
		BigInteger p2 = BigInteger.valueOf(p1); 
		BigInteger A = BigInteger.valueOf(g1).pow(a).mod(p2);//Ia iniiaa o?ao ?enae nicaaai ?enei A 
		String A1= A.toString(); 
		out1.writeInt(g);//Ioi?aaeyai aaa ?enea e naii A eeeaioo 
		out2.writeInt(p); 
		out3.writeUTF(A1); 

		String B = in1.readUTF();//Iieo?aiiia a eeeaioa A 
		int B1 = Integer.parseInt(B); 
		long B2 = B1; 


		BigInteger K = BigInteger.valueOf(B2).pow(a).mod(p2);//Nicaaiea iaeiaeiaiai ?enea e aai i?aia?aciaaiey a ee?? 

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
		res = (res + (char)pres);//Iieo?aiiue ee?? 

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
		}}

public void windowGUI(){
	 Platform.runLater(() ->{
	Stage stage = new Stage();
    AnchorPane root2= new AnchorPane();
     Scene scene2 = new Scene(root2,600,400);
     
     ImageView fon = new ImageView("img/fon.png");
     fon.setLayoutX(0);
     fon.setLayoutY(0);
     root2.getChildren().add(fon);
     
     
      ulog = new TextArea();
     ulog.setPrefHeight(245);
     ulog.setPrefWidth(245);
     ulog.setLayoutX(65);
     ulog.setLayoutY(75);
     ulog.setEditable(false);
     root2.getChildren().add(ulog);
     
      dlog = new TextArea();
     dlog.setPrefHeight(245);
     dlog.setPrefWidth(240);
     dlog.setLayoutX(315);
     dlog.setLayoutY(75);
     dlog.setEditable(false);
     root2.getChildren().add(dlog);
     
      enter = new TextArea();
     enter.setPrefHeight(43);
     enter.setPrefWidth(410);
     enter.setLayoutX(65);
     enter.setLayoutY(320);
     
     send = new Button();//470*325
     send.setLayoutX(470);
     send.setLayoutY(320);
     send.setPrefHeight(41);
     send.setOpacity(0);
     send.setPrefWidth(86);
     root2.getChildren().add(send);
     root2.getChildren().add(enter);
     
     
     stage.setScene(scene2);
       stage.show();
	 });
}

}
