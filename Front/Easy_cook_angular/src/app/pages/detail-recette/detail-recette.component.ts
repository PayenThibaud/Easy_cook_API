import { Component, Input } from '@angular/core';
import { ListeRecettesComponent } from '../liste-recettes/liste-recettes.component';
import { Recette } from '../../components/utils/types/recette.type';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-detail-recette',
  standalone: true,
  imports: [ListeRecettesComponent, RouterLink],
  templateUrl: './detail-recette.component.html',
  styleUrl: './detail-recette.component.css'
})
export class DetailRecetteComponent {

  recipe: Recette = {
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
  }



}
