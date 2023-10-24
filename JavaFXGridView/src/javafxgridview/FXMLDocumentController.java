/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxgridview;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxgridview.cis.Requete_img;
import javafxgridview.cis.Index;

import javax.imageio.ImageIO;

/**
 *
 * @author Cool IT Help
 */
public class FXMLDocumentController implements Initializable {
    String path_req=new String();
    static List<BufferedImage> results = new ArrayList<>();
    
    @FXML
    private Label label;
    
    @FXML 
    private TilePane tilePane;
    @FXML
    private BorderPane goo;
    @FXML
    private TilePane tile_req;

    @FXML
    private AnchorPane myAnchor;
    
    int count = 0;
    
    private int nRows = 3;  //no of row for tile pane
    private int nCols = 3;  // no of column for tile pane
    
    private static final double ELEMENT_SIZE = 130;
    private static final double GAP = ELEMENT_SIZE / 10;
    static boolean loadIndexFromFile = false;
    static String imagesFolderPath = "data.img";
    static File defaultIndexFile = new File("index/index.txt");
    
     File filesJpg[]; // file array to store read images info
    
    
    @FXML
    private void byColor(ActionEvent event ){
        try {
            tilePane.getChildren().clear();

            Requete_img img=new Requete_img(path_req);
            boolean useTexture = false;
            boolean useColor = true;
            int binsPerColor = 8;

            ArrayList<Float> similarity_val= new ArrayList<>();

            Index index = new Index(binsPerColor, useTexture, useColor);
            // extraireDesc = true;
            int numberOfResults = 9;

            if (loadIndexFromFile) {
                index = Index.extraireDesc(defaultIndexFile);
            } else {
                index.addToIndex(imagesFolderPath);
                //index.saveToFile(defaultIndexFile);
            }

            for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
                similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
            }


            BufferedImage img2 = ImageIO.read(new File(path_req));
            List<File> topKMatches = index.Top(img2, numberOfResults);

            ////////
            ///////
            //results.add(img2);
            int j=0;
            for (File f : topKMatches) {
//                BufferedImage bi = ImageIO.read(f);
//                results.add(bi);
                ImageView img_res=new ImageView();
                img_res.setFitHeight(ELEMENT_SIZE);
                img_res.setFitWidth(ELEMENT_SIZE);
                Image im_res=new Image(f.getAbsolutePath());
                img_res.setImage(im_res);
                VBox v_res=new VBox(img_res);
                String x= String.valueOf(similarity_val.get(j)*100);
                Text  t1=new Text(x+"%");
                t1.setTextAlignment(TextAlignment.CENTER);
                j=j+1;

                tilePane.getChildren().add(v_res);
                v_res.getChildren().add(t1);
                v_res.setAlignment(Pos.BASELINE_CENTER);

            }

        }

        catch (IOException ex) {

            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void byforme(ActionEvent event ){
            try {
                tilePane.getChildren().clear();

                Requete_img img=new Requete_img(path_req);
                boolean useTexture = true;
                boolean useColor = false;
                int binsPerColor = 8;

                ArrayList<Float> similarity_val= new ArrayList<>();

                Index index = new Index(binsPerColor, useTexture, useColor);
                // extraireDesc = true;
                int numberOfResults = 9;

                if (loadIndexFromFile) {
                    index = Index.extraireDesc(defaultIndexFile);
                } else {
                    index.addToIndex(imagesFolderPath);
                    //index.saveToFile(defaultIndexFile);
                }

                for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
                    similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
                }



                BufferedImage img2 = ImageIO.read(new File(path_req));
                List<File> topKMatches = index.Top(img2, numberOfResults);

                ////////
                ///////
                //results.add(img2);
                int j=0;
                for (File f : topKMatches) {
//                BufferedImage bi = ImageIO.read(f);
//                results.add(bi);
                    ImageView img_res=new ImageView();
                    img_res.setFitHeight(ELEMENT_SIZE);
                    img_res.setFitWidth(ELEMENT_SIZE);
                    Image im_res=new Image(f.getAbsolutePath());
                    img_res.setImage(im_res);
                    VBox v_res=new VBox(img_res);
                    String x= String.valueOf(similarity_val.get(j)*100);
                    Text  t1=new Text(x+"%");
                    t1.setTextAlignment(TextAlignment.CENTER);
                    j=j+1;
                    tilePane.getChildren().add(v_res);
                    v_res.getChildren().add(t1);
                    v_res.setAlignment(Pos.BASELINE_CENTER);

                }


            }

            catch (IOException ex) {

                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    @FXML
    private void byAll(ActionEvent event ){
        try {
            tilePane.getChildren().clear();

            Requete_img img=new Requete_img(path_req);
            boolean useTexture = true;
            boolean useColor = true;
            int binsPerColor = 8;

            ArrayList<Float> similarity_val= new ArrayList<>();

            Index index = new Index(binsPerColor, useTexture, useColor);
            // extraireDesc = true;
            int numberOfResults = 9;

            if (loadIndexFromFile) {
                index = Index.extraireDesc(defaultIndexFile);
            } else {
                index.addToIndex(imagesFolderPath);
                //index.saveToFile(defaultIndexFile);
            }

            for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
                similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
            }



            BufferedImage img2 = ImageIO.read(new File(path_req));
            List<File> topKMatches = index.Top(img2, numberOfResults);

            ////////
            ///////
            //results.add(img2);
            int j=0;
            for (File f : topKMatches) {
//                BufferedImage bi = ImageIO.read(f);
//                results.add(bi);
                ImageView img_res=new ImageView();
                img_res.setFitHeight(ELEMENT_SIZE);
                img_res.setFitWidth(ELEMENT_SIZE);
                Image im_res=new Image(f.getAbsolutePath());
                img_res.setImage(im_res);
                VBox v_res=new VBox(img_res);
                String x= String.valueOf(similarity_val.get(j)*100);
                Text  t1=new Text(x+"%");
                t1.setTextAlignment(TextAlignment.CENTER);
                j=j+1;
                tilePane.getChildren().add(v_res);
                v_res.getChildren().add(t1);
                v_res.setAlignment(Pos.BASELINE_CENTER);

            }

        }

        catch (IOException ex) {

            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
@FXML
private void Reload(ActionEvent event){
    tile_req.getChildren().clear();
    tilePane.getChildren().clear();
}
    @FXML
    private void go(ActionEvent event){
        goo.getChildren().clear();
    }
    @FXML
    private void ChooseFile(ActionEvent event) {
        tilePane.getChildren().clear();
        //load Images on button click and open directory chooser
        Stage parent = (Stage)myAnchor.getScene().getWindow();
        
       
//        DirectoryChooser directoryChooser = new DirectoryChooser();
//        File selectedDirectory = directoryChooser.showDialog(parent);
        FileChooser f=new FileChooser();
        File selectedfile=f.showOpenDialog(parent);
        path_req=selectedfile.getAbsolutePath();
        System.out.println(path_req);
        ImageView imageView=new ImageView();
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        Image im_req=new Image(path_req);
        imageView.setImage(im_req);
        VBox v=new VBox(imageView);

        tile_req.getChildren().add(v);
        
        //now set image in tiles
        //createElements();
        
                        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tilePane.setPrefColumns(nCols);
        tilePane.setPrefRows(nRows);
        myAnchor.setStyle("-fx-background-image: background.jpeg;"); //-fx-background-color: rgba(0, 0, 0);
        //tilePane.setStyle("-fx-background-color: rgba(0, 0, 0);");
        tilePane.setHgap(GAP);
        tilePane.setVgap(GAP); 
        
        
    }    
    
//    private void createElements() {
//            tilePane.getChildren().clear();
//
//            for (int i = 0; i < nCols; i++) {
//                for (int j = 0; j < nRows; j++) {
//                    tilePane.getChildren().add(createPage(count));
//                    count++;
//
//                }
//            }
//        }

        
//    public VBox createPage(int index) {
//
//        ImageView imageView = new ImageView();
//
//        File file = filesJpg[index];
//        try {
//            BufferedImage bufferedImage = ImageIO.read(file);
//            Image image = new Image(file.toURI().toString());
//            imageView.setImage(image);
//            imageView.setFitWidth(ELEMENT_SIZE);
//            imageView.setFitHeight(ELEMENT_SIZE);
//           // imageView.setPreserveRatio(true);
//
//            imageView.setSmooth(true);
//            imageView.setCache(true);
//        } catch (IOException ex) {
//
//        }
//
//        VBox pageBox = new VBox();
//        pageBox.getChildren().add(imageView);
//        pageBox.setStyle("-fx-border-color: orange;");
//
//        imageView = null;
//        return pageBox;
//    }
    
}
