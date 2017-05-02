package application;
import java.net.Socket; 
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*; 
import java.math.BigInteger; 

/** 
* Eeann-eeeaio ?ao-na?aa?a. ?aaioaao a eiiniee. Eiiaiaie n eiiniee shutdown iinueaai na?aa? a iooeaei 
*/ 
public class Client extends Application { 
	static Stage classStage = new Stage();
	Socket s; // yoi aoaao nieao aey na?aa?a 
 BufferedReader socketReader; // aooa?ece?iaaiiue ?eoaoaeu n na?aa?a 
 BufferedWriter socketWriter; // aooa?ece?iaaiiue ienaoaeu ia na?aa? 
 BufferedReader userInput; // aooa?ece?iaaiiue ?eoaoaeu iieuciaaoaeuneiai aaiaa n eiiniee 
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
Button leave;
int shetchik = 0; 
int mcount = 1; 
int massiv[] =new int[12]; 
BufferedReader line; 
int kascii[]; 
int j; 
ImageView bg;
TextField search;
ImageView sres;
Scene sc2;
AnchorPane ap2;
TextArea chat;
Button con;
TextArea write;
Button send;
String userString= ""; 
String mch =""; 
String userLine; 
private int usmesh = (int)'A';//niauaiea aeoaaeoa ioiineoaeuii oaaeeou ?ieeiaia
private int smesh = (int)'a';//niauaiea aeoaaeoa ioiineoaeuii oaaeeou ?ieeiaia
AnchorPane ap3;
Scene sc3;
/** 
* Eiino?oeoi? iauaeoa eeeaioa 
* @param host - IP aa?an eee localhost eee aiiaiiia eiy 
* @param port - ii?o, ia eioi?ii aeneo na?aa? 
* @throws java.io.IOException - anee ia niiaee i?eeiiiaeoeouny, eeaaaony enee??aiea, ?oiau 
* i?aaioa?aoeou nicaaiea iauaeoa 
*/ 

public void start(Stage st1) throws Exception {

	Client.classStage = st1;
	ap2 = new AnchorPane();
	 sc2= new Scene(ap2,700,500);
	 ap3 = new AnchorPane();
	 sc3= new Scene(ap3,700,500);
	 ImageView bg= new ImageView("img/bg3.png");
			bg.setLayoutX(0);
			bg.setLayoutX(0);
			ap2.getChildren().add(bg);
		
			
			search = new TextField();
			search.setLayoutX(45);
			search.setLayoutY(120);
			search.setPrefWidth(130);
			search.setPrefHeight(18);
			ap2.getChildren().add(search);
		
		sres = new ImageView("img/sres.png");
		sres.setLayoutX(5);
		sres.setLayoutY(110);
		sres.setFitHeight(75);
		sres.setFitWidth(177);
	
		//MenuBar mainBar = new MenuBar();
		//mainBar.setLayoutX(0);
		///mainBar.setLayoutY(0);
		//mainBar.add
		//ap2.getChildren().add(mainBar);
		
		
		con= new Button();
		con.setLayoutX(572);
		con.setLayoutY(79);
		con.setPrefWidth(30);
		con.setPrefHeight(30);
		con.setOpacity(0);
		con.setOnAction(conn->{ 
				
				//try {
				//	new Client("localhost",45536).run();
				//} catch (IOException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				
				//st.close();
				
			//Thread tc = new LaunchClient();
		        //tc.start();
		});
		ap2.getChildren().add(con);
		
		//st.close();
		
		send= new Button();
		send.setLayoutX(632);
		send.setLayoutY(462);
		send.setPrefWidth(50);
		send.setPrefHeight(30);
		send.setOpacity(0);
		
		ap2.getChildren().add(send);
		
		chat = new TextArea();
		chat.setLayoutX(211);
		chat.setLayoutY(163);
		chat.setPrefWidth(431);
		chat.setPrefHeight(260);
		chat.setFont(new Font(16));
		
		chat.setStyle("-fx-text-inner-color: red;");
		chat.setEditable(false);
		ap2.getChildren().add(chat);
		
		write = new TextArea();
		write.setLayoutX(200);
		write.setLayoutY(450);
		write.setPrefWidth(420);
		write.setPrefHeight(45);
	    
		leave = new Button();
		leave.setLayoutX(570);
		leave.setLayoutY(75);
		leave.setPrefHeight(40);
		leave.setPrefWidth(30);
		leave.setOpacity(0);
		leave.setOnAction(f->{
		try {
			s.close();
			Server s = new Server();
			try {
				s.start(Server.classStage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			st1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
			
		});
		//write.setOpacity(1);
		ap2.getChildren().add(leave);
	     ap2.getChildren().add(write);
		
		
		st1.setScene(sc2);
	    st1.show();
new Thread(new CClient()).start();	

}




/** 
* iaoia, aaa i?ienoiaeo aeaaiue oeee ?oaiey niiauaiee n eiiniee e ioi?aaee ia na?aa? 
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
int g = in1.readInt();//I?eieiaai ia?aneaiiua ?enea 
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
BigInteger B = BigInteger.valueOf(g1).pow(b).mod(p2); //Aaia?e?oai A 
BigInteger K= BigInteger.valueOf(A2).pow(b).mod(p2);//Nicaaai iauaa ?enei, iii eaaioe?ii ?eneo ia na?aa?a 
String B1 = B.toString(); 
out1.writeUTF(B1);//Ioi?aaeyai ?enei A aey nicaaiey iauaai ?enea ia na?aa?a 
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
(char)pres);// Iieo?aai iauee ee?? 

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
     char c;
    
     int num = ((text.charAt(i) + keyWord.charAt(i % keyWord.length()) - 2 * smesh) % 26);
   
    	
    	 c = (char)(num + smesh);
      
     
//      char c = (char)(num + smesh);//iieo?aai io?iue neiaie
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











 

public synchronized void close() {//iaoia neio?iiece?iaai, ?oiau enee??eou aaieiia cae?uoea. 
if (!s.isClosed()) { // i?iaa?yai, ?oi nieao ia cae?uo... 
try { 
s.close(); // cae?uaaai... 
System.exit(0); // auoiaei! 
} catch (IOException ignored) { 
ignored.printStackTrace(); 
} 
} 
} 




public static void main(String[] args) { // aoiaiay oi?ea i?ia?aiiu 
launch(args);
} 
private class Receiver implements Runnable{ 
/** 
* run() auciaaony iinea caionea ieoe ec eiino?oeoi?a eeeaioa ?aoa. 
*/ 
//String line =""; 
public void run() { 

while (!s.isClosed()) { //noiao i?iaa?yai eiiiaeo. 
if(kn==true){ 
String line = null; 
try { 
line = socketReader.readLine(); // i?iaoai i?i?anou 
chat.appendText("Name2"+decrypt(line,res));
chat.appendText("\n");
} catch (IOException e) { // anee a iiiaio ?oaiey ioeaea, oi... 
// i?iaa?ei, ?oi yoi ia aaiaeuiia ooaoiia cae?uoea nieaoa na?aa?ii 
if ("Socket closed".equals(e.getMessage())) { 
break; 
} 
System.out.println("Connection lost"); // a n?aa iu iiiaaai a neo?aa ioeaie naoe. 
close(); // io e cae?uaaai nieao (enoaoe, aucaaaony iaoia eeanna ChatClient, anou ainooi) 
} 
if (line ==
null) { // no?iea aoaao null anee na?aa? i?ee?ue eiiiaeo ii naiae eieoeaoeaa, naou ?aaioaao 
System.out.println("Server has closed connection"); 
close(); // ...cae?uaaainy 
} 
else { // eia?a ia?aoaai oi, ?oi i?eneae na?aa?. 
System.out.println("Server:" + decrypt(line,res)); 

} 
} 
} 
} 
} 

public String decrypt(String shifr, String keyWord)
{
    StringBuilder ans = new StringBuilder();
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
        int num = ((shifr.charAt(i)  - keyWord.charAt(i % keyWord.length()) + 26) % 26);
        //ia?aoiua i?aia?aciaaiey n iiia?ii aoeau a aeoaaeoa
        char c;
       
       // System.out.print(shifr.charAt(i)+"/"+num+"//");
       
         c = (char)(num + smesh);
      
    	
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
    return result;
}//


public class CClient implements Runnable{

	
	public void run() {
		try {
			//Server sv1 = new Server();
			//String ip1= sv1.getIp();
			s = new Socket("localhost",45536);
		
		socketReader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8")); 
		socketWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8")); 
		// nicaaai ?eoaoaey n eiiniee (io iieuciaaoaey) 
		userInput = new BufferedReader(new InputStreamReader(System.in)); 
		new Thread(new Receiver()).start(); 
		line = new BufferedReader(new InputStreamReader(System.in)); 
		sout5 = s.getOutputStream(); 
		out5= new DataOutputStream(sout5); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	df(); 
	System.out.println("Type phrase(s) (hit Enter to exit):"); 
	String mess = "User has been connected";
	//try {
		//socketWriter.write(encrypt(mess,res)); //ieoai no?ieo iieuciaaoaey 
		//socketWriter.write("\n");
		//socketWriter.flush(); // ioi?aaeyai 
	//} catch (IOException e1) {
		//// TODO Auto-generated catch block
//		e1.printStackTrace();
	//} //aiaaaeyai "iiai? no?ieo", aaau readLine() na?aa?a n?aaioae 

	while (true) {
	 
	//String userString = null; 
//String us1 = ""; 
	//try { 
	//userString = userInput.readLine(); // ?eoaai no?ieo io iieuciaaoaey 
	send.setOnAction(senn->{
	String userString = write.getText();
	String	us1 = encrypt(userString,res); 
	chat.appendText("You:"+decrypt(us1,res));
	chat.appendText("\n");
	mch = "";
	 write.clear();
	try { 
	socketWriter.write(us1); //ieoai no?ieo iieuciaaoaey 
	socketWriter.write("\n"); //aiaaaeyai "iiai? no?ieo", aaau readLine() na?aa?a n?aaioae 
	socketWriter.flush(); // ioi?aaeyai 
	
	} catch (IOException e) { 
	close(); // a e?aie ioeaea - cae?uaaai. 
	} 
	 
	});
	}
	}
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
		int g = in1.readInt();//I?eieiaai ia?aneaiiua ?enea 
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
		BigInteger B = BigInteger.valueOf(g1).pow(b).mod(p2); //Aaia?e?oai A 
		BigInteger K= BigInteger.valueOf(A2).pow(b).mod(p2);//Nicaaai iauaa ?enei, iii eaaioe?ii ?eneo ia na?aa?a 
		String B1 = B.toString(); 
		out1.writeUTF(B1);//Ioi?aaeyai ?enei A aey nicaaiey iauaai ?enea ia na?aa?a 
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
		(char)pres);// Iieo?aai iauee ee?? 

		} 
		kn = true; 
		System.out.println(res); 
		} catch (IOException e) { 
		// TODO Auto-generated catch block 
		e.printStackTrace(); 
		} 

		} 

}

}