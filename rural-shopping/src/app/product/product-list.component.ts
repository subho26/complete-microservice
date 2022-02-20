import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { ProductService } from '../shared/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  destroy$ = new Subject;
  products: any;
  showHide: boolean = true;


  constructor(private productService: ProductService,
    private router: Router) { }
    /* constructor(private authenticationService: AuthenticationService, private productService: ProductService,
      private router: Router) {
      this.currentUser = this.authenticationService.currentUserValue;
    } */

  ngOnInit(): void {
  }

  public callProduct(): void {
    this.showHide = !this.showHide;
    if(!this.showHide){
      this.productService.getAllProducts().pipe(takeUntil(this.destroy$)).subscribe({
        next: data => {
          this.products = data;
        },
        error: error => {
          console.error('There was an error!', error.message);
        }
      });
    }

  }

}
