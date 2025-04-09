class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Boolean> availableSupplies = new HashMap<>();
        Map<String, List<String>> requiredIngredients = new HashMap<>();
        for (String supply : supplies) {
            availableSupplies.put(supply, true);
        }

        List<String> canDo = new ArrayList<>();

        int n = recipes.length;

        for (int i = 0; i < n; i++) {
            requiredIngredients.put(recipes[i], ingredients.get(i));
        }

        for (int i = 0; i < n; i++) {
            if (canMake(recipes[i], availableSupplies, requiredIngredients)) {
                canDo.add(recipes[i]);
            }
        }

        return canDo;
    }

    private boolean canMake(String recipe, Map<String, Boolean> availableSupplies,  Map<String, List<String>> requiredIngredients) {
        if (requiredIngredients.get(recipe) == null) {
            return false;
        }
        for (String ingred : requiredIngredients.get(recipe)) {
            if (availableSupplies.get(ingred) == null) {
                if (canMake(ingred, availableSupplies, requiredIngredients)) {
                    availableSupplies.put(recipe, true);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}