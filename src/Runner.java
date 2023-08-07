import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Runner extends JFrame implements KeyListener {

	static DifferentiationGenerator g1;
	static Container cont;
	static JTextField tx = new JTextField();
	int shift1, shift2;

	public static void main(String[] args) {

		new Runner();
	}

	public Runner() {

		g1 = new DifferentiationGenerator(true, true, true, true, true, true, true, true);
		shift1 = (int) (750 - 200 * g1.numTerms);
		shift2 = 500;

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setSize(1840, 1200);
		this.setVisible(true);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.BOLD, 150);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		String problem = g1.getProblem();
		g2.setFont(font);
		g2.drawString("d", 50 + shift1, 280);
		g2.drawString("__", 25 + shift1, 285);
		g2.drawString("dx", 25 + shift1, 440);
		g2.drawString("(" + problem + ")", 220 + shift1, 346);
		cont = this.getContentPane();
		cont.setLayout(null);
		tx.setForeground(Color.BLACK);
		tx.setBackground(Color.WHITE);
		tx.setFont(font);
		tx.setBounds(100, 500, 1600, 200);
		tx.addKeyListener(this);
		cont.add(tx);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			if (tx.getText().equals(g1.getAnswer())) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
