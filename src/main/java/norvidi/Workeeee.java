package norvidi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Workeeee {
    private String number;
    private String client;
    private String address;
    private String zone;
    private String date;
    private String price;
    private String iva;
    private String total;

    Workeeee() {
    }

    public String toString() {
        return String.format("%s ** %s ** %s ** %s ** %s ** %s ** %s ** %s", number, client, address, zone, date, price, iva, total);
    }

    // getters and setters

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
        }

        return null;
    }

    List<Workeeee> readWorksFromExcelFile(String excelFilePath, Integer Month) throws IOException {
        List<Workeeee> listWorkeeees = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(Month-1);
        Iterator<Row> iterator = firstSheet.iterator();
        Row nextRow = iterator.next();
        while (iterator.hasNext()) {
            nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            Workeeee aWorkeeee = new Workeeee();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        aWorkeeee.setNumber(getCellValue(nextCell) + "");
                        break;
                    case 1:
                        aWorkeeee.setClient(getCellValue(nextCell) + "");
                        break;
                    case 2:
                        aWorkeeee.setAddress(getCellValue(nextCell) + "");
                        break;
                    case 3:
                        aWorkeeee.setZone(getCellValue(nextCell) + "");
                        break;
                    case 4:
                        Date d = nextCell.getDateCellValue();
                        aWorkeeee.setDate(String.valueOf(d));
                        break;
                    case 5:
                        aWorkeeee.setPrice(String.valueOf(getCellValue(nextCell)));
                        break;
                    case 6:
                        aWorkeeee.setIva(String.valueOf(getCellValue(nextCell) + ""));
                        break;
                    case 7:
                        aWorkeeee.setTotal(String.valueOf(getCellValue(nextCell)));
                        break;
                }


            }
            listWorkeeees.add(aWorkeeee);
        }

        workbook.close();
        inputStream.close();

        return listWorkeeees;
    }

    private void setNumber(String number) {
        this.number = number;
    }

    private void setClient(String client) {
        this.client = client;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setZone(String zone) {
        this.zone = zone;
    }

    private void setDate(String date) {
        this.date = date;
    }

    private void setPrice(String price) {
        this.price = price;
    }

    private void setIva(String iva) {
        this.iva = iva;
    }

    private void setTotal(String total) {
        this.total = total;
    }
}

