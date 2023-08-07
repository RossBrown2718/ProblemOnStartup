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
import java.util.ArrayList;

class DifferentiationGenerator {

	private boolean power;
	private boolean product;
	private boolean quotient;
	private boolean chain;

	private boolean polynomial;
	private boolean exponential;
	private boolean basicTrig;
	private boolean advancedTrig;

	private String problem;
	private String answer;

	private ArrayList<Integer> coeffs;
	private ArrayList<Integer> polyPowers;

	public double numTerms;

	public DifferentiationGenerator(boolean p1, boolean p2, boolean q, boolean c, boolean p3, boolean e, boolean t1, boolean t2) {
		power = p1;
		product = p2;
		quotient = q;
		chain = c;

		polynomial = p3;
		exponential = e;
		basicTrig = t1;
		advancedTrig = t2;

		problem = "";
		answer = "";

		createAndDifferentiate((int) (3 * Math.random())); // 0 = power, 1 = product, 2 = chain
	}

	public void createAndDifferentiate(int type) {
		boolean neg = false;
		boolean neg2 = false;
		String tempAns = "";
		
		if (type == 0) { // TODO change to 0
			// Polynomial
			numTerms = (int) (2 * Math.random() + 2);
			coeffs = new ArrayList<Integer>();
			polyPowers = new ArrayList<Integer>();

			for (int i = 0; i < numTerms; i++) {
				coeffs.add((int) (11 * Math.random() + 2));
				polyPowers.add((int) (10 * Math.random() + 3));

				problem += i != 0 && coeffs.get(i) >= 0 ? "+" : "";
				problem += (coeffs.get(i) != 1 ? coeffs.get(i) : "") + "x^" + polyPowers.get(i);
				answer += i != 0 && coeffs.get(i) >= 0 ? "+" : "";
				answer += (coeffs.get(i) == 0 ? "0"
						: coeffs.get(i) * polyPowers.get(i) + "x^" + (polyPowers.get(i) - 1));
			}
		} else if (type == 1) { // TODO change to 1
			// Product rule
			numTerms = 1.75;
			int type1 = (int) (4 * Math.random()); // 0 == poly, 1 == exp, 2 == ezTrig, 3 == hardTrig
			int type2 = (int) (4 * Math.random()); // 0 == poly, 1 == exp, 2 == ezTrig, 3 == hardTrig
			while (type2 == type1) type2 = (int) (4 * Math.random());

			if (type1 == 0) {
				int power = (int) (Math.random() * 4) + 3;
				problem += "x^" + power + "*";
				answer += power + "x^" + (power - 1) + "*";
				tempAns = "x^" + power + "*";
			} else if (type1 == 1) {
				int base = (int) (Math.random() * 8 + 2);
				problem += base + "^x*";
				answer += "ln(" + base + ")*" + base + "^x*";
				tempAns = base + "^x*";
			} else if (type1 == 2) {
				int sine = (int) (Math.random() * 2); // 0 if sine, 1 if cosine
				problem += sine == 0 ? "sin(x)*" : "cos(x)*";
				answer += sine == 0 ? "cos(x)*" : "sin(x)*";
				tempAns = sine == 0 ? "sin(x)*" : "cos(x)*";
				if (sine == 1) neg = !neg;
			} else if (type1 == 3) {
				int typeTrig = (int) (Math.random() * 4); //0 is tan, 1 is cot, 2 is sec, 3 is csc
				problem += typeTrig == 0 ? "tan(x)*" : typeTrig == 1 ? "cot(x)*" : typeTrig == 2 ? "sec(x)*" : "csc(x)*" ;
				answer += typeTrig == 0 ? "sec^2(x)*" : typeTrig == 1 ? "csc^2(x)*" : typeTrig == 2 ? "tan(x)*sec(x)*" : "cot(x)*csc(x)*" ;
				tempAns = typeTrig == 0 ? "tan(x)*" : typeTrig == 1 ? "cot(x)*" : typeTrig == 2 ? "sec(x)*" : "csc(x)*" ;
				if (typeTrig % 2 == 1) neg = !neg;
			}
			
			if (type2 == 0) {
				int power = (int) (Math.random() * 4) + 3;
				problem += "x^" + power;
				answer += "x^" + power + (neg2 ? "-" : "+") + tempAns + power + "x^" + (power - 1);
			} else if (type2 == 1) {
				int base = (int) (Math.random() * 8 + 2);
				problem += base + "^x";
				answer += base + "^x" + (neg2 ? "-" : "+") + tempAns + "ln(" + base + ")*" + base + "^x";
			} else if (type2 == 2) {
				int sine = (int) (Math.random() * 2); // 0 if sine, 1 if cosine
				if (sine == 1) neg2 = !neg2;
				problem += sine == 0 ? "sin(x)" : "cos(x)";
				answer += (sine == 0 ? "sin(x)" : "cos(x)") + (neg2 ? "-" : "+") + tempAns + (sine == 0 ? "cos(x)" : "sin(x)");
			} else if (type2 == 3) {
				int typeTrig = (int) (Math.random() * 4); //0 is tan, 1 is cot, 2 is sec, 3 is csc
				if (typeTrig % 2 == 1) neg2 = !neg2;
				problem += typeTrig == 0 ? "tan(x)" : typeTrig == 1 ? "cot(x)" : typeTrig == 2 ? "sec(x)" : "csc(x)" ;
				answer += (typeTrig == 0 ? "tan(x)" : typeTrig == 1 ? "cot(x)" : typeTrig == 2 ? "sec(x)" : "csc(x)") + (neg2 ? "-" : "+") + tempAns + (typeTrig == 0 ? "sec^2(x)" : typeTrig == 1 ? "csc^2(x)" : typeTrig == 2 ? "tan(x)*sec(x)" : "cot(x)*csc(x)");
			}
			answer = neg ? "-" + answer : answer;
		} else {
			//Chain rule
			numTerms = 1.5;
			
			int type1 = (int) (4 * Math.random()); // g(x)
			int type2 = (int) (4 * Math.random()); // f(x)
			while (type2 == type1) type2 = (int) (4 * Math.random());
			
			if (type1 == 0) {
				int power = (int) (Math.random() * 4) + 3;
				problem += "(x^" + power + ")";
				answer += power + "x^" + (power - 1) + "*";
				tempAns = "(x^" + power + ")";
			} else if (type1 == 1) {
				int base = (int) (Math.random() * 8 + 2);
				problem += "(" + base + "^x)";
				answer += "ln(" + base + ")*" + base + "^x*";
				tempAns = "(" + base + "^x)";
			} else if (type1 == 2) {
				int sine = (int) (Math.random() * 2); // 0 if sine, 1 if cosine
				problem += sine == 0 ? "(sin(x))" : "(cos(x))";
				answer += sine == 0 ? "cos(x)*" : "sin(x)*";
				tempAns = sine == 0 ? "(sin(x))" : "(cos(x))";
				if (sine == 1) neg = !neg;
			} else if (type1 == 3) {
				int typeTrig = (int) (Math.random() * 4); //0 is tan, 1 is cot, 2 is sec, 3 is csc
				problem += typeTrig == 0 ? "(tan(x))" : typeTrig == 1 ? "(cot(x))" : typeTrig == 2 ? "(sec(x))" : "(csc(x))" ;
				answer += typeTrig == 0 ? "sec^2(x)*" : typeTrig == 1 ? "csc^2(x)*" : typeTrig == 2 ? "tan(x)*sec(x)*" : "cot(x)*csc(x)*" ;
				tempAns = typeTrig == 0 ? "(tan(x))" : typeTrig == 1 ? "(cot(x))" : typeTrig == 2 ? "(sec(x))" : "(csc(x))" ;
				if (typeTrig % 2 == 1) neg = !neg;
			}
			
			if (type2 == 0) {
				int power = (int) (Math.random() * 4) + 3;
				problem += "^" + power;
				answer += power + tempAns + "^" + (power - 1);
			} else if (type2 == 1) {
				int base = (int) (Math.random() * 8 + 2);
				problem = base + "^" + problem;
				answer += "ln(" + base + ")*" + base + "^" + tempAns;
			} else if (type2 == 2) {
				int sine = (int) (Math.random() * 2); // 0 if sine, 1 if cosine
				if (sine == 1)neg = !neg;
				problem = (sine == 0 ? "sin" : "cos") + problem;
				answer += (sine == 0 ? "cos" : "sin") + tempAns;
			} else if (type2 == 3) {
				int typeTrig = (int) (Math.random() * 4); //0 is tan, 1 is cot, 2 is sec, 3 is csc
				if (typeTrig % 2 == 1)neg = !neg;
				problem = (typeTrig == 0 ? "tan" : typeTrig == 1 ? "cot" : typeTrig == 2 ? "sec" : "csc") + problem;
				answer += (typeTrig == 0 ? "sec^2" + tempAns : typeTrig == 1 ? "csc^2" + tempAns : typeTrig == 2 ? "tan" + tempAns + "*sec" + tempAns: "cot" + tempAns + "*csc" + tempAns);
			}
			answer = neg ? "-" + answer : answer;
		}
		System.out.println(answer);
	}

	public void printEm() {
		System.out.println(problem + "\n" + answer);
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}

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
