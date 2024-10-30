import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

interface ProductInfo {
  code: string;
  product_name: string;
  energy_kcal: number | null;
}

@Injectable({
  providedIn: 'root'
})
export class OpenFoodFactsService {

  private apiUrl = 'https://world.openfoodfacts.org/api/v3/product/';

  constructor(private http: HttpClient) {}

  getProductByCode(code: number): Observable<ProductInfo> {
    return this.http.get<any>(`${this.apiUrl}${code}.json`).pipe(
      map((data) => {
        const product = data.product;
        return {
          code: product.code,
          product_name: product.product_name_fr || product.product_name || 'Nom inconnu',
          energy_kcal: product.nutriments ? product.nutriments['energy-kcal_100g'] : null
        } as ProductInfo;
      })
    );
  }
}
