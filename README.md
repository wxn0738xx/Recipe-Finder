# Recipe-Finder
1. To run the code, use command **java -jar RecipeFinder.jar**
2. Then you need to type the file path of *fridge.csv* and *recipe.json*
3. There are multiple csv files to test:
* *fridge_equaldate.csv* : In this file, all ingredients have equal and valid use-by date. Since dates are same, output will contains all of available recipes.
* *fridge_takeout.csv*: In this file, all ingredients are out of date. Hence, the output will be "take out".
* *fridge_test.csv*: In this file, cheese has the cloest use-by date. Hnece, the output will be "grilled cheese on toast".
* *fridge.csv* : In this file, mixed salad has the cloest use-by date. Hence, the output will be "salad sandwich".
4. *recipe.json* contains recipes.
5. *MultipleAvailableTest.java*, *ReadFileTest.java*, *SameDateIngredientTest.java* and *TakeoutTest.java* are included.
