package pl.lublin.wsei.java.cwiczenia;

import javafx.application.HostServices;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class Controller {
    public Label lbFile;
    public ImageView imgMiniaturka;
    public Button btnPokazInfografike;
    public TextField txtAdresStrony;
    public Button btnPrzejdzDoStrony;

    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Pliki XML (*.xml)", "*.xml");

    public ListView lstInfografiki;
    ObservableList<String> tytuly = FXCollections.observableArrayList();
    GusInfoGraphicList igList;

    private Infografika selInfografika;
    private Stage stage;
    private HostServices hostServices;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    public void setHostServices(HostServices hostServices){
        this.hostServices = hostServices;
    }

    @FXML
    public void initialize(){
        fileChooser.getExtensionFilters().add(xmlFilter);
        lstInfografiki.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number old_val, Number new_val) {
                        int index = new_val.intValue();
                        if (index != -1 ){
                            selInfografika = igList.infografiki.get(index);
                            txtAdresStrony.setText(selInfografika.adresStrony);
                            Image image = new Image(selInfografika.adresMiniaturki);
                            imgMiniaturka.setImage(image);

                        }
                        else{
                            txtAdresStrony.setText("");
                            imgMiniaturka.setImage(null);
                            selInfografika = null;
                        }
                    }
                }
        );
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file !=null){
            igList = new GusInfoGraphicList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            for (Infografika ig: igList.infografiki) tytuly.add(ig.tytul);
            lstInfografiki.setItems(tytuly);
        }
        else {
            lbFile.setText("Proszę wczytać plik ...");
        }
    }
    public void btnZaladujStrone(ActionEvent actionEvent){
        if (selInfografika != null)
            hostServices.showDocument(selInfografika.adresStrony);
    }
}
