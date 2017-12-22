package de.qubit.view;

import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import model.MetaTraderDataFrame;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class DataParserApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = createGUIElements();
		Scene scene = new Scene(root, 1200, 900);
		stage.setScene(scene);
		stage.setTitle("Meta Trader Parser");
		stage.show();
	

	}

	private GridPane createGUIElements() {
		GridPane pane = new GridPane();
		
		Label areaLabel = new Label("Input content from Meta Trader dataframe:");
		TextArea textArea = new TextArea();
		
		final TableView<MetaTraderDataFrame> table = new TableView<MetaTraderDataFrame>();
		TableColumn<MetaTraderDataFrame, String> columnSymbol = new TableColumn<>("Symbol");
		TableColumn<MetaTraderDataFrame, Double> columnOpen = new TableColumn<>("Open");
		TableColumn<MetaTraderDataFrame, Double> columnHigh= new TableColumn<>("High");
		TableColumn<MetaTraderDataFrame, Double> columnLow = new TableColumn<>("Low");
		TableColumn<MetaTraderDataFrame, Double> columnClose = new TableColumn<>("Close");
		TableColumn<MetaTraderDataFrame, Double> columnRsi = new TableColumn<>("RSI");
		TableColumn<MetaTraderDataFrame, Double> columnAtr= new TableColumn<>("ATR");
		
		table.getColumns().addAll(columnSymbol,columnOpen,columnHigh,columnLow,columnClose,columnRsi,columnAtr);
		
		Label calculateLabel = new Label("Stop loss:");
		
		TextField calculateField = new TextField();
		
		FlowPane calcBox = new FlowPane();
		calcBox.getChildren().add(calculateLabel);
		calcBox.getChildren().add(calculateField);
		
		Button calculateButton = new Button("Calculate");
		calculateButton.setOnAction((ActionEvent actionEvent) -> {
			
		});
		
		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				
			}
		});
		
		pane.add(areaLabel, 0, 0);
		pane.add(textArea, 0, 1);
		pane.add(table, 1, 1);
		pane.add(calculateButton, 1, 2);
		pane.add(calcBox, 0, 2);
		
		return pane;
	}

}
