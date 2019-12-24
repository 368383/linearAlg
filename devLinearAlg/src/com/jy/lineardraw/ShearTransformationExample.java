package com.jy.lineardraw;

import java.awt.Component;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class ShearTransformationExample {

	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.add(new CustomPaintComponent());
		int frameWidth = 1000;
		int frameHeight = 100;
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
	}

//
	static class CustomPaintComponent extends Component {
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			axis(g2d);
			drawingLetters(g2d);
		}

	}

	public static void axis(Graphics axis) {

		axis.drawLine(500, 0, 500, 1000);
		axis.drawLine(0, 500, 1000, 500);
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

	public static void drawingLetters(Graphics draw) {
		System.out.println("________________________________");
		System.out.println("LETTER M");
		int[] alphaM = { 0, 50, 0, 0, 25, 25, 50, 0, 50, 50 };
		drawingM(alphaM, draw);
		yShift(shear(alphaM));
		drawingM(alphaM, draw);

		System.out.println("\n" + "________________________________");
		System.out.println("LETTER A");
		int[] alphaA = { 60, 50, 85, 0, 110, 50, 72, 25, 97, 25 };
		drawingA(alphaA, draw);
		yShift(shear(alphaA));
		drawingA(alphaA, draw);

		System.out.println("\n" + "________________________________");
		System.out.println("LETTER T");
		int[] alphaT = { 120, 0, 170, 0, 145, 0, 145, 50 };
		drawingT(alphaT, draw);
		yShift(shear(alphaT));
		drawingT(alphaT, draw);

		System.out.println("\n" + "________________________________");
		System.out.println("LETTER H");
		int[] alphaH = { 180, 50, 180, 0, 230, 50, 230, 0, 180, 25, 230, 25 };
		drawingH(alphaH, draw);
		yShift(shear(alphaH));
		drawingH(alphaH, draw);

	}

	public static void drawingM(int[] alphaM, Graphics draw) {

		// VERTICAL 1
		draw.drawLine(alphaM[0] + 500, alphaM[1] + 450, alphaM[2] + 500, alphaM[3] + 450);
		// SLANT
		draw.drawLine(alphaM[2] + 500, alphaM[3] + 450, alphaM[4] + 500, alphaM[5] + 450);
		// SLANT
		draw.drawLine(alphaM[4] + 500, alphaM[5] + 450, alphaM[6] + 500, alphaM[7] + 450);
		// SLANT
		draw.drawLine(alphaM[6] + 500, alphaM[7] + 450, alphaM[8] + 500, alphaM[9] + 450);

	}

	public static void drawingA(int[] alphaA, Graphics draw) {

		// SLANT
		draw.drawLine(alphaA[0] + 500, alphaA[1] + 450, alphaA[2] + 500, alphaA[3] + 450);
		// SLANT
		draw.drawLine(alphaA[2] + 500, alphaA[3] + 450, alphaA[4] + 500, alphaA[5] + 450);
		// HORRIZONTAL
		draw.drawLine(alphaA[6] + 500, alphaA[7] + 450, alphaA[8] + 500, alphaA[9] + 450);

	}

	public static void drawingT(int[] alphaT, Graphics draw) {

		// HORRIZONTAL
		draw.drawLine(alphaT[0] + 500, alphaT[1] + 450, alphaT[2] + 500, alphaT[3] + 450);
		// VERITCAL
		draw.drawLine(alphaT[4] + 500, alphaT[5] + 450, alphaT[6] + 500, alphaT[7] + 450);

	}

	public static void drawingH(int[] alphaH, Graphics draw) {

		// VERTICAL
		draw.drawLine(alphaH[0] + 500, alphaH[1] + 450, alphaH[2] + 500, alphaH[3] + 450);
		// VERTICAL
		draw.drawLine(alphaH[4] + 500, alphaH[5] + 450, alphaH[6] + 500, alphaH[7] + 450);
		// HORRIZONTAL
		draw.drawLine(alphaH[8] + 500, alphaH[9] + 450, alphaH[10] + 500, alphaH[11] + 450);

	}
}