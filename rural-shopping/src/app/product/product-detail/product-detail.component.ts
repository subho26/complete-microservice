import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/shared/services/product.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent implements OnInit {
  pageTitle: string = 'Product Detail';
  errorMessage: string = '';
  product: any | undefined;

  constructor(private productServise: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private alertService: AlertService) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.getProduct(id);
    }
  }

  getProduct(id: number): void {
    this.productServise.getProduct(id).subscribe({
      next: product => this.product = product,
      error: err => this.errorMessage = err
    });
  }

  addToCart(): void {
    this.alertService.success(this.product.productName + ' Added to cart');
    setTimeout(() => {
      this.alertService.clear();
    }, 3000);
  }
}

