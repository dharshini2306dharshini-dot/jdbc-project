package com.jdbc.main;


import com.jdbc.dto.OwnerDTO;
import com.jdbc.service.OwnerService;
import com.jdbc.service.impl.OwnerServiceImpl;
import com.jdbc.util.InputUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
	public void run() {
		OwnerService ownerService=new OwnerServiceImpl();
		System.out.println("Welcome to Petistaan");
		try(Scanner sc= new Scanner(System.in)){
			do{
				int choice=InputUtil.acceptMenuOption(sc);
				switch (choice){
					case 1:
						System.out.println("Enter Owner Details");
						OwnerDTO ownerDTO=InputUtil.acceptOwnerDetailsToSave(sc);
						ownerService.saveOwner(ownerDTO);
						System.out.println("Owner details saved successfully");
						break;
					case 2:
						int ownerID=InputUtil.acceptOwnerIdToOperate(sc);
						ownerDTO=ownerService.findOwner(ownerID);
						System.out.println(ownerDTO);
						break;
					default:
						System.out.println("Invalid choice");
						break;
					case 3:
					ownerID=InputUtil.acceptOwnerIdToOperate(sc);
					String petName=InputUtil.acceptPetDetailsToUpdate(sc);
					ownerService.updatePetDetails(ownerID,petName);
					System.out.println("Pet details updated sucessfully");
					break;
					case 4:
						ownerID=InputUtil.acceptOwnerIdToOperate(sc);
						ownerService.deleteOwner(ownerID);
						System.out.println("Owner deleted successfilly");
						break;
					case 5:
						ownerService.findAllOwners();
						List<OwnerDTO> ownerList=ownerService.findAllOwners();
						ownerList.forEach(System.out::println);
						break;
					case 6:
						String ownerEmailId=InputUtil.acceptOwnerEmailIdToOperate(sc);
						LocalDate petBirthDate=InputUtil.acceptPetBirthDateToOperate(sc);
						ownerList=ownerService.findOwner(ownerEmailId,petBirthDate);
						ownerList.forEach(System.out::println);
						break;


				}
			}while (InputUtil.wantToContinue(sc));
		}catch (Exception e){
			System.out.println(e.getMessage());

		}
	}

}