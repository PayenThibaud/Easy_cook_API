import { Component } from '@angular/core';
import { RecetteCardComponent } from '../../components/recettes/recette-card/recette-card.component';
import { Recette } from '../../components/utils/types/recette.type';
import { Utilisateur } from '../../components/utils/types/utilisateur.type';

@Component({
  selector: 'app-liste-recettes',
  standalone: true,
  imports: [RecetteCardComponent],
  templateUrl: './liste-recettes.component.html',
  styleUrl: './liste-recettes.component.css'
})
export class ListeRecettesComponent {
  
  
  recettes: Recette[] =[
    {
      id: 1,
      nom: "Spagetti bolognaise",
      image: "https://images.pexels.com/photos/116738/pexels-photo-116738.jpeg",
      duree: 60,
      calorie: 350,
      regime: ["sans lactose", "sans noix"],
      ingredients: [{
        nom: "Spagetti",
        gramme: 215,
        litre:0
        },
        {
          nom: "Tomate",
          gramme: 300,    
          litre: 0    
        },
        {
          nom: "Céléri",
          gramme: 50,
          litre: 0   
        },
        {
          nom: "Carotte",
          gramme: 200,
          litre: 0   
        },
        {
          nom: "Boeuf haché",
          gramme: 300,
          litre: 0   
        },
        {
          nom: "Fromage emmental",
          gramme: 100,
          litre: 0   
        },
        {
          nom: "Vin rouge",
          gramme: 0,
          litre: 0.3
        },
        {
          nom: "Bouillon de légumes",
          gramme: 0,
          litre: 0.5
        },
        {
          nom: "Huile d'olive",
          gramme: 0,
          litre: 0.015
        },
        {
          nom: "Thym",
          gramme: 5,
          litre: 0   
        },
        {
          nom: "Sel et poivre",
          gramme: 0,
          litre: 0   
      }],
    etapes: [
      "Coupez les carottes, tomates et céléris en petits dès.",
      "Faites revenir le boeuf hachée avec l'huile d'olive dans une casserole à feu moyen durant 3 minutes.",
      "Ajoutez les légumes coupés en dès dans la casserole",
      "Ajoutez le vin rouge et laisser bouillir pendant 5 minutes.",
      "Ajoutez le bouillon de légumes et faire mijoter pendant 30 minutes à feu moyen à partir de l'ébullition.",
      "Dans une autre casserole, faites cuire les spagettis en fonction des indications du fabricants",
      "Dans une assiette, ajoutez les spagettis à la base, ensuite la sauce par dessus et parsemer de fromage."
    ],
    auteur: "Nico"
    },
    {
      id: 2,
      nom: "Pizza veggie", 
      image: "https://cdn.pixabay.com/photo/2017/12/10/14/47/pizza-3010062_1280.jpg",
      duree: 20,
      calorie: 300,
      regime: ["sans lactose", "sans noix"],
      ingredients: [
        {
          nom: "Pâte à pizza",
          gramme: 80,
          litre: 0
        },
        {
          nom: "Sauce tomate",
          gramme: 0,
          litre: 0.20
        },
        {
          nom: "Tomate fraîche",
          gramme: 50,
          litre: 0
        },
        {
          nom: "Basilic",
          gramme: 0,
          litre: 0
        },
        {
          nom: "Fromage râpé",
          gramme: 100,
          litre: 0
        },
        {
          nom: "Sel, poivre",
          gramme: 0,
          litre: 0
        },
      ],
      etapes: [
        "Aplatissez la pâte avec un rouleau patissier.",
        "Etalez la sauce tomate sur la pâte.",
        "Coupez les tomates fraîches en tranche et les ajouter sur le dessus de la pâte.",
        "Ajoutez le fromage râpé.",
        "Salez et poivrez selon vos goûts.",
        "Préchauffez le four à 210°C et cuire la pizza pendant 10 minutes.",
        "Sortez la pizza du four et ajouter le basilic."
      ],
      auteur: "Nico"
    },
    {
      id: 3,
      nom: "Salade d'été",
      image: "https://cdn.pixabay.com/photo/2021/01/10/04/37/salad-5904093_1280.jpg",
      duree: 15,
      calorie: 200,
      regime: ["végétarien", "vegan"],
      ingredients: [
        {
          nom:"cocombre",
          gramme: 40,
          litre: 0,
        },
        {
          nom:"tomate",
          gramme: 50,
          litre: 0,
        },
        {
          nom:"olive noire",
          gramme: 10,
          litre: 0,
        },
        {
          nom:"tofu",
          gramme: 80,
          litre: 0,
        },
        {
          nom:"huile d'olive",
          gramme: 40,
          litre: 0,
        },
        {
          nom:"échalotte",
          gramme: 10,
          litre: 0,
        },
        {
          nom:"vinaige de cidre",
          gramme:0,
          litre: 0.05,
        },
        {
          nom:"Sel et poivre",
          gramme: 0,
          litre: 0,
        },
      ],
      etapes: [
        "Coupez les légumes selon vos préférences.",
        "Coupez le tofu en cube et émincez l'échalotte.",
        "Mélangez le tout dans une assiette.",
        "Ajoutez l'huile, le vinaigre et les olives.",
        "Salez et poivrez."
      ],
      auteur: "Niko"
    }
  ]

}
