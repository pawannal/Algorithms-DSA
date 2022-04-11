package com.gl.JavaFsd.payMoney.Trans;

import com.javaFSD.traveller.currencyspend.src.com.gl.datastructures.common.DataStructureUtils;

public class TargetAchievmentCalculator {

	private int[] transcations;
	private int dailyTarget;
	private boolean dailyTargetAchieved;
	private int transactionCounter;

	public TargetAchievmentCalculator(int[] transcations, int dailyTarget) {

		this.transcations = transcations;
		this.dailyTarget = dailyTarget;
		this.dailyTargetAchieved = false;
		this.transactionCounter = 0;
	}

	public void calculate() {

		int tempCounter = 0;

		for (int index = 0; index < transcations.length; index++) {

			int targetValue = transcations[index];
			tempCounter = tempCounter + targetValue;

			transactionCounter++;
			if (tempCounter >= dailyTarget) {
				dailyTargetAchieved = true;
				break;
			}
		}
	}

	public void printOutcome() {
		if (dailyTargetAchieved) {
			System.out.println(
					"Daily target of [" + dailyTarget + "] achieved after [" + transactionCounter + "] transcation(s)");
		} else {
			System.out.println("Daily target of [" + dailyTarget + "] is yet to be achieved..");
		}
	}

	public static void main(String[] args) {

		test(new int[] { 75, 54, 23, 34, 14 }, 175);
		test(new int[] { 20, 12, 31 }, 175);
		test(new int[] { 20, 12, 31 }, 19);
		test(new int[] { 100 }, 101);
	}

	private static void test(int[] transcations, int dailyTarget) {
		System.out.println("--------------------------");
		DataStructureUtils.print(transcations);
		TargetAchievmentCalculator calculator = new TargetAchievmentCalculator(transcations, dailyTarget);

		calculator.calculate();
		calculator.printOutcome();
	}

}
