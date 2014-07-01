/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import classes.Board;
import classes.Cell;
import classes.Color;
import classes.Computer;
import classes.Piece;
import classes.Player;
import classes.Player1;
import classes.PlayerMode;
import events.TimerListener;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Adrian
 */
public class Game extends javax.swing.JFrame {

  /**
   * Creates new form Game
   */
  public Game() {
    initComponents();
  }
  
  private int count = 0;
  private String game_name;
  private classes.Game game;
  private Player player1;
  private Player player2;
  private Timer timer;
  
  public Game(String game_name, PlayerMode player_mode, Color player1_color, Color player2_color) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    initComponents();
    setLayout(null);
    
    String selected_game_name;
    String imagepath;
    String classname;
    String setround;
    String settimer;
    int boardsize_x;
    int boardsize_y;
    
    this.game_name = game_name;
//    jLabelGameName.setText(game_name);
    Board board = null;
//    jTextFieldTimer.setText("00:00:00");
//    jTextFieldRoundNumber.setText("0");
    
    try {
	File fXmlFile = new File("src/snakeandladders/SnakeAndLadders.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
	NodeList nList = doc.getElementsByTagName("gamesettings");
        Node nNode = nList.item(0);
        Element eElement = (Element) nNode;
        
//        System.out.println("name : " + );
        selected_game_name = eElement.getElementsByTagName("name").item(0).getTextContent().toString();
        imagepath = eElement.getElementsByTagName("imagepath").item(0).getTextContent().toString();
        classname = eElement.getElementsByTagName("classname").item(0).getTextContent().toString();
        setround = eElement.getElementsByTagName("setround").item(0).getTextContent().toString();
        settimer = eElement.getElementsByTagName("settimer").item(0).getTextContent().toString();
        
        jLabelGameName.setText(selected_game_name);
        jTextFieldTimer.setText(settimer);
        jTextFieldRoundNumber.setText(setround);
        
        NodeList nList2 = eElement.getElementsByTagName("boardsize");
        Node nNode2 = nList2.item(0);
        Element eElement2 = (Element) nNode2;
        
        boardsize_x = Integer.valueOf(eElement2.getElementsByTagName("x").item(0).getTextContent());
        boardsize_y = Integer.valueOf(eElement2.getElementsByTagName("y").item(0).getTextContent());
        
        board = new Board(boardsize_x,boardsize_y);
        
        List<classes.SpecialField> special_field_list;
        special_field_list = new ArrayList<>();

        Class<?> class_ladder = Class.forName("snakeandladders.Ladder");
        Class<?> class_snake= Class.forName("snakeandladders.Snake");
        Constructor<?> cons_class_ladder = class_ladder.getConstructor(Cell.class, Cell.class, int.class);
        Constructor<?> cons_class_snake = class_snake.getConstructor(Cell.class, Cell.class, int.class);
        classes.SpecialField ladder;
        classes.SpecialField snake;

        nList = eElement.getElementsByTagName("snake");
        for (int temp = 0; temp < nList.getLength(); temp++) 
        {
          nNode = nList.item(temp);
          eElement2 = (Element) nNode;
          int activationcell_x = Integer.valueOf(eElement2.getElementsByTagName("activationcell").item(0).getTextContent().toString().split("\\\n")[1].trim());
          int activationcell_y = Integer.valueOf(eElement2.getElementsByTagName("activationcell").item(0).getTextContent().toString().split("\\\n")[2].trim());
          int setcell_x = Integer.valueOf(eElement2.getElementsByTagName("setcell").item(0).getTextContent().toString().split("\\\n")[1].trim());
          int setcell_y = Integer.valueOf(eElement2.getElementsByTagName("setcell").item(0).getTextContent().toString().split("\\\n")[2].trim());
          int distance = Integer.valueOf(eElement2.getElementsByTagName("distance").item(0).getTextContent().toString());
          snake = (classes.SpecialField) cons_class_snake.newInstance(new Cell(activationcell_x,activationcell_y), new Cell(setcell_x,setcell_y), distance);
          special_field_list.add(snake);
        }

        nList = eElement.getElementsByTagName("ladder");
        for (int temp = 0; temp < nList.getLength(); temp++) 
        {
          nNode = nList.item(temp);
          eElement = (Element) nNode;
          int activationcell_x = Integer.valueOf(eElement.getElementsByTagName("activationcell").item(0).getTextContent().toString().split("\\\n")[1].trim());
          int activationcell_y = Integer.valueOf(eElement.getElementsByTagName("activationcell").item(0).getTextContent().toString().split("\\\n")[2].trim());
          int setcell_x = Integer.valueOf(eElement.getElementsByTagName("setcell").item(0).getTextContent().toString().split("\\\n")[1].trim());
          int setcell_y = Integer.valueOf(eElement.getElementsByTagName("setcell").item(0).getTextContent().toString().split("\\\n")[2].trim());
          int distance = Integer.valueOf(eElement.getElementsByTagName("distance").item(0).getTextContent().toString());
          ladder = (classes.SpecialField) cons_class_ladder.newInstance(new Cell(activationcell_x,activationcell_y), new Cell(setcell_x,setcell_y), distance);
          special_field_list.add(ladder);
        }
        board.addSpecialFields(special_field_list);
    } 
    catch (Exception e) {
	e.printStackTrace();
    }
    
    // Players
    Player1 player1 = new Player1(player1_color, "PLAYER1");
    this.player1 = player1;
    Player player2 = new Player();
    switch(player_mode)
    {
        case PLAYER1  : player2 = new Player1(player2_color, "PLAYER2");
                        break;
        case COMPUTER : player2 = new Computer(player2_color);
                        break;
    }
    this.player2 = player2;
    
    Class<?> c = Class.forName("snakeandladders.SingleDicingFigure");
    Constructor<?> cons = c.getConstructor(Cell.class);
    
    Piece player1_figure = (Piece) cons.newInstance(new Cell(0,0));
    Piece player2_figure = (Piece) cons.newInstance(new Cell(0,0));

    player1.setSinglePiece(player1_figure);
    player2.setSinglePiece(player2_figure);
    
    // Game
    List<Player> player_list;
    player_list = new ArrayList<>();
    player_list.add(player1);
    player_list.add(player2);
    classes.Game game = new classes.Game(board, player_list);
    this.game = game;    
    
    jLayeredPaneAll.setLayout(null);
    jLabelSecond.setIcon(new javax.swing.ImageIcon(getClass().getResource(player2.getIconPath())));
    jLabelFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource(player1.getIconPath())));
    jLabelFirst.setBounds(20,20+330, jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
    jLabelSecond.setBounds(30,30+330, jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
    jLabelRolledPlayer2.setText("Rolled "+player2.getName()+":");
    refreshGameSituation();
  }

  
  private void refreshGameSituation() {
    jTextFieldRoundNumber.setText(Integer.toString(game.getRound()));
    jTextFieldPlayerColor.setText(game.getPlayerList().get(game.getCurrentPlayerIndex()).getName());
    
    
    switch(game.getPlayerList().get(game.getCurrentPlayerIndex()).getColor())
    {
      case YELLOW : jTextFieldPlayerColor.setForeground(new java.awt.Color(255, 255, 0));
                    break;
      case BLUE   : jTextFieldPlayerColor.setForeground(new java.awt.Color(0, 0, 255));
                    break;
      case WHITE  : jTextFieldPlayerColor.setForeground(new java.awt.Color(255, 255, 255));
                    break;
      case GREEN  : jTextFieldPlayerColor.setForeground(new java.awt.Color(0, 255, 0));
                    break;
      case RED    : jTextFieldPlayerColor.setForeground(new java.awt.Color(255, 0, 0));
                    break;
      case BROWN  : jTextFieldPlayerColor.setForeground(new java.awt.Color(153, 50, 35));
                    break;
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabelGameName = new javax.swing.JLabel();
    jLayeredPaneAll = new javax.swing.JLayeredPane();
    jLabelFirst = new javax.swing.JLabel();
    jLabelSecond = new javax.swing.JLabel();
    jLayeredPaneBoard = new javax.swing.JLayeredPane();
    jLabelBoardImg = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jPanelGameSituation = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jTextFieldPlayerColor = new javax.swing.JTextField();
    jTextFieldTimer = new javax.swing.JTextField();
    jTextFieldRoundNumber = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jTextFieldRolledPlayer1 = new javax.swing.JTextField();
    jLabelRolledPlayer2 = new javax.swing.JLabel();
    jTextFieldRolledPlayer2 = new javax.swing.JTextField();
    jButtonStartNewGame = new javax.swing.JButton();
    jButtonMove = new javax.swing.JButton();
    jButtonBack = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);

    jLabelGameName.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
    jLabelGameName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    jLabelFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/figure_brown.png"))); // NOI18N

    jLabelSecond.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/figure_blue.png"))); // NOI18N

    jLabelBoardImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SnakeAndLadders1_small.jpg"))); // NOI18N

    javax.swing.GroupLayout jLayeredPaneBoardLayout = new javax.swing.GroupLayout(jLayeredPaneBoard);
    jLayeredPaneBoard.setLayout(jLayeredPaneBoardLayout);
    jLayeredPaneBoardLayout.setHorizontalGroup(
      jLayeredPaneBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPaneBoardLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabelBoardImg)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jLayeredPaneBoardLayout.setVerticalGroup(
      jLayeredPaneBoardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPaneBoardLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabelBoardImg)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jLayeredPaneBoard.setLayer(jLabelBoardImg, javax.swing.JLayeredPane.DEFAULT_LAYER);

    jPanelGameSituation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Game Situation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("Round:");

    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel6.setText("Playing Time:");

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel4.setText("Turn:");

    jTextFieldPlayerColor.setEditable(false);
    jTextFieldPlayerColor.setBackground(new java.awt.Color(204, 204, 204));
    jTextFieldPlayerColor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextFieldPlayerColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldPlayerColor.setFocusable(false);

    jTextFieldTimer.setEditable(false);
    jTextFieldTimer.setBackground(new java.awt.Color(240, 240, 240));
    jTextFieldTimer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextFieldTimer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldTimer.setFocusable(false);

    jTextFieldRoundNumber.setEditable(false);
    jTextFieldRoundNumber.setBackground(new java.awt.Color(240, 240, 240));
    jTextFieldRoundNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextFieldRoundNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldRoundNumber.setToolTipText("");
    jTextFieldRoundNumber.setFocusable(false);

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel1.setText("Rolled PLAYER1:");

    jTextFieldRolledPlayer1.setEditable(false);
    jTextFieldRolledPlayer1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextFieldRolledPlayer1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldRolledPlayer1.setToolTipText("");
    jTextFieldRolledPlayer1.setFocusable(false);

    jLabelRolledPlayer2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabelRolledPlayer2.setText("Rolled PLAYER2:");

    jTextFieldRolledPlayer2.setEditable(false);
    jTextFieldRolledPlayer2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextFieldRolledPlayer2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    jTextFieldRolledPlayer2.setFocusable(false);

    javax.swing.GroupLayout jPanelGameSituationLayout = new javax.swing.GroupLayout(jPanelGameSituation);
    jPanelGameSituation.setLayout(jPanelGameSituationLayout);
    jPanelGameSituationLayout.setHorizontalGroup(
      jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelGameSituationLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jLabelRolledPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jTextFieldRolledPlayer2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldRolledPlayer1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldRoundNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldPlayerColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jTextFieldTimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );
    jPanelGameSituationLayout.setVerticalGroup(
      jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanelGameSituationLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel6)
          .addComponent(jTextFieldTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel4)
          .addComponent(jTextFieldPlayerColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel3)
          .addComponent(jTextFieldRoundNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(jTextFieldRolledPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanelGameSituationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabelRolledPlayer2)
          .addComponent(jTextFieldRolledPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jButtonStartNewGame.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonStartNewGame.setText("Start");
    jButtonStartNewGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonStartNewGameActionPerformed(evt);
      }
    });

    jButtonMove.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonMove.setText("Move");
    jButtonMove.setEnabled(false);
    jButtonMove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonMoveActionPerformed(evt);
      }
    });

    jButtonBack.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
    jButtonBack.setText("Back");
    jButtonBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButtonBackActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jPanelGameSituation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonStartNewGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonMove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jButtonBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanelGameSituation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonStartNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonMove, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jLayeredPaneAllLayout = new javax.swing.GroupLayout(jLayeredPaneAll);
    jLayeredPaneAll.setLayout(jLayeredPaneAllLayout);
    jLayeredPaneAllLayout.setHorizontalGroup(
      jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLayeredPaneBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
          .addGap(374, 374, 374)
          .addComponent(jLabelSecond)
          .addContainerGap(143, Short.MAX_VALUE)))
      .addGroup(jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
          .addGap(374, 374, 374)
          .addComponent(jLabelFirst)
          .addContainerGap(143, Short.MAX_VALUE)))
    );
    jLayeredPaneAllLayout.setVerticalGroup(
      jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
        .addGroup(jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLayeredPaneBoard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
      .addGroup(jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
          .addGap(196, 196, 196)
          .addComponent(jLabelSecond)
          .addContainerGap(192, Short.MAX_VALUE)))
      .addGroup(jLayeredPaneAllLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jLayeredPaneAllLayout.createSequentialGroup()
          .addGap(196, 196, 196)
          .addComponent(jLabelFirst)
          .addContainerGap(192, Short.MAX_VALUE)))
    );
    jLayeredPaneAll.setLayer(jLabelFirst, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPaneAll.setLayer(jLabelSecond, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPaneAll.setLayer(jLayeredPaneBoard, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jLayeredPaneAll.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLayeredPaneAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabelGameName, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(20, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jLabelGameName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLayeredPaneAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jButtonMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMoveActionPerformed
 
      Object[] return_statement_move;
      Cell new_cell;
      return_statement_move = this.game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().move(game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().getPosition(), this.game);
      this.game = (classes.Game) return_statement_move[2];
      
      int board_size = game.getBoard().getX() * game.getBoard().getY();
      
      // move is false - only refresh the rolled number
      if(return_statement_move[0].equals(0))
      {
        if(this.game.getCurrentPlayerIndex() == 0)
        {
          jTextFieldRolledPlayer1.setText(return_statement_move[1].toString());
        }
        else
        {
          jTextFieldRolledPlayer2.setText(return_statement_move[1].toString());
        }
      }
      
      // player wins!
      if(game.getCurrentPlayerObject().getSinglePiece().getBoardPosition() == board_size)
      {
        this.jButtonMove.setEnabled(false);
        new_cell = this.game.getCurrentPlayerObject().getSinglePiece().getPosition();
        
        if(this.game.getCurrentPlayerIndex() == 0)
        {
          jTextFieldRolledPlayer1.setText(return_statement_move[1].toString());
        }
        else
        {
          jTextFieldRolledPlayer2.setText(return_statement_move[1].toString());
        }
        
        if(this.game.getCurrentPlayerIndex() == 0)
        {
          jLabelFirst.setBounds(20+(84 * new_cell.getX()),20+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
        }
        else
        {
          jLabelSecond.setBounds(30+(84 * new_cell.getX()),30+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
        }
        timer.stop();
        JOptionPane.showMessageDialog(this,game.getCurrentPlayerObject().getName()+" WIN", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
        
      }
      else // player not win
      {
        classes.SpecialField spezial_object = null; 
        if(return_statement_move[0] != Integer.valueOf(0)) // TRUE
        {
            new_cell = this.game.getCurrentPlayerObject().getSinglePiece().getPosition();
//            System.out.println(this.game.getCurrentPlayerObject().getSinglePiece().getBoardPosition());

            if(this.game.getCurrentPlayerIndex() == 0)
            {
              jLabelFirst.setBounds(20+(84 * new_cell.getX()),20+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
            }
            else
            {
              jLabelSecond.setBounds(30+(84 * new_cell.getX()),30+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
            }
            
            if(this.game.getCurrentPlayerIndex() == 0)
            {
              jTextFieldRolledPlayer1.setText(return_statement_move[1].toString());
            }
            else
            {
              jTextFieldRolledPlayer2.setText(return_statement_move[1].toString());
            }
            
            spezial_object = (classes.SpecialField) return_statement_move[3];
            if(spezial_object != null) // it exist a spezial object on the current position
            {
              JOptionPane.showMessageDialog(this,game.getCurrentPlayerObject().getName()+" get on a "+spezial_object.getClass().getName().toUpperCase().split("\\.")[1], "Spezial Field", JOptionPane.INFORMATION_MESSAGE);
              return_statement_move = this.game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().move(game.getPlayerList().get(game.getCurrentPlayerIndex()).getSinglePiece().getPosition(), this.game);
              this.game = (classes.Game) return_statement_move[2];
//              System.out.println(this.game.getCurrentPlayerObject().getSinglePiece().getBoardPosition());
              new_cell = this.game.getCurrentPlayerObject().getSinglePiece().getPosition();
              if(this.game.getCurrentPlayerIndex() == 0)
              {
                jLabelFirst.setBounds(20+(84 * new_cell.getX()),20+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
              }
              else
              {
                jLabelSecond.setBounds(30+(84 * new_cell.getX()),30+ (330 - (80 * new_cell.getY())), jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
              }
            }
        }

        this.game.nextRound();
        this.game.nextPlayer();

        // switch current player in layered pane
        if(jLayeredPaneAll.getPosition(jLabelFirst) == 0) {
          jLayeredPaneAll.setLayer(jLabelSecond,0,0);
        }
        else {
          jLayeredPaneAll.setLayer(jLabelSecond,0,1);
        }
        refreshGameSituation();

        if(this.game.getCurrentPlayerObject().getName().equals("COMPUTER")) // AUTOMATIC COMPUTERMODUS
        {
          this.jButtonMoveActionPerformed(evt);
        }
      }
      
    }//GEN-LAST:event_jButtonMoveActionPerformed

    
    private void jButtonStartNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartNewGameActionPerformed

        if(jButtonStartNewGame.getText() == "Start")
        {
          jButtonStartNewGame.setText("New Game");
          jButtonMove.setEnabled(true);
          jTextFieldRoundNumber.setText(Integer.toString(game.nextRound()));
          TimerListener timer_listener = new TimerListener(jTextFieldTimer);
          this.timer = new Timer(1000, (ActionListener) timer_listener);
          timer.start();
        }
        else
        {
          timer.stop();
          Object[] options = {"Yes, please", "No way!"};
          int n = JOptionPane.showOptionDialog(this, "Would you really start a new game?",
            "New Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
            null, // do not use a custom Icon
            options, // the titles of buttons
            options[0]); //default button title
          
          if(n == 0) {
            jButtonStartNewGame.setText("Start");
            jButtonMove.setEnabled(false);
            this.timer.stop();
            jTextFieldTimer.setText("00:00:00");
            jTextFieldRoundNumber.setText("0");
            game.resetRound(0);
            Player player1 = game.getPlayerList().get(0);
            game.setCurrent_player(0);
            jLabelFirst.setBounds(20,20+330, jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
            jLabelSecond.setBounds(30,30+330, jLabelFirst.getPreferredSize().width, jLabelFirst.getPreferredSize().height);
            jLayeredPaneAll.setLayer(jLabelFirst, 0, 0);
            jTextFieldRolledPlayer1.setText("");
            jTextFieldRolledPlayer2.setText("");
            game.setCurrent_player(0);
            game.getPlayerList().get(0).getSinglePiece().setBoardPosition(1);
            game.getPlayerList().get(0).getSinglePiece().setPosition(new Cell(1,1)); 
            game.getPlayerList().get(1).getSinglePiece().setBoardPosition(1);
            game.getPlayerList().get(1).getSinglePiece().setPosition(new Cell(1,1)); 
            refreshGameSituation();
            // reset all game settings
          }
          if(n == 1) {
            timer.start();
          }
        }
    }//GEN-LAST:event_jButtonStartNewGameActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.setVisible(false);
        GameSettings game_settings_frame = new GameSettings(this.game_name);
        game_settings_frame.setBounds(this.getBounds().x, this.getBounds().y, 332, 554);
        game_settings_frame.setVisible(true);
    }//GEN-LAST:event_jButtonBackActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
        //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Game().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButtonBack;
  private javax.swing.JButton jButtonMove;
  private javax.swing.JButton jButtonStartNewGame;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabelBoardImg;
  private javax.swing.JLabel jLabelFirst;
  private javax.swing.JLabel jLabelGameName;
  private javax.swing.JLabel jLabelRolledPlayer2;
  private javax.swing.JLabel jLabelSecond;
  private javax.swing.JLayeredPane jLayeredPaneAll;
  private javax.swing.JLayeredPane jLayeredPaneBoard;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanelGameSituation;
  private javax.swing.JTextField jTextFieldPlayerColor;
  private javax.swing.JTextField jTextFieldRolledPlayer1;
  private javax.swing.JTextField jTextFieldRolledPlayer2;
  private javax.swing.JTextField jTextFieldRoundNumber;
  private javax.swing.JTextField jTextFieldTimer;
  // End of variables declaration//GEN-END:variables
}
