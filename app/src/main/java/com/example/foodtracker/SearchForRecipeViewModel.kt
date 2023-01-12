package com.example.foodtracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodtracker.network.RecipeDataClass
import com.example.foodtracker.network.SpoonacularApi
import kotlinx.coroutines.launch

class SearchForRecipeViewModel : ViewModel() {
    private val _recipes = MutableLiveData<List<RecipeDataClass>>()

    val recipes: LiveData<List<RecipeDataClass>>
        get() = _recipes

    init {
        getRecipes(10,50,200,250)
    }

    private fun getRecipes (minCarbs: Int, maxCarbs: Int, minCalories: Int, maxCalories: Int) {
        viewModelScope.launch {
            _recipes.value = SpoonacularApi.retrofitService.getRecipesWithFilter(
                minCarbs = minCarbs,
                maxCarbs = maxCarbs,
                minCalories = minCalories,
                maxCalories = maxCalories
            )
        }
    }
}