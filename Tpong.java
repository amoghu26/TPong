import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TPong extends JPanel implements KeyListener,ActionListener{
	final int P1UP = KeyEvent.VK_UP,P2UP = KeyEvent.VK_W,P1DOWN = KeyEvent.VK_DOWN,P2DOWN = KeyEvent.VK_S,ESC = KeyEvent.VK_ESCAPE;
	JFrame f = new JFrame();
	Timer t = new Timer(2,this),t1 = new Timer(1500,this);
	JLabel player1Score = new JLabel(), player2Score = new JLabel();
	int subX = 3,subY = 3, P1Y = f.getHeight()/3,P2Y = f.getHeight()/3,ballX,ballY,p1Score = 0,p2Score = 0;
	public TPong() {
		f.setSize(1920, 1080);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Pong");
		ballX = f.getWidth()/3;
		ballY = f.getWidth()/3;
	}
	public void setup(TPong p) {
		p.add(player1Score);
		p.add(player2Score);

		f.add(p);
		f.setVisible(true);
		f.addKeyListener(this);

		t.start();
	}
	public void actionPerformed(ActionEvent e) {
		ballX+=subX;
		ballY+=subY;

		if(e.getSource() == t) {
			if(ballX <= 0) {
				p2Score++;
				ballX = f.getWidth()/2;
				ballY = f.getHeight()/2;
			}
			else if(ballX>=f.getWidth()-(f.getWidth()/100)) {
				p1Score++;
				ballX = f.getWidth()/2;
				ballY = f.getHeight()/2;
			}
			if(ballY<=0) {
				subY*=-1;
				if(subX < 0)
					subX--;
				else
					subX++;
				t1.start();
			}
			if(ballY>=f.getHeight()-100) {
				subY*=-1;
				if(subX < 0)
					subX--;
				else
					subX++;
				t1.start();
			}
			if(ballX <= (f.getWidth()/10)+20&&ballX>=f.getWidth()/10&&ballY>P2Y&&ballY<P2Y+f.getHeight()/3) {
				subX*=-1;
				if(subY < 0)
					subY--;
				else
					subY++;
				t1.start();
			}
			if(ballX >= (f.getWidth()/10)*8 && ballX <= ((f.getWidth()/10)*8)+20&&ballY>=P1Y&&ballY<=P1Y+f.getHeight()/3) {
				subX*=-1;
				if(subY < 0)
					subY--;
				else
					subY++;
				t1.start();
			}
		}
		else if(e.getSource() == t1) {
			t1.stop();
			if(subX<0)
				subX = -4;
			else
				subX = 4;
			
			if(subY<0)
				subY = -4;
			else
				subY = 4;
			
		}

		repaint();
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==P1UP&&P1Y>0)
			P1Y-=25;
		if(e.getKeyCode() == P1DOWN&&P1Y<2*f.getHeight()/3)
			P1Y+=25;
		if(e.getKeyCode() == P2UP&&P2Y>0) 
			P2Y-=25;
		if(e.getKeyCode() == P2DOWN&&P2Y<2*f.getHeight()/3)
			P2Y+=25;

		repaint();
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}

	public void paintComponent(Graphics g) {
		Font font = new Font("Serif",Font.BOLD,24);
		player1Score.setText("Player 1: "+p1Score);
		player1Score.setFont(font);
		player2Score.setText("              Player 2: "+p2Score);
		player2Score.setFont(font);

		player1Score.setForeground(Color.WHITE);
		player2Score.setForeground(Color.WHITE);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, f.getWidth(), f.getHeight());

		g.setColor(Color.WHITE);
		g.fillRect(f.getWidth()/10, P2Y, 20, f.getHeight()/3);

		g.fillRect(8*(f.getWidth()/10), P1Y, 20, f.getHeight()/3);

		g.fillOval(ballX, ballY, f.getWidth()/100, f.getWidth()/100);

		g.drawString(p1Score+" Points", 0, 0);
	}

	public static void main(String[] args) {
		TPong p = new TPong();
		p.setup(p);
	}
}