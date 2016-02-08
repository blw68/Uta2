package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.ObservableList;
import SongLibView.Song;
import SongLibView.SongViewController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SongLib extends Application {
	
	private static Stage primaryStage;
	private AnchorPane mainLayout;
	private static AnchorPane addNewSong, editSong, deleteSong;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		SongLib.primaryStage = primaryStage;
		SongLib.primaryStage.setTitle("Song Library");
		
		System.out.println("calling start");
		ArrayList<Song> songList = new ArrayList<Song>();
		
		File f = new File("output.txt");
		input(songList, f);
		
		printList(songList);
		
		//addInAbcOrder(songList, s);
		ListView<Song> listView = new ListView<>();
		ObservableList<Song> obsList = FXCollections.observableArrayList(songList);
		System.out.println("printing obs list");
		printObsList(obsList);
		
		listView.setItems(obsList);
		
		showMainView();
		
		System.out.println("end of start");
	}
	
	public void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/SongLibView/SongLibMainScene.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		try {
			String title = "Ode to Joy";
			String artist = "Beethoven";
						
//			obsList = FXCollections.observableArrayList();
//			obsList.add(s);
//			
//			listView = new ListView(obsList);
//			
//			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//				public void changed(ObservableValue ov, Object t, Object t1) {
//					title.setText((String) t1);
//				}
//			});
//			
////			listView.getSelectionModel().select(0);
////			
//			System.out.println("before set items in list view");
//			listView.setItems(obsList);
		} catch (Exception e) {
			System.out.println("exception in ok button event");
			e.printStackTrace(System.out);
		}
	}
	
	public static void showAddScene() throws IOException {
		// add button
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/PopUpWindow.fxml"));
		addNewSong = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Add New Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (addNewSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
		
		// get text field for title
		//String songName = titlePopup;
	}
	
	public static void showEditScene() throws IOException {
		// edit button
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/PopUpWindow.fxml"));
		editSong = loader.load();
		Stage addWindow = new Stage();
		addWindow.setTitle("Edit Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (editSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
	}
	
	public static void showDeleteScene() throws IOException {
		// delete button
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SongLib.class.getResource("/SongLibView/PopUpWindow.fxml"));
		deleteSong = loader.load();		// title of pop up
		Stage addWindow = new Stage();
		addWindow.setTitle("Delete Song");
		addWindow.initModality(Modality.WINDOW_MODAL);
		addWindow.initOwner(primaryStage);
		Scene scene = new Scene (deleteSong);
		addWindow.setScene(scene);
		addWindow.showAndWait();
		//System.out.println("end of delete scene, activates when hit x");
	}

	public static void main(String[] args) {
		// 1. create array list
		
		launch(args);
	}
	
	/**
	 * add newSong in correct spot in songList
	 * @param songList list of songs, should always be in abc order
	 * @param newSong Song instance that is about to be added into songList
	 */
	public static void addInAbcOrder(ArrayList<Song> songList, Song newSong) {
		if (songList.isEmpty() == true) {
			// no songs, add songName here
			songList.add(newSong);
			return;
		}
		
		if (songList.get(0).songName.compareTo(newSong.songName) > 0) {
			// songName is even smaller than first element, add at very beginning
			songList.add(0, newSong);
			return;
		}
		
		if (songList.get(songList.size() - 1).songName.compareTo(newSong.songName) < 0) {
			// songName is even bigger than last element, add at very end
			songList.add(newSong);
			return;
		}
		
		for (int i = 0; i < songList.size(); i++) {
			// search for correct location
			if (songList.get(i).songName.equals(newSong.songName) && songList.get(i).artist.equals(newSong.artist)) {
				// duplicate, not allowed
				System.out.println(newSong.songName + " by " + newSong.artist + " is a duplicate");
				return;
			}
			if (songList.get(i).songName.equals(newSong.songName)) {
				// songName is already in list, but artist is different, so ok, add here
				songList.add(i, newSong);
				return;
			}
			if (songList.get(i).songName.compareTo(newSong.songName) > 0) {
				// insert in index i
				songList.add(i, newSong);
				return;
			}
		}
	}
	
	/**
	 * goes through every song in songList and stores in output.txt
	 * @param songList list of songs, should be in abc order
	 * @return true if success, false otherwise
	 */
	public static boolean output(ArrayList<Song> songList) {
		try {
			File o = new File("output.txt");
			o.createNewFile();
			
			PrintWriter out = new PrintWriter(new FileWriter(o, false), true);
			for (int i = 0; i < songList.size(); i++) {
				out.println(songList.get(i).outString());
			}
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("Error reading file in Control.output");
			return false;
		} catch (Exception e) {
			System.out.println("Exception in Control.output");
			return false;
		}
	}
	
	/**
	 * takes in emptyList and file (output.txt) and puts lines in file into emptyList
	 * @param emptyList list of songs, should be empty when this called
	 * @param o output.txt
	 * @return true if success, false otherwise
	 */
	public static boolean input(ArrayList<Song> emptyList, File o) {
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(o);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while ((line = bufferedReader.readLine()) != null) {
				stickIn1Line(emptyList, line);
			}
			
			bufferedReader.close();
			return true;
		} catch (FileNotFoundException e) {
			System.out.println("File not found in Control.input");
			return false;
		} catch (IOException e) {
			System.out.println("Error reading file in Control.input");
			return false;
		} catch (Exception e) {
			System.out.println("Exception in Control.input");
			return false;
		}
	}
	
	/**
	 * create new Song instance with line (songName~artist~album~year) and add to emptyList
	 * @param emptyList list of songs, should be empty first time this method called
	 * @param line (songName~artist~album~year)
	 */
	public static void stickIn1Line(ArrayList<Song> emptyList, String line) {
		String[] strArr = line.split("~");
		
		emptyList.add(new Song(strArr[0], strArr[1], strArr[2], Integer.parseInt(strArr[3])));
	}
	
	/**
	 * prints out each song in songList, only used for testing purposes
	 * @param songList list of songs, should always be in abc order
	 */
	public static void printList(ArrayList<Song> songList) {
		for (int i = 0; i < songList.size(); i++) {
			System.out.println(i + " " + songList.get(i).toString());
		}
	}
	
	public static void printObsList(ObservableList<Song> obsList) {
		for (int i = 0; i < obsList.size(); i++) {
			System.out.println(i + " " + obsList.get(i).toString());
		}
	}
}
