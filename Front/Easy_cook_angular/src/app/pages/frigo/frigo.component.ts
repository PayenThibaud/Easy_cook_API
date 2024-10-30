import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { OpenFoodFactsService } from '../../utils/services/open-food-facts.service';
import { Ingredient } from '../../utils/types/ingredient.type';
import { FrigoAliment } from '../../utils/types/frigoAliment.type';
import { FrigoIngredientService } from '../../utils/services/frigo-ingredient.service';
import { IngredientService } from '../../utils/services/ingredient.service';
import { catchError, Observable, of, forkJoin  } from 'rxjs';

@Component({
  selector: 'app-frigo',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './frigo.component.html',
  styleUrls: ['./frigo.component.css'],
})
export class FrigoComponent {

  listIngredients: Ingredient[] = [];
  frigo: FrigoAliment[] = [];

  constructor(
    private openFoodFactsService: OpenFoodFactsService, 
    private frigo_ingredientService: FrigoIngredientService,
    private ingredientService: IngredientService
  ) {}

  alimentFrigo: FrigoAliment = {
    id_frigoAliment: 0,
    id_aliment: 0,
    nombreAliment: 0,
    id_frigo: 1,
  };

  ingredient: Ingredient = {
    id_ingredient: 0,
    nom: "",
    calories: 0,
    allergens: "",
    barcode: 0,
  };

  isSubmitted: boolean = false;

  conversionToIngredient(frigoAliment_id: number): Observable<Ingredient> {
    return this.ingredientService.getIngredient(frigoAliment_id).pipe(
      catchError((error) => {
        console.error('Error fetching ingredient:', error);
        // Valeur par défaut en cas d'erreur
        const defaultIngredient: Ingredient = {
          id_ingredient: 0,
          nom: 'Inconnu',
          calories: 0,
          allergens: 'Non spécifié',
          barcode: 0
        };
        return of(defaultIngredient);
      })
    );
  }

  ngOnInit() {
    this.frigo_ingredientService.getFrigo().subscribe((data: FrigoAliment[]) => {
      this.frigo = data;
      const ingredientObservables = this.frigo.map((frigoAliment) =>
        this.conversionToIngredient(frigoAliment.id_aliment)
      );
  
      // Utilisez forkJoin pour exécuter tous les observables en parallèle
      forkJoin(ingredientObservables).subscribe((ingredients: Ingredient[]) => {
        this.listIngredients = ingredients;
      });
    });
  }

  submitIngredientForm(): void {
    this.isSubmitted = true;
    this.openFoodFactsService.getProductByCode(this.alimentFrigo.id_aliment).subscribe(
      (productInfo) => {
        const newIngredient: Ingredient = {
          id_ingredient: 0,
          nom: productInfo.product_name || '',
          calories: productInfo.energy_kcal || 0,
          allergens: "",
          barcode: Number(productInfo.code) || 0,
        };
        console.log(newIngredient);
        this.ingredientService.postIngredient(newIngredient).subscribe(
          (dataI) => {
            console.log(dataI);
            const newIngredientFrigo: FrigoAliment = {
              id_frigoAliment: 1,
              id_aliment: dataI.id_ingredient,
              nombreAliment: this.alimentFrigo.nombreAliment,
              id_frigo: 1,
            };
            console.log(newIngredientFrigo);

            this.frigo_ingredientService.postFrigoIngredient(newIngredientFrigo).subscribe(
              (dataF: FrigoAliment) => {
                // newingredienList = 
                // this.listIngredients.push(dataI); // Ajouter seulement après sauvegarde
                console.log('Nouvel ingrédient ajouté au frigo:', dataF);
              },
              (error) => {
                console.error('Error saving ingredient in fridge:', error);
              }
            );
          },
          (error) => {
            console.error('Error saving ingredient:', error);
          }
        );
      },
      (error) => {
        console.error('Error retrieving product information:', error);
      }
    );
  }

  removeIngredient(index: number): void {
    this.listIngredients = this.listIngredients.filter((_, i) => i !== index);
  }
}
