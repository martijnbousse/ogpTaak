package asteroids;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

import asteroids.IFacade.ParseOutcome;
import asteroids.IFacade.TypeCheckOutcome;

@SuppressWarnings("serial")
public class Asteroids<World, Ship, Asteroid, Bullet, Program> extends JFrame {

  private AsteroidsMenu<World, Ship, Asteroid, Bullet, Program> menu;
  private WorldView<World, Ship, Asteroid, Bullet, Program> view;
  private IFacade<World, Ship, Asteroid, Bullet, Program> facade;
  private int width;
  private int height;
  private Sound sound;
  private URL aiProgramUrl;

  public Asteroids(IFacade<World, Ship, Asteroid, Bullet, Program> facade, int width, int height, boolean undecorated, Sound sound, URL aiProgramUrl) {
    super("Asteroids");
    this.sound = sound;
    this.width = width;
    this.height = height;
    this.aiProgramUrl = aiProgramUrl;
    menu = new AsteroidsMenu<World, Ship, Asteroid, Bullet, Program>(this);
    this.facade = facade;
    setUndecorated(undecorated);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    getContentPane().add(menu);
    pack();
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Sound getSound() {
    return sound;
  }
  
  public IFacade<World, Ship, Asteroid, Bullet, Program> getFacade() {
    return facade;
  }

  public void start() {
    menu.reset();
    sound.start();
    setVisible(true);
    menu.requestFocusInWindow();
  }

  public void startSinglePlayerGame() {
    World world = facade.createWorld(width, height);
    Ship player = facade.createShip(width / 2., height / 2., 0, 0, 40, 0, 5E15);
    facade.addShip(world, player);
    Asteroid asteroid1 = facade.createAsteroid(100, 100, 25, 50, 60);
    facade.addAsteroid(world, asteroid1);
    Asteroid asteroid2 = facade.createAsteroid(600, 100, -30, -40, 80);
    facade.addAsteroid(world, asteroid2);
    view = new WorldView<World, Ship, Asteroid, Bullet, Program>(this, world, player, null, false);
    if (!isUndecorated())
      view.setPreferredSize(new Dimension(width, height));
    getContentPane().remove(menu);
    getContentPane().add(view);
    validate();
    repaint();
    view.requestFocusInWindow();
    view.startGame();
  }

  public void startMultiPlayerGame(boolean vsAI) {
    World world = facade.createWorld(width, height);
    Ship player1 = facade.createShip(width / 5 * 4, height / 2., 0, 0, 40, Math.PI, 5E15);
    facade.addShip(world, player1);
    Ship player2 = facade.createShip(width / 5, height / 2., 0, 0, 40, 0, 5E15);
    facade.addShip(world, player2);
    if(vsAI) {
      ParseOutcome<Program> parseOutcome;
      try {
        parseOutcome = facade.loadProgramFromUrl(aiProgramUrl);
      } catch (IOException e) {
        System.err.println(e.getMessage());
        sound.play("load-error");
        return;
      }
      if(parseOutcome.isSuccessful()) {
        Program program = parseOutcome.getProgram();
        if(facade.isTypeCheckingSupported()) {
          TypeCheckOutcome typeCheckOutcome = facade.typeCheckProgram(program);
          if(typeCheckOutcome.isSuccessful()) {
            facade.setShipProgram(player2, program);
          } else {
            System.err.println(typeCheckOutcome.getMessage());
            sound.play("load-error");
            return;
          }
        } else {
          facade.setShipProgram(player2, program);
        }
      } else {
        System.err.println(parseOutcome.getMessage());
        sound.play("load-error");
        return;
      }
    }
    Asteroid asteroid1 = facade.createAsteroid(width / 2.5, height / 2.5, 25, 50, 75);
    facade.addAsteroid(world, asteroid1);
    Asteroid asteroid2 = facade.createAsteroid(600, 100, -30, -40, 40);
    facade.addAsteroid(world, asteroid2);
    Asteroid asteroid3 = facade.createAsteroid(990, 550, -20, -3, 25);
    facade.addAsteroid(world, asteroid3);
    Asteroid asteroid4 = facade.createAsteroid(40, height - 100, 10, -8, 15);
    facade.addAsteroid(world, asteroid4);
    view = new WorldView<World, Ship, Asteroid, Bullet, Program>(this, world, player1, player2, vsAI);
    if (!isUndecorated())
      view.setPreferredSize(new Dimension(width, height));
    getContentPane().remove(menu);
    getContentPane().add(view);
    validate();
    repaint();
    view.requestFocusInWindow();
    view.startGame();
  }

  public void showMenu() {
    if (view != null) {
      getContentPane().remove(view);
      view = null;
    }
    menu.reset();
    getContentPane().add(menu);
    validate();
    repaint();
    menu.requestFocusInWindow();
    pack();
    menu.repaint();
  }

  public static void main(final String[] args) {
    boolean tryFullscreen = true;
    boolean enableSound = true;
    URL aiProgramUrl = Asteroids.class.getClassLoader().getResource("asteroids/resources/program.txt"); 
    for(int i = 0; i < args.length; i++) {
      String arg = args[i];
      if(arg.equals("-window")) {
        tryFullscreen = false;
      } else if(arg.equals("-nosound")) {
        enableSound = false;
      } else if(arg.equals("-ai")) {
        if(i + 1 < args.length) {
          String aiProgramPath = args[++i];
          File file = new File(aiProgramPath);
          if(! file.exists()) {
            System.out.println("file " + aiProgramPath + " not found");
            return;
          } else {
            try {
              aiProgramUrl = file.toURI().toURL();
            } catch (MalformedURLException e) {
              System.out.println("malformed url");
              return;
            }
          }
          i++;
        } else {
          System.out.println("no path specified");
          return;
        }
      } else {
        System.out.println("unknown option: " + arg);
        return;
      }
    }
    if (args.length > 0 && args[0].equals("-window")) {
      tryFullscreen = false;
    }
    if (GraphicsEnvironment.isHeadless()) {
      System.out.println("no screen found");
      return;
    }
    // <begin>
    IFacade<asteroids.model.World, asteroids.model.Ship, asteroids.model.Asteroid, asteroids.model.Bullet, asteroids.model.programs.Program> facade = new asteroids.model.Facade();
    // <end>
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice screen = env.getDefaultScreenDevice();
    Asteroids<?, ?, ?, ?, ?> asteroids;
    Sound sound = enableSound ? new FileSoundManager("asteroids/resources/sounds.txt") : new NullSound();
    if (tryFullscreen && screen.isFullScreenSupported()) {
      Rectangle dimensions = screen.getDefaultConfiguration().getBounds();
      asteroids = new Asteroids<>(facade, dimensions.width, dimensions.height, true, sound, aiProgramUrl);
      screen.setFullScreenWindow(asteroids);
    } else {
      asteroids = new Asteroids<>(facade, 1024, 768, false, sound, aiProgramUrl);
    }
    asteroids.start();
  }
}
