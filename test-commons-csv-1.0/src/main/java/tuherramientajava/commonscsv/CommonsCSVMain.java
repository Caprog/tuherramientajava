package tuherramientajava.commonscsv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


/**
 * @author Caprog
 * your-java-tool.blogspot.com
 *
 */
public class CommonsCSVMain {

	private static final String PRUEBA_LECTURA_CSV = "src/main/resources/lectura.csv";
	private static final String PRUEBA_ESCRITURA_CSV = "src/main/resources/escritura.csv";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File(PRUEBA_LECTURA_CSV);
		CSVParser parser = null;
		CSVPrinter printer = null;
		try {
			//Leyendo de un archivo
			parser = CSVFormat.RFC4180.parse(new FileReader(file));
			Iterator<CSVRecord> it = parser.iterator();
			if(it.hasNext()){
				CSVRecord cabecera = it.next();
				printCSV(cabecera);
				while(it.hasNext()){
					CSVRecord detalle = it.next();
					printCSV(detalle);
				}
			}
			//Escribiendo en un archivo
			printer = CSVFormat.DEFAULT.withHeader("name", "lastname","age","gender").print(new FileWriter(PRUEBA_ESCRITURA_CSV));
			printer.printRecord("Carlos","Garcia","28","M");
			printer.printRecord("Victor","Sanchez","35","M");
			printer.printRecord("María","Lobo","20","F");
			printer.printRecord("Pamela","González","10","F");
			printer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(parser != null){
				parser.close();
			}
			if(printer != null){
				printer.close();
			}
		}
		
	}

	private static void printCSV(CSVRecord registro) {
		System.out.println(registro.get(0) +","+registro.get(1)+","+registro.get(2)+","+registro.get(3));
	}
}
