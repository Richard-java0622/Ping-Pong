import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;


	GamePanel(){
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		
		gameThread = new Thread(this);
		gameThread.start();
    }

    public void newBall() {

    } 
    public void newPaddles() {
	    paddle1 = new Paddle(0,(Game_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
	    paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(Game_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }
    public void paint(Graphics g) {
	    image = createImage(getWidth(),getHeight());
	    graphics = image.getGraphics();
	    draw(graphics);
	    g.drawImage(image,0,0,this);

    }
    public void draw(Graphics g) {
	    paddle1.draw(g);
	    paddle2.draw(g);

    }
    public void move() {

    }
    public void checkCollisions() {
// stops paddles at windo aeges
	    if(paddle1.y<=0)
		    paddle1.y=0;
	    if(paddle1.y >= (GANE_HEIGHT-PADLE_HEIOGHT))
		    paddle1 = GAME_HEIGHT-PADDLE_HEIGHT;
	if(paddle2.y<=0)
		    paddle2.y=0;
	    if(paddle2.y >= (GANE_HEIGHT-PADLE_HEIOGHT))
		    paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
    }
    public void run() {
	    //game loop
	    long lastTime = System.nanoTime();
	    double amountOfTicks =60.0;
	    double ns = 1000000000 / amountOfTicks;
	    double delta = 0;
	    while(true){
		    long now = System.nanoTime();
		    delta += (now -lastTime)/ns;
		    lastTime = now;
		    if(delta >=1){
			    move();
			    checkCollision();
			    repaint();
			    delta--;

    }
    public class AL extends keyAdapter implements KeyListener{ 
        public void keyPressed(KeyEvent e) { 
		paddle1.keyPressed(e);
		paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
	    paddle2.keyReleased(e);
        }
    }
}
