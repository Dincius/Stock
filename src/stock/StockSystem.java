package stock;

import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;



public class StockSystem {

	private int minQuantity;
	private String userInput;
	private String date;
	public static List<Item> itemList;

	public void Start() throws ParseException {

		Scanner sn = new Scanner(System.in);

        // Crate list of all available items
		CSVreader reader = new CSVreader();
		itemList = reader.createItemList();

		while(true){
			System.out.println("***** WELCOME TO STOCK MANAGEMENT SYSTEM *****");
			System.out.println("* Press 1 to get list of items to order");
			System.out.println("* Press 2 to get list of expired items");
			System.out.println("* Press 3 to exit");

			System.out.println("Enter your choice");

			userInput = sn.next();

			//Check the user input
			switch(userInput){
				case "1":
					System.out.println("Enter minimum quantity for items to check stock");
					minQuantity = sn.nextInt();
					ItemsNeedList(itemList, minQuantity);
					System.out.println("Choose again:\n");
					break;
				case "2":
					System.out.println("Enter expiration date in format (dd/MM/yyyy)");
					date = sn.next();
					ItemsToExpire(itemList, date);
					System.out.println("Choose again:\n");
					break;
				case "3":
					//exit the program
					System.out.println("Exiting...");
					System.exit(0);
				default:
					//inform user in case of invalid choice.
					System.out.println("Invalid choice. Read the options carefully...");
			}
		}
	}

	public void ItemsNeedList (List<Item> allItems, int minQuantity){
		List<Item> itemsNeed = new ArrayList<>();
		 for (Item i : allItems) {
			 if(i.getQuantity() < minQuantity) {
				  	itemsNeed.add(i);
			 }
		 }

		 if(itemsNeed.size() > 0) {
			 removeDuplicates(itemsNeed);
			 sortAndPrint(itemsNeed);
		 } else {
		 	System.out.println("No such items exist in stock!");
		 }
    }
	
	public void ItemsToExpire (List<Item> allItems, String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Item> list = new ArrayList<Item>();
		

		Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        
        DateValidator dv = new DateValidator();
        
		try {
			if (dv.isDateValid(date) == true) {
				Date date1 = sdf.parse(date);
				cal1.setTime(date1);
				
			for (Item i : allItems) {
				Date date2 = sdf.parse(i.getExpDate());
				cal2.setTime(date2);
				 if(date1.equals(date2)){
					list.add(i);
					}
				 else if(cal2.before(cal1)) {
				 	list.add(i);
				 }
				 else {
					 
				 }
			 	}
			}
			else
			{
			    System.out.println("Date " + date + " is not valid according to mm/MM/yyyy pattern.");
				System.out.println("Please check format of the date!");
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}

		if(list.size() > 0) {
			removeDuplicates(list);
			sortAndPrint(list);
		} 
		else if (dv.isDateValid(date) == true) {
			System.out.println("No such items exist in stock!");	
		}
		else {
		
		}
		
	}
	
	public void removeDuplicates (List<Item> list) {
		int sumQ = 0;
		List<Item> dupbList = new ArrayList<Item>();
		list.sort(Comparator.comparing(Item::getName));
		int replI = 0;
		int i,j;
	
			for(i = 0; i<list.size(); i++) {
				for(j =i+1; j < list.size(); j++) {
					if (list.get(i).getName().equals(list.get(j).getName())
						&&
						list.get(i).getCode() == list.get(j).getCode() 
					    &&
					    list.get(i).getExpDate().equals(list.get(j).getExpDate())    
							) {
						sumQ = list.get(i).getQuantity() + list.get(j).getQuantity();
						list.get(i).setQuantity(sumQ);
						dupbList.add(replI++, list.get(i));	
						list.remove(j);
				}
				else {
					dupbList.add(replI, list.get(i));
					replI++;
				}
				}			
		}
		
		Set<Item> itemSet = new HashSet<Item>();
		for (Item x : dupbList) {
			itemSet.add(x);
		}
		
		List<Item> finalList = new ArrayList<Item>();
		for (Item y : itemSet) {
			finalList.add(y);
		}
	}

	public void sortAndPrint (List<Item> list) {
		list.sort(Comparator.comparing(Item::getName)); 
        list.forEach(System.out::println);
	}
}
 