package SongLibView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupController {
	
	@FXML
	private TextField title;
	@FXML
	private TextField artist;
	@FXML
	private TextField album;
	@FXML
	private TextField year;
	@FXML
	public Button cancelButton, okButton;
	//public ObservableList<Song> obsList;
	
	@FXML
	private void cancelButtonEvent(ActionEvent event) {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void okButtonEvent(ActionEvent event) {
		try {
			System.out.println("ok button pressed");
			System.out.println("title is " + title.getText());
			System.out.println("artist is " + artist.getText());
			System.out.println("album is " + album.getText());
			System.out.println("year is " + year.getText());
			
			Song s = new Song(title.getText(), artist.getText(), album.getText(), Integer.parseInt(year.getText()));
					
			Song.addInAbcOrder(Song.songList, s);
			Song.output(Song.songList);
			//System.out.println("printing songList");
			//Song.printList(Song.songList);
			//System.out.println("after printing songList");
			/*
			ListView<Song> listView = new ListView<>();
			obsList = FXCollections.observableArrayList();
			ObservableList<Song> obsList = FXCollections.observableArrayList(Song.songList);
			listView.setItems(obsList);*/
//			for (Song i : Song.songList) {
//				obsList.add(i);
//			}
			
			//listView.setItems(obsList);
			
			//System.out.println("done");
			
//			obsList.add(s);
//			
//			listView = new ListView(obsList);
//			
//			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//				public void changed(ObservableValue ov, Object t, Object t1) {
//					title.setText((String) t1);
//				}
//			});
			
//			listView.getSelectionModel().select(0);
//			
			
//			System.out.println("before set items in list view");
//			listView.setItems(obsList);
//			System.out.println("after set items in list view");
		} catch (Exception e) {
			System.out.println("exception in ok button event");
			e.printStackTrace(System.out);
		}

		// close pop up window just like cancel does
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

//	@FXML
//	private void TitleAction(ActionEvent event) {
//		//		TextField source = (TextField)event.getSource();
//		//		System.out.println("You entered " + source.getText());
//
//
//	}

}
