package com.javaFSD.traveller.currencyspend;

import java.util.Arrays;

import com.javaFSD.traveller.currencyspend.src.com.gl.datastructures.common.DataStructureUtils;
//import com.javaFSD.traveller.currencyspend.BubbleSort;

public class CurrencyDenominationDeterminator {
	private int[] denominations;
	private int payAmount;

	private int[] resultdenominationArray;
	private int[] resultdenominationCountArray;

	public CurrencyDenominationDeterminator(int[] denominations, int payAmount) {

		this.denominations = denominations;
		this.payAmount = payAmount;

		this.resultdenominationArray = new int[this.denominations.length];
		this.resultdenominationCountArray = new int[this.denominations.length];

	}

	public void determine() {

		sort();

		int index = 0;
		int resultIndex = 0;
		int balancedOutPayAmount = payAmount;

		while (index < denominations.length) {

			int denomination = denominations[index];
			int quotient = balancedOutPayAmount / denomination;

			int remainder = balancedOutPayAmount % denomination;

			if (quotient != 0) {

				int temp = denomination * quotient;

				balancedOutPayAmount = balancedOutPayAmount - (temp);

				resultdenominationArray[resultIndex] = denomination;

				resultdenominationCountArray[resultIndex] = quotient;

				resultIndex++;

				if (remainder != 0) {

				} else if (remainder == 0) {

					if (balancedOutPayAmount == 0) {
						printSuccessMessage(resultIndex);
						break;
					}
				}

			} else if (quotient == 0) {
				if (remainder != 0) {

					if (!isLastDenomination(index)) {
					} else {
						printErrorMessage();
						break;
					}
				} else if (remainder == 0) {
					break;
				}
			}
			index++;
		}

	}

	private void sort() {

		BubbleSort bubbleSort = new BubbleSort(denominations);
		bubbleSort.sortDesc();
	}

	private boolean isLastDenomination(int index) {

		if (index == denominations.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	private void printErrorMessage() {

		System.out.println("For entered denomination values -->" + DataStructureUtils.printAndReturn(denominations)
				+ "\nIt is not possible to give for the payment ₹ [" + payAmount + "]" + "\n\t\tKindly try again");
	}

	private void printSuccessMessage(int resultIndex) {

		resultdenominationArray = Arrays.copyOf(resultdenominationArray, resultIndex);

		resultdenominationCountArray = Arrays.copyOf(resultdenominationCountArray, resultIndex);

		System.out.println("Thank you for using PayMoney.\n Your payment is  ₹ " + payAmount
				+ " and available denominations are --> " + DataStructureUtils.printAndReturn(denominations));
		System.out.println("\t\t\t-----------------------------------------------------------");

		System.out.println("Here is  payment approach can be used to give minimum number of notes :");
		for (int index = 0; index < resultdenominationArray.length; index++) {

			int denomination = resultdenominationArray[index];
			int denominationCount = resultdenominationCountArray[index];

			System.out.println("Denomination  ₹ " + denomination + " to be given [" + denominationCount + "] times.");
		}
	}

	public static void main(String[] args) {

		test(new int[] { 10, 5, 1 }, 12);
		test(new int[] { 78, 60, 25, 12, 5 }, 128);
		test(new int[] { 123, 18, 12, 5 }, 158);
		test(new int[] { 15 }, 10);
		test(new int[] { 25, 15 }, 10);

	}

	private static void test(int[] denominations, int payAmount) {

		System.out.println("---------------------------------------");
		CurrencyDenominationDeterminator determinator = new CurrencyDenominationDeterminator(denominations, payAmount);

		determinator.determine();
	}
}
