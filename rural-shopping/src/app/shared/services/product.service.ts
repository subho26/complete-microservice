import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '@environments/environment';
import { Observable, Subject } from 'rxjs';
import { finalize, map, takeUntil } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  destroy$ = new Subject;
  products: any;

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<any> {
    return this.http.get<any>(`${environment.apiUrl}/product/products`).pipe(finalize(() => {
    }));
  }

  getProduct(id: number): Observable<any> {
    // this.getAllProducts().pipe(takeUntil(this.destroy$)).subscribe({
    //   next: data => {
    //     this.products = data;
    //   },
    //   error: error => {
    //     console.error('There was an error!', error.message);
    //   }
    // });
    return this.getAllProducts().pipe(map((x: any[]) => x.find(p => p.productId === id)));
  }
}
