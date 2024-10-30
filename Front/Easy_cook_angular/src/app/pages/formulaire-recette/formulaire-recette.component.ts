import { Component } from '@angular/core';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { ListeRecettesComponent } from '../liste-recettes/liste-recettes.component';

@Component({
  selector: 'app-formulaire-recette',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, ListeRecettesComponent],
  templateUrl: './formulaire-recette.component.html',
  styleUrl: './formulaire-recette.component.css'
})
export class FormulaireRecetteComponent {

  recipeForm= new FormGroup({
    nom: new FormControl(""),
    image: new FormControl(""),
    duree: new FormControl(0),
    calorie: new FormControl,
    regime: new FormControl([]),
    ingredients: new FormArray([
      new FormGroup({
        nom: new FormControl(""),
        gramme: new FormControl(0),
        litre: new FormControl(0),
        quantite: new FormControl(0)
      }),
    ]),
    etapes: new FormControl([]),
    auteur: new FormControl("")
  })

  get ingredients() {
    return this.recipeForm.controls.ingredients;
  }

  addIngredient(): void {
    this.ingredients.push(
      new FormGroup({
        nom: new FormControl(""),
        gramme: new FormControl(0),
        litre: new FormControl(0),
        quantite: new FormControl(0)
      })
    )
  }

  

}
