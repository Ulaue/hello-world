package application;

import javafx.scene.control.MenuItem;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Main extends Application {
	
	public double startX;
	public double startY;
	public Group groupForRectangles;
	public Rectangle newRectangle = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();
			root.setHgap(00);
			root.setVgap(0);
			primaryStage.setTitle("ImageProcessor 2.0");
			
			// Create Labels
//			Label lblFile = new Label("File");
//			Label lblEdit = new Label("Edit");
//			Label lblHelp = new Label("Help");
			
			// Create HBox for Labels
//			HBox hboxLabels = new HBox();
//			hboxLabels.setSpacing(20);
			
			// Add Labels to HBox
//			hboxLabels.getChildren().addAll(lblFile, lblEdit, lblHelp);
			
			// Add HBox to root
//			root.add(hboxLabels, 1, 1);
			
			// Create MenuBar
			MenuBar menuBar = new MenuBar();
			// Second MenuBar for big MenuBar ^^
			MenuBar menuBar2 = new MenuBar();
			
			// Menu File
			Menu menuFile = new Menu("File");
			Menu menuEdit = new Menu("Edit");
			Menu menuHelp = new Menu("Help");
			Menu menuEmpty = new Menu("");
			
			// Create MenuItems
			MenuItem itemSave = new MenuItem("Open");
			MenuItem itemSaveAs = new MenuItem("Save As");
			MenuItem itemExit = new MenuItem("Exit");
			
			// Menu add to MenuBar
			// Vielleicht noch die Items ab‰ndern, weil scheiﬂ Inhalte und so
			menuFile.getItems().add(itemSave);
			menuFile.getItems().add(itemSaveAs);
			menuFile.getItems().add(itemExit);
			menuBar.getMenus().addAll(menuFile, menuEdit, menuHelp);
			menuBar2.getMenus().add(menuEmpty);
			
			// Add to root
			root.add(menuBar, 1, 1);
			root.add(menuBar2, 2, 1);
			
			// Load Image
			Image img = new Image("porg.jpg");
			ImageView image = new ImageView();
			image.setImage(img);
			image.setFitWidth(850);
			image.setFitHeight(600);
			
			// Create StackPane for Image
			StackPane stackPaneImage = new StackPane();
			//HBox.setMargin(image, new Insets(0,0,0,0));
			
			// Add Image to HBox
			stackPaneImage.getChildren().add(image);
			
			// EventHandler for Rectangles
			stackPaneImage.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event)
				{
					startX = event.getX();
					startY = event.getY();
					
					newRectangle = new Rectangle();
					
					// Set style for Rectangle
					newRectangle.setFill(Color.SNOW);
					newRectangle.setOpacity(0.3);
					
					// Add Rectangle
					stackPaneImage.getChildren().add(newRectangle);
					
					// Set Allignment
					StackPane.setAlignment(newRectangle, Pos.TOP_LEFT);
				}
			});
			
			stackPaneImage.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event)
				{
					double endX = event.getX();
					double endY = event.getY();
					
					newRectangle.setX(startX);
					newRectangle.setY(startY);
					newRectangle.setWidth(endX - startX);
					newRectangle.setHeight(endY - startY);
					
					double newStartX = startX;
					double newStartY = startY;
					
					if( newRectangle.getWidth() < 0)
					{
						newRectangle.setWidth( - (newRectangle.getWidth()));
						newStartX = newRectangle.getX() - newRectangle.getWidth();
					}
					
					if( newRectangle.getHeight() < 0)
					{
						newRectangle.setHeight( - (newRectangle.getHeight()));
						newStartY = newRectangle.getY() - newRectangle.getHeight();
					}
					StackPane.setMargin(newRectangle, new Insets(newStartY, 0, 0, newStartX));
					
				}
			});
			
			// Add StackPane to root and setAlignment
			root.add(stackPaneImage, 1, 2);
			
			// Create HBox for Accordion
			HBox hBoxAccordion = new HBox();
			
			GridPane gridTP = new GridPane();
			gridTP.setVgap(4);
			gridTP.setHgap(4);
			gridTP.setPadding(new Insets(3,0,3,3));
			
			//Create Accordion
			Accordion layout = new Accordion();
			
			//gridTP.setGridLinesVisible(true);
			//Create Slider 
			Slider bluriness = new Slider();
			bluriness.setValue(50);
			
			//Create Button
			Button btn = new Button("Button 1");
			Button btnApply = new Button("Apply");
			
			//Create RadioButton 
			RadioButton rbtnImage = new RadioButton("Whole Image");
			RadioButton rbtnSelection = new RadioButton("Selection");
			
			// Toggle RadioButton
			ToggleGroup group = new ToggleGroup();
			rbtnImage.setToggleGroup(group);
			rbtnImage.setSelected(true);
			rbtnSelection.setToggleGroup(group);
			
			//Create TitledPane
			TitledPane tp  = new TitledPane("Blur", btn);
			TitledPane tp1 = new TitledPane("Comic", new Label("Hier kˆnnte noch etwas stehen"));
			TitledPane tp2 = new TitledPane("Edge", new Label("Hier auch"));
			
			//Add to GridPane
			gridTP.add(new Label("Blurness"), 1, 1);
			gridTP.add(bluriness, 2, 1);
			gridTP.add(new Label("Area"), 1,3);
			gridTP.add(rbtnImage, 2, 3);
			gridTP.add(rbtnSelection, 2, 4);
			
			// Create AnchorPane for BlurContent
			AnchorPane anchorBlur = new AnchorPane();
			AnchorPane.setTopAnchor(gridTP, 10.0);
			AnchorPane.setBottomAnchor(btnApply, 5.0);
			AnchorPane.setRightAnchor(btnApply, 5.0);
			
			anchorBlur.getChildren().addAll(gridTP, btnApply);
			
			//Add to titledPane
			tp.setContent(anchorBlur);
			
			//Add Accordion
			layout.getPanes().addAll(tp, tp1, tp2);
			
			// Add Accordion to HBox
			hBoxAccordion.getChildren().add(layout);
			
			// Add HBox to root
			root.add(hBoxAccordion, 2, 2);
			root.setAlignment(Pos.CENTER);
			
			Scene scene = new Scene(root,1125,625);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
