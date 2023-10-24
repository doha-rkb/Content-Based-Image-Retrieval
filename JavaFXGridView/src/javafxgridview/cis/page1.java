//package javafxgridview.cis;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class page1 extends JFrame {
//    Container cont=null;
//    JPanel p1=null;
//    JPanel p2=null;
//    JButton color=null;
//    JButton texture=new JButton();
//    JButton contenu=new JButton();
//    JButton Bsearch=null;
//    JButton exit=null;
//    JLabel searchby =null;
//    FileChooser f=null;
//    String path_req=new String();
//    static List<BufferedImage> results = new ArrayList<>();
//    static File defaultIndexFile = new File("index/index.txt");
//    static boolean loadIndexFromFile = false;
//    static String imagesFolderPath = "data.img/data.img";
//    public page1() throws IOException {
//        GridLayout grid=new GridLayout(1,2);
//        JPanel fenetre=new JPanel();
//        fenetre.setLayout(grid);
//
//        cont=getContentPane();
//        this.setBounds(0,0,900,700);
//        this.setTitle("search image by content");
//        //////
//        p1=new JPanel();
//        //p1.setLayout(new FlowLayout(FlowLayout.LEFT));
//        //p1.setBounds(0,0,300,500);
//        ///p2 contient deux panels P2_img,p2_sim
//        p2=new JPanel();
//        GridLayout grid_p2=new GridLayout(3,3);
//        p2.setLayout(grid_p2);
//        p2.setBackground(Color.lightGray);
//        //pannel p2_image
//        JPanel p1_search=new JPanel();
//        GridLayout grid_P1=new GridLayout(2,1);
//        JPanel p1_sim=new JPanel();
//        p1.setLayout(grid_P1);
//        GridLayout grid_p1_sim=new GridLayout(1,2);
//        grid_p1_sim.setHgap(20);
//        JPanel p1_sim_req=new JPanel();
//        JPanel p1_sim_sim=new JPanel();
//        p1_sim.setLayout(grid_p1_sim);
//        GridLayout grid_p1_sim_sim=new GridLayout(10,1);
//        p1_sim_sim.setLayout(grid_p1_sim_sim);
//        p1_sim_req.setLayout(new FlowLayout(FlowLayout.CENTER));
//        //p2.setBounds(350,0,600,500);
//        //p2.setBackground(Color.lightGray);
//        //ajouter l'image
//        BufferedImage img = ImageIO.read(new File("C:\\Users\\hp\\OneDrive\\Bureau\\java tp\\mini_p\\src\\search1.png"));
//        JLabel pic = new JLabel(new ImageIcon(img));
//        p1_search.add(pic);
//        p1_search.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//        //p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        //p3.setLayout(new GridLayout(2,2));
//        //ajouter le label de recherche
//        Bsearch =new JButton("Choisir une image");
//        Bsearch.setSize(10,10);
//        //l'option de recherche :
//        //color
//        color=new JButton("color");
//        color.setBounds(500,500,10,10);
//        exit =new JButton("exit");
//        exit.setBounds(500,500,10,10);
//        //texte
//        texture=new JButton("forme");
//        texture.setBounds(500,500,10,10);
//        //forme
//        contenu=new JButton("contenu");
//        contenu.setBounds(500,500,10,10);
//        //act1 permet de lancer l action de cherche une image en utilisant File_chooser et on recupere ensuite le path dans path_req
//        ActionListener act1=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                FileChooser f=new FileChooser();
//                path_req=f.get_path();
//            }
//        };
//
//        //rechercher par couleurs
//        ActionListener act2=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//
//                    Requete_img img=new Requete_img(path_req);
//                    boolean useTexture = false;
//                    boolean useColor = true;
//                    int binsPerColor = 8;
//
//                    ArrayList<Float> similarity_val= new ArrayList<>();
//
//                    Index index = new Index(binsPerColor, useTexture, useColor);
//                    // extraireDesc = true;
//                    int numberOfResults = 9;
//
//                    if (loadIndexFromFile) {
//                        index = Index.extraireDesc(defaultIndexFile);
//                    } else {
//                        index.addToIndex(imagesFolderPath);
//                        //index.saveToFile(defaultIndexFile);
//                    }
//
//                    for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
//                        similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
//                    }
//
//                    BufferedImage img2 = ImageIO.read(new File(path_req));
//                    List<File> topKMatches = index.Top(img2, numberOfResults);
//                    JLabel j_sim_req= new JLabel(new ImageIcon(img2.getScaledInstance(300,450, Image.SCALE_DEFAULT)));
//                    j_sim_req.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
//                    p1_sim_req.add(j_sim_req);
//                    //results.add(img2);
//                    for (File f : topKMatches) {
//                        BufferedImage bi = ImageIO.read(f);
//                        results.add(bi);
//                    }
//
//                    int count = 0;
//                    JLabel sim=new JLabel("similarities:");
//                    sim.setFont(new Font("serif",Font.CENTER_BASELINE,20));
//                    p1_sim_sim.add(sim);
//                    for (BufferedImage bi : results) {
//                        JPanel p_cmp=new JPanel();
//                        p_cmp.setLayout(new BorderLayout());
//                        p1_sim_sim.add(new JLabel((count+1)+" => "+String.valueOf(similarity_val.get(count))+"\n"));
//                        int tmp=count+1;
//                        //p_cmp.add(new JLabel("" + tmp),BorderLayout.WEST);
//                        count++;
//                        ImageIcon imageIcon = new ImageIcon(
//                                bi.getScaledInstance(200,200, Image.SCALE_DEFAULT));
//                        JLabel label = new JLabel(imageIcon);
//                        p_cmp.add(label,BorderLayout.CENTER);
//                        JLabel simm=new JLabel("                 Similarite: "+String.valueOf(similarity_val.get(tmp-1)));
//                        simm.setBackground(Color.lightGray);
//                        simm.setFont(new Font("serif",Font.CENTER_BASELINE,15));
//                        p_cmp.add(simm,BorderLayout.SOUTH);
//                        p2.add(p_cmp);
//                    }
//                }
//
//
//                catch (IOException ex) {
//
//                    throw new RuntimeException(ex);
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        };
//        /////// recherche par contenu
//        ActionListener act4=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    ArrayList<Float> similarity_val= new ArrayList<>();
//                    Requete_img img=new Requete_img(path_req);
//                    boolean useTexture = true;
//                    boolean useColor =true;
//                    int binsPerColor = 8;
//                    int x=0;
//                    Index index = new Index(binsPerColor, useTexture, useColor);
//                    // extraireDesc = true;
//
//                    int numberOfResults = 9;
//
//                    if (loadIndexFromFile) {
//                        index = Index.extraireDesc(defaultIndexFile);
//                    } else {
//                        index.addToIndex(imagesFolderPath);
//                        //index.saveToFile(defaultIndexFile);
//                    }
//                    //////// calculation de taux de similarite//////
//                    for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
//                        similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
//                    }
//
//                    BufferedImage img2 = ImageIO.read(new File(path_req));
//                    List<File> topKMatches = index.Top(img2, numberOfResults);
//                    JLabel j_sim_req= new JLabel(new ImageIcon(img2.getScaledInstance(300,450, Image.SCALE_DEFAULT)));
//                    j_sim_req.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
//                    p1_sim_req.add(j_sim_req);
//                    for (File f : topKMatches) {
//                        BufferedImage bi = ImageIO.read(f);
//                        results.add(bi);
//                    }
//
//                    int count = 0;
//                    JLabel sim=new JLabel("similarities:");
//                    sim.setFont(new Font("serif",Font.CENTER_BASELINE,20));
//                    p1_sim_sim.add(sim);
//                    for (BufferedImage bi : results) {
//                        JPanel p_cmp=new JPanel();
//                        p_cmp.setLayout(new BorderLayout());
//                        p1_sim_sim.add(new JLabel((count+1)+" => "+String.valueOf(similarity_val.get(count))+"\n"));
//                        int tmp=count+1;
//                        //p_cmp.add(new JLabel("" + tmp),BorderLayout.WEST);
//                        count++;
//                        ImageIcon imageIcon = new ImageIcon(
//                                bi.getScaledInstance(200,200, Image.SCALE_DEFAULT));
//                        JLabel label = new JLabel(imageIcon);
//                        p_cmp.add(label,BorderLayout.CENTER);
//                        p_cmp.add(new JLabel("                 Similarite: "+String.valueOf(similarity_val.get(tmp-1))),BorderLayout.SOUTH);
//                        p2.add(p_cmp);
//                    }
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            }
//        };
//        //rechreche par forme
//        ActionListener act3=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    ArrayList<Float> similarity_val= new ArrayList<>();
//
//                    Requete_img img=new Requete_img(path_req);
//                    boolean useTexture = true;
//                    boolean useColor =false;
//                    int binsPerColor = 8;
//
//                    Index index = new Index(binsPerColor, useTexture, useColor);
//                    //extraireDesc = true;
//                    int numberOfResults = 9;
//
//                    if (loadIndexFromFile) {
//                        index = Index.extraireDesc(defaultIndexFile);
//                    } else {
//                        index.addToIndex(imagesFolderPath);
//                    }
//
//                    for (File queryImageFile : new File(imagesFolderPath).listFiles()) {
//                        similarity_val = index.similarity_values(ImageIO.read(queryImageFile), numberOfResults);
//                    }
//
//                    BufferedImage img2 = ImageIO.read(new File(path_req));
//                    List<File> topKMatches = index.Top(img2, numberOfResults);
//                    JLabel j_sim_req= new JLabel(new ImageIcon(img2.getScaledInstance(300,450, Image.SCALE_DEFAULT)));
//                    j_sim_req.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
//                    p1_sim_req.add(j_sim_req);
//                    for (File f : topKMatches) {
//                        BufferedImage bi = ImageIO.read(f);
//                        results.add(bi);
//                    }
//
//                    int count = 0;
//
//                    JLabel sim=new JLabel("similarities:");
//                    sim.setFont(new Font("serif",Font.CENTER_BASELINE,20));
//                    p1_sim_sim.add(sim);
//                    for (BufferedImage bi : results) {
//                        JPanel p_cmp=new JPanel();
//                        p_cmp.setLayout(new BorderLayout());
//                        p1_sim_sim.add(new JLabel((count+1)+" => "+String.valueOf(similarity_val.get(count))+"\n"));
//                        int tmp=count+1;
//                        //p_cmp.add(new JLabel("" + tmp),BorderLayout.WEST);
//                        count++;
//                        ImageIcon imageIcon = new ImageIcon(
//                                bi.getScaledInstance(200,200, Image.SCALE_DEFAULT));
//
//                        JLabel label = new JLabel(imageIcon);
//                        //label.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
//                        p_cmp.add(label,BorderLayout.CENTER);
//                        p_cmp.add(new JLabel("                 Similarite: "+String.valueOf(similarity_val.get(tmp-1))),BorderLayout.SOUTH);
//                        p2.add(p_cmp);
//                    }
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                } catch (ClassNotFoundException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        };
//        ActionListener exit1=new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.exit(1);
//            }
//        };
//        exit.addActionListener(exit1);
//        color.addActionListener(act2);
//        texture.addActionListener(act3);
//        contenu.addActionListener(act4);
//
//        searchby=new JLabel("                                                                                                             ");
//        searchby.setFont(new Font("serif",Font.ROMAN_BASELINE,15));
//        Bsearch.addActionListener(act1);
//        p1_search.add(Bsearch);
//        p1_search.add(searchby);
//        //p1_search.setBackground(Color.lightGray);
//        p1_search.add(color);
//        p1_search.add(texture);
//        p1_search.add(contenu);
//        p1_search.add(exit);
//        p1.add(p1_search);
//        p1_sim.add(p1_sim_req);
//        //p1_sim_req.setBackground(Color.lightGray);
//        //p1_sim.add(p1_sim_sim);
//        p1.add(p1_sim);
//        fenetre.add(p1);
//        fenetre.add(p2);
//        cont.add(fenetre);
//        //cont.add(p2);
//    }
//    public static void main(String[] args) throws IOException {
//        page1 p=new page1();
//        p.setVisible(true);
//    }
////
//}
