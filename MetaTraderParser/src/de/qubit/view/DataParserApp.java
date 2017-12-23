package de.qubit.view;

import de.qubit.controller.DataFrameValidator;
import de.qubit.controller.StopLossCalculator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MetaTraderDataFrame;
import javafx.scene.paint.Color;

public class DataParserApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = createGUIElements();
		Scene scene = new Scene(root, 1200, 400);
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
		TableColumn<MetaTraderDataFrame, String> columnOpen = new TableColumn<>("Open");
		TableColumn<MetaTraderDataFrame, String> columnHigh = new TableColumn<>("High");
		TableColumn<MetaTraderDataFrame, String> columnLow = new TableColumn<>("Low");
		TableColumn<MetaTraderDataFrame, String> columnClose = new TableColumn<>("Close");
		TableColumn<MetaTraderDataFrame, String> columnRsi = new TableColumn<>("RSI");
		TableColumn<MetaTraderDataFrame, String> columnAtr = new TableColumn<>("ATR");

		columnSymbol.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("symbol"));
		columnOpen.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("open"));
		columnHigh.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("high"));
		columnLow.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("low"));
		columnClose.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("close"));
		columnRsi.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("rsi"));
		columnAtr.setCellValueFactory(new PropertyValueFactory<MetaTraderDataFrame, String>("atr"));

		table.getColumns().addAll(columnSymbol, columnOpen, columnHigh, columnLow, columnClose, columnRsi, columnAtr);

		Label calculateLabel = new Label("Stop loss:");

		TextField calculateField = new TextField();
		FlowPane calcBox = new FlowPane();
		calcBox.getChildren().add(calculateLabel);
		calcBox.getChildren().add(calculateField);

		Button calculateButton = new Button("Calculate");
		calculateButton.setOnAction((ActionEvent actionEvent) -> {
			MetaTraderDataFrame dataframe = MetaTraderDataFrame.getInstance();
			StopLossCalculator calc = new StopLossCalculator();
			double stopLoss = calc.calculateStopLoss(dataframe.getClose(), dataframe.getAtr());
			calculateField.insertText(0,  String.valueOf(stopLoss));
		});

		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				DataFrameValidator validator = new DataFrameValidator(newValue);
				if (validator.contentIsValid()) {
					validator.createDataFrame();
					MetaTraderDataFrame dataframe = validator.getDataFrame();
					table.getItems().add(dataframe);
					textArea.setBackground(
							new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));

				} else {
					String currentContent = textArea.getText();
					// String errorMessage = "Content from dataframe is not
					// valid:\n"+currentContent;
					textArea.setBackground(
							new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
					// throw new IllegalArgumentException(errorMessage);
				}

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
