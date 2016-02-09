package SongLibView;


import java.io.File;
import java.io.IOException;


import app.SongLib;
import javafx.event.ActionEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SongViewController {

	private SongLib songlib;
	

	@FXML 
	public ListView<Song> listView;

	ObservableList<Song> obsList = FXCollections.observableArrayList(Song.songList);
	

	@FXML
	private void homeScene() throws IOException {
		songlib.showMainView();
	}

	@FXML
	private void addButton() throws IOException {
		SongLib.showAddScene();
	}

	@FXML
	private void editButton() throws IOException {
		SongLib.showEditScene();
	}

	@FXML
	private void deleteButton() throws IOException {
		SongLib.showDeleteScene();
	}
	/*ListView<Song> listView = new ListView<>();
	ObservableList<Song> obsList = FXCollections.observableArrayList(songList);
	System.out.println("printing obs list");
	Song.printObsList(obsList);
	listView.setItems(obsList);*/
	public void initialize(){
		File f = new File("output.txt");
		Song.input(Song.songList, f);
		listView.setItems(obsList);
		Song.printList(Song.songList);
		Song.printObsList(obsList);
	}
	
}
