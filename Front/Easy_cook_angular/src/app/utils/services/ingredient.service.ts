import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, map, of } from 'rxjs';
import { Ingredient } from '../types/ingredient.type';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {
  private serviceingredient_url: string = "http://localhost:8083/ingredient"
  constructor(private http: HttpClient) { }

  getIngredient(id : number) : Observable<Ingredient>{
    return this.http.get <Ingredient>(`${this.serviceingredient_url}/${id}`).pipe(
      map(response => response),
      catchError(error => {
        alert(error.message);
        // Valeurs par défaut pour un Ingredient inconnu
        const defaultIngredient: Ingredient = {
          id_ingredient: 0,
          nom: 'Inconnu',
          calories: 0,
          allergens: 'Non spécifié',
          barcode: 0
        };
        return of(defaultIngredient);
      })
    )
  }

  postIngredient(ingredient: Ingredient): Observable<Ingredient> {
    return this.http.post<Ingredient>(this.serviceingredient_url, ingredient).pipe(
      map(response => response),
      catchError(error => {
        alert(error.message);
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
}
