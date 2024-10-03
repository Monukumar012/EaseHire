package com.easehire.application.utility;

import com.easehire.application.utility.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
public class Utility {
    private Utility() {
    }

    public static String parseCell(Cell cell){
        return Optional.ofNullable(cell)
                .map(Cell::getStringCellValue)
                .orElse(null);
    }
    public static Gender parseGender(Cell cell) {
        String value=parseCell(cell);
        if(value==null)return null;

        try {
            return Gender.valueOf(value);
        } catch (IllegalArgumentException e) {
            log.warn("Unable to parse gender from excel : {}",value);
            return null;
        }
    }

    public static LocalDate parseDate(Cell cell) {
        String value=parseCell(cell);
        if(value==null)return null;

        try {
            return LocalDate.parse(value, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            log.warn("Unable to parse date from excel : {}",value);
            return null;
        }
    }

    public static String parseString(Cell cell) {
        String value=parseCell(cell);
        return "NA".equalsIgnoreCase(value) ? null : value;
    }

    public static Double parseDouble(Cell cell) {
        String value=parseCell(cell);
        if(value==null)return null;
        try {
            return "NA".equalsIgnoreCase(value) ? null : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("Unable to parse double from excel : {}",value);
            return null;
        }
    }

    public static Integer parseInteger(Cell cell) {
        String value=parseCell(cell);
        if(value==null)return null;
        
        try {
            return "NA".equalsIgnoreCase(value) ? null : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Unable to parse Integer from excel : {}",value);
            return null;
        }
    }

    public static Long parseLong(Cell cell) {
        String value=parseCell(cell);
        if(value==null)return null;

        try {
            return "NA".equalsIgnoreCase(value) ? null : Long.parseLong(value);
        } catch (NumberFormatException e) {
            log.warn("Unable to parse Long from excel : {}",value);
            return null;
        }
    }

    public static Sheet verifyFileFormat(String originalFilename, InputStream in) throws IOException {
        log.debug("OriginalFilename: {}",originalFilename);
        if(originalFilename==null)return null;
        if(originalFilename.toLowerCase().endsWith(".xls"))
            return new HSSFWorkbook(in).getSheetAt(0);
        if(originalFilename.toLowerCase().endsWith(".xlsx"))
            return new XSSFWorkbook(in).getSheetAt(0);
        return null;
    }

    public static boolean validateHeaders(Row sheetHeader) {
        String[] expectedHeaders = {"First Name","Last Name","Gender","Email Address","Total Marks (55.0)","Cognitive Ability Marks (30.0)","Coding Marks (10.0)","Technical Marks (15.0)","Final Marks","Result (OK/NotOk)","Report Link","Date Of Birth","Contact Number","10%","12%","Graduation","Graduation %","Branch","Graduation YOP","College Code (graduation)","Post Graduation","PG %","PG YOP"};
        if (sheetHeader == null || sheetHeader.getPhysicalNumberOfCells()!=expectedHeaders.length)return false;

        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = sheetHeader.getCell(i);
            if (cell == null || !expectedHeaders[i].equals(cell.getStringCellValue().trim())){
                assert cell != null;
                log.debug("Given Sheet Header not matched on : {} != {}",expectedHeaders[i],cell.getStringCellValue());
                return false;
            }
        }
        return true;
    }
}
