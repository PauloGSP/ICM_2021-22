import 'package:json_annotation/json_annotation.dart';

class Recipe {
  final String name;
  final String images;
  final double rating;
  final String totalTime;
  @JsonKey(defaultValue: '')
  final String description;
  @JsonKey(defaultValue: false)
  final String steps;

  Recipe(
      {required this.name,
      required this.images,
      required this.rating,
      required this.totalTime,
      required this.steps,
      required this.description});

  factory Recipe.fromJson(dynamic json) {
    print(json['preparationSteps'].runtimeType);
    if (json['description'].runtimeType == Null ||
        json['preparationSteps'].runtimeType == Null) {
      return Recipe(
          name: json['details']['name'] as String,
          images: json['details']['images'][0]['hostedLargeUrl'] as String,
          rating: json['details']['rating'] as double,
          totalTime: json['details']['totalTime'] as String,
          description: ' ',
          steps: ' ');
    }
    return Recipe(
        name: json['details']['name'] as String,
        images: json['details']['images'][0]['hostedLargeUrl'] as String,
        rating: json['details']['rating'] as double,
        totalTime: json['details']['totalTime'] as String,
        description: json['description']['text'] as String,
        steps: json['preparationSteps'].toString() as String);
  }

  static List<Recipe> recipesFromSnapshot(List snapshot) {
    return snapshot.map((data) {
      return Recipe.fromJson(data);
    }).toList();
  }

  @override
  String toString() {
    return 'Recipe {name: $name, image: $images, rating: $rating, totalTime: $totalTime}';
  }
}
