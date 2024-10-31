import { Component, Input } from '@angular/core';
import { Recette } from '../../../utils/types/recette.type';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-recette-card',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './recette-card.component.html',
  styleUrl: './recette-card.component.css'
})
export class RecetteCardComponent {
  @Input() recette!: Recette

}
