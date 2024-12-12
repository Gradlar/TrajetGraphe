
import java.io.IOException;

import fr.ulille.but.sae_s2_2024.ModaliteTransport;
import fr.ulille.but.sae_s2_2024.Trancon;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Interface extends Application {
    private Logistic log;
    private Voyageur utilisateur;

    public Logistic getLogTemps() {
        return this.log;
    }

    public Voyageur getUtilisateur() {
        return this.utilisateur;
    }

    @Override
    public void start(Stage primaryStage){
        VBox root = new VBox();
        Image image2 = new Image("file://../res/Arrow-Down_BL.png");
        root.setStyle("-fx-background-color: #C7E1EA; ");

        this.utilisateur = new Voyageur(null,"temps", null);
        this.log = new Logistic(this.utilisateur, null, null);

        log.csvFileLogistic("./data.csv");
        log.csvFileLogistic("./dataCorrespondance.csv");
        log.csvFileLogistic("./save.csv");

        log.createVilles();
        log.createDirection();
        log.correspondance();
        log.initialisationGraphe();

        
        Label labelGraph = new Label("");

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(20.0);
        menuBar.setPrefWidth(660.0);
        menuBar.setMaxHeight(Region.USE_PREF_SIZE);
        menuBar.setStyle("-fx-background-color: rgba(149, 208, 229, 1); -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 3px 0px 0px 0px;");


        MenuItem mi8 = new MenuItem("Préférences");
        MenuItem mi9 = new MenuItem("Historique");
        MenuItem mi10 = new MenuItem("Save");


        Menu fileMenu = new Menu();
        fileMenu.setStyle("-fx-background-color: rgba(0, 36, 59, 1);");
        fileMenu.getItems().addAll(
                mi8,
                new SeparatorMenuItem(),
                mi9,
                new SeparatorMenuItem(),
                mi10
        );

        mi8.setOnAction(e ->{
            Stage newStage = new Stage();
            VBox vBox = new VBox();
            vBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
            vBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
            HBox hBox = new HBox();
            HBox hBox2 = new HBox();
            Button b1 = new Button("Avion");
            Button b2 = new Button("Train");
            Button b3 = new Button("Bus");
            Button b4 = new Button("Aucune");

            Label votreNom = new Label("Quelle est votre nom : ");
            TextField txtField = new TextField();

            Label label1 = new Label("Quel est votre moyen de locomotion : ");
            label1.setStyle("-fx-alignment: center;");
            label1.setPadding(new Insets(10, 0, 10, 0));

            b1.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            b2.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            b3.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            b1.getStyleClass().add("combo-box-2");
            b1.setLayoutX(25.0);
            b1.setLayoutY(40.0);
            b1.setPrefWidth(150.0);
            b2.getStyleClass().add("combo-box-2");
            b2.setLayoutX(25.0);
            b2.setLayoutY(40.0);
            b2.setPrefWidth(150.0);
            b3.getStyleClass().add("combo-box-2");
            b3.setLayoutX(25.0);
            b3.setLayoutY(40.0);
            b3.setPrefWidth(150.0);
            b4.getStyleClass().add("combo-box-2");
            b4.setLayoutX(25.0);
            b4.setLayoutY(40.0);
            b4.setPrefWidth(150.0);
            b4.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");

            b1.setAlignment(Pos.CENTER);
            b1.setContentDisplay(ContentDisplay.CENTER);
            b1.setPrefHeight(29.0);
            b1.setPrefWidth(151.0);
            b1.setScaleShape(false);

            b1.setOnAction(event1 ->{
                log.setModalité(ModaliteTransport.AVION);
                b1.setStyle("-fx-background-color: rgba(0, 36, 59, 1); -fx-background-radius: 15px; -fx-border-color: white; -fx-border-color: white;  -fx-border-radius: 10px; -fx-border-width: 1.5px");
                b2.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                b3.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                newStage.close();
            });

            b2.setOnAction(event2 ->{
                log.setModalité(ModaliteTransport.TRAIN);
                b2.setStyle("-fx-background-color: rgba(0, 36, 59, 1); -fx-background-radius: 15px; -fx-border-color: white; -fx-border-color: white;  -fx-border-radius: 10px; -fx-border-width: 1.5px");
                b1.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                b3.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                newStage.close();;
            });

            b3.setOnAction(event3 ->{
                log.setModalité(ModaliteTransport.BUS);
                b3.setStyle("-fx-background-color: rgba(0, 36, 59, 1); -fx-background-radius: 15px; -fx-border-color: white; -fx-border-color: white;  -fx-border-radius: 10px; -fx-border-width: 1.5px");
                b2.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                b1.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                newStage.close();;
            });

            b4.setOnAction(event4 ->{
                log.setModalité(null);
                b1.setStyle("-fx-background-color: rgba(0, 36, 59, 1); -fx-background-radius: 15px; -fx-border-color: white; -fx-border-color: white;  -fx-border-radius: 10px; -fx-border-width: 1.5px");
                b2.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                b3.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
                newStage.close();
            });

            txtField.setOnAction(event5 ->{
                log.setNom(txtField.getText());
            });

            HBox.setMargin(hBox, new Insets(10, 10, 10, 10));

            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(b1,b2,b3,b4);

            hBox2.setSpacing(20);
            hBox2.setAlignment(Pos.CENTER_LEFT);
            hBox2.getChildren().addAll(votreNom, txtField);
            
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(hBox2 ,label1, hBox);

            Scene scene = new Scene(vBox);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            newStage.getIcons().add(image2);
            newStage.setScene(scene);
            newStage.setTitle("Préferences");
            newStage.show();
        });

        mi9.setOnAction(e ->{
            Stage newStage = new Stage();
            VBox vBoxNewWindow = new VBox();
            vBoxNewWindow.setMaxHeight(Region.USE_COMPUTED_SIZE);
            vBoxNewWindow.setMaxWidth(Region.USE_COMPUTED_SIZE);
            TextArea labelHistorique = new TextArea();
            try {
                labelHistorique.setText(log.deserializeHistory());
            } catch (ClassNotFoundException | IOException e1) {
                e1.printStackTrace();
            }

            vBoxNewWindow.getChildren().add(labelHistorique);
            VBox.setVgrow(labelHistorique, javafx.scene.layout.Priority.ALWAYS);

            Scene scene = new Scene(vBoxNewWindow);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            newStage.getIcons().add(image2);
            newStage.setScene(scene);
            newStage.setTitle("Historique");
            newStage.show();
        });

        HBox titleFileMenu = new HBox();

        Image image1 = new Image("file://../res/Arrow-Down.png");
        ImageView imageView1 = new ImageView(image1);

        imageView1.setFitHeight(14);
        imageView1.setFitWidth(14);
        Label titleLabel = new Label("Menu");
        titleLabel.setStyle("-fx-text-fill: white;");

        HBox.setMargin(imageView1, new Insets(2, 0, 0, 5));

        titleFileMenu.getChildren().addAll(titleLabel,imageView1);

        fileMenu.setGraphic(titleFileMenu);

        
        menuBar.setPadding(new Insets(5, 0, 5, 5));
        menuBar.getMenus().addAll(fileMenu);


        HBox contentBox = new HBox();
        contentBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        contentBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        contentBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        contentBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
        VBox.setVgrow(contentBox, Priority.ALWAYS);

        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.setPrefHeight(375.0);
        leftVBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        leftVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        leftVBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
        leftVBox.setStyle("-fx-background-color: C7E1EA; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 3px 1.5px 0px 0px; -fx-border-radius: 0px 0px 0px 0px;");
        HBox.setHgrow(leftVBox, Priority.SOMETIMES);

        Label label1 = new Label("Ville de départ:");
        label1.setAlignment(Pos.CENTER);
        label1.setContentDisplay(ContentDisplay.CENTER);
        label1.setPrefHeight(29.0);
        label1.setPrefWidth(151.0);
        label1.setScaleShape(false);
        label1.setText("ViIle de départ:");
        VBox.setMargin(label1, new Insets(0.0, 0.0, 0.0, 0.0));

        ComboBox<Ville> comboBox1 = new ComboBox<Ville>();
        comboBox1.getStyleClass().add("combo-box-1");
        comboBox1.setPrefWidth(150.0);
        comboBox1.setPromptText("Sélectionner");
        comboBox1.setStyle("-fx-background-color: rgba(0, 36, 59, 1); -fx-background-radius: 15px; -fx-border-color: white; -fx-border-color: white;  -fx-border-radius: 10px; -fx-border-width: 1.5px");
        for(Ville v : log.getVilles()){
            comboBox1.getItems().add(v);
        }

        Label label2 = new Label("Ville d'arrivée :");
        VBox.setMargin(label2, new Insets(10.0, 0.0, 0.0, 0.0));

        ComboBox<Ville> comboBox2 = new ComboBox<Ville>();
        comboBox2.getStyleClass().add("combo-box-2");
        comboBox2.setLayoutX(25.0);
        comboBox2.setLayoutY(40.0);
        comboBox2.setPrefWidth(150.0);
        comboBox2.setPromptText("Sélectionner");
        comboBox2.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
        comboBox2.getStyleClass().add("combo-box2");
        for(Ville v : log.getVilles()){
            comboBox2.getItems().add(v);
        }
        VBox.setMargin(comboBox2, new Insets(5.0, 0.0, 0.0, 0.0));

        VBox vBox = new VBox();
        vBox.setPrefWidth(190);
        vBox.setPrefHeight(210);
        vBox.setMaxWidth(Region.USE_PREF_SIZE);
        vBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        vBox.setMinWidth(Region.USE_PREF_SIZE);
        vBox.setMinHeight(Region.USE_COMPUTED_SIZE);
        vBox.setStyle("-fx-background-color: C7E1EA; -fx-background-radius: 10px; -fx-border-radius: 10px;");
        VBox.setMargin(vBox, new Insets(10.0, 0.0, 0.0, 0.0));
        VBox.setVgrow(vBox, Priority.SOMETIMES);

        Label label3 = new Label("Correspondances :");
        label3.setLayoutX(20.0);
        label3.setLayoutY(74.0);
        VBox.setMargin(label3, new Insets(20.0, 0.0, 0.0, 10.0));

        Label label4 = new Label("Etapes :");
        label4.setLayoutX(24.0);
        label4.setLayoutY(117.0);
        VBox.setMargin(label4, new Insets(25.0, 0.0, 0.0, 10.0));

        comboBox1.setOnAction(e ->{
            log.setDepart(comboBox1.getValue());
            log.setArrivee(comboBox2.getValue());
            try {
                log.serializeHistory();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            labelGraph.setText((log.toStringCorrespondance(3, 0)));
        });

        comboBox2.setOnAction(e ->{
            log.setDepart(comboBox1.getValue());
            log.setArrivee(comboBox2.getValue());
            try {
                log.serializeHistory();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            labelGraph.setText((log.toStringCorrespondance(3, 0)));
        });

        labelGraph.setPadding(new Insets(10, 10, 10, 10));
        labelGraph.getStyleClass().add("label-graphe");

        vBox.getChildren().addAll(label3, label4);

        leftVBox.getChildren().addAll(label1, comboBox1, label2, comboBox2, vBox);

        VBox rightVBox = new VBox();
        rightVBox.setPrefHeight(374.0);
        rightVBox.setPrefWidth(458.0);
        rightVBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
        rightVBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
        rightVBox.setStyle("-fx-background-color: EDF6F9; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 3px 0px 0px 1.5px; -fx-border-radius: 0px 0px 0px 0px;");
        HBox.setHgrow(rightVBox, Priority.ALWAYS);

        VBox rightVBox2 = new VBox();

        HBox rightHBox = new HBox();
        rightHBox.setSpacing(20);

        Label labelRight = new Label("Critères de sélection :");
        labelRight.getStyleClass().add("label-critere");
        labelRight.setPadding(new Insets(5, 0, 0, 0));
        Button bTemps = new Button("Temps");
        Button bPrix = new Button("Prix");
        Button bPollution = new Button("Pollution");

        bTemps.setOnAction(event4 -> {
            log.setCritere("temps");
            bTemps.setTextFill(Color.WHITE);
            bPrix.setTextFill(Color.BLACK);
            bPollution.setTextFill(Color.BLACK);
            bTemps.setStyle("-fx-background-color: rgba(0, 36, 59, 1);  -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(235, 235, 235, 1); -fx-border-width: 1.5px; ");
            bPrix.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            bPollution.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            this.log.modifierPoidsGraphe();
            labelGraph.setText((log.toStringCorrespondance(3, 0)));
        });

        bPrix.setOnAction(event5 -> {
            log.setCritere("prix");
            bPrix.setTextFill(Color.WHITE);
            bTemps.setTextFill(Color.BLACK);
            bPollution.setTextFill(Color.BLACK);
            bPrix.setStyle("-fx-background-color: rgba(0, 36, 59, 1);  -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(235, 235, 235, 1); -fx-border-width: 1.5px; ");
            bTemps.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            bPollution.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            this.log.modifierPoidsGraphe();
            labelGraph.setText((log.toStringCorrespondance(3, 0)));
        });

        bPollution.setOnAction(event4 -> {
            log.setCritere("co2");
            bPollution.setTextFill(Color.WHITE);
            bPrix.setTextFill(Color.BLACK);
            bTemps.setTextFill(Color.BLACK);
            bPollution.setStyle("-fx-background-color: rgba(0, 36, 59, 1);  -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(235, 235, 235, 1); -fx-border-width: 1.5px; ");
            bPrix.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            bTemps.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
            this.log.modifierPoidsGraphe();
            labelGraph.setText((log.toStringCorrespondance(3, 0)));
        });

        bTemps.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
        bPrix.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");
        bPollution.setStyle("-fx-background-color: rgba(235, 235, 235, 1); -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 1.5px;");

        bTemps.getStyleClass().add("combo-box-2");
        bTemps.setLayoutX(25.0);
        bTemps.setLayoutY(40.0);
        bTemps.setPrefWidth(150.0);

        bPrix.getStyleClass().add("combo-box-2");
        bPrix.setLayoutX(25.0);
        bPrix.setLayoutY(40.0);
        bPrix.setPrefWidth(150.0);

        bPollution.getStyleClass().add("combo-box-2");
        bPollution.setLayoutX(25.0);
        bPollution.setLayoutY(40.0);
        bPollution.setPrefWidth(150.0);

        rightHBox.setAlignment(Pos.TOP_CENTER);
        rightVBox2.setAlignment(Pos.TOP_CENTER);

        rightVBox2.setPrefHeight(75.0);
        rightVBox2.setPrefWidth(200.0);
        rightVBox2.setMaxHeight(Region.USE_COMPUTED_SIZE);
        rightVBox2.setMaxWidth(Region.USE_COMPUTED_SIZE);
        rightVBox2.setStyle("-fx-background-color: EDF6F9; -fx-border-color: rgba(0, 36, 59, 1); -fx-border-width: 0px 0px 0px 0px; -fx-border-radius: 0px 0px 0px 0px;");

        rightHBox.setPadding(new Insets(5, 5, 0, 5));

        rightHBox.getChildren().addAll(bTemps, bPrix, bPollution);
        rightVBox2.getChildren().addAll(labelRight, rightHBox);
        rightVBox.getChildren().addAll(rightVBox2 , labelGraph);

        contentBox.getChildren().addAll(leftVBox, rightVBox);
        root.getChildren().addAll(menuBar, contentBox);
        root.setPrefHeight(720);
        root.setPrefWidth(1080);
        root.setMaxHeight(Region.USE_COMPUTED_SIZE);    
        root.setMaxWidth(Region.USE_COMPUTED_SIZE);
        

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.getIcons().add(image2);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CorresPondo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}