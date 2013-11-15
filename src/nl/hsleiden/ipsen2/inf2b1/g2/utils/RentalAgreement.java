package nl.hsleiden.ipsen2.inf2b1.g2.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.imageio.ImageIO;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RentalAgreement {
	private WritableCellFormat timesBoldUnderline;
	private WritableCellFormat times;
	private WritableCellFormat timesHeader;
	private int customerId, rentalId;
	private String inputFile, customerName, vehicleName, expectedReceiveDate,
			receiveDate, lisencePlate, adres, city, phone, vehicleType, color,
			zipcode;
	private ArrayList<String> options = new ArrayList<String>();
	private double payment, total;

	public void setOutputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public void write() throws IOException, WriteException {
		File file = new File(inputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("nl", "NL"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Huurovereenkomst " + rentalId, 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createLabel(excelSheet);
		createContent(excelSheet);

		workbook.write();
		workbook.close();
	}

	private void createLabel(WritableSheet sheet) throws WriteException,
			IOException {
		// Lets create a times font
		WritableFont times10pt = new WritableFont(WritableFont.TIMES, 11);
		// Define the cell format
		times = new WritableCellFormat(times10pt);
		// Lets automatically wrap the cells
		times.setWrap(false);

		// create create a bold font with unterlines
		WritableFont times10ptBoldUnderline = new WritableFont(
				WritableFont.TIMES, 11, WritableFont.BOLD, false,
				UnderlineStyle.SINGLE);
		timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		// Lets automatically wrap the cells
		timesBoldUnderline.setWrap(false);

		// create create a bold font with unterlines
		WritableFont timesHeader16pt = new WritableFont(WritableFont.TIMES, 16,
				WritableFont.BOLD, false);
		timesHeader = new WritableCellFormat(timesHeader16pt);
		// Lets automatically wrap the cells
		timesHeader.setWrap(false);

		CellView cv = new CellView();
		cv.setFormat(times);
		cv.setFormat(timesBoldUnderline);
		cv.setFormat(timesHeader);
		cv.setAutosize(true);
		sheet.setColumnView(0, 25);
		sheet.setColumnView(2, 16);
		sheet.setColumnView(3, 11);

		addImage(sheet);
		// Write a few headers
		addHeader(sheet, 0, 3, "Huurovereenkomst");
		addCaption(sheet, 0, 5, "Gegevens voertuig");
		addCaption(sheet, 2, 5, "Verhuurnummer");
		addCaption(sheet, 4, 5, "Klantgegevens");
		addCaption(sheet, 0, 11, "Geselecteerde opties");
		addCaption(sheet, 0, 18, "Datum afgifte");
		addCaption(sheet, 2, 18, "Datum verwachtte inname");
		addCaption(sheet, 0, 22, "Aanbetaling");
		addCaption(sheet, 2, 22, "Totaal");
		addCaption(sheet, 0, 26, "Handtekening klant:");
	}

	private void createContent(WritableSheet sheet) throws WriteException,
			RowsExceededException {

		// Klant gegevens
		addLabel(sheet, 4, 6, customerName);
		addLabel(sheet, 4, 7, adres);
		addLabel(sheet, 4, 8, zipcode);
		addLabel(sheet, 4, 9, city);
		addLabel(sheet, 4, 10, phone);
		addLabel(sheet, 4, 11, "Klantnummer: " + customerId);

		// Verhuurnummer
		addNumber(sheet, 2, 6, rentalId);

		// Voertuig gegevens
		addLabel(sheet, 0, 6, vehicleName);
		addLabel(sheet, 0, 7, vehicleType);
		addLabel(sheet, 0, 8, lisencePlate);
		addLabel(sheet, 0, 9, color);

		// Voertuig opties
		int i = 12;
		for (String option : options)
		{
			addLabel(sheet, 0, i, option);
			i++;
		}

		// Data
		addLabel(sheet, 0, 19, receiveDate);
		addLabel(sheet, 2, 19, expectedReceiveDate);

		// Payments
		addLabel(sheet, 0, 23, "€" + payment);
		addLabel(sheet, 2, 23, "€" + total);
	}

	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesBoldUnderline);
		sheet.addCell(label);
	}

	private void addHeader(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, timesHeader);
		sheet.addCell(label);
	}

	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		Number number;
		number = new Number(column, row, integer, times);
		sheet.addCell(number);
	}

	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, times);
		sheet.addCell(label);
	}

	private void addImage(WritableSheet sheet) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getResource(
				"/image/logo.png"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "PNG", baos);
		sheet.addImage(new WritableImage(3, 0, 3, 4, baos.toByteArray()));
	}

	public void setKlantNummer(int id) {
		this.customerId = id;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public void setKlantNaam(String name) {
		this.customerName = name;
	}

	public void setNaamVoertuig(String name) {
		this.vehicleName = name;
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

	public void setLisencePlate(String lisence) {
		this.lisencePlate = lisence;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
}
