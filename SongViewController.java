package SongLibView;

import java.awt.TextField;
import java.io.IOException;

import app.SongLib;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SongViewController {
	
	private SongLib songlib;
	
	@FXML
	private void homeScene() throws IOException {
		songlib.showMainView();
	}
	
	@FXML
	private void addButton() throws IOException {
		// add button on main window pressed
		SongLib.showAddScene();		
	}
	
	@FXML
	private void editButton() throws IOException {
		// edit button on main window pressed
		SongLib.showEditScene();
	}
	
	@FXML
	private void deleteButton() throws IOException {
		// delete button on main window pressed
		SongLib.showDeleteScene();
	}
	
	@FXML
	private void okButton() throws IOException {
		// when OK button pressed
		System.out.println("In ok button method in SongViewController");
		//String songName = title.getText();
		String artistStr = artist.getText();
		String albumStr = album.getText();
		int yearInt = Integer.parseInt(year.getText());
		
		System.out.println(title.getText() + " " + artistStr + " " + albumStr + " " + yearInt);
		
		//SongLib.addInAbcOrder(songList, new Song(songName, artistStr, albumStr, yearInt));
	}
	
	@FXML
	private void cancelButtonEvent(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private Button addButton, editBitton, deleteButton, cancelButton, okButton;
	
	@FXML
	private TextField title;
	@FXML
	private TextField artist;
	@FXML
	private TextField album;
	@FXML
	private TextField year;
	
}
