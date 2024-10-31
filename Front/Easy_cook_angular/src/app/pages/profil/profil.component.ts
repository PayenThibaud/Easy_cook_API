import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-profil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent {
  recettesFavorites = [

    {
      titre: 'Spaghetti Carbonara ♥',
      image: "https://www.pequerecetas.com/wp-content/uploads/2010/10/pasta-carbonara-espaguetis-receta.jpg"
    },
    {
      titre: 'Brookies Gourmands ♥',
      image: "https://www.dessarts.com/wp-content/uploads/2020/03/Brookie_1200px_F.jpg"

    }

  ];

  constructor() {
    console.log(this.recettesFavorites);
  }
}
