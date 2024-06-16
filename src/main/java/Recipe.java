/**************************************************************************
* File name: Recipe 
*
* Description: Creating a Recipe object and storing a recipe list
*
* Author: Thomas Murzaku  
*
* Date: June 3rd, 2024  
*
* Concepts: Object Oriented Programming, Constructors, Overriding
*           Iteration through various collections, reading and 
*           parsing info from a text file, Data validation
* Input: Tab/Comma/Period separated .txt file
* Processing: Parsing .txt file, creating recipe objects and storing them
*             in an ArrayList, search for various keywords and criteria
* Output: List of recipe objects that satisfy user's criteria
***************************************************************************/

//Importing util package
import java.util.*;

public class Recipe {

  // Declaring all necessary fields
  private String name;
  private String type;
  private ArrayList<String> ingredients;
  private ArrayList<String> instructions;
  private int[] nutrients;
  private int servings, time;
  public static ArrayList<Recipe> recipes;

  // Constructor method which constructs the Recipe object
  public Recipe(String name, String type, String ingredients, String instructions, String nutrients, String servings,
      String time) {
    this.name = name;
    this.type = type;
    this.ingredients = new ArrayList<String>();
    String[] strIngredients = ingredients.split(",");
    // Separating values by commas
    for (String el : strIngredients) {
      this.ingredients.add(el);
    }
    this.instructions = new ArrayList<String>();
    String[] strInstructions = instructions.split("[.]");
    // Separating values by periods
    for (String el : strInstructions) {
      this.instructions.add(el);
    }
    // Separating values by spaces
    String[] strNutrients = nutrients.split(" ");
    this.nutrients = new int[strNutrients.length];
    getNutrients(strNutrients);
    // Parsing data
    try {
      this.servings = Integer.parseInt(servings);

    } catch (NumberFormatException nfe) {
    }
    // Trimming min from time value
    String strTime = "";
    if (time.contains("min")) {
      strTime = time.substring(0, time.indexOf("min")).trim();
    }
    try {
      this.time = Integer.parseInt(strTime);

    } catch (NumberFormatException nfe) {
    }

  }

  // Initializing all getters and setters

  public void setIngredients(ArrayList<String> ingredients) {
    this.ingredients = ingredients;
  }

  public ArrayList<String> getIngredients() {
    return ingredients;
  }

  public ArrayList<String> getInstructions() {
    return instructions;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getServings() {
    return servings;
  }

  public void setServings(int servings) {
    this.servings = servings;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getNutrients() {
    return nutrients;
  }

  // getNutrients method takes a string[] of nutrients and parses them and adds
  // them to a nutrients list
  public void getNutrients(String[] strNutrients) {
    int idx = 0;
    for (String nutrient : strNutrients) {
      try {
        nutrients[idx++] = Integer.parseInt(nutrient);
      } catch (NumberFormatException nfe) {

      }
    }
  }

  // loadRecipes method uses the scanner class to traverse through the recipes.txt
  // file and compile each field of the recipes
  public static void loadRecipes(String fileName) {
    Scanner sc = new Scanner(Recipe.class.getResourceAsStream(fileName));

    recipes = new ArrayList<Recipe>();
    if (sc != null) {
      while (sc.hasNextLine()) {
        String str = sc.nextLine();
        String[] elements = str.split("\t");
        if (elements.length == 7) {
          Recipe recipe = new Recipe(elements[0], elements[1], elements[2], elements[3], elements[4], elements[5],
              elements[6]);
          if (recipe.getTime() != 0) {
            recipes.add(recipe);

          }
        }

      }
    }

  }

  // searchByToken method traverses through an arraylist of type Recipe and checks
  // to see if it contains the desired token
  public static ArrayList<Recipe> searchByToken(String token, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (recipe.getName().toLowerCase().contains(token.toLowerCase())) {
          compatible.add(recipe);
        } else {
          for (String el : recipe.getIngredients()) {
            if (el.toLowerCase().contains(token.toLowerCase())) {
              compatible.add(recipe);
            }
          }
        }
      }
    }

    return compatible;
  }

  // searchByType method traverses through an arraylist of type Recipe and checks
  // to see if it contains the desired type
  public static ArrayList<Recipe> searchByType(String type, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (recipe.getType().toLowerCase().contains(type.toLowerCase())) {
          compatible.add(recipe);
        }
      }
    }

    return compatible;
  }

  // searchByCalories method traverses through an arraylist of type Recipe and
  // checks to see if it contains the desired amount of Calories(less or more than
  // depending on boolean)
  public static ArrayList<Recipe> searchByCalories(int cals, boolean less, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (less && recipe.getNutrients()[0] <= cals) {
          compatible.add(recipe);
        } else if (!less && recipe.getNutrients()[0] >= cals) {
          compatible.add(recipe);
        }
      }
    }

    return compatible;
  }

  // searchByCarbs method traverses through an arraylist of type Recipe and checks
  // to see if it contains the desired amount of Carbs(less or more than depending
  // on boolean)
  public static ArrayList<Recipe> searchByCarbs(int carbs, boolean less, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (less && recipe.getNutrients()[1] <= carbs) {
          compatible.add(recipe);
        } else if (!less && recipe.getNutrients()[1] >= carbs) {
          compatible.add(recipe);
        }
      }
    }

    return compatible;
  }

  // searchByProtein method traverses through an arraylist of type Recipe and
  // checks to see if it contains the desired amount of Protein(less or more than
  // depending on boolean)
  public static ArrayList<Recipe> searchByProtein(int protein, boolean less, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (less && recipe.getNutrients()[2] <= protein) {
          compatible.add(recipe);
        } else if (!less && recipe.getNutrients()[2] >= protein) {
          compatible.add(recipe);
        }
      }
    }

    return compatible;
  }

  // searchByFat method traverses through an arraylist of type Recipe and checks
  // to see if it contains the desired amount of fat(less or more than depending
  // on boolean)
  public static ArrayList<Recipe> searchByFat(int fat, boolean less, ArrayList<Recipe> recipes) {
    ArrayList<Recipe> compatible = new ArrayList<Recipe>();
    if (recipes != null && !recipes.isEmpty()) {
      for (Recipe recipe : recipes) {
        if (less && recipe.getNutrients()[3] <= fat) {
          compatible.add(recipe);
        } else if (!less && recipe.getNutrients()[3] >= fat) {
          compatible.add(recipe);
        }
      }
    }

    return compatible;
  }

  // Overriding toString method to use it for different fields of the Recipe
  // object
  @Override
  public String toString() {
    String str = "Name: " + name;
    str += "\nType: " + type;
    str += "\nIngredients: \n";
    for (String ing : ingredients) {
      str += ing + "\n";
    }

    str += "\nNutrients: \n";
    str += "Cal: " + nutrients[0] + "\n";
    str += "Carbs: " + nutrients[1] + "\n";
    str += "Protein: " + nutrients[2] + "\n";
    str += "Fat: " + nutrients[3] + "\n";
    str += "Servings: " + servings + "\n";
    str += "Time: " + time + " mins\n";
    return str;
  }

}