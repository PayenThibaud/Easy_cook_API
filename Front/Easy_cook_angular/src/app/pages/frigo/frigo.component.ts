import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { OpenFoodFactsService } from '../../utils/services/open-food-facts.service';
import { Ingredient } from '../../utils/types/ingredient.type';
import { FrigoAliment } from '../../utils/types/frigoAliment.type';
import { FrigoIngredientService } from '../../utils/services/frigo-ingredient.service';
import { IngredientService } from '../../utils/services/ingredient.service';
import { catchError, Observable, of, forkJoin  } from 'rxjs';
import { Router, RouterLink } from '@angular/router';

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

  getIngredientQuantity(ingredientId: number): number | string {
    const frigoAliment = this.frigo.find(aliment => aliment.id_aliment === ingredientId);
    return frigoAliment ? frigoAliment.nombreAliment : 'N/A'; // 'N/A' si aucun aliment correspondant n'est trouvé
  }
  
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
  
        this.ingredientService.postIngredient(newIngredient).subscribe(
          (dataI) => {
            console.log('Quantité entrée:', this.alimentFrigo.nombreAliment);
            const newIngredientFrigo: FrigoAliment = {
              id_frigoAliment: 1,
              id_aliment: dataI.id_ingredient,
              nombreAliment: this.alimentFrigo.nombreAliment,
              id_frigo: 1,
            };
  
            this.frigo_ingredientService.postFrigoIngredient(newIngredientFrigo).subscribe(
              (dataF: FrigoAliment) => {
                // Ajoutez l'ingrédient à `listIngredients`
                this.listIngredients.push(dataI);
                // Ajoutez également à `frigo` pour que `getIngredientQuantity` fonctionne
                this.frigo.push(dataF);
                console.log(`Ingrédient ${dataI.nom} ajouté avec succès avec quantité ${dataF.nombreAliment}`);
              },
              (error) => {
                console.error('Erreur lors de l\'ajout de l\'ingrédient dans le frigo:', error);
              }
            );
          },
          (error) => {
            console.error('Erreur lors de l\'enregistrement de l\'ingrédient:', error);
          }
        );
      },
      (error) => {
        console.error('Erreur lors de la récupération des informations du produit:', error);
      }
    );
  }
  

  removeIngredient(index: number): void {
    const ingredientToRemove = this.listIngredients[index];
    const frigoAliment = this.frigo.find(aliment => aliment.id_aliment === ingredientToRemove.id_ingredient);
  
    if (frigoAliment) {
      this.frigo_ingredientService.deleteFrigoIngredient(frigoAliment.id_frigoAliment).subscribe(
        () => {
          // Mise à jour de `listIngredients` et `frigo` après la suppression
          this.listIngredients = this.listIngredients.filter((_, i) => i !== index);
          this.frigo = this.frigo.filter(aliment => aliment.id_frigoAliment !== frigoAliment.id_frigoAliment);
          console.log(`Ingredient ${ingredientToRemove.nom} supprimé avec succès`);
        },
        (error) => {
          console.error('Error deleting ingredient from fridge:', error);
        }
      );
    } else {
      console.warn("L'ingrédient à supprimer n'a pas été trouvé dans le frigo.");
    }
  }
  
  resetForm(){
    this.alimentFrigo = {
      id_frigoAliment: 0,
      id_aliment: 0,
      nombreAliment: 0,
      id_frigo: 1,
    };
  }
}
