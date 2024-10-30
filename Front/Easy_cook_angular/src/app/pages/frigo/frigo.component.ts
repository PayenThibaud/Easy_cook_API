// frigo.component.ts
import { Component } from '@angular/core';
import { FormsModule, FormControl, FormGroup } from '@angular/forms';
import { OpenFoodFactsService } from '../../services/open-food-facts.service';
import { Ingredient } from '../../components/utils/types/ingredient.type';
import { FrigoAliment } from '../../components/utils/types/frigoAliment.type';
import { FrigoIngredientService } from '../../services/frigo-ingredient.service';


@Component({
  selector: 'app-frigo',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './frigo.component.html',
  styleUrls: ['./frigo.component.css'],
  
})
export class FrigoComponent {

  listIngredients: Ingredient[] = [
    { id: 1, nom: "carotte" },
    { id: 2, nom: "celeri" },
    { id: 3, nom: "boeuf hachée" }
  ];

  frigo : FrigoAliment []= []

  constructor(private openFoodFactsService: OpenFoodFactsService, 
    private frigo_ingredient : FrigoIngredientService) {}

  aliment: FrigoAliment = {
    id_aliment: 0,
    nombreAliment: 0,
    id_frigo: 1
  }

  isSubmitted: boolean = false;

  ngOnInit() {
    this.frigo_ingredient.getFrigo().subscribe((data: FrigoAliment[]) => {
      this.frigo = data;
      console.log("Frigo data from database:", this.frigo);
    });
  }

  submitIngredientForm(): void {
    this.isSubmitted = true;

    this.openFoodFactsService.getProductByCode(this.aliment.id_aliment).subscribe(
      (productInfo) => {
        console.log('Product information:', productInfo);
      },
      (error) => {
        console.error('Error retrieving product information:', error);
      }
    );
    console.log(this.frigo);

  }

  removeIngredient(index : number): void {
    this.listIngredients = this.listIngredients.filter((_, i) => i != index)
  }

}
