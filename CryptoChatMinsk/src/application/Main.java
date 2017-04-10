package application;
	
import java.io.IOException;
import java.sql.*;


import com.mysql.jdbc.Driver;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public static Connection conn;
	public static Statement statmt;
	public static ResultSet resSet;
	TextField login;
	PasswordField password;
	TextField ename;
	TextField elogin;
	PasswordField epassword;
	PasswordField eapassword;
	
	AnchorPane ap1;
	AnchorPane ap2;
	AnchorPane ap3;
	Scene sc1;
	Scene sc2;
	Scene sc3;
	
	Statement stat = null; 
	ResultSet rs =null;
	PreparedStatement stat1 = null;
	PreparedStatement stat2 = null;
	public void start(Stage mStage) {
		
		 ap1 = new AnchorPane();
		 sc1= new Scene(ap1,700,500);
		 ap2 = new AnchorPane();
		 sc2= new Scene(ap2,700,500);
		 ap3 = new AnchorPane();
		 sc3= new Scene(ap3,700,500);
		
		signInGUI();
		signUpGUI();
		mainGUI();
		

		
		
		
		
		
		
		Button signin = new Button();
		signin.setLayoutX(275);
		signin.setLayoutY(325);
		signin.setPrefWidth(170);
		signin.setPrefHeight(40);
		signin.setOpacity(0);
		signin.setOnAction(v->{
			try {
				send();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			mStage.setScene(sc2);
			mStage.show();
			
			
		});
		ap1.getChildren().add(signin);
		
		
		Button registrate = new Button();
		registrate.setLayoutX(175);
		registrate.setLayoutY(370);
		registrate.setPrefWidth(380);
		registrate.setPrefHeight(50);
		registrate.setOpacity(1);
		ap3.getChildren().add(registrate);
		
		
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
	public void mainGUI(){
		ImageView bg= new ImageView("img/bg2.png");
		bg.setLayoutX(0);
		bg.setLayoutX(0);
		ap2.getChildren().add(bg);
		
		
		
	}
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
	
	// localhost - это веб-сервер и связанная с ними база данных,которая создана непосредственно 
	// на вашем компьютере, либо на компьютер локальной сети. 
	// test - имя бызы данных в Workbench 
	// root - Пользователь 
	
	stat=conn.createStatement(); 

	rs=stat.executeQuery("Select * from table2");// register - имя таблицы 
	
	
	while(rs.next()){ 
		System.out.println("://");
		
		System.out.println(rs.getString(1)+"//"+rs.getString(2)+"//"+rs.getString(3)+"// "+rs.getString(4)+"// "+rs.getString(5)); 
	// rs.getInt(1) вывод 1 колонки из БД в консоль и.т.д 
	} 
	rs.close(); 
	//delete();
	conn.close(); 
	System.out.println("Good connection"); 
	
}

	
	public void delete() throws SQLException{
		String query = "delete from table2 ";
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
		
	}
	
	
	public void send() throws SQLException{
		try {
			DriverManager.registerDriver(new Driver());
			 conn = DriverManager.getConnection( 
					"jdbc:mysql://localhost/mydb?useSSL=false", "root","12345"); 
					System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
			stat1 = conn.prepareStatement("INSERT INTO table2" 
				+ " (ID,Name, Login, Password,IP) values (?,?,?,?,?)");
		//stat1.setInt(1, 5 ); 
		System.out.println(login.getText()+"/"+password.getText());
		stat1.setString(1,"2");
		stat1.setString(2,"2");
		stat1.setString(3,login.getText()); 
		stat1.setString(4,password.getText()); 
		stat1.setString(5,"2");
		stat1.executeUpdate(); 
		stat1.close(); 
		
		try {
			readdb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
