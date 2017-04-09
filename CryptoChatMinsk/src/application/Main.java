package application;
	
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
	Statement stat = null; 
	ResultSet rs =null;
	PreparedStatement stat1 = null;
	public void start(Stage mStage) {
		
		AnchorPane ap1 = new AnchorPane();
		Scene sc1= new Scene(ap1,700,500);
		AnchorPane ap2 = new AnchorPane();
		Scene sc2= new Scene(ap2,700,500);
		
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
		
		
		
		ImageView bg= new ImageView("img/bg2.png");
		bg.setLayoutX(0);
		bg.setLayoutX(0);
		ap2.getChildren().add(bg);
		
		
		mStage.setScene(sc1);
		mStage.show();
		
	}
	
	public static void main(String[] args) {
		
		
		launch(args);
		
		
	}
public void db()throws Exception{
	 
	// Class.forName ("com.mysql.jdbc.Driver").newInstance(); 
	
	// localhost - это веб-сервер и связанная с ними база данных,которая создана непосредственно 
	// на вашем компьютере, либо на компьютер локальной сети. 
	// test - имя бызы данных в Workbench 
	// root - Пользователь 
	
	stat=conn.createStatement(); 

	rs=stat.executeQuery("Select * from table1");// register - имя таблицы 
	
	
	while(rs.next()){ 
		System.out.println("://");
		
		System.out.println(rs.getString(1)+" "+rs.getString(2)); 
	// rs.getInt(1) вывод 1 колонки из БД в консоль и.т.д 
	} 
	rs.close(); 
	
	conn.close(); 
	System.out.println("Good connection"); 
	
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
		
			stat1 = conn.prepareStatement("INSERT INTO table1" 
				+ " ( Login, Password) values (?,?)");
		//stat1.setInt(1, 5 ); 
		System.out.println(login.getText()+"/"+password.getText());
			stat1.setString(1,login.getText()); 
		stat1.setString(2,password.getText()); 
		stat1.executeUpdate(); 
		stat1.close(); 
		
		try {
			db();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
