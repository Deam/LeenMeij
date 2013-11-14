package nl.hsleiden.ipsen2.inf2b1.g2.utils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RentalAgreement {
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private String inputFile;
	private int klantNummer;
	private int rentalId;
	private String klantNaam;
	private String naamVoertuig;
	private String expectedReceiveDate;
	private String receiveDate;
	private double payment;
	private double total;

	public void setOutputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void write() throws IOException, WriteException {
		File file = new File(inputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Report", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet);
		createContent(excelSheet);

		workbook.write();
		workbook.close();
	}

	private void createLabel(WritableSheet sheet) throws WriteException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(true);

		// create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(true);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setAutosize(true);

		// Write a few headers
		addCaption(sheet, 0, 0, "Klantnummer");
		addCaption(sheet, 2, 0, "Verhuurnummer");
		addCaption(sheet, 0, 3, "Naam klant");
		addCaption(sheet, 2, 3, "Voertuig");
		addCaption(sheet, 0, 5, "Ontvangst datum");
		addCaption(sheet, 2, 5, "Retour datum");
		addCaption(sheet, 0, 7, "Aanbetaling");
		addCaption(sheet, 2, 7, "Totaal");
	}

	private void createContent(WritableSheet sheet) throws WriteException,
	RowsExceededException {
		// Write a few number
		addNumber(sheet, 0, 1, klantNummer);
		addNumber(sheet, 2, 1, rentalId);
		addLabel(sheet, 0, 4, klantNaam);
		addLabel(sheet, 2, 4, naamVoertuig);
		addLabel(sheet, 0, 6, receiveDate);
		addLabel(sheet, 2, 6, expectedReceiveDate);
		addNumber(sheet, 0, 8, payment);
		addNumber(sheet, 2, 8, total);
		/*// Lets calculate the sum of it
		StringBuffer buf = new StringBuffer();
		buf.append("SUM(A2:A10)");
		Formula f = new Formula(0, 10, buf.toString());
		sheet.addCell(f);
		buf = new StringBuffer();
		buf.append("SUM(B2:B10)");
		f = new Formula(1, 10, buf.toString());
		sheet.addCell(f);

		// now a bit of text
		for (int i = 12; i < 20; i++) {
			// First column
			addLabel(sheet, 0, i, "SomuchTest " + i);
			// Second column
			addLabel(sheet, 1, i, "M");
		}*/
	}

	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}
	
	private void addNumber(WritableSheet sheet, int column, int row,
			Double dbl) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, dbl, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}

	public void setKlantNummer(int klantNummer) {
		this.klantNummer = klantNummer;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public void setKlantNaam(String klantNaam) {
		this.klantNaam = klantNaam;
	}

	public void setNaamVoertuig(String naamVoertuig) {
		this.naamVoertuig = naamVoertuig;
	}

	public void setExpectedReceiveDate(String expectedReceiveDate) {
		this.expectedReceiveDate = expectedReceiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
