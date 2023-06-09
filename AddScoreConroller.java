import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddScoreConroller implements Initializable {

    @FXML
    private FlowPane card;

    @FXML
    private Label date;

    // @FXML
    // private Label goals1;

    // @FXML
    // private Label goals2;

    @FXML
    private Label team1;

    @FXML
    private Label team2;

    @FXML
    private Label tournament;

    @FXML
    private ScrollPane scrollPane;

    /**
     * @param event
     */
    @FXML
    void DateClicked(ActionEvent event) throws IOException {

        VBox vbox = new VBox();
        ArrayList<Tournament> tournaments = App.getTournaments();
        // System.out.println("))))))))))))))))"+tournaments.get(0).getMatches());
        for (int i = 0; i < tournaments.size(); i++) {
            ArrayList<Match> matches = tournaments.get(i).getMatches();
            for (int j = 0; j < matches.size(); j++) {
                LocalDate today = LocalDate.now();
                // if (!(matches.get(j).getDate().isAfter(today))) {
                Match match = matches.get(j);
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefSize(353.0, 100.0);
                anchorPane.setTopAnchor(anchorPane, 0.0);
                anchorPane.setLeftAnchor(anchorPane, 0.0);
                anchorPane.setBottomAnchor(anchorPane, 0.0);
                anchorPane.setRightAnchor(anchorPane, 0.0);

                FlowPane card = new FlowPane();
                card.setPrefSize(318.0, 152.0);
                card.setLayoutX(18.0);
                card.setLayoutY(27.0);
                // card.setEffect(new ColorAdjust(-0.1, -1.0, 1.0, 1.0));

                Line line = new Line();
                line.setStartX(-100.0);
                line.setEndX(217.2928924560547);
                line.setEndY(-0.621320366859436);
                line.setStrokeWidth(1.0);
                line.setStroke(Color.BLACK);

                Label team1 = new Label(match.getTeam1());
                team1.setPrefSize(144.0, 29.0);
                team1.setAlignment(Pos.CENTER);
                team1.setFont(new Font(24.0));

                Label vs = new Label("vs");

                Label team2 = new Label(match.getTeam2());
                team2.setPrefSize(151.0, 29.0);
                team2.setAlignment(Pos.CENTER);
                team2.setFont(new Font(24.0));

                // Label goals1 = new Label(match.getScore().get(0).toString(i));
                // goals1.setPrefSize(143.0, 38.0);
                // goals1.setAlignment(Pos.CENTER);

                TextField goals1 = new TextField();
                goals1.setPromptText("Enter the goals");
                goals1.setPrefSize(100, 30);
                //goals1.setPadding(new Insets(0, 50, 0, 50));
                // goals1.setVisible(true);
                // // goals1.setDisable(false);
                // goals1.setEditable (true);

                Label vs2 = new Label("vs");

                // Label goals2 = new Label(match.getScore().get(1).toString(i));
                // goals2.setPrefSize(141.0, 38.0);
                // goals2.setAlignment(Pos.CENTER);

                TextField goals2 = new TextField();
                goals2.setPromptText("Enter the goals");
                goals2.setPrefSize(100, 30);
                //goals2.setPadding(new Insets(0, 50, 0, 50));
                // goals2.setDisable(false);
                // goals2.setEditable (true);

                Label tournament = new Label(tournaments.get(i).tString());
                tournament.setPrefSize(317.0, 34.0);
                tournament.setAlignment(Pos.CENTER);

                Label date = new Label(match.getDate().toString());
                date.setPrefSize(267.0, 34.0);
                date.setAlignment(Pos.CENTER);

                Button saveButton = new Button("Save");
                saveButton.setPrefSize(50, 34.0);

                if (goals1.getText() != null && goals2.getText() != null) {
                    saveButton.setOnAction(event2 -> {
                        try {
                            SaveCleched(event2, goals1.getText(), goals2.getText(), match);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    });
                }

                card.getChildren().addAll(team1, vs, team2, goals1, vs2, goals2, tournament, date, saveButton, line);
                card.setMargin(goals1, new Insets(10, 0, 5, 25));
                card.setMargin(goals2, new Insets(10, 0, 5, 0));
                card.setMargin(vs2, new Insets(10, 20, 5, 20));

                ColorAdjust effect = new ColorAdjust();
                effect.setBrightness(-0.1);
                effect.setContrast(-1.0);
                effect.setHue(1.0);
                effect.setSaturation(1.0);

                // card.setEffect(effect);

                anchorPane.getChildren().add(card);
                vbox.getChildren().add(anchorPane); // add each AnchorPane to the VBox
                // }
            }
        }
        scrollPane.setContent(vbox); // set the VBox as the content of the ScrollPane
    }

    @FXML
    void HomeClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void MatchesClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Matches.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ProfileClicked(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void SaveCleched(ActionEvent event, String goals1, String goals2 , Match match) throws IOException {
        //System.out.println(goals1 + "  " + goals2);
        int gol1 = Integer.valueOf(goals1);
        int gol2 = Integer.valueOf(goals2);
        match.recordScore(gol1 ,gol2);
        App.write();
        System.out.println(match.getScore().get(0)+""+match.getScore().get(1));


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // dateChosen.setValue(LocalDate.now());
        try {
            DateClicked(null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}