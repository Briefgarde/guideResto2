package ch.hearc.ig.guideresto.presentation;

import static java.util.stream.Collectors.joining;

import ch.hearc.ig.guideresto.business.BasicEvaluation;
import ch.hearc.ig.guideresto.business.City;
import ch.hearc.ig.guideresto.business.CompleteEvaluation;
import ch.hearc.ig.guideresto.business.Evaluation;
import ch.hearc.ig.guideresto.business.EvaluationCriteria;
import ch.hearc.ig.guideresto.business.Grade;
import ch.hearc.ig.guideresto.business.Restaurant;
import ch.hearc.ig.guideresto.business.RestaurantType;
import ch.hearc.ig.guideresto.persistence.handlers.specializedHandlers.*;

import javax.persistence.EntityManager;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.*;

public class CLI2 {

    private final Scanner scanner;
    private final PrintStream printStream;
    private final RestaurantHandler restaurantHandler;
    private final CityHandler cityHandler;
    private final RestaurantTypeHandler restaurantTypeHandler;
    private final GradeHandler gradeHandler;
    private final BasicEvalHandler basicEvalHandler;
    private final CompEvalHandler compEvalHandler;
    private final EvaluationHandler evaluationHandler;
    private final EvaluationCriteriaHandler evaluationCriteriaHandler;

    // Injection de dépendances
    public CLI2(Scanner scanner, PrintStream printStream, EntityManager em) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.restaurantHandler = new RestaurantHandler(em);
        this.cityHandler = new CityHandler(em);
        this.restaurantTypeHandler = new RestaurantTypeHandler(em);
        this.gradeHandler = new GradeHandler(em);
        this.basicEvalHandler = new BasicEvalHandler(em);
        this.compEvalHandler = new CompEvalHandler(em);
        this.evaluationHandler = new EvaluationHandler(em);
        this.evaluationCriteriaHandler = new EvaluationCriteriaHandler(em);
    }

    public void start() {
        println("Bienvenue dans GuideResto ! Que souhaitez-vous faire ?");
        int choice;
        do {
            printMainMenu();
            choice = readInt();
            proceedMainMenu(choice);
        } while (choice != 0);
    }

    private void printMainMenu() {
        println("======================================================");
        println("Que voulez-vous faire ?");
        println("1. Afficher la liste de tous les restaurants");
        println("2. Rechercher un restaurant par son nom");
        println("3. Rechercher un restaurant par ville");
        println("4. Rechercher un restaurant par son type de cuisine");
        println("5. Saisir un nouveau restaurant");
        println("0. Quitter l'application");
    }

    private void proceedMainMenu(int choice) {
        switch (choice) {
            case 1:
                showRestaurantsList();
                break;
            case 2:
                searchRestaurantByName();
                break;
            case 3:
                searchRestaurantByCity();
                break;
            case 4:
                searchRestaurantByType();
                break;
            case 5:
                addNewRestaurant();
                break;
            case 0:
                println("Au revoir !");

                break;
            default:
                println("Erreur : saisie incorrecte. Veuillez réessayer");
                break;
        }
    }

    private Optional<Restaurant> pickRestaurant(List<ch.hearc.ig.guideresto.business.Restaurant> restaurants) {
        if (restaurants.isEmpty()) {
            println("Aucun restaurant n'a été trouvé !");
            return Optional.empty();
        }

        String restaurantsText = restaurants.stream()
                .map(r -> "\"" + r.getName() + "\" - " + r.getStreet() + " - "
                        + r.getZipCode() + " " + r.getCityName())
                .collect(joining("\n", "", "\n"));

        println(restaurantsText);
        println(
                "Veuillez saisir le nom exact du restaurant dont vous voulez voir le détail, ou appuyez sur Enter pour revenir en arrière");
        String choice = readString();

        return searchRestaurantByName(restaurants, choice);
    }

    private void showRestaurantsList() {
        println("Liste des restaurants : ");

        List<Restaurant> restaurants = restaurantHandler.findAll();


        Optional<Restaurant> maybeRestaurant = pickRestaurant(restaurants);
        // Si l'utilisateur a choisi un restaurant, on l'affiche, sinon on ne fait rien et l'application va réafficher le menu principal
        maybeRestaurant.ifPresent(this::showRestaurant);
    }

    private void searchRestaurantByName() {
        println("Veuillez entrer une partie du nom recherché : ");
        String research = readString();

        List<Restaurant> restaurants = restaurantHandler.findByName(research);

        Optional<Restaurant> maybeRestaurant = pickRestaurant(restaurants);
        maybeRestaurant.ifPresent(this::showRestaurant);
    }

    /**
     * Affiche une liste de restaurants dont le nom de la ville contient une chaîne de caractères
     * saisie par l'utilisateur
     */
    private void searchRestaurantByCity() {
        println("Veuillez entrer une partie du nom de la ville désirée : ");
        String research = readString();

        List<Restaurant> restaurants = restaurantHandler.findByCity(research);

        Optional<Restaurant> maybeRestaurant = pickRestaurant(restaurants);
        maybeRestaurant.ifPresent(this::showRestaurant);
    }

    private City pickCity(List<City> cities) {
        println(
                "Voici la liste des villes possibles, veuillez entrer le NPA de la ville désirée : ");

        cities.forEach((currentCity) -> System.out
                .println(currentCity.getZipCode() + " " + currentCity.getCityName()));
        println("Entrez \"NEW\" pour créer une nouvelle ville");
        String choice = readString();

        if (choice.equalsIgnoreCase("NEW")) {
            println("Veuillez entrer le NPA de la nouvelle ville : ");
            String zipCode = readString();
            println("Veuillez entrer le nom de la nouvelle ville : ");
            String cityName = readString();
            City city = City.createCity(zipCode, cityName);
            cityHandler.save(city);
            return city;
        }

        return searchCityByZipCode(cities, choice).orElseGet(() -> pickCity(cities));
    }

    private RestaurantType pickRestaurantType(List<RestaurantType> types) {

        String typesText = types.stream()
                .map(currentType -> "\"" + currentType.getLabel() + "\" : " + currentType.getDescription())
                .collect(joining("\n"));

        println(
                "Voici la liste des types possibles, veuillez entrer le libellé exact du type désiré : ");
        println(typesText);
        String choice = readString();

        Optional<RestaurantType> maybeRestaurantType = searchTypeByLabel(types, choice);
        return maybeRestaurantType.orElseGet(() -> pickRestaurantType(types));
    }

    private void searchRestaurantByType() {
        List<RestaurantType> restaurantTypes = restaurantTypeHandler.findAll();
        RestaurantType chosenType = pickRestaurantType(restaurantTypes);

        List<Restaurant> restaurants = restaurantHandler.findByType(chosenType);

        Optional<Restaurant> maybeRestaurant = pickRestaurant(restaurants);
        maybeRestaurant.ifPresent(this::showRestaurant);
    }

    private void addNewRestaurant() {
        println("Vous allez ajouter un nouveau restaurant !");
        println("Quel est son nom ?");
        String name = readString();
        println("Veuillez entrer une courte description : ");
        String description = readString();
        println("Veuillez entrer l'adresse de son site internet : ");
        String website = readString();
        println("Rue : ");
        String street = readString();
        City city;
        do
        { // La sélection d'une ville est obligatoire, donc l'opération se répètera tant qu'aucune ville n'est sélectionnée.
            List<City> cities = cityHandler.findAll();
            city = pickCity(cities);
        } while (city == null);

        RestaurantType restaurantType;

        // La sélection d'un type est obligatoire, donc l'opération se répètera tant qu'aucun type n'est sélectionné.
        List<RestaurantType> restaurantTypes = restaurantTypeHandler.findAll();
        restaurantType = pickRestaurantType(restaurantTypes);

        Restaurant restaurant = Restaurant.createRestaurant(name, description, website, street, city, restaurantType);
        city.getRestaurants().add(restaurant);
        restaurant.getAddress().setCity(city);
        restaurantHandler.save(restaurant);
        cityHandler.save(city);

        showRestaurant(restaurant);
    }

    private void showRestaurant(Restaurant restaurant) {
        String sb = restaurant.getName() + "\n" +
                restaurant.getDescription() + "\n" +
                restaurant.getType().getLabel() + "\n" +
                restaurant.getWebsite() + "\n" +
                restaurant.getAddress().getStreet() + ", " +
                restaurant.getAddress().getCity().getZipCode() + " " + restaurant.getAddress().getCity()
                .getCityName() + "\n" +
                "Nombre de likes : " + countLikes(restaurant.getEvaluations(), true) + "\n" +
                "Nombre de dislikes : " + countLikes(restaurant.getEvaluations(), false) + "\n" +
                "\nEvaluations reçues : " + "\n";

        String text = restaurant.getEvaluations()
                .stream()
                .filter(CompleteEvaluation.class::isInstance)
                .map(CompleteEvaluation.class::cast)
                .map(this::getCompleteEvaluationDescription)
                .collect(joining("\n"));

        println("Affichage d'un restaurant : ");
        println(sb);
        println(text);

        int choice;
        do { // Tant que l'utilisateur n'entre pas 0 ou 6, on lui propose à nouveau les actions
            showRestaurantMenu();
            choice = readInt();
            proceedRestaurantMenu(choice, restaurant);
        } while (choice != 0 && choice != 6); // 6 car le restaurant est alors supprimé...
    }

    private long countLikes(Set<Evaluation> evaluations, boolean likeRestaurant) {
        return evaluations.stream()
                .filter(BasicEvaluation.class::isInstance)
                .map(BasicEvaluation.class::cast)
                .filter(eval -> likeRestaurant == eval.isLikeRestaurant())
                .count();
    }

    private String getCompleteEvaluationDescription(CompleteEvaluation eval) {
        String result = "Evaluation de : " + eval.getUsername() + "\n";
        result += "Commentaire : " + eval.getComment() + "\n";

        return result + eval.getGrades().stream()
                .map(g-> g.getCriteria().getName() + " : " + g.getGrade() + "/5")
                .collect(joining("\n", "\n", "\n"));
    }

    private void showRestaurantMenu() {
        println("======================================================");
        println("Que souhaitez-vous faire ?");
        println("1. J'aime ce restaurant !");
        println("2. Je n'aime pas ce restaurant !");
        println("3. Faire une évaluation complète de ce restaurant !");
        println("4. Editer ce restaurant");
        println("5. Editer l'adresse du restaurant");
        println("6. Supprimer ce restaurant");
        println("0. Revenir au menu principal");
    }

    private void proceedRestaurantMenu(int choice, Restaurant restaurant) {
        switch (choice) {
            case 1:
                addBasicEvaluation(restaurant, true);
                break;
            case 2:
                addBasicEvaluation(restaurant, false);
                break;
            case 3:
                evaluateRestaurant(restaurant);
                break;
            case 4:
                editRestaurant(restaurant);
                break;
            case 5:
                editRestaurantAddress(restaurant);
                break;
            case 6:
                deleteRestaurant(restaurant);
                break;
            case 0:
            default:
                break;
        }
    }

    private void addBasicEvaluation(Restaurant restaurant, Boolean like) {
        BasicEvaluation eval = new BasicEvaluation(null, LocalDate.now(), restaurant, like, getIpAddress());
        restaurant.getEvaluations().add(eval);
        basicEvalHandler.save(eval);
        println("Votre vote a été pris en compte !");
    }

    private String getIpAddress() {
        try {
            return Inet4Address.getLocalHost().toString();
        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void evaluateRestaurant(Restaurant restaurant) {
        println("Merci d'évaluer ce restaurant !");
        println("Quel est votre nom d'utilisateur ? ");
        String username = readString();
        println("Quel commentaire aimeriez-vous publier ?");
        String comment = readString();

        CompleteEvaluation eval = new CompleteEvaluation(null, LocalDate.now(), restaurant, comment,
                username);
        restaurant.getEvaluations().add(eval);


        println("Veuillez svp donner une note entre 1 et 5 pour chacun de ces critères : ");

        List<EvaluationCriteria> evaluationCriterias = evaluationCriteriaHandler.findAll();

        evaluationCriterias.forEach(currentCriteria -> {
            println(currentCriteria.getName() + " : " + currentCriteria.getDescription());
            Integer note = readInt();
            Grade grade = new Grade(null, note, eval, currentCriteria);
            eval.getGrades().add(grade);
        });

        compEvalHandler.save(eval);
        for (Grade grade : eval.getGrades()) {
            gradeHandler.save(grade);
        }

        println("Votre évaluation a bien été enregistrée, merci !");
    }

    private void editRestaurant(Restaurant restaurant) {
        println("Edition d'un restaurant !");

        println("Nouveau nom : ");
        restaurant.setName(readString());
        println("Nouvelle description : ");
        restaurant.setDescription(readString());
        println("Nouveau site web : ");
        restaurant.setWebsite(readString());
        println("Nouveau type de restaurant : ");

        List<RestaurantType> restaurantTypes = restaurantTypeHandler.findAll();

        RestaurantType newType = pickRestaurantType(restaurantTypes);
        if (newType != restaurant.getType()) {
            restaurant.getType().getRestaurants().remove(restaurant); // Il faut d'abord supprimer notre restaurant puisque le type va peut-être changer
            newType.getRestaurants().add(restaurant);
            restaurantTypeHandler.save(newType);
            restaurant.setType(newType);
            restaurantHandler.update(restaurant);
        }

        println("Merci, le restaurant a bien été modifié !");
    }

    private void editRestaurantAddress(Restaurant restaurant) {
        println("Edition de l'adresse d'un restaurant !");

        println("Nouvelle rue : ");
        restaurant.getAddress().setStreet(readString());

        List<City> cities = cityHandler.findAll();

        City newCity = pickCity(cities);
        if (!newCity.equals(restaurant.getAddress().getCity())) {
            restaurant.getAddress().getCity().getRestaurants().remove(restaurant);
            newCity.getRestaurants().add(restaurant);
            restaurant.getAddress().setCity(newCity);
        }
        restaurantHandler.update(restaurant);
        println("L'adresse a bien été modifiée ! Merci !");
    }

    private void deleteRestaurant(Restaurant restaurant) {
        println("Etes-vous sûr de vouloir supprimer ce restaurant ? (O/n)");
        String choice = readString();
        if ("o".equalsIgnoreCase(choice)) {
            restaurant.getAddress().getCity().getRestaurants().remove(restaurant);
            restaurant.getType().getRestaurants().remove(restaurant);
            restaurantHandler.delete(restaurant);
            println("Le restaurant a bien été supprimé !");
        }
    }

    private Optional<Restaurant> searchRestaurantByName(List<Restaurant> restaurants,
                                                        String name) {
        return restaurants.stream()
                .filter(current -> current.getName().equalsIgnoreCase(name.toUpperCase()))
                .findFirst();
    }

    private Optional<City> searchCityByZipCode(List<City> cities, String zipCode) {
        return cities.stream()
                .filter(current -> current.getZipCode().equalsIgnoreCase(zipCode.toUpperCase()))
                .findFirst();
    }

    private Optional<RestaurantType> searchTypeByLabel(List<RestaurantType> types, String label) {
        return types.stream()
                .filter(current -> current.getLabel().equalsIgnoreCase(label.toUpperCase()))
                .findFirst();
    }

    private int readInt() {
        int i = 0;
        boolean success = false;
        do
        { // Tant que l'utilisateur n'aura pas saisi un nombre entier, on va lui demander une nouvelle saisie
            try {
                i = scanner.nextInt();
                success = true;
            } catch (InputMismatchException e) {
                println("Erreur ! Veuillez entrer un nombre entier s'il vous plaît !");
            } finally {
                scanner.nextLine();
            }

        } while (!success);

        return i;
    }

    private String readString() {
        return scanner.nextLine();
    }

    private void println(String text) {
        printStream.println(text);
    }
}
