import { Component } from '@angular/core';
import { Utilisateur } from '../../utils/types/utilisateur.type';
import { Ingredient } from '../../utils/types/ingredient.type';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-frigo',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './frigo.component.html',
  styleUrl: './frigo.component.css'
})

export class FrigoComponent {

  listIngredients: Ingredient[] = [
    {
      id: 1,
      nom: "carotte"
    },
    {
      id: 2,
      nom: "celeri"
    },
    {
      id: 3,
      nom: "boeuf hachée"
    }
  ];

}
