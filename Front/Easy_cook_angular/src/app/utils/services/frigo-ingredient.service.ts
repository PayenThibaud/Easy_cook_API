import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FrigoAliment } from '../types/frigoAliment.type';
import { catchError, Observable, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrigoIngredientService {
  private serviceFrigo_ingredient_url: string = "http://localhost:8087/frigo-aliment"
  constructor(private http: HttpClient) { }

  getFrigo() : Observable<FrigoAliment[]>{
    return this.http.get<FrigoAliment[]>(this.serviceFrigo_ingredient_url).pipe( 
      map(response => response),
      catchError(error => {
        alert(error.message)
        return of([] as FrigoAliment[])
      })
    )
  }

  postFrigoIngredient(frigoIngredient : FrigoAliment) : Observable<FrigoAliment>{
    return this.http.post<FrigoAliment>(this.serviceFrigo_ingredient_url, frigoIngredient).pipe(
      map(response => response),
      catchError(error => {
        alert(error.message);
        const defautlFrigoIngredient: FrigoAliment = {
          id_frigoAliment: 0,
          id_aliment: 0,
          nombreAliment: 0,
          id_frigo: 0,
        };
        return of(defautlFrigoIngredient);
      })
    )
  }

  

}
