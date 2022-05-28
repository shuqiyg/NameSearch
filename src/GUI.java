/********************************************** 
Workshop # 7
Course: JAC433
Last Name:Yang
First Name:Shuqi
ID:132162207
Section:NBB 
This assignment represents my own work in accordance with Seneca Academic Policy. 
Signature 
Date:2022-03-25
**********************************************/ 
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {	
	int yearInput;
	String genderInput, nameInput;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sceneSearch; 
		Scene sceneResult;
		
		GridPane searchPane = new GridPane();
		searchPane.setHgap(2);
		searchPane.setVgap(2);
		GridPane resultPane = new GridPane();
		resultPane.setHgap(2);
		resultPane.setVgap(2);
		Label yearL = new Label("Enter the Year: ");
		Label genderL = new Label("Enter the Gender: ");
		Label nameL = new Label("Enter the Name: ");
		Label resultL = new Label();
		Label againL = new Label("Do you want to Search for another Name: ");
		TextField yearTF = new TextField();
		//yearTF.
		TextField genderTF = new TextField();
		genderTF.setMaxWidth(30);
		genderTF.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.length() > 1 ) {
				String copy = genderTF.getText().substring(0, 1);
				genderTF.setText(copy);
			}
		});
		TextField nameTF = new TextField();
		Button submit = new Button("Submit Query");
		
		Button exit = new Button("Exit");
		Button yes = new Button("Yes");
		Button no = new Button("No");
		//searchPane
		searchPane.setPadding(new Insets(15,30,10,15));
		searchPane.add(yearL,5, 5);
		searchPane.add(yearTF,10,5);
		searchPane.add(genderL,5, 10);
		searchPane.add(genderTF,10,10);
		searchPane.add(nameL,5, 15);
		searchPane.add(nameTF,10,15);
		searchPane.add(submit, 5, 23);
		searchPane.add(exit, 8, 23);
		//resultPane
		resultPane.setPadding(new Insets(15,30,10,15));
		resultPane.add(resultL, 5, 5);
		resultPane.add(againL, 5, 10);
		resultPane.add(yes, 5, 15);
		resultPane.add(no, 8, 15);
		sceneSearch = new Scene(searchPane, 400,300);
		sceneResult = new Scene(resultPane, 400,300);
		//set up alerts for invalid inputs when submit button is clicked 
		Alert alert =new Alert(AlertType.WARNING); 
		submit.setOnAction(e->{
			try {
				yearInput = Integer.parseInt(yearTF.getText());
			}catch(Exception excep) {
				//check if user's input is within the constrains we defined
				alert.setContentText("Please Enter an Integer Value");
				alert.show();
				return;
			} 
		
			genderInput = genderTF.getText();
			nameInput = nameTF.getText();
			
			if(yearInput > 2020 || yearInput < 2010) {
				alert.setContentText("Please Enter year between 2010 and 2020");
				alert.show();
			}else if(!genderInput.toUpperCase().equals("M") && !genderInput.toUpperCase().equals("F")) {
				alert.setContentText("Please Enter Gender 'M' or 'F'");
				alert.show();
			}else if(nameTF.getText().length() == 0){
				alert.setContentText("Please Enter a Name");
				alert.show();
			}else {
				Ranking nameRank = new Ranking(yearInput, nameInput, genderInput);
				resultL.setText(nameRank.find());
				nameRank.closeFile();
				primaryStage.setScene(sceneResult);	
			}
			 
		});
		exit.setOnAction(e->{
			primaryStage.close();
			System.out.println("Good Bye from exitButton");
		});
		yes.setOnAction(e->{
			yearTF.setText("");
			nameTF.setText("");
			genderTF.setText("");
			primaryStage.setScene(sceneSearch);
		});
		no.setOnAction(e->{
			primaryStage.close();
			System.out.println("Good Bye from noButton");
		});
		primaryStage.setMinWidth(550);
		primaryStage.setTitle("Baby Name Ranking Search Application");
		primaryStage.setScene(sceneSearch);
		primaryStage.show();
	}

}
