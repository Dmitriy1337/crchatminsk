package application;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.jdbc.Driver;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Main extends Application {
	
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	InetAddress	ip1;
	TextField login;
	PasswordField password;
	TextField ename;
	TextField elogin;
	TextField search;
	Button find;
	PasswordField epassword;
	ImageView lwarning;
	ImageView pwarning;
	ImageView ewarning;
	ImageView sres;
	PasswordField eapassword;
	int regg = 0;
	public static String elog;
	AnchorPane ap1;
	AnchorPane ap2;
	AnchorPane ap3;
	boolean isI;
	Scene sc1;
	Scene sc2;
	Scene sc3;
	AnchorPane ap4;
	Scene sc4;
	boolean a=false;
	int id = 1;
	int id1  =0;
	int id2  =0;
	ImageView accinfo;
	String log;
	boolean isTrue;
	Statement stat = null; 
	Statement stat3 = null; 
	Statement stat4 = null; 
	ResultSet rs =null;
	ResultSet rs1 =null;
	ResultSet rs2 =null;
	PreparedStatement stat1 = null;
	PreparedStatement stat2 = null;
	Label iname;
	Label ipass;
	Label ipAd;
	Label ilogin;

	public void start(Stage mStage) {
		
		 ap1 = new AnchorPane();
		 sc1= new Scene(ap1,700,500);
		 ap2 = new AnchorPane();
		 sc2= new Scene(ap2,700,500);
		 ap3 = new AnchorPane();
		 sc3= new Scene(ap3,700,500);
		 ap4 = new AnchorPane();
		 sc4= new Scene(ap4,700,500);
		signInGUI();
		signUpGUI();
		//mainGUI();
		
     
		mStage.setOnCloseRequest(cr->{
			try {
				DriverManager.registerDriver(new Driver());
				 conn = DriverManager.getConnection( 
						"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
						System.out.println("connected");
						stat3 = conn.createStatement();
						rs1= stat3.executeQuery("Select * from table2");
						 stat4 = conn.createStatement();
                     
                      
						while(rs1.next()){ 
							if(rs1.getString(2).equals(log)){
							String onl ="UPDATE table2 SET Online = '0' WHERE Login = '"+log+"'";
								stat4.executeUpdate(onl);
							 try {
								readdb();
							
							 } catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}
							else if((!rs1.getString(4).equals(password.getText()))||(!rs1.getString(3).equals(login.getText()))){
								System.out.println("Write legal password or login");
							}
						
						}
						
						//System.out.println(regg);
			//delete();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
		
		});
		
		accinfo = new ImageView("img/accinfo.png");
		accinfo.setLayoutX(0);
		accinfo.setLayoutY(0);
		

		ap4.getChildren().add(accinfo);
		
		
		
		 lwarning = new ImageView("img/warning.png");
		lwarning.setLayoutX(520);
		lwarning.setLayoutY(95);
		lwarning.setFitHeight(30);
		lwarning.setFitWidth(40);
		lwarning.setOpacity(0);
		ap3.getChildren().add(lwarning);
		
		pwarning = new ImageView("img/warning.png");
		pwarning.setLayoutX(520);
		pwarning.setLayoutY(270);
		pwarning.setFitHeight(30);
		pwarning.setFitWidth(40);
		pwarning.setOpacity(0);
		ap3.getChildren().add(pwarning);
		
		ewarning = new ImageView("img/warning.png");
		ewarning.setLayoutX(520);
		ewarning.setLayoutY(180);
		ewarning.setFitHeight(30);
		ewarning.setFitWidth(40);
		ewarning.setOpacity(0);
		ap1.getChildren().add(ewarning);
		
		
		Button signin = new Button();
		signin.setLayoutX(275);
		signin.setLayoutY(325);
		signin.setPrefWidth(170);
		signin.setPrefHeight(40);
		signin.setOpacity(0);
		signin.setOnAction(v->{
			
			try {
				DriverManager.registerDriver(new Driver());
				 conn = DriverManager.getConnection( 
						"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
						
				 System.out.println("connected");
						
				 stat3 = conn.createStatement();
						rs1= stat3.executeQuery("Select * from table2");
						stat4 = conn.createStatement();
						//System.out.println(rs1.next());
					elog=elogin.getText();
						//if(rs1.next()==false){
						//	ewarning.setOpacity(1);
							//mStage.show();	
						//}
						while(rs1.next()){ 
							if((rs1.getString(4).equals(password.getText()))&&(rs1.getString(3).equals(login.getText()))){
								System.out.println("enter");
								regg = 1; 
								log = login.getText();
								a=true;
								String onl1 ="UPDATE table2 SET Online = '1' WHERE Login = '"+log+"'";
								stat4.executeUpdate(onl1);
								 try {
										readdb();
									
								 } catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								 
								 try {
									 Server sv = new Server();
									sv.start(Server.classStage);
								
									mStage.close();
								 } catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
								 
							 }
							else if(!a){//if((!rs1.getString(4).equals(password.getText()))||(!rs1.getString(3).equals(login.getText()))){
								ewarning.setOpacity(1);
								mStage.show();
								
								
								System.out.println("Write legal password or login");
							}
						
						}
						System.out.println(regg);
						//System.out.println(regg);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			

			
		});
		ap1.getChildren().add(signin);
		
		
		Button registrate = new Button();
		registrate.setLayoutX(175);
		registrate.setLayoutY(370);
		registrate.setPrefWidth(380);
		registrate.setPrefHeight(50);
		registrate.setOpacity(0);
		registrate.setOnAction(reg->{
		isTrue = false;
			try {
			try {
				DriverManager.registerDriver(new Driver());
				 conn = DriverManager.getConnection( 
						"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
						System.out.println("connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			stat3 = conn.createStatement();
			rs1= stat3.executeQuery("Select * from table2");
			while(rs1.next()){ 
				if(rs1.getString(3).equals(elogin.getText())){ 
					isTrue  = true;
				
				} 
				

				}
			System.out.println(isTrue);
			if(!isTrue){
				lwarning.setOpacity(0);
				if(epassword.getText().equals(eapassword.getText())){
					send();	
					pwarning.setOpacity(0);
					iname.setText(ename.getText());
					ipass.setText(epassword.getText());
					ilogin.setText(elogin.getText());
					ipAd.setText(ip1.toString().replaceAll("localhost/",""));
					System.out.println("Send");	
					mStage.setScene(sc4);
					mStage.show();
					
				}
				else{
					pwarning.setOpacity(1);
				}
				
			}
			else{
				
					lwarning.setOpacity(1);
					System.out.println("Unavailable");
					mStage.show();
				}
			
			
			
			
			
		  // delete();
		  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	});
		ap3.getChildren().add(registrate);
		
		
		
		 iname = new Label("text");
		iname.setLayoutX(400);
		iname.setLayoutY(110);
		iname.setFont(new Font("Rockwell Condensed",36));
		iname.setOpacity(1);
		ap4.getChildren().add(iname);
		
		ilogin = new Label("text");
		ilogin.setLayoutX(400);
		ilogin.setLayoutY(150);
		ilogin.setFont(new Font("Rockwell Condensed",36));
		ilogin.setOpacity(1);
		ap4.getChildren().add(ilogin);
		
		ipass = new Label("text");
		ipass.setLayoutX(400);
		ipass.setLayoutY(200);
		ipass.setFont(new Font("Rockwell Condensed",36));
		ipass.setOpacity(1);
		ap4.getChildren().add(ipass);
		
		ipAd = new Label("text");
		ipAd.setLayoutX(400);
		ipAd.setLayoutY(250);
		ipAd.setFont(new Font("Rockwell Condensed",36));
		ipAd.setOpacity(1);
		ap4.getChildren().add(ipAd);
		
		
		Button signup = new Button();
		signup.setLayoutX(275);
		signup.setLayoutY(400);
		signup.setPrefWidth(170);
		signup.setPrefHeight(40);
		signup.setOpacity(0);
		signup.setOnAction(pr->{
			mStage.setScene(sc3);
			mStage.show();
			
		});
		
		ap1.getChildren().add(signup);
		
		Button back1= new Button();
		back1.setLayoutX(25);
		back1.setLayoutY(450);
		back1.setPrefWidth(70);
		back1.setPrefHeight(20);
		back1.setOpacity(0);
		back1.setOnAction(r->{
			mStage.setScene(sc1);
			mStage.show();
		   
		});
		ap3.getChildren().add(back1);
		
		Button back2= new Button();
		back2.setLayoutX(320);
		back2.setLayoutY(355);
		back2.setPrefWidth(60);
		back2.setPrefHeight(30);
		back2.setOpacity(0);
		back2.setOnAction(r->{
			
			mStage.setScene(sc1);
			mStage.show();
		});
		ap4.getChildren().add(back2);
		
		
		find= new Button();
		find.setLayoutX(17);
		find.setLayoutY(120);
		find.setPrefWidth(22);
		find.setPrefHeight(20);
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
					
						
						System.out.println(search.getText());	
					
					}}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		});
		ap2.getChildren().add(find);
		
		
		
		
		mStage.setScene(sc1);
		mStage.show();
		
	}
	
	public static void main(String[] args) {
		
		
		launch(args);
		
		
	}

	public void signInGUI(){
		ImageView sign= new ImageView("img/sign.png");
		sign.setLayoutX(0);
		sign.setLayoutX(0);
		ap1.getChildren().add(sign);
			
		
		
		 login = new TextField();//225x150
		login.setLayoutX(225);
		login.setLayoutY(140);
		login.setPrefWidth(270);
		ap1.getChildren().add(login);

		
		 password = new PasswordField();//225x150
		password.setLayoutX(225);
		password.setLayoutY(215);
		password.setPrefWidth(270);
		ap1.getChildren().add(password);
		
	}
	//public void mainGUI(){
		//ImageView bg= new ImageView("img/bg2.png");
		//bg.setLayoutX(0);
		//bg.setLayoutX(0);
		//ap2.getChildren().add(bg);
	
		
		//search = new TextField();
		//search.setLayoutX(45);
		//search.setLayoutY(120);
		//search.setPrefWidth(130);
		//search.setPrefHeight(18);
		//ap2.getChildren().add(search);
	
	//sres = new ImageView("img/sres.png");
	//sres.setLayoutX(5);
	//sres.setLayoutY(110);
	//sres.setFitHeight(75);
	//sres.setFitWidth(177);
	//}
	public void signUpGUI(){
		ImageView reg= new ImageView("img/registration.png");
		reg.setLayoutX(0);
		reg.setLayoutX(0);
		ap3.getChildren().add(reg);
		
		ename = new TextField();//225x150
		ename.setLayoutX(250);
		ename.setLayoutY(95);
		ename.setPrefWidth(270);
		ap3.getChildren().add(ename);
		
		elogin = new TextField();//225x150
		elogin.setLayoutX(250);
		elogin.setLayoutY(150);
		elogin.setPrefWidth(270);
		ap3.getChildren().add(elogin);
		
		epassword = new PasswordField();//225x150
		epassword.setLayoutX(250);
		epassword.setLayoutY(210);
		epassword.setPrefWidth(270);
		ap3.getChildren().add(epassword);
		
		eapassword = new PasswordField();//225x150
		eapassword.setLayoutX(250);
		eapassword.setLayoutY(270);
		eapassword.setPrefWidth(270);
		ap3.getChildren().add(eapassword);
		
	}
	
	
	
	public void readdb()throws Exception{
	 
	 //Class.forName ("com.mysql.jdbc.Driver").newInstance(); 
	
	// localhost - yoi aaa-na?aa? e naycaiiay n ieie aaca aaiiuo,eioi?ay nicaaia iaiin?aanoaaiii 
	// ia aaoai eiiiu?oa?a, eeai ia eiiiu?oa? eieaeuiie naoe. 
	// test - eiy aucu aaiiuo a Workbench 
	// root - Iieuciaaoaeu 
	
	
		
		stat=conn.createStatement(); 
		
	rs=stat.executeQuery("Select * from table2");// register - eiy oaaeeou 
	

	while(rs.next()){ 
		System.out.println("://");
		System.out.println(rs.getString(1)+"//"+rs.getString(2)+"//"+rs.getString(3)+"// "+rs.getString(4)+"// "+rs.getString(5)+"// "+rs.getString(6)); 
	
	} 
	 
	//delete();
	
	
	rs.close();
	//conn.close(); 
	System.out.println("Good connection"); 
	}

	
	public void delete() throws SQLException{
		String query = "TRUNCATE table2 ";
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
		
	
	}

	
	public void send() throws SQLException{
		
		try {
			DriverManager.registerDriver(new Driver());
			 conn = DriverManager.getConnection( 
					"jdbc:mysql://127.0.0.1/mydb?useSSL=false", "root","12345"); 
					System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String ip=null;
		try {
			ip1 = InetAddress.getByName(ip);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			stat1 = conn.prepareStatement("INSERT INTO table2" 
				+ " (Name, Login, Password,IP,Online,PInfo) values (?,?,?,?,?,?)");
		//stat1.setInt(1, 5 ); 
		System.out.println(login.getText()+"/"+password.getText());
		//stat1.setInt(1,id);
		stat1.setString(1,ename.getText());
		stat1.setString(2,elogin.getText()); 
		stat1.setString(3,epassword.getText()); 
		stat1.setString(4,ip1.toString().replaceAll("localhost/",""));
		stat1.setString(5,"0");
		stat1.setString(6,"d");
		stat1.executeUpdate(); 
		stat1.close(); 
	
		try {
			readdb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getName(){
		return elog;
		
	}
	//public class TurnServer extends Thread{
		   

		//public void run() {
			//try {
				//new Server(45536).run();
			//} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
		//}
		
	//}

}