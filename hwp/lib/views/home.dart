import 'dart:html';

import 'package:flutter/material.dart';
import 'package:hwp/models/recipe.api.dart';
import 'package:hwp/models/recipe.dart';
import 'package:hwp/views/widgets/recipe_card.dart';
import 'package:hwp/views/profile.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  late List<Recipe> _recipes;
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    getRecipes();
  }

  Future<void> getRecipes() async {
    _recipes = await RecipeApi.getRecipe();
    setState(() {
      _isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
        length: 2,
        child: Scaffold(
            appBar: AppBar(
              title: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.restaurant_menu),
                  SizedBox(width: 20),
                  Text('FoodY',
                      style:
                          TextStyle(fontWeight: FontWeight.bold, fontSize: 50))
                ],
              ),
              bottom: TabBar(tabs: [
                Tab(text: "Recipes", icon: Icon(Icons.home)),
                Tab(text: "Profile", icon: Icon(Icons.person)),
              ]),
            ),
            body: TabBarView(children: [
              Center(
                  child: _isLoading
                      ? Center(child: CircularProgressIndicator())
                      : ListView.builder(
                          itemCount: _recipes.length,
                          itemBuilder: (context, index) {
                            return RecipeCard(
                                counter: index,
                                steps: _recipes[index].steps,
                                description: _recipes[index].description,
                                title: _recipes[index].name,
                                cookTime: _recipes[index].totalTime,
                                rating: _recipes[index].rating.toString(),
                                thumbnailUrl: _recipes[index].images);
                          })),
              Profile(),
            ])));
  }
}
