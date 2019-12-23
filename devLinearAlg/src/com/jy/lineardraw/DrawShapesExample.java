package com.jy.lineardraw;

import java.awt.Component;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class DrawShapesExample {

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.add(new CustomPaintComponent());
		int frameWidth = 500;
		int frameHeight = 500;
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
	}

//
	static class CustomPaintComponent extends Component {
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			axis(g2d);
			tickmarks(g2d);
			letteringArrays(g2d);
		}

	}

	public static void axis(Graphics axis) {
		axis.drawLine(10, 450, 450, 450);
		axis.drawLine(10, 450, 10, 10);
	}

	public static void tickmarks(Graphics ticks) {
		int intialx = 10;
		int intialy = 450;
		int increment = 10;
		for (int i = 1; i <= 44; i++) {
			int xCoord = increment * i;
			ticks.drawLine(xCoord, intialy - 10, xCoord, intialy + 10);
			int yCoord = intialy - increment * i;
			ticks.drawLine(intialx - 10, yCoord, intialx + 10, yCoord);
		}
	}

	public static int[] shear(int[] matrix) {
		System.out.println("PRIOR TO SHIFT");
		printIndex(matrix);
		System.out.println();

		System.out.println("SHEER MATRIX" + "  double[] sheer = { 1, .25, 0, 1 }\r\n");
		double[] sheer = { 1, .25, 0, 1 };
		printIndex(sheer);
		System.out.println();

		int matrixIndex = 0;

		for (int i = 0; i < matrix.length - 1; i = i + 2) {
			// PRESERVE EACH OF THE VALUES
			int firstHolder = matrix[i];
			int secondHolder = matrix[i + 1];

			// MATRIX MULTIPLIACATION
			// double[] sheer = { 1, .25, 0, 1 };

			matrix[i] = (int) (firstHolder * sheer[0] + secondHolder * sheer[1]);
			matrix[i + 1] = (int) (firstHolder * sheer[2] + secondHolder * sheer[3]);

			// NEXT SET
			matrixIndex++;
		}

		System.out.println("AFTER TO SHIFT");
		printIndex(matrix);
		System.out.println();

		return matrix;
	}

	private static void printIndex(int[] matrix) {
		for (int i = 0; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
		System.out.println();
		for (int i = 1; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
		System.out.println();

	}

	private static void printIndex(double[] matrix) {
		for (int i = 0; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
		System.out.println();

		for (int i = 1; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
		System.out.println();

	}

	private static void printXY(int[] matrix) {
		for (int i = 0; i < matrix.length / 2; i++) {
			System.out.print(" " + "x" + "   " + "y");
			System.out.print("\t");
		}
	}

	public static int[] yShift(int[] matrix) {

		for (int i = 1; i < matrix.length; i = i + 2) {
			matrix[i] = matrix[i] - 100;
		}

		return matrix;
	}

	public static void letteringArrays(Graphics draw) {
		System.out.println("________________________________");
		System.out.println("LETTER M");
		int[] alphaM = { 50, 440, 50, 380, 75, 420, 100, 380, 100, 440 };
		drawingM(alphaM, draw);
		alphaM = yShift(shear(alphaM));
		drawingM(alphaM, draw);
		System.out.println("\n" + "________________________________");
		System.out.println("LETTER A");
		int[] alphaA = { 110, 440, 135, 380, 160, 440, 123, 410, 147, 410 };
		drawingA(alphaA, draw);
		alphaA = yShift(shear(alphaA));
		drawingA(alphaA, draw);
		System.out.println("\n" + "________________________________");
		System.out.println("LETTER T");
		int[] alphaT = { 170, 380, 220, 380, 195, 380, 195, 440 };
		drawingT(alphaT, draw);
		alphaT = yShift(shear(alphaT));
		drawingT(alphaT, draw);
		System.out.println("\n" + "________________________________");
		System.out.println("LETTER H");
		int[] alphaH = { 230, 380, 230, 440, 280, 380, 280, 440, 230, 410, 280, 410 };
		drawingH(alphaH, draw);
		alphaH = yShift(shear(alphaH));
		drawingH(alphaH, draw);

	}

	public static void drawingM(int[] alphaM, Graphics draw) {
		// VERTICAL 1
		draw.drawLine(alphaM[0], alphaM[1], alphaM[2], alphaM[3]);
		// SLANT
		draw.drawLine(alphaM[2], alphaM[3], alphaM[4], alphaM[5]);
		// SLANT
		draw.drawLine(alphaM[4], alphaM[5], alphaM[6], alphaM[7]);
		// SLANT
		draw.drawLine(alphaM[6], alphaM[7], alphaM[8], alphaM[9]);

	}

	public static void drawingA(int[] alphaA, Graphics draw) {
		// SLANT
		draw.drawLine(alphaA[0], alphaA[1], alphaA[2], alphaA[3]);
		// SLANT
		draw.drawLine(alphaA[2], alphaA[3], alphaA[4], alphaA[5]);
		// HORRIZONTAL
		draw.drawLine(alphaA[6], alphaA[7], alphaA[8], alphaA[9]);

	}

	public static void drawingT(int[] alphaT, Graphics draw) {
		// HORRIZONTAL
		draw.drawLine(alphaT[0], alphaT[1], alphaT[2], alphaT[3]);
		// VERITCAL
		draw.drawLine(alphaT[4], alphaT[5], alphaT[6], alphaT[7]);

	}

	public static void drawingH(int[] alphaH, Graphics draw) {
		// VERTICAL
		draw.drawLine(alphaH[0], alphaH[1], alphaH[2], alphaH[3]);
		// VERTICAL
		draw.drawLine(alphaH[4], alphaH[5], alphaH[6], alphaH[7]);
		// HORRIZONTAL
		draw.drawLine(alphaH[8], alphaH[9], alphaH[10], alphaH[11]);

	}
}