import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FrigoAliment } from '../components/utils/types/frigoAliment.type';
import { catchError, Observable, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrigoIngredientService {
  private serviceFrigo_ingredient_url: string = "http://localhost:8087/frigo-aliment"
  constructor(private http: HttpClient) { }

  getFrigo() : Observable<FrigoAliment[]>{
    return this.http.get<{value : FrigoAliment[]}>(this.serviceFrigo_ingredient_url).pipe( 
      map(response => response.value),
      catchError(error => {
        alert(error.message)
        return of([] as FrigoAliment[])
      })
    )
  }

}
