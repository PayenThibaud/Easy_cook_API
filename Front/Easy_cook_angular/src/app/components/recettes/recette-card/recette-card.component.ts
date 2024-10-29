import { Component, Input } from '@angular/core';
import { Recette } from '../../utils/types/recette.type';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recette-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './recette-card.component.html',
  styleUrl: './recette-card.component.css'
})
export class RecetteCardComponent {
  @Input() recette!: Recette

}
