package org.com.pack.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.com.pack.dto.Emp;
import org.com.pack.util.DateTimeUtil;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@Service
public class CSVGeneratorService {

	@PostConstruct
	public void generateCSV() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String fileLocation;
		Emp emp1 = new Emp(1, "xxx", new Date());
		Emp emp2 = new Emp(2, "yyy", DateTimeUtil.createFormatedDate());
		
		List<Emp> empList= new ArrayList<>();
		empList.add(emp1);
		empList.add(emp2);
		
		System.out.println(empList);
		
		File currDir = new File(".");
		String path = currDir.getAbsolutePath()+"/generated-file/"; 
		String generatedFileName = "test.csv";
        fileLocation = path + generatedFileName;
        
        try {
        	CSVWriter writer = new CSVWriter(new FileWriter(fileLocation));
        	
        	/*       
        	 * Write Array to CSV	
            String line2[] = {"1", "Krishna", "2548", "2012-01-01", "IT"};
            String line3[] = {"2", "Vishnu", "4522", "2013-02-26", "Operations"};
            String line4[] = {"3", "Raja", "3021", "2016-10-10", "HR"};
            String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
            //Instantiating the List Object
            List list = new ArrayList();
            list.add(line1);
            list.add(line2);
            list.add(line3);
            list.add(line4);
            list.add(line5);
            //Writing data to the csv file
            writer.writeAll(list);
            writer.flush();
            */
        	
        	/*
        	 * write object from array list to CSV
        ColumnPositionMappingStrategy mappingStrategy= new ColumnPositionMappingStrategy(); 
        mappingStrategy.setType(Emp.class); 
        
        String[] columns = new String[] { "empID", "empName", "DOB"}; 
        mappingStrategy.setColumnMapping(columns);
        
        StatefulBeanToCsvBuilder<Emp> builder= new StatefulBeanToCsvBuilder(writer); 
        StatefulBeanToCsv beanWriter =  builder.withMappingStrategy(mappingStrategy).build(); 

    
        beanWriter.write(empList); 
        writer.close(); 
        */
        	List empCSVList = new ArrayList<>();
        	String[] header = new String[] { "empID", "empName", "DOB"};
        	empCSVList.add(header);
        	for(Emp empObj:empList) {
        		String line[] = new String[] { String.valueOf(empObj.getEmpId()), empObj.getEmpName(), String.valueOf(empObj.getDob())};
        		empCSVList.add(line);
        	}
    
        	 writer.writeAll(empCSVList);
             writer.flush();	
        
            System.out.println("Data entered");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
