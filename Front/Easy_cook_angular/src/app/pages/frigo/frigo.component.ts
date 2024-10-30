// frigo.component.ts
import { Component } from '@angular/core';
import { FormsModule, FormControl, FormGroup } from '@angular/forms';
import { OpenFoodFactsService } from '../../utils/services/open-food-facts.service';
import { Ingredient } from '../../utils/types/ingredient.type';
import { FrigoAliment } from '../../utils/types/frigoAliment.type';
import { FrigoIngredientService } from '../../utils/services/frigo-ingredient.service';
import { IngredientService } from '../../utils/services/ingredient.service';


@Component({
  selector: 'app-frigo',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './frigo.component.html',
  styleUrls: ['./frigo.component.css'],
  
})
export class FrigoComponent {

  listIngredients: Ingredient[] = [
    // { id: 1, nom: "carotte" },
    // { id: 2, nom: "celeri" },
    // { id: 3, nom: "boeuf hachée" }
  ];

  frigo : FrigoAliment []= []

  constructor(private openFoodFactsService: OpenFoodFactsService, 
    private frigo_ingredientService : FrigoIngredientService,
  private ingredientService : IngredientService) {}

  aliment: FrigoAliment = {
    id_frigoAliment : 0,
    id_aliment: 0,
    nombreAliment: 0,
    id_frigo: 1
  }

  ingredient : Ingredient = {
    id: 0,
    nom: "",
    calories : 0,
    allergens: "",
    barcode: 0
  }
  isSubmitted: boolean = false;

  ngOnInit() {
    this.frigo_ingredientService.getFrigo().subscribe((data: FrigoAliment[]) => {
      this.frigo = data;
      // console.log("Frigo data from database:", this.frigo);
      for (let i = 0; i < this.frigo.length; i++) {
        this.ingredientService.getIngredient(this.frigo[i].id_aliment).subscribe((dataA : Ingredient) => {
          this.ingredient =dataA
          // console.log(dataA);
          this.listIngredients.push(dataA)
        })
      }

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

  }

  removeIngredient(index : number): void {
    this.listIngredients = this.listIngredients.filter((_, i) => i != index)
  }

}
