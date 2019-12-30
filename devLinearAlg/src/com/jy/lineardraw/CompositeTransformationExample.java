package com.jy.lineardraw;

import java.awt.Component;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Scanner;

public class CompositeTransformationExample {
	private static ArrayList<String> listOfActions = new ArrayList<String>();
	private static ArrayList<Double> unitsOfActions = new ArrayList<Double>();

	public static void main(String[] args) {

		askActions();

		Frame frame = new Frame();
		frame.add(new CustomPaintComponent());
		int frameWidth = 1000;
		int frameHeight = 1000;
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);

	}

	static class CustomPaintComponent extends Component {
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			axis(g2d);
			// tickmarks(g2d);
			drawingLetters(g2d);
		}
	}

	public static void askActions() {

		while (true) {
			System.out.println("LINEAR ALGEBRA TRANSFORMATIONS DEMO - LIST OF COMMANDS"
					+ "\nSYNTAX: ENTER IN COMMAND FIRST (NOT CASE SENSITIVE), THEN ENTER AMOUNT"
					+ "\n----------------------------------------------------------------------"
					+ "\nHOR - HORRIZONTAL SHIFT (-500 TO 500 PIXELS)" + "\nVER - VERTICAL SHIFT (-500 TO 500 PIXELS)"
					+ "\nROT - ROTATION(-INFINITY TO INFINITY IN DEGREES)"
					+ "\nDIA -  DIALATION (-3 TO 3 - WARNING, MAY LEAVE GRAPH IF TOO LARGE)"
					+ "\nHORREF - HORRIZONTAL REFLECTION" + "\nVERREF - VERTICAL REFLECTION"
					+ "\nFINISH - TO COMPLETE ADDING COMMANDS");
			System.out.print("Your Desired Command Input:");
			Scanner actionInput = new Scanner(System.in);
			String alpha = actionInput.next().toUpperCase();
			if (alpha.equals("FINISH")) {
				actionInput.close();
				break;
			}
			if (alpha.contains("HOR") || alpha.contains("VER") || alpha.contains("ROT") || alpha.contains("REF")
					|| alpha.contains("DIA")) {
				double input = 0;
				listOfActions.add(alpha);
				if (!alpha.contains("REF")) {
					System.out.print("Your Desired Amount Input:");

					input = actionInput.nextDouble();
					unitsOfActions.add(input);
				} else {
					unitsOfActions.add((double) -1);
				}
				System.out.println("__________________________________________________\n" + "ACTION ADDED \t ACTION: "
						+ alpha + "\t AMOUNT: " + input + "\n__________________________________________________");

			} else {
				System.out.println("INVALID INPUT");
			}
		}
		System.out.println("__________________________________________________\n" + "HERE ARE YOUR INPUTS");
		for (String currentAction : listOfActions) {
			System.out.print(currentAction + "\t");
		}
		System.out.println();
		for (Double currentParam : unitsOfActions) {
			System.out.print(currentParam + "\t");
		}
		System.out.println();

	}

	public static void axis(Graphics axis) {

		axis.drawLine(500, 0, 500, 1000);
		axis.drawLine(0, 500, 1000, 500);
		// axis.drawLine(0, 400, 1000, 400);
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

	public static void execution(int[] arraySet) {
		int actionNumber = -1;
		while (actionNumber < listOfActions.size() - 1) {
			actionNumber++;

			// System.out.println("Action Number " + actionNumber + " " +
			// listOfActions.size());
			String action = listOfActions.get(actionNumber);
			switch (action) {
			case "HOR":
				horrizontalShift(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				// System.out.println("action ");
				continue;
			case "VER":
				verticalShift(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				continue;
			case "DIA":
				dialation(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				continue;
			case "ROT":
				rotation(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				continue;
			case "HORDIA":
				continue;
			case "HORREF":
				horrizontalReflection(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				continue;
			case "VERREF":
				verticalReflection(arraySet, (int) Math.round(unitsOfActions.get(actionNumber)));
				continue;
			}
		}
	}

	public static void horrizontalShift(int[] matrix, int amount) {
		System.out.println("HORRIZONTAL SHIFT");
		int[] originalMatrix = matrix.clone();
		
		for (int i = 0; i < matrix.length - 1; i = i + 2) {
			matrix[i] = (int) (matrix[i] + amount);
		}
		
		String[] operationsMatrix = { "X+" + amount, "", "y", "" };
		printIndex(matrix, originalMatrix, operationsMatrix);
	}

	public static void verticalReflection(int[] matrix, int amount) {
		System.out.println("VERTICAL REFLECTION");
		int[] originalMatrix = matrix.clone();
		
		for (int i = 1; i < matrix.length; i = i + 2) {
			matrix[i] = (int) (matrix[i] * -1);
		}
		
		String[] operationsMatrix = { "1", "0", "0", "-1" };
		printIndex(matrix, originalMatrix, operationsMatrix);
	}

	public static void horrizontalReflection(int[] matrix, int amount) {
		System.out.println("HORRIZONTAL REFLECTION");
		int[] originalMatrix = matrix.clone();
		for (int i = 0; i < matrix.length; i = i + 2) {
			matrix[i] = (int) (matrix[i] * -1);
		}
		String[] operationsMatrix = { "-1", "0", "0", "1" };
		printIndex(matrix, originalMatrix, operationsMatrix);
	}

	public static void verticalShift(int[] matrix, int amount) {
		System.out.println("VERTICAL SHIFT");
		int[] originalMatrix = matrix.clone();
		for (int i = 1; i < matrix.length; i = i + 2) {
			matrix[i] = (int) (matrix[i] + amount);

		}
		String[] operationsMatrix = { "x", "", "y+" + amount, "" };
		printIndex(matrix, originalMatrix, operationsMatrix);
	}

	public static void rotation(int[] matrix, int amount) {
		System.out.println("ROTATION");
		int[] originalMatrix = matrix.clone();
		double[] rotation = { Math.cos(2 * Math.PI - Math.toRadians(amount)),
				Math.sin(2 * Math.PI - Math.toRadians(amount)), -Math.sin(2 * Math.PI - Math.toRadians(amount)),
				Math.cos(2 * Math.PI - Math.toRadians(amount)) };

		for (int i = 0; i < matrix.length - 1; i = i + 2) {
			// PRESERVE EACH OF THE VALUES
			int firstHolder = matrix[i];
			int secondHolder = matrix[i + 1];
			// MATRIX MULTIPLIACATION
			matrix[i] = (int) (firstHolder * rotation[0] + secondHolder * rotation[1]);
			matrix[i + 1] = (int) (firstHolder * rotation[2] + secondHolder * rotation[3]);
			// NEXT SET
		}

		String[] operationsMatrix = new String[rotation.length];
		for (int i=0;i<operationsMatrix.length;i++) {
			operationsMatrix[i] = String.valueOf(rotation[i]);
		}
		printIndex(matrix, originalMatrix, operationsMatrix);
	}

	public static void dialation(int[] matrix, int amount) {

		System.out.println("DIALATION");
		int[] originalMatrix = matrix.clone();

		for (int i = 0; i < matrix.length; i = i + 2) {
			matrix[i] = (int) (matrix[i] * amount);
		}
		for (int i = 1; i < matrix.length; i = i + 2) {
			matrix[i] = (int) (matrix[i] * amount);
		}
		String[] operationsMatrix = {String.valueOf(amount),"0","0",String.valueOf(amount)};
		printIndex(matrix, originalMatrix,operationsMatrix);
	}

	private static void printIndex(int[] originalMatrix, int[] matrix, String[] operationsMatrix) {
		System.out.print("Action Matrix");
		for (int i = 0; i < (int) (operationsMatrix[0].length()*2); i++) {
			System.out.print(" ");
		}
		System.out.print("Before Shift");
		for (int i = 0; i < (int) (originalMatrix.length/2) - 1; i++) {
			System.out.print("\t");
		}
		System.out.print("AFTER SHIFT" + "\n");
		for (int i = 0; i < 2; i++) {
			System.out.print(operationsMatrix[i] + "\t");
		}
		System.out.print("|  ");

		printTopRow(matrix);
		System.out.print("|  ");

		printTopRow(originalMatrix);
		System.out.println();
		for (int i = 2; i < 4; i++) {
			System.out.print(operationsMatrix[i] + "\t");
		}
		System.out.print("|  ");

		printBottomRow(matrix);
		System.out.print("|  ");

		printBottomRow(originalMatrix);
		System.out.println();
		printActualDisplayValues(matrix,originalMatrix );
	}

	private static void printBottomRow(int[] matrix) {
		for (int i = 1; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
	}

	private static void printTopRow(int[] matrix) {
		for (int i = 0; i < matrix.length; i = i + 2) {
			System.out.print(matrix[i] + "\t");
		}
	}

	private static void printActualDisplayValues(int[] matrix, int[] originalMatrix) {
		System.out.print("DEBUG LINE"+" : "+"BEFORE SHIFT MATRIX");
		
	
		for (int i = 0; i < (int) (originalMatrix.length/2) - 1; i++) {
			System.out.print("\t");
		}
		System.out.print("AFTER SHIFT MATRIX");
		System.out.println();
		for (int i = 0; i < matrix.length; i = i + 2) {
			System.out.print(500 + matrix[i] + "\t");
		}
		System.out.print("|  ");

		for (int i = 0; i < originalMatrix.length; i = i + 2) {
			System.out.print(500 + originalMatrix[i] + "\t");
		}
		System.out.println();
		for (int i = 1; i < matrix.length; i = i + 2) {
			System.out.print(500 - matrix[i] + "\t");
		}
		System.out.print("|  ");

		for (int i = 1; i < originalMatrix.length; i = i + 2) {
			System.out.print(500 - originalMatrix[i] + "\t");
		}
		System.out.println("\n---------------------------------------");

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

	public static void drawingLetters(Graphics draw) {
		System.out.println("\n__________________________________________________");
		System.out.print("LETTER M" + "\t");
		int[] alphaM = { 0, 0, 0, 50, 25, 25, 50, 50, 50, 0 };

		drawingM(alphaM, draw);
		execution(alphaM);
		drawingM(alphaM, draw);

		System.out.println("\n__________________________________________________");
		System.out.print("LETTER A" + "\t");
		int[] alphaA = { 60, 0, 85, 50, 110, 0, 72, 25, 97, 25 };

		drawingA(alphaA, draw);
		execution(alphaA);
		drawingA(alphaA, draw);

		System.out.println("\n__________________________________________________");
		System.out.print("LETTER T" + "\t");
		int[] alphaT = { 120, 50, 170, 50, 145, 50, 145, 0 };

		drawingT(alphaT, draw);
		execution(alphaT);
		drawingT(alphaT, draw);

		System.out.println("\n__________________________________________________");
		System.out.print("LETTER H" + "\t");
		int[] alphaH = { 180, 0, 180, 50, 230, 0, 230, 50, 180, 25, 230, 25 };

		drawingH(alphaH, draw);
		execution(alphaH);
		drawingH(alphaH, draw);

	}

	public static void drawingM(int[] alphaM, Graphics draw) {
		// VERTICAL 1
		draw.drawLine(alphaM[2] + 500, -1 * alphaM[3] + 500, alphaM[0] + 500, -1 * alphaM[1] + 500);
		// SLANT
		draw.drawLine(alphaM[4] + 500, -1 * alphaM[5] + 500, alphaM[2] + 500, -1 * alphaM[3] + 500);
		// SLANT
		draw.drawLine(alphaM[6] + 500, -1 * alphaM[7] + 500, alphaM[4] + 500, -1 * alphaM[5] + 500);
		// SLANT
		draw.drawLine(alphaM[8] + 500, -1 * alphaM[9] + 500, alphaM[6] + 500, -1 * alphaM[7] + 500);

	}

	public static void drawingA(int[] alphaA, Graphics draw) {

		// SLANT
		draw.drawLine(alphaA[0] + 500, -1 * alphaA[1] + 500, alphaA[2] + 500, -1 * alphaA[3] + 500);
		// SLANT
		draw.drawLine(alphaA[2] + 500, -1 * alphaA[3] + 500, alphaA[4] + 500, -1 * alphaA[5] + 500);
		// HORRIZONTAL
		draw.drawLine(alphaA[6] + 500, -1 * alphaA[7] + 500, alphaA[8] + 500, -1 * alphaA[9] + 500);

	}

	public static void drawingT(int[] alphaT, Graphics draw) {

		// HORRIZONTAL
		draw.drawLine(alphaT[0] + 500, -1 * alphaT[1] + 500, alphaT[2] + 500, -1 * alphaT[3] + 500);
		// VERITCAL
		draw.drawLine(alphaT[4] + 500, -1 * alphaT[5] + 500, alphaT[6] + 500, -1 * alphaT[7] + 500);
	}

	public static void drawingH(int[] alphaH, Graphics draw) {

		// VERTICAL
		draw.drawLine(alphaH[0] + 500, -1 * alphaH[1] + 500, alphaH[2] + 500, -1 * alphaH[3] + 500);
		// VERTICAL
		draw.drawLine(alphaH[4] + 500, -1 * alphaH[5] + 500, alphaH[6] + 500, -1 * alphaH[7] + 500);
		// HORRIZONTAL
		draw.drawLine(alphaH[8] + 500, -1 * alphaH[9] + 500, alphaH[10] + 500, -1 * alphaH[11] + 500);

	}
}