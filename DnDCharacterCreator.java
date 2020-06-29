import java.util.Scanner;

public class DnDCharacterCreator
{
	public static void main(String[] args)
	{
		String sectionBreak = "_____\n";
		final String[] ORDINAL = {"1st", "2nd", "3rd", "4th", "5th",
				"6th", "7th", "8th", "9th"};
		final String[] SKILLS = {
				"Acrobatics",
				"Animal Handling",
				"Arcana",
				"Athletics",
				"Deception",
				"History",
				"Insight",
				"Intimidation",
				"Investigation",
				"Medicine",
				"Nature",
				"Perception",
				"Performance",
				"Persuasion",
				"Religion",
				"Sleight of Hand",
				"Stealth",
				"Survival"};
		final int[] SKILL_ABILITIES = {2, 5, 4, 1, 6, 4, 5, 6, 4,
				5, 4, 5, 6, 6, 4, 2, 2, 5};
		
		String characterName = "Sydri";
		String characterRace = "Human";
		String characterClass = "Artificer";
		int characterLevel = 1;
		String characterBackground = "Noble";
		
		int ac = 12;
		int hpCurrent = 8;
		int hpMax = 8;
		int proficiencyBonus = 2;
		int initiativeModifier;
		
		int strScore = 13;
		int dexScore = 14;
		int conScore = 11;
		int intScore = 16;		//awkward-looking variable name
		int wisScore = 15;
		int chaScore =  9;
		
		int strModifier = getModifier(strScore);
		int dexModifier = getModifier(dexScore);
		int conModifier = getModifier(conScore);
		int intModifier = getModifier(intScore);
		int wisModifier = getModifier(wisScore);
		int chaModifier = getModifier(chaScore);
		
		boolean[] skillProficiencies = new boolean[18];
			skillProficiencies[5] = true;
			skillProficiencies[11] = true;
			skillProficiencies[13] = true;
			skillProficiencies[15] = true;
		
		String armorProficiency = "Light, Medium Armor";
		int weaponProficiencyCount = 0;
		String[] weaponProficiency = new String[20];
			weaponProficiency[weaponProficiencyCount] = "Simple Weapons";
			weaponProficiencyCount++;
		int languagesCount = 0;
		String[] languages = new String[10];
			languages[languagesCount] = "Common";
			languagesCount++;
			languages[languagesCount] = "Halfling";
			languagesCount++;
			languages[languagesCount] = "Elvish";
			languagesCount++;
		int toolProficiencyCount = 0;
		String[] toolProficiency = new String[10];
			toolProficiency[toolProficiencyCount] = "Thieves' Tools";
			toolProficiencyCount++;
			toolProficiency[toolProficiencyCount] = "Tinker's Tools";
			toolProficiencyCount++;
			toolProficiency[toolProficiencyCount] = "Painter's Supplies";
			toolProficiencyCount++;
			toolProficiency[toolProficiencyCount] = "Chess Set";
			toolProficiencyCount++;
		String[] features = new String[20];
			features[0] = "Magical Tinkering";
			features[1] = "Spellcasting (Artificer)";
			
			boolean isCaster = true;
			int[] spellSlots = new int[10];
				spellSlots[1] = 2;
				spellSlots[9] = 1;
			int[] spellCount = new int[10];
			String[][] spellList = new String[10][15];
				//add cantrips
				spellList[0][spellCount[0]] = "Guidance";
				spellCount[0]++;
				spellList[0][spellCount[0]] = "Prestidigitation";
				spellCount[0]++;
				//add 1st level spells
				spellList[1][spellCount[1]] = "Feather Fall";
				spellCount[1]++;
				spellList[1][spellCount[1]] = "Jump";
				spellCount[1]++;
				spellList[1][spellCount[1]] = "Purify Food and Drink";
				spellCount[1]++;
		
		Scanner input = new Scanner(System.in);
		
		//name, race, class, level, and background
		System.out.println(characterName);
		System.out.println(characterRace + " " + characterClass + " "
				+ characterLevel);
		System.out.println("Background: " + characterBackground);
		System.out.println(sectionBreak);
		
		//HP, AC, proficiency, and initiative
		System.out.printf("AC\t%2d\n", ac);
		System.out.printf("HP\t%2d / %d\n", hpCurrent, hpMax);
		System.out.println("Proficiency Bonus: +" + proficiencyBonus);
		System.out.println("Initiative: +2");
		System.out.println(sectionBreak);
		
		//ability scores and modifiers, with saving throw proficiencies
		System.out.printf("STR %2d (%2s)%s\n", strScore,
				modifierToString(strModifier), "");
		System.out.printf("DEX %2d (%2s)%s\n", dexScore,
				modifierToString(dexModifier), "");
		System.out.printf("CON %2d (%2s)%s\n", conScore,
				modifierToString(conModifier), " (save +2)");
		System.out.printf("INT %2d (%2s)%s\n", intScore,
				modifierToString(intModifier), " (save +5)");
		System.out.printf("WIS %2d (%2s)%s\n", wisScore,
				modifierToString(wisModifier), "");
		System.out.printf("CHA %2d (%2s)%s\n", chaScore,
				modifierToString(chaModifier), "");
		System.out.println(sectionBreak);
		
		//proficiencies
			//skills
		System.out.println("\tProficiencies\n");
		for (int i = 0; i < skillProficiencies.length; i++)
		{
			if (skillProficiencies[i])
			{
				System.out.printf("%-15s\t%s\n", SKILLS[i],
						getSkillModifier(strScore, dexScore, conScore,
								intScore, wisScore, chaScore,
								proficiencyBonus, SKILL_ABILITIES[i], true));
			}
		}
		System.out.println();
		
			//armor and weapons
		System.out.println(armorProficiency);
		for (int i = 0; i < weaponProficiency.length; i++)
		{
			if (weaponProficiency[i] != null)
			{
				if (weaponProficiency[i + 1] != null)
					System.out.print(weaponProficiency[i] + ", ");
				else
					System.out.print(weaponProficiency[i]);
			}
			else
				break;
		}
		System.out.println("\n");
		
			//languages
		printList(languages);
		System.out.println();
		
			//tools, etc.
		printList(toolProficiency);
		System.out.println(sectionBreak);
		
		//traits and features
		printList(features);
		
		//spellcasting
		if (isCaster)
		{
			System.out.println(sectionBreak);
			System.out.println("\tSpellcasting\n");
			//general
			System.out.println("Spellcasting class/ability:\tArtificer (Int)");
			System.out.printf("Spell save DC:\t\t\t%2d\n", 13);
			System.out.printf("Spell attack modifier:\t\t%2s", "+5");
			System.out.println("\n");
			
			//cantrips & at will
			System.out.println("\tCantrips/At will:");
			printList(spellList[0]);
			System.out.println();
			for (int i = 1; i < spellList.length; i++)
			{
				if (spellList[i][0] != null)
				{
					String header = "\t" + ORDINAL[i - 1] + " Level ("
							+ spellSlots[i];
					if (spellSlots[i] == 1)
						header += " slot)";
					else
						header += " slots)";
					
					System.out.println(header);
					printList(spellList[i]);
					System.out.println();
				}
			}
		}
		
	}
	
	//returns modifier for a given ability score
	public static int getModifier(int score)
	{
		if (score >= 10)
			return (score - 10) / 2;
		else
		{
			if (score % 2 == 0)			 //some fiddly stuff because of how
				return (score - 10) / 2; //integer division works
			else
				return (score - 11) / 2;
		}
	}
	
	//returns modifier with + or - sign
	public static String modifierToString(int modifier)
	{
		String modifierString = "";
		
		if (modifier == 0)
			modifierString += " ";
		else if (modifier > 0)
			modifierString += "+";
		
		modifierString += modifier;
		return modifierString;
	}
	
	/*
	 * returns modifier for a particular skill.
	 * key determines the applicable ability score
	 * 
	 * note: this method is a bit clunky and awkward without
	 * 		use of objects. Will clean this up later.
	 */
	public static String getSkillModifier(int str, int dex, int con,
			int intel, int wis, int cha, int prof, int key, boolean isProf)
	{
		int modifier;
		if (key == 1)
			modifier = getModifier(str);
		else if (key == 2)
			modifier = getModifier(dex);
		else if (key == 4)
			modifier = getModifier(intel);
		else if (key == 5)
			modifier = getModifier(wis);
		else
			modifier = getModifier(cha);
		
		if (isProf)
			modifier += prof;
		
		return modifierToString(modifier);
	}
	
	public static void printList(String[] list)
	{
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null)
				System.out.println(list[i]);
			else
				return;
		}
	}
	
}