package com.example.foodtracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodtracker.network.RecipeDataClass
import com.example.foodtracker.network.SpoonacularApi
import kotlinx.coroutines.launch

enum class spoonacularApiStatus { LOADING, ERROR, DONE }


class SearchForRecipeViewModel : ViewModel() {
    private val _status = MutableLiveData<spoonacularApiStatus>()

    val status: LiveData<spoonacularApiStatus>
        get() = _status

    private val _recipes = MutableLiveData<List<RecipeDataClass>>()

    val recipes: LiveData<List<RecipeDataClass>>
        get() = _recipes

    private val _navigateToSelectedRecipe = MutableLiveData<RecipeDataClass>()
    val navigateToSelectedRecipe: LiveData<RecipeDataClass>
        get() = _navigateToSelectedRecipe

    init {
        getRecipes(10,50,200,250)
    }

    private fun getRecipes (minCarbs: Int, maxCarbs: Int, minCalories: Int, maxCalories: Int) {
        viewModelScope.launch {
            _status.value = spoonacularApiStatus.LOADING
            try {
                _recipes.value = SpoonacularApi.retrofitService.getRecipesWithFilter(
                    minCarbs = minCarbs,
                    maxCarbs = maxCarbs,
                    minCalories = minCalories,
                    maxCalories = maxCalories
                )
                _status.value = spoonacularApiStatus.DONE
            } catch (e: Exception) {
                _status.value = spoonacularApiStatus.ERROR
                _recipes.value = ArrayList()
            }
        }
    }

    fun displayRecipeDetails(recipe: RecipeDataClass) {
        _navigateToSelectedRecipe.value = recipe
    }
}