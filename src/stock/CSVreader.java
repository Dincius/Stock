package stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVreader {
	private static final String splitCSV = ",";
	
	 public List<Item> createItemList() {
		    
		BufferedReader br = null;
		String csvFile = "sample.csv";
		List<Item> itemList = new ArrayList<Item>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
			
			String line = "";
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] allItems = line.split(splitCSV);
				if(allItems.length > 0) {
					Item it = new Item(allItems[0], 
							Double.parseDouble(allItems[1]),
							Integer.parseInt(allItems[2]),
							allItems[3]);
					itemList.add(it);
				}
			}
			 for(Item i : itemList) {
//				 System.out.println(i.getName() + " "+ i.getCode() + " "+  i.getQuantity() + " "+ i.getExpDate());
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				br.close();
			}
			catch(IOException ie) {
				System.out.println("Error while closing");
				ie.printStackTrace();
			}
		}
		return itemList;
	}

}