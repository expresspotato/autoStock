/**
 * 
 */
package com.autoStock.menu;

import com.autoStock.Co;
import com.autoStock.internal.ApplicationStates;
import com.autoStock.menu.MenuDefinitions.MenuArgumentTypes;
import com.autoStock.menu.MenuDefinitions.MenuArguments;
import com.autoStock.menu.MenuDefinitions.MenuStructures;
import com.autoStock.tools.MiscUtils;
import com.autoStock.tools.StringUtils;
import com.autoStock.tools.ValidilityCheck;

/**
 * @author Kevin Kowalewski
 *
 */
public class MenuController {
	public void displayMenu(MenuStructures menuStructure){
		if (menuStructure == MenuStructures.menu_main){
			for (MenuStructures menuStructureEntry : MenuStructures.values()){
				Co.print(StringUtils.removePrefix(menuStructureEntry.name(),"_") + " ");
				for (MenuArguments menuArgumentsEntry : menuStructureEntry.arrayOfMenuArguments){
					Co.print(StringUtils.removePrefix(menuArgumentsEntry.name(), "_") + " [");
					for (MenuArgumentTypes menuArgumentTypesEntry : menuArgumentsEntry.arrayOfArgumentTypes){
						if (menuArgumentsEntry.arrayOfArgumentTypes.length-1 == MiscUtils.getArrayIndex(menuArgumentsEntry.arrayOfArgumentTypes, menuArgumentTypesEntry)){
							Co.print(StringUtils.removePrefix(menuArgumentTypesEntry.name(), "_") + "");
						}else{
							Co.print(StringUtils.removePrefix(menuArgumentTypesEntry.name(), "_") + " | ");
						}
					}
					Co.print("] ");
				}
				Co.print("\n");
			}
		}
	}
	
	public MenuStructures getRelatedMenu(String[] arguments){
		if (arguments.length == 0){return null;}
		String command = arguments[0];
		for (MenuStructures menuStructureEntry : MenuStructures.values()){
			if (StringUtils.removePrefix(menuStructureEntry.name(), "_").equals(command)){
				return menuStructureEntry;
			}
		}
		
		return null;
	}
	
	public void getArgument(){
		
	}

	public void handleMenuStructure(MenuStructures menuStructure, String[] args) {
		if (menuStructure == null){
			Co.println("\nError: No known command was entered");
			ApplicationStates.shutdown();
			return;
		}
		
		//Co.println("Got menu: " + menuStructure.name());
		
		if (menuStructure.arrayOfMenuArguments[0] == MenuArguments.arg_none){
			return;
		}
		
		int index = 1;
		for (MenuArguments menuArgument : menuStructure.arrayOfMenuArguments){
			//Co.println("Need argument: " + menuArgument.name());
			//Co.println("Have value:" + args[index]);
			try {
				menuArgument.value = args[index];
			}catch (IndexOutOfBoundsException e){
				Co.println("\nError: Invalid argument length");
				ApplicationStates.shutdown();
				return;
			}
			
			if (new ValidilityCheck().isValidMenuArgument(menuArgument) == false){
				Co.println("\nInvalid menu argument type or value for: " + menuArgument.name());
				ApplicationStates.shutdown();
				return;
			}
			
			index++;
		}
	}
}